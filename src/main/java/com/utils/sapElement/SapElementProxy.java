package com.utils.sapElement;

import com.utils.sapElement.commands.Commands;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SapElementProxy implements InvocationHandler {
    private ElementSource elementSource;

    public SapElementProxy(ElementSource elementSource) {
        this.elementSource = elementSource;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object... args) {
        try {
            return invokeSession(proxy, method, args);
        } catch (Exception e) {
        }
        return null;
    }

    private Object invokeSession(Object proxy, Method method, Object[] args) {
        try {
            if (SapElement.class.isAssignableFrom(method.getDeclaringClass())) {
                Object o = Commands.getInstance().execute(proxy, elementSource, method.getName(), args);
                return o;
            }
            return method.invoke(elementSource.getSapElement(), args);
        } catch (Exception e) {
        }
        return null;
    }
}