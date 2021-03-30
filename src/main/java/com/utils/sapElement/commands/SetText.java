package com.utils.sapElement.commands;

import com.jacob.activeX.ActiveXComponent;
import com.utils.sapElement.ElementSource;
import com.utils.sapElement.SapElement;
import com.utils.sapElement.SapElementImpl;
import com.utils.sapGeneric.DesktopFunctions;
import com.utils.sapGeneric.SessionRunner;

public class SetText extends DesktopFunctions implements Command {
    SetText() {
        super();
    }
    @Override
    public Object execute(SapElement proxy, ElementSource source, Object[] args) {
        String text = (String) args[0];
        ActiveXComponent ac = (ActiveXComponent) source.getSapElement();
        String id = ac.getProperty("ID").getString();
        /*ac.setProperty("Text", text);*/
        super.session= SessionRunner.getCurrentSession();
        setText(id, text);
        return new SapElementImpl(ac);
    }
}
