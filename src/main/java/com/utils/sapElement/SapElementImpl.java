package com.utils.sapElement;

import com.constants.Helpers.SapKeys;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class SapElementImpl extends ActiveXComponent implements SapElement {
    public SapElementImpl(ActiveXComponent ac) {
        super(ac);
    }

    public SapElementImpl(Dispatch findById) {
        super(findById);
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public SapElement setText(String text) {
        return null;
    }

    @Override
    public SapElement select() {
        return null;
    }

    @Override
    public SapElement pressButton() {
        return null;
    }

    @Override
    public SapElement setFocus() {
        return null;
    }

    @Override
    public SapElement selectCurrentCellRow(int iJ) {
        return null;
    }

    @Override
    public SapElement setSelectedRows(int iRow) {
        return null;
    }

    @Override
    public String getCellValue(int iRow, String sColumn) {
        return null;
    }

    @Override
    public boolean isCheckBoxSelected() {
        return false;
    }

    @Override
    public SapElement doubleClickItem(String sNodeKey, String sItemColumn) {
        return null;
    }

    @Override
    public SapElement doubleClickNode(String sNodeId) {
        return null;
    }

    @Override
    public SapElement doubleClickCurrentCell() {
        return null;
    }

    @Override
    public SapElement doubleClick() {
        return null;
    }

    @Override
    public SapElement clickCurrentCell() {
        return null;
    }

    @Override
    public SapElement selectAll() {
        return null;
    }

    @Override
    public SapElement setCurrentCell(int iRow, String sColumn) {
        return null;
    }

    @Override
    public SapElement pressContextButton(String sSubItem) {
        return null;
    }

    @Override
    public SapElement selectRow(int iIndex, boolean bBool) {
        return null;
    }

    @Override
    public SapElement selectContextMenuItem(String sUri, String sFunctionCode, String... sOption) {
        return null;
    }

    @Override
    public SapElement selectContextMenuItem(String sSubItem) {
        return null;
    }

    @Override
    public SapElement nodeContextMenu(String sSubItem) {
        return null;
    }

    @Override
    public SapElement selectedNode(String sSubItem) {
        return null;
    }

    @Override
    public SapElement selectNode(String sNodeId) {
        return null;
    }

    @Override
    public String getNextNodeKey(String sNodeId) {
        return null;
    }

    @Override
    public String getStringNodeKey(int iNodeKey) {
        return null;
    }

    @Override
    public String getStringNodeKey(String sNodeKey) {
        return null;
    }

    @Override
    public String getNodeText(String sNodeId) {
        return null;
    }

    @Override
    public String getItemText(String sNodeKey, String sItemColumn) {
        return null;
    }

    @Override
    public String pressButton(String sUri) {
        return null;
    }

    @Override
    public SapElement expandNode(String sNodeId) {
        return null;
    }

    @Override
    public SapElement selectItem(String sNodeKey, String sItemColumn, String... sOption) {
        return null;
    }

    @Override
    public SapElement clearSelection() {
        return null;
    }

    @Override
    public SapElement pressToolbarContextButton(String sUri) {
        return null;
    }

    @Override
    public SapElement modifyCheckBox(int iRow, String sColumn, Boolean bChecked) {
        return null;
    }

    @Override
    public SapElement triggerModified() {
        return null;
    }

    @Override
    public SapElement modifyCell(int iRow, String sColumn, String sValue) {
        return null;
    }

    @Override
    public SapElement pressEnter() {
        return null;
    }

    @Override
    public SapElement currentCellColumn(String sValue) {
        return null;
    }

    @Override
    public SapElement pressToolbarButton() {
        return null;
    }

    @Override
    public SapElement checkTooltip(String sExpectedValue) {
        return null;
    }

    @Override
    public SapElement sendKey(int iKey) {
        return null;
    }

    @Override
    public SapElement sendKey(SapKeys iKey) {
        return null;
    }

    @Override
    public SapElement caretPosition(int iKey) {
        return null;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public SapElement pressButtonCurrentCell() {
        return null;
    }

    @Override
    public SapElement selectRadioButton(String sText) {
        return null;
    }

    @Override
    public int getVisibleRowCount() {
        return 0;
    }
}