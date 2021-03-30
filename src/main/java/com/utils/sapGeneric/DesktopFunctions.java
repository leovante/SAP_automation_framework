package com.utils.sapGeneric;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComFailException;
import com.jacob.com.Variant;
import org.apache.commons.lang.StringUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DesktopFunctions {

    protected ActiveXComponent session;
    private Set<String> allIdList;
    private String parsedCurrentId;

    private static final String STATUS_BAR_ID = "wnd[0]/sbar";

    public DesktopFunctions() {
        this.session = SessionRunner.getCurrentSession();
        this.allIdList = new HashSet<>();
        this.parsedCurrentId = "";
    }

    protected void waitSessionToBeLoaded() {
        long waitForLoading = System.currentTimeMillis() + 600000; //10 min
        boolean isBusy = session.getPropertyAsBoolean("Busy");
        while (isBusy && (System.currentTimeMillis() < waitForLoading)) {
            isBusy = session.getPropertyAsBoolean("Busy");
        }
        assertFalse(isBusy, "Значение свойства сессии Busy = true больше 10 минут");

    }

    public void setText(String sId, String sText) {
        findElement(sId).setProperty("Text", sText);
    }

    private ActiveXComponent findElement(String sId) {
        waitSessionToBeLoaded();
        if (sId.contains("usr/lbl[")) {
            return new ActiveXComponent(session.invoke("FindById", sId).toDispatch());
        }
        try {
            return new ActiveXComponent(session.invoke("FindById", sId).toDispatch());
        } catch (ComFailException ex) {
            if (isElementPresent(sId)) {
                return new ActiveXComponent(session.invoke("FindById", parsedCurrentId).toDispatch());
            } else return new ActiveXComponent(session.invoke("FindById", sId).toDispatch());
        }
    }

    public boolean isElementPresent(String sId) {
        getAllId();
        parsedCurrentId = allIdList.stream().filter(id -> id.length() >= sId.length() && StringUtils.endsWith(id, sId))
                .findFirst()
                .orElse("");
        return !parsedCurrentId.isEmpty();
    }

    public String getTextFunction(String sId) {
        ActiveXComponent obj = findElement(sId);
        Pattern pattern = Pattern.compile("\\W");
        String text = obj.getProperty("Text").toString();
        Matcher matcher = pattern.matcher(text);
        try {
            if (!matcher.find()) {
                String tooltip = obj.getProperty("Tooltip").toString();
                Matcher matcher2 = pattern.matcher(tooltip);
                if (!matcher2.find()) {
                    String defaultTooltip = obj.getProperty("DefaultTooltip").toString();
                    Matcher matcher3 = pattern.matcher(defaultTooltip);
                    if (!matcher3.find()) {
                        String accTooltip = obj.getProperty("AccTooltip").toString();
                        Matcher matcher4 = pattern.matcher(accTooltip);
                        if (matcher4.find()) {
                            text = accTooltip;
                        }
                    } else text = defaultTooltip;
                } else if (!tooltip.equals("")) {
                    text = tooltip;
                }
            }
        } catch (ComFailException ignored) {
            System.err.println("Ошибка в getTextFunction");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return text;
    }

    /**
     * <b> Получение значения из текстового поля </b>
     *
     * @param sId = Идентификатор
     */
    public String getText(String sId) {
        return getObjectProperty(sId, "Text");
    }

    /**
     * <b> Получение статуса чекбокса </b>
     *
     * @param sId
     */
    public boolean isCheckBoxSelected(String sId) {
        return findElement(sId).getProperty("Selected").getBoolean();
    }

    /**
     * <b> Нажатие на кнопку </b>
     *
     * @param sId = Идентификатор
     */
    public void pressButton(String sId) {
        findElement(sId).invoke("Press");
    }

    /**
     * <b> Установка фокуса </b>
     *
     * @param sId = Идентификатор
     */
    public void setFocus(String sId) {
        findElement(sId).invoke("SetFocus");
    }

    /**
     * <b> Выбор текущей ячейки </b>
     *
     * @param sId = Идентификатор
     * @param iJ
     */
    public void selectCurrentCellRow(String sId, int iJ) {
        findElement(sId).setProperty("currentCellRow", iJ);
    }

    /**
     * <b> Установка выбранной строки </b>
     *
     * @param sId  = Идентификатор
     * @param iRow = Строка
     */
    public void setSelectedRows(String sId, int iRow) {
        findElement(sId).setProperty("selectedRows", iRow);
    }

    /**
     * <b> Получение значения из ячейки таблицы </b>
     *
     * @param sId     = Идентификатор
     * @param iRow    = Строка
     * @param sColumn = Колонка
     */
    public String getCellValue(String sId, int iRow, String sColumn) {
        Variant[] v = new Variant[2];
        v[0] = new Variant(iRow);
        v[1] = new Variant(sColumn);
        return findElement(sId).invoke("getCellValue", v).getString();
    }

    /**
     * <b> Получение номера активного окна </b>
     */
    public Integer getActiveWindow() {
        ActiveXComponent ActiveWindow = new ActiveXComponent(session.invoke("activeWindow").toDispatch());
        String windowID = ActiveWindow.getProperty("ID").getString();
        Pattern pattern = Pattern.compile(".*wnd.(.).");
        Matcher matcher = pattern.matcher(windowID);
        matcher.matches();
        return Integer.valueOf(matcher.group(1));
    }

    /**
     * <b> Получение параметров статус-бара </b>
     */
    public String getMessageParameters() {
        return getTextFunction(STATUS_BAR_ID);
    }

    /**
     * <b> Закрытие активного окна SAP </b>
     */
    public void closeSap() {
        System.out.println("Закрытие SAP");
        ActiveXComponent obj = findElement("wnd[0]");
        obj.invoke("Close");
        obj = findElement("wnd[" + getActiveWindow() + "]/usr/btnSPOP-OPTION1");
        obj.invoke("Press");
    }

    /**
     * <b> Двойной клик по элементу в дереве </b>
     *
     * @param sId         = Идентификатор
     * @param sNodeKey    = Свойство
     * @param sItemColumn = Колонка
     */
    public void doubleClickItem(String sId, String sNodeKey, String sItemColumn) {
        ActiveXComponent Obj = new ActiveXComponent(session.invoke("FindById", sId).toDispatch());
        String сell = getTextFunction(sId);
        Variant[] variant = new Variant[2];
        variant[0] = new Variant(getStringNodeKey(sNodeKey));
        variant[1] = new Variant(sItemColumn);
        Obj.invoke("doubleClickItem", variant);
    }

    /**
     * <b> Нажать на клавиши </b>
     *
     * @param sId  = Идентификатор
     * @param iKey = Клавиша
     */
    public void sendKey(String sId, int iKey) {
        findElement(sId).invoke("sendVKey", iKey);
    }

    /**
     * @param sId     = Идентификатор
     * @param sNodeId = Свойство
     */
    public void doubleClickNode(String sId, String sNodeId) {
        ActiveXComponent obj = findElement(sId);
        String cell = getTextFunction(sId);
        obj.invoke("doubleClickNode", sNodeId);

    }


    /**
     * @param sId = Идентификатор
     */
    public void doubleClickCurrentCell(String sId) {
        ActiveXComponent obj = findElement(sId);
        String currentCell = getTextFunction(sId);
        obj.invoke("doubleClickCurrentCell");

    }

    /**
     * @param sId = Идентификатор
     */
    public void doubleClick(String sId) {
        ActiveXComponent obj = findElement(sId);
        String currentCell = getTextFunction(sId);
        obj.invoke("doubleClick");

    }

    /**
     * @param sId = Идентификатор
     */
    public void clickCurrentCell(String sId) {
        ActiveXComponent obj = findElement(sId);
        String currentCell = getTextFunction(sId);
        obj.invoke("ClickCurrentCell");

    }

    /**
     * @param sId      = Идентификатор
     * @param property = Свойство
     */
    public String getObjectProperty(String sId, String property) {
        ActiveXComponent obj = findElement(sId);
        String propertyValue = obj.getPropertyAsString(property);

        return propertyValue;
    }

    public void setCurrentCell(String sId, int iRow, String sColumn) {
        ActiveXComponent obj = findElement(sId);
        Variant[] v = new Variant[2];
        v[0] = new Variant(iRow);
        v[1] = new Variant(sColumn);
        obj.invoke("setCurrentCell", v);
        String text = getTextFunction(sId);

    }

    /**
     * @param sId      - идентификатор
     * @param sSubItem - контекстная кнопка(ее имя)
     */
    public void pressContextButton(String sId, String sSubItem) {
        ActiveXComponent obj = findElement(sId);
        String text = getTextFunction(sId);
        obj.invoke("pressContextButton", sSubItem);

    }

    public DesktopFunctions pressExecute() {
        pressButton("wnd[0]/tbar[1]/btn[8]");
        return this;
    }

    /**
     * @param sId   = Идентификатор (адрес)
     * @param bBool = true or false!? (Если указать true выберется радиокнопка)
     */
    public void setSelected(String sId, boolean bBool) {
        ActiveXComponent obj = findElement(sId);
        String text = getTextFunction(sId);
        obj.setProperty("Selected", bBool);

    }

    /**
     * @param sId       = Идентификатор
     * @param sProperty = Свойство
     * @return = Полученное текстовое значение
     */
    public String getProperty(String sId, String sProperty) {
        ActiveXComponent obj = findElement(sId);
        String text = getTextFunction(sId);
        Variant cellValue = null;
        Matcher matcher = null;
        String[] strings = null;

        try {
            //Дробим значение в аргументе property и
            //получаем первое значение (название функции)
            Pattern pattern = Pattern.compile("(.*?)\\(\\s*(.*?)\\s*\\)");
            matcher = pattern.matcher(sProperty);
            matcher.matches();

            //Дробим знаение в скобка аргумента property
            pattern = Pattern.compile("\\s*,\\s*");
            strings = pattern.split(matcher.group(2), 8);

            //Записываем значения в переменую типа Variant
            Variant[] arg = new Variant[2];
            arg[0] = new Variant(strings[0]);
            arg[1] = new Variant(strings[1]);

            cellValue = obj.invoke(matcher.group(1), arg);
        } catch (Exception e) {
            e.printStackTrace();


        }

        return cellValue.toString();
    }

    /**
     * Получить строковый параметр NodeKey в объекте GuiTree
     * т.к. Sap Scripting работает с номерами нод в строковом формате длинной 11 символов
     *
     * @param iNodeKey Номер ноды
     * @return NodeKey формата длинной 11 символов, например: "          1"
     */
    public String getStringNodeKey(int iNodeKey) {
        StringBuilder sb = new StringBuilder();
        sb.append(iNodeKey);
        while (sb.length() < 11) {
            sb.insert(0, " ");
        }
        return sb.toString();
    }

    /**
     * Получить строковый параметр NodeKey в объекте GuiTree
     * т.к. Sap Scripting работает с номерами нод в строковом формате длинной 11 символов
     *
     * @param sNodeKey Номер ноды
     * @return NodeKey формата длинной 11 символов, например: "          1"
     */
    public String getStringNodeKey(String sNodeKey) {
        if (sNodeKey.length() == 11) {
            return sNodeKey;
        } else if (sNodeKey.startsWith("000")) { //Для sNodeKey вида: 000001
            return sNodeKey;
        }
        return getStringNodeKey(Integer.parseInt(sNodeKey.trim()));
    }

    public byte[] captureScreen() {
        String pathImage = Paths.get("target/allure-results/image.png").toAbsolutePath().toString();
        byte[] captureScreen = null;
        try {
            ActiveXComponent activeWindow = new ActiveXComponent(session.invoke("activeWindow").toDispatch());
            activeWindow.invoke("HardCopy", pathImage, 2);
            try {
                captureScreen = Files.readAllBytes(Paths.get(pathImage));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return captureScreen;
    }

    public void sapScreenShotAndExit() {
        try {
            captureScreen();
            String dumpText = getText("wnd[0]/titl");
            if (dumpText.length() > 20) {
                dumpText = dumpText.substring(0, 20);
                if (dumpText.equals("Динамическая ошибка")) {

                }
            }
        } catch (Exception e) {
            System.out.println("Не удалось сделать скриншот!");
        }
        try {
            System.out.println("Тест завершен. Значение статус бара: " + getMessageParameters());
        } catch (Exception e) {
            System.out.println("Тест завершен");
        }
        closeSap();
    }

    /**
     * <b> Рекурсивно собрать все id элементов, присутсвующих в текущий момент в DOM </b>
     * <b> Работает от текущей сессии и собирает id элементов во всех активных окнах </b>
     */
    private void getId(ActiveXComponent obj) {
        String id = obj.getPropertyAsString("Id");
        if (id.contains("wnd")) {
            allIdList.add(id.substring(id.indexOf("wnd")));
        }
        if (obj.getPropertyAsBoolean("ContainerType")) {
            ActiveXComponent objCollection = obj.getPropertyAsComponent("Children");

            if (session.getPropertyAsBoolean("Busy")) {
                return;
            }
            int count = objCollection.getPropertyAsInt("Length");
            for (int i = 0; i < count; i++) {
                ActiveXComponent childObj = new ActiveXComponent(objCollection
                        .invoke("ElementAt", i).toDispatch());
                getId(childObj);
            }
        }
    }

    /**
     * <b> Рекурсивно собрать все id элементов, присутсвующих в текущий момент в DOM, Точка входа </b>
     * <b> Работает от текущей сессии и собирает id элементов во всех активных окнах </b>
     */
    private void getAllId() {
        allIdList.clear();
        getId(session);
    }
}