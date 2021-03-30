package com.utils.sapElement;

import com.jacob.activeX.ActiveXComponent;

import static com.utils.sapGeneric.SessionRunner.getCurrentSession;

public class Saper {
    public static SapElement findById(String id) {
        return new SapSession(getCurrentSession()).findElement(By.id(id), null);
    }

    public static SapElement findByName(String name) {
        return new SapSession(
                new ActiveXComponent(
                        getCurrentSession()
                                .invoke("FindById", "wnd[0]")
                                .toDispatch()))
                .findElement(By.name(name), null);
    }

    public static SapElement findByName(String name, GuiClassName guiClassName) {
        return new SapSession(
                new ActiveXComponent(
                        getCurrentSession()
                                .invoke("FindById", "wnd[0]")
                                .toDispatch()))
                .findElement(By.name(name), null, guiClassName);
    }

    public static SapElement findByName(String name, long typeAsNumber) {
        return new SapSession(
                new ActiveXComponent(
                        getCurrentSession()
                                .invoke("FindById", "wnd[0]")
                                .toDispatch()))
                .findElement(By.name(name), typeAsNumber);
    }

    public static ElementsCollection findAllByName(String name) {
        return new SapSession(
                new ActiveXComponent(
                        getCurrentSession()
                                .invoke("FindById", "wnd[0]")
                                .toDispatch()))
                .findAllElements(By.name(name), null);
    }

    public static ElementsCollection findAllByName(String name, GuiClassName guiClassName) {
        return new SapSession(
                new ActiveXComponent(
                        getCurrentSession()
                                .invoke("FindById", "wnd[0]")
                                .toDispatch()))
                .findAllElements(By.name(name), null, guiClassName);
    }

    public static ElementsCollection findAllByName(String name, long typeAsNumber) {
        return new SapSession(
                new ActiveXComponent(
                        getCurrentSession()
                                .invoke("FindById", "wnd[0]")
                                .toDispatch()))
                .findAllElements(By.name(name), typeAsNumber);
    }
}