package com.utils.settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.function.Consumer;

public final class PropertyReader {
    private static String propertiesPath;
    private static volatile Properties properties;
    private static InputStream inputStream;

    private PropertyReader() {
    }

    private static String getCorrectPath() {
        if (propertiesPath.charAt(0) != '/')
            propertiesPath = "/" + propertiesPath;
        return propertiesPath;
    }


    public static Properties readProperties() throws IOException {
        properties = new Properties();
        try {
            inputStream = PropertyReader.class.getResourceAsStream(getCorrectPath());
            if (inputStream != null)
                properties.load(inputStream);
        } catch (Exception ex) {
            if (inputStream != null) inputStream.close();
        }
        if(properties.getProperty("config_file")!=null){
            Properties additionalProperties = getProperties(properties.getProperty("config_file"));
            properties.putAll(additionalProperties);
        }
        return properties;
    }

    private static Properties loadProperties() throws IOException {
        return properties != null ? properties : readProperties();
    }

    public static Properties getProperties(String path) throws IOException {
        propertiesPath = path;
        return readProperties();
    }

    public static Properties getLocalProperties(String path) throws IOException {
        properties = new Properties();
        FileInputStream in = new FileInputStream(path);
        try {
            properties.load(in);
        } finally {
            in.close();
        }
        return properties;
    }

    public static String getProperty(String propertyName) throws IOException {
        return loadProperties().getProperty(propertyName);
    }

    public static void fillAction(Consumer<String> action, String name) {
        Object prop = null;
        try {
            prop = getProperty(name);
        } catch (Exception ignore) {}
        if (prop != null && !prop.equals(""))
            action.accept(prop.toString());
    }

    public static void fillActionEncode(Consumer<String> action, String name) {
        String s = null;
        try {
            s = getProperty(name);
            byte[] bytes = s.getBytes(StandardCharsets.ISO_8859_1);
            s = new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception ignore) {}
        if (s != null && !s.equals(""))
            action.accept(s.toString());
    }
}