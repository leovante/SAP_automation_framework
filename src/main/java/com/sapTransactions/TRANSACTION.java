package com.sapTransactions;

import com.utils.sapGeneric.DesktopFunctions;

public class TRANSACTION extends DesktopFunctions {
    public final String storageNumber = "wnd[0]/usr/ctxtS1_LGNUM";
    public final String internalNumOfSign = "wnd[0]/usr/ctxtSO_ATINN-LOW";

    public TRANSACTION fillStorageNumber(String text) {
        setText(storageNumber, text);
        return this;
    }

    public TRANSACTION internalNumOfSign() {
        setText(internalNumOfSign, "BARCODE");
        return this;
    }

}