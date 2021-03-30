package com.utils.sapElement.commands;


import com.utils.sapElement.ElementSource;
import com.utils.sapElement.SapElement;

public interface Command <T> {
    Object[] NO_ARGS = new Object[0];

    T execute(SapElement proxy, ElementSource source, Object[] args) ;
}
