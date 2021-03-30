package com.utils.sapElement.commands;

import com.jacob.activeX.ActiveXComponent;
import com.utils.sapElement.ElementSource;
import com.utils.sapElement.SapElement;
import com.utils.sapGeneric.DesktopFunctions;
import com.utils.sapGeneric.SessionRunner;

public class SetFocus extends DesktopFunctions implements Command {
    SetFocus() {
        super();
    }
    @Override
    public Object execute(SapElement proxy, ElementSource source, Object[] args) {
        ActiveXComponent ac = (ActiveXComponent) source.getSapElement();
        String id = ac.getProperty("ID").getString();
        super.session= SessionRunner.getCurrentSession();
        setFocus(id);
        return proxy;
    }
}
