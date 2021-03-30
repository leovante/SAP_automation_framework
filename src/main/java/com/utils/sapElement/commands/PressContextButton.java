package com.utils.sapElement.commands;

import com.jacob.activeX.ActiveXComponent;
import com.utils.sapElement.ElementSource;
import com.utils.sapElement.SapElement;
import com.utils.sapGeneric.DesktopFunctions;
import com.utils.sapGeneric.SessionRunner;

public class PressContextButton extends DesktopFunctions implements Command {
    PressContextButton() {
        super();
    }
    @Override
    public Object execute(SapElement proxy, ElementSource source, Object[] args) {
        String text = (String) args[0];
        ActiveXComponent ac = (ActiveXComponent) source.getSapElement();
        String id = ac.getProperty("ID").getString();
        super.session= SessionRunner.getCurrentSession();
        pressContextButton(id, text);
        return proxy;
    }
}
