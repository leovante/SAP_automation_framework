package com.settings;

public class SAPElementNotFoundException extends RuntimeException {
    private static final String msg = "SAP Element not found";

    public SAPElementNotFoundException(){
        super(msg);
    }
}
