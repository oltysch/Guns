package com.epam.ot.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    Properties properties;
    public static final Logger logger = Logger.getLogger(PropertyManager.class);

    public PropertyManager(String fileName) {
        properties = new Properties();
        logger.info("properties loading started");
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            properties.load(inputStream);
            logger.info("properties loaded");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    public String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
