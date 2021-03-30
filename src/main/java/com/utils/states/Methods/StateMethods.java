package com.utils.states.Methods;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.utils.sapGeneric.SessionRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface StateMethods {

    default ActiveXComponent runSAP(String sSystemName) {
        ComThread.InitSTA();
        // -Variables------------------------------------------------------
        ActiveXComponent SAPROTWr, GUIApp, Connection = null, Session;
        Dispatch ROTEntry;
        Variant ScriptEngine = null;

        //Opening the SAP Logon
        String sapLogonPath = "\"C:\\Program\fFiles\f(x86)\\SAP\\FrontEnd\\SAPgui\\saplogon.exe\"";
        try {
            Runtime.getRuntime().exec(sapLogonPath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            try {
                // -Set SapGuiAuto = GetObject("SAPGUI")---------------------------
                SAPROTWr = new ActiveXComponent("SapROTWr.SapROTWrapper");
                System.out.println("SAPROTWr: " + SAPROTWr);
                ROTEntry = SAPROTWr.invoke("GetROTEntry", "SAPGUI").toDispatch();
                System.out.println("ROTEntry: " + ROTEntry);
                // -Set application = SapGuiAuto.GetScriptingEngine------------
                ScriptEngine = Dispatch.call(ROTEntry, "GetScriptingEngine");
                System.out.println("ScriptEngine: " + ScriptEngine);
                break;
            } catch (Exception e) {
                System.out.println(i);
            }
        }

        GUIApp = new ActiveXComponent(ScriptEngine.toDispatch());
        System.out.println("GUIApp: " + GUIApp);
        // SAP Connection Name
        String sapConnectionName = sSystemName; // this is the name of the connection in SAP Logon
        try {
            Connection = new ActiveXComponent(GUIApp.invoke("OpenConnection", sapConnectionName).toDispatch());
        } catch (com.jacob.com.ComFailException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        //-Set connection = application.Children(0)-------------------
        Session = new ActiveXComponent(Connection.invoke("Children", 0).toDispatch());
        System.out.println("Нашел окно");
        System.out.println(Session);
        return Session;
    }

    default void resizeWorkingPane() {
        ActiveXComponent Obj = new ActiveXComponent(SessionRunner.getCurrentSession().invoke("FindById", "wnd[0]").toDispatch());
        Variant[] v = new Variant[3];
        v[0] = new Variant(180);
        v[1] = new Variant(24);
        v[2] = new Variant(true);
        Obj.invoke("resizeWorkingPane", v);
    }

    default byte[] captureScreen() {
        String pathImage = Paths.get("target/allure-results/image.png").toAbsolutePath().toString();
        byte[] captureScreen = null;
        try {
            ActiveXComponent activeWindow = new ActiveXComponent(SessionRunner.getCurrentSession().invoke("activeWindow").toDispatch());
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

    default void ifDumpAppears() {
        String dumpText = getText("wnd[0]/titl");
        if (dumpText.length() > 20) {
            dumpText = dumpText.substring(0, 20);
            if (dumpText.equals("Динамическая ошибка")) {
            }
        }
    }

    default String getText(String sId) {
        ActiveXComponent Obj = new ActiveXComponent(SessionRunner.getCurrentSession().invoke("FindById", sId).toDispatch());
        Variant text = Obj.getProperty("Text");
        System.out.println("Получение текста: " + text);
        return text.getString();
    }

    default void sapScreenShotAndExit() {
        try {
            captureScreen();
            ifDumpAppears();
        } catch (Exception e) {
            System.out.println("Не удалось сделать скриншот!");
        }
        try {
            String message = "Тест завершен. Значение статус бара: " + getTextFunction("wnd[0]/sbar");
            System.out.println(message);
        } catch (Exception e) {
            System.out.println("Тест завершен");
        }
        closeSap();
    }

    default String getTextFunction(String sId) {
        ActiveXComponent Obj = new ActiveXComponent(SessionRunner.getCurrentSession().invoke("FindById", sId).toDispatch());
        Pattern pattern = Pattern.compile("\\W");
        String text = Obj.getProperty("Text").toString();
        Matcher matcher = pattern.matcher(text);
        try {
            if (!matcher.find()) {
                String tooltip = Obj.getProperty("Tooltip").toString();
                Matcher matcher2 = pattern.matcher(tooltip);
                if (!matcher2.find()) {
                    String defaultTooltip = Obj.getProperty("DefaultTooltip").toString();
                    Matcher matcher3 = pattern.matcher(defaultTooltip);
                    if (!matcher3.find()) {
                        String accTooltip = Obj.getProperty("AccTooltip").toString();
                        Matcher matcher4 = pattern.matcher(accTooltip);
                        if (matcher4.find()) {
                            text = accTooltip;
                        } else System.out.println("Текст отсутствует");
                    } else text = defaultTooltip;
                } else if (!tooltip.equals("")) {
                    text = tooltip;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return text;
    }

    default void closeSap() {
        System.out.println("Закрытие SAP");
        ActiveXComponent Obj = new ActiveXComponent(SessionRunner.getCurrentSession().invoke("FindById", "wnd[0]").toDispatch());
        Obj.invoke("Close");
        int window = getActiveWindow();
        ActiveXComponent Obj2 = new ActiveXComponent(SessionRunner.getCurrentSession().invoke("FindById", "wnd[" + window + "]/usr/btnSPOP-OPTION1").toDispatch());
        Obj2.invoke("Press");
        ComThread.Release();

    }

    default Integer getActiveWindow() {
        ActiveXComponent ActiveWindow = new ActiveXComponent(SessionRunner.getCurrentSession().invoke("activeWindow").toDispatch());
        String windowID = ActiveWindow.getProperty("ID").getString();
        Pattern pattern = Pattern.compile(".*wnd.(.).");
        Matcher matcher = pattern.matcher(windowID);
        matcher.matches();
        return Integer.valueOf(matcher.group(1));
    }

}
