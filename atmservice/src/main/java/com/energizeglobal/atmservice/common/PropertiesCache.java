package com.energizeglobal.atmservice.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class PropertiesCache {
    private final Properties configProp = new Properties();
    private static Logger logger = LogManager.getLogger(PropertiesCache.class);

    private PropertiesCache() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        try {
            configProp.load(in);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private static class LazyHolder {
        private static final PropertiesCache INSTANCE = new PropertiesCache();
    }

    public static PropertiesCache getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String getProperty(String key) {
        return configProp.getProperty(key);
    }

    public Set<String> getAllPropertyNames() {
        return configProp.stringPropertyNames();
    }

    public boolean containsKey(String key) {
        return configProp.containsKey(key);
    }

    // TODO Implement Encoded properties
    public String getPropertyDecoded(String key) {
        return configProp.getProperty(key);
    }
}
