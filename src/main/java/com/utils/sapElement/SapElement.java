package com.utils.sapElement;

import com.constants.Helpers.SapKeys;

public interface SapElement {
    String getText();

    SapElement setText(String text);

    SapElement select();

//    SapElement getTitle();

    SapElement pressButton();

    SapElement setFocus();

    SapElement selectCurrentCellRow(int iJ);

    SapElement setSelectedRows(int iRow);

    String getCellValue(int iRow, String sColumn);

//    String getActiveWindow();

//    String getMessageParameter(int iPrmNum);

//    String getMessageParameter(int iIndex, String... sOption);

//    String getMessageParameter();

//    String getMessageType();

    boolean isCheckBoxSelected();

//    SapElement closeSap();

    SapElement doubleClickItem(String sNodeKey, String sItemColumn);

    SapElement doubleClickNode(String sNodeId);

    SapElement doubleClickCurrentCell();

    SapElement doubleClick();

    SapElement clickCurrentCell();

//    String getTextFunction();

//    String getTextProperty(String sProperty);

//    String getObjectProperty(String property);

//    boolean isChangeable();

    SapElement selectAll();

    SapElement setCurrentCell(int iRow, String sColumn);

//    SapElement setKey(String sKey);

    SapElement pressContextButton(String sSubItem);

//    SapElement setSelected(boolean bBool);

    SapElement selectRow(int iIndex, boolean bBool);

    void setProperty(String sProperty, String sValue);

//    String getProperty(String sProperty);

    SapElement selectContextMenuItem(String sUri, String sFunctionCode, String... sOption);

    SapElement selectContextMenuItem(String sSubItem);

    SapElement nodeContextMenu(String sSubItem);

    SapElement selectedNode(String sSubItem);

    SapElement selectNode(String sNodeId);

    String getNextNodeKey(String sNodeId);

    String getStringNodeKey(int iNodeKey);

    String getStringNodeKey(String sNodeKey);

    String getNodeText(String sNodeId);

    String getItemText(String sNodeKey, String sItemColumn);

    String pressButton(String sUri);

    SapElement expandNode(String sNodeId);

    SapElement selectItem(String sNodeKey, String sItemColumn, String... sOption);

    SapElement clearSelection();

    SapElement pressToolbarContextButton(String sUri);

    SapElement modifyCheckBox(int iRow, String sColumn, Boolean bChecked);

    SapElement triggerModified();

    SapElement modifyCell(int iRow, String sColumn, String sValue);

    SapElement pressEnter();

    SapElement currentCellColumn(String sValue);

    SapElement pressToolbarButton();

    SapElement checkTooltip(String sExpectedValue);

    SapElement sendKey(int iKey);

    SapElement sendKey(SapKeys iKey);

    SapElement caretPosition(int iKey);

    int getColumnCount();

    int getRowCount();

    SapElement pressButtonCurrentCell();

    SapElement selectRadioButton(String sText);

    int getVisibleRowCount();
}
