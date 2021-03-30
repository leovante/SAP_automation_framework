package com.pageObject.S4;

import com.constants.SystemTransactions;
import com.utils.sapGeneric.DesktopFunctions;
import org.junit.platform.commons.util.StringUtils;

public class UserMenuDesktop extends DesktopFunctions {

    public UserMenuDesktop() {
        super();
    }

    public void startTransaction(String sText) {
        if (StringUtils.isNotBlank(sText)) {
            session.invoke("startTransaction", sText);
            System.out.println(String.format("Транзакция: %s успешно запущена", sText));
            waitSessionToBeLoaded();
        }
    }

    public void startTransaction(SystemTransactions systemTransactions) {
        session.invoke("startTransaction", systemTransactions.getTransaction());
        System.out.println(String.format("Транзакция: %s успешно запущена", systemTransactions.getTransaction()));
        waitSessionToBeLoaded();
    }

}
