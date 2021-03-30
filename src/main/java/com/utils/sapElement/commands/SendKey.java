package com.utils.sapElement.commands;

import com.jacob.activeX.ActiveXComponent;
import com.utils.sapElement.ElementSource;
import com.utils.sapElement.SapElement;
import com.utils.sapElement.SapElementImpl;
import com.utils.sapGeneric.DesktopFunctions;
import com.utils.sapGeneric.SessionRunner;

public class SendKey extends DesktopFunctions implements Command {
    SendKey() {
        super();
    }
    @Override
    public Object execute(SapElement proxy, ElementSource source, Object[] args) {
        Integer text = (Integer) args[0];
        ActiveXComponent ac = (ActiveXComponent) source.getSapElement();
        String id = ac.getProperty("ID").getString();
        super.session= SessionRunner.getCurrentSession();
        sendKey(id, text);
        return new SapElementImpl(ac);
    }
}