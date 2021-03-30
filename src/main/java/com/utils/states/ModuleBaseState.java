package com.utils.states;

import com.annotations.SystemSAP;
import com.annotations.TransactionSAP;
import com.annotations.UserSAP;
import com.pageObject.S4.LoginPageDesktop;
import com.pageObject.S4.UserMenuDesktop;
import com.utils.states.Methods.StateMethods;
import com.utils.sapGeneric.SessionRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class ModuleBaseState extends SessionRunner implements StateMethods {
    private <T extends Class> Optional<Annotation> getAnnotationValue(T t, TestInfo ts) {
        return
                Stream.concat(
                        Stream.of(ts.getTestMethod().get().getAnnotation(t)),
                        Stream.of(ts.getTestClass().get().getAnnotation(t)))
                        .map(Optional::ofNullable)
                        .findFirst()
                        .flatMap(Function.identity());
    }

    @BeforeEach
    public void beforeInitSap(TestInfo testInfo) {
        getAnnotationValue(SystemSAP.class, testInfo).map(i -> (SystemSAP) i).ifPresent(i -> {
            runSapSessionEqualsCurrentThread(i.sys().getSystem());
            resizeWorkingPane();
        });
        getAnnotationValue(UserSAP.class, testInfo).map(i -> (UserSAP) i).ifPresent(i -> {
            new LoginPageDesktop()
                    .login(i.user());
        });
        getAnnotationValue(TransactionSAP.class, testInfo).map(i -> (TransactionSAP) i).ifPresent(i -> {
            new UserMenuDesktop()
                    .startTransaction(i.transaction().toString());
        });
    }

    @AfterEach
    public void after(TestInfo testInfo) {
        Annotation[] methodAnnotations = testInfo.getTestMethod().get().getAnnotations();
        SystemSAP systemSAP = null;
        if (methodAnnotations.length != 0) {
            systemSAP = Arrays.stream(methodAnnotations)
                    .filter(a -> a instanceof SystemSAP)
                    .map(a -> (SystemSAP) a)
                    .findFirst().orElse(null);
        }
        if (systemSAP != null)
            afterCloseSap();
    }

    protected void afterCloseSap() {
        try {
            if (sessions.size() > 0 && sessions.containsValue(getCurrentSession()))
                sapScreenShotAndExit();
        } finally {
            sessions.remove(Thread.currentThread().getId());
        }
    }

    protected void runSapSessionEqualsCurrentThread(String sysType) {
        Long threadID = Thread.currentThread().getId();
        if (!sessions.containsKey(threadID)) {
            sessions.put(threadID, runSAP(sysType));
        } else {
            throw new IllegalThreadStateException("Поток с таким ID существует");
        }
    }
}
