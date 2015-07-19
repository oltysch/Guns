package com.epam.ot.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    Properties properties = new Properties();
    //TODO create logger

    public PropertyManager(String fileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
