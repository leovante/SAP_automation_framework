package com.utils.sapElement;

import com.jacob.activeX.ActiveXComponent;

import java.util.AbstractList;
import java.util.List;

public class ElementsCollection extends AbstractList<SapElement> {
    private SapElementCollection collection;

    ElementsCollection(SapElementCollection collection) {
        this.collection = collection;
    }

    public ElementsCollection(ActiveXComponent session, By criteria, Long num, GuiClassName... guiClassName) {
        this(new ByNameAndGuiClassCollection(session, criteria, num, guiClassName));
    }

    @Override
    public SapElement get(int index) {
        return CollectionElement.wrap(collection, index);
    }

    @Override
    public int size() {
        try {
            return getElements().size();
        } catch (IndexOutOfBoundsException outOfCollection) {
            return 0;
        }
    }

    private List<ActiveXComponent> getElements() {
        return collection.getSapElements();
    }
}
