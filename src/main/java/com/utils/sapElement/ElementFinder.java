package com.utils.sapElement;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;

import java.lang.reflect.Proxy;

import static java.lang.Thread.currentThread;

public class ElementFinder extends ElementSource {
    private ActiveXComponent session;
    private By criteria;
    private Long typeAsNumber;
    private Object[] guiClassName;

    public ElementFinder(ActiveXComponent session, By criteria, Long typeAsNumber, Object... guiClassName) {
        this.session = session;
        this.criteria = criteria;
        this.guiClassName = guiClassName;
        this.typeAsNumber = typeAsNumber;
    }

    @SuppressWarnings("unchecked")
    public static <T extends SapElement> T wrap(ActiveXComponent session, Class<T> clazz, By criteria, Long typeAsNumber, Object... guiClassName) {
        return (T) Proxy.newProxyInstance(
                currentThread().getContextClassLoader(),
                new Class<?>[]{clazz},
                new SapElementProxy(new ElementFinder(session, criteria, typeAsNumber, guiClassName)));
    }

    @Override
    public ActiveXComponent getSession() {
        return session;
    }

    @Override
    public By getSearchCriteria() {
        return criteria;
    }

    @Override
    public Long getTypeAsNumber() {
        return typeAsNumber;
    }

    @Override
    public Object[] getGuiClassName() {
        return guiClassName;
    }

    @Override
    public SapElement getSapElement() {
        if (criteria instanceof By.ById) {
            return new SapElementImpl(session.invoke("FindById", criteria.toString()).toDispatch());
        } else if (criteria instanceof By.ByName && guiClassName.length == 0 && typeAsNumber == null) {
            String name = new TypeAsNumberDictionary().getName(criteria.toString());
            Long num = new TypeAsNumberDictionary().getNum(criteria.toString());

            Variant[] arg = new Variant[2];
            arg[0] = new Variant(name);
            arg[1] = new Variant(num);
            return new SapElementImpl(session.invoke("FindByNameEx", arg).toDispatch());
        } else if (criteria instanceof By.ByName && guiClassName.length > 0) {
            Variant[] arg = new Variant[2];
            arg[0] = new Variant(criteria.toString());
            arg[1] = new Variant(guiClassName[0].toString());
            return new SapElementImpl(session.invoke("FindByName", arg).toDispatch());
        } else if (criteria instanceof By.ByName && typeAsNumber != null) {
            Variant[] arg = new Variant[2];
            arg[0] = new Variant(criteria.toString());
            arg[1] = new Variant(typeAsNumber);
            return new SapElementImpl(session.invoke("FindByNameEx", arg).toDispatch());
        }
        return null;
    }

    @Override
    public String toString() {
        return "{" + getSearchCriteria() + '}';
    }
}
