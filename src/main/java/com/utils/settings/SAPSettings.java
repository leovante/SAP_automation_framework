package com.utils.settings;

import java.io.File;
import java.io.IOException;

import static com.utils.settings.PropertyReader.fillAction;
import static com.utils.settings.PropertyReader.fillActionEncode;
import static com.utils.settings.SAPPropertiesReader.getProperties;

public abstract class SAPSettings {
    public static String sapSettingPath = "test.properties";
    public static String domain;
    public static String proxyHost;
    public static int proxyPort;
    public static String proxyAuth;
    public static String proxyPass;
    public static boolean proxyEnabled;
    public static String hybriscom;
    public static String SYSTEM_SAP;
    public static String SOURCE_FOLDER;
    public static String DATA_SHEET;
    public static String FIORI_URL;
    public static String WIRING_DATE;

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
        fillAction(p -> SYSTEM_SAP = p, "wms.system");
        fillActionEncode(p -> SOURCE_FOLDER = p, "wms.sourcefolder");
        fillAction(p -> DATA_SHEET = p, "wms.datasheet");
        fillAction(p -> FIORI_URL = p, "wms.fioriurl");
        fillAction(p -> WIRING_DATE = p, "wms.wiringdate");
    }
}
