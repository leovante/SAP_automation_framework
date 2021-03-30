package com.pageObject.S4;

import com.constants.Credentials;
import com.utils.sapGeneric.DesktopFunctions;

public class LoginPageDesktop extends DesktopFunctions {

    public LoginPageDesktop() {
        super();
    }

    public UserMenuDesktop login(Credentials credentials) {
        // -Set GUITextField User--------------------------------------
        setText("wnd[0]/usr/txtRSYST-BNAME", credentials.getLogin());
        // -Set GUIPasswordField Password------------------------------
        setText("wnd[0]/usr/pwdRSYST-BCODE", credentials.getPassword());
        // -Set GUITextField Language----------------------------------
        setText("wnd[0]/usr/txtRSYST-LANGU", "RU");
        // -Set GUITextField Mandate--------------------------------------
        if (!credentials.getMandate().isEmpty())
            setText("wnd[0]/usr/txtRSYST-MANDT", credentials.getMandate());
        // -Press enter------------------------------------------------
        sendKey("wnd[0]", 0);

        if (getText("wnd[0]/sbar").equals("Неправильное имя или пароль (попытайтесь зарегистрироваться еще раз)")) {
            sapScreenShotAndExit();
            System.exit(0);
        }
        if (isElementPresent("wnd[1]")) {
            setSelected("wnd[1]/usr/radMULTI_LOGON_OPT2", true);
            pressButton("wnd[1]/tbar[0]/btn[0]");
        }
        System.out.println(String.format("Пользователь %s успешно залогинился", credentials.getLogin()));
        return new UserMenuDesktop();
    }

}
