package com.settings;

import java.io.File;
import java.io.IOException;

import static com.settings.PropertyReader.fillAction;
import static com.settings.SAPPropertiesReader.getProperties;

public abstract class SAPSettings {
    public static String sapSettingPath = "test.properties";
    public static String domain;
    public static String proxyHost;
    public static int proxyPort;
    public static String proxyAuth;
    public static String proxyPass;
    public static boolean proxyEnabled;
    public static String hybriscom;
    public static String Z_CRM_RUN_UI_SSO_com;

    public static synchronized void initFromProperties() {
        try {
            String localPath = System.getProperty("user.home") + "\\Desktop\\test.properties";
            if (new File(localPath).exists()) {
                PropertyReader.getLocalProperties(localPath);
            } else {
                getProperties(sapSettingPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        fillAction(p -> domain = p, "sap.domain");
        fillAction(p -> proxyHost = p, "proxy.host");
        fillAction(p -> proxyPort = Integer.parseInt(p), "proxy.port");
        fillAction(p -> proxyAuth = p, "proxy.auth");
        fillAction(p -> proxyPass = p, "proxy.pass");
        fillAction(p -> proxyEnabled = Boolean.parseBoolean(p), "proxy.enabled");
        fillAction(p -> hybriscom = p, "test.hybriscom");
        fillAction(p -> Z_CRM_RUN_UI_SSO_com = p, "transaction.Z_CRM_RUN_UI_SSO");
    }
}
