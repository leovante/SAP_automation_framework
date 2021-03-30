package com.utils.sapElement;

import com.jacob.activeX.ActiveXComponent;

public abstract class ElementSource {
    public abstract ActiveXComponent getSession();
    public abstract By getSearchCriteria();
    public abstract Long getTypeAsNumber();
    public abstract Object[] getGuiClassName();
    public abstract SapElement getSapElement();
}
