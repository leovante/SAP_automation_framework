package com.utils.sapElement.commands;


import com.utils.sapElement.ElementSource;
import com.utils.sapElement.SapElement;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Commands {
    private static Commands collection;

    private final Map<String, Command> commands = new ConcurrentHashMap<>(128);

    public static synchronized Commands getInstance() {
        if (collection == null) {
            collection = new Commands();
            collection.resetDefaults();
        }
        return collection;
    }

    public final synchronized void resetDefaults() {
        commands.clear();
        addInvokes();
        addFunctions();
    }

    private void addInvokes() {
        add("getText", new GetText());
        add("setText", new SetText());
        add("select", new Select());
        add("pressButton", new PressButton());
        add("setFocus", new SetFocus());
        add("selectCurrentCellRow", new SelectCurrentCellRow());
        add("setSelectedRows", new SetSelectedRows());
        add("getCellValue", new GetCellValue());
        add("isCheckBoxSelected", new IsCheckBoxSelected());
        add("doubleClickItem", new DoubleClickItem());
        add("doubleClickNode", new DoubleClickNode());
        add("doubleClickCurrentCell", new DoubleClickCurrentCell());
        add("doubleClick", new DoubleClick());
        add("clickCurrentCell", new ClickCurrentCell());
        add("selectAll", new SelectAll());
        add("setCurrentCell", new SetCurrentCell());
        add("pressContextButton", new PressContextButton());
        add("sendKey", new SendKey());
    }

    private void addFunctions() {
        add("getTextFunction", new GetTextFunction());
    }

    public void add(String method, Command command) {
        commands.put(method, command);
    }

    @SuppressWarnings("unchecked")
    public <T> T execute(Object proxy, ElementSource source, String methodName, Object[] args) {
        Command command = commands.get(methodName);
        if (command == null) {
            throw new IllegalArgumentException("Unknown Saper method: " + methodName);
        }
        return (T) command.execute((SapElement) proxy, source, args);
    }
}
