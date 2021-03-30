package com.utils.settings;

import java.io.IOException;
import java.util.Properties;

public class SAPPropertiesReader {
    public static Properties getProperties(String path) throws IOException {
        Properties p = PropertyReader.getProperties("/../../target/classes/" + path);
        return p.size() > 0 ? p : PropertyReader.getProperties(path);
    }
}