package com.utils.sapElement.commands;

import com.jacob.activeX.ActiveXComponent;
import com.utils.sapElement.ElementSource;
import com.utils.sapElement.SapElement;

public class SelectAll implements Command {

    @Override
    public Object execute(SapElement proxy, ElementSource source, Object[] args) {
        ActiveXComponent sc = (ActiveXComponent)source.getSapElement();
        sc.invoke("selectAll");
        return null;
    }
}
