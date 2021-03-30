package com.utils.sapElement;

import com.jacob.activeX.ActiveXComponent;

public class SapSession {
    protected ActiveXComponent session;

    public SapSession(ActiveXComponent session) {
        this.session = session;
    }

    public SapElement findElement(By criteria, Long num, GuiClassName... guiClassName) {
        return ElementFinder.wrap(session, SapElement.class, criteria, num, guiClassName);
    }

    public ElementsCollection findAllElements(By criteria, Long num, GuiClassName... guiClassName) {
        return new ElementsCollection(session, criteria, num, guiClassName);
    }
}