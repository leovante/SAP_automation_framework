package com.utils.sapElement;

import com.jacob.activeX.ActiveXComponent;

import java.lang.reflect.Proxy;

public class CollectionElement extends ElementSource {
    private ActiveXComponent session;
    private By criteria;
    private Long num;
    private Object[] guiClassName;

    private SapElementCollection collection;
    private int index;

    public CollectionElement(SapElementCollection collection, int index) {
        this.collection=collection;
        this.index=index;
        this.session=collection.getSession();
    }

//    @SuppressWarnings("unchecked")
//    public static <T extends SapElement> T wrap(ActiveXComponent session, Class<T> clazz, By criteria, Long num, Object... guiClassName) {
//        return (T) Proxy.newProxyInstance(
//                currentThread().getContextClassLoader(),
//                new Class<?>[]{clazz},
//                new SapElementProxy(new ElementFinder(session, criteria, num, guiClassName)));
//    }

//    public CollectionElement(ActiveXComponent session, By criteria, Long num, Object... guiClassName) {
//        this.session = session;
//        this.criteria = criteria;
//        this.guiClassName = guiClassName;
//        this.num = num;
//    }

    public static SapElement wrap(SapElementCollection collection, int index) {
        return (SapElement) Proxy.newProxyInstance(
                collection.getClass().getClassLoader(),
                new Class<?>[]{SapElement.class},
                new SapElementProxy(new CollectionElement(collection, index)));
    }

    @Override
    public SapElement getSapElement() {
        return new SapElementImpl(collection.getSapElements().get(index));
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
        return num;
    }

    @Override
    public Object[] getGuiClassName() {
        return guiClassName;
    }
}