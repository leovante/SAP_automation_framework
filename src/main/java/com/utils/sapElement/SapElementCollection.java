package com.utils.sapElement;

import com.jacob.activeX.ActiveXComponent;

import java.util.List;

public interface SapElementCollection {
    List<ActiveXComponent> getSapElements();
    ActiveXComponent getSession();
}
