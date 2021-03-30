package com.utils.sapElement;

public enum GuiClassName {
    GuiTextField("GuiTextField"),
    GuiCTextField("GuiCTextField"),
    GuiPasswordField("GuiPasswordField"),
    GuiRadioButton("GuiRadioButton"),
    GuiButton("GuiButton"),
    GuiMenu("GuiMenu"),
    GuiOkCodeField("GuiOkCodeField"),
    GuiCheckBox("GuiCheckBox"),
    GuiLabel("GuiLabel"),
    GuiTab("GuiTab"),
    GuiComboBox("GuiComboBox"),
    GuiTableControl("GuiTableControl");

    String name;
    GuiClassName(String guiTextField) {
        this.name = guiTextField;
    }
}
