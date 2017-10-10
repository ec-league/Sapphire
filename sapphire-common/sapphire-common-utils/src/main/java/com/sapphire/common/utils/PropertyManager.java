package com.sapphire.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.sapphire.common.utils.annotation.Util;
import com.sapphire.common.utils.exception.PropertyManagerInitException;
import com.sapphire.common.utils.exception.PropertyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Author: Ethan <br/>
 * Date: 2015/11/19.<br/>
 * Email: byp5303628@hotmail.com <br/>
 * This class will load two places property, one is src/main/resources, the
 * other is APP_CONFIG_PATH which is wrote in tomcat start script.
 * APP_CONFIG_PATH's property will overwrite the property in war package. Which
 * means, if there are property keys conflict, the APP_CONFIG_PATH's property
 * will work. <br />
 */
public class PropertyManager {
    private static final Logger        LOGGER = LoggerFactory.getLogger(PropertyManager.class);
    private static Map<String, String> map    = new HashMap<>();
    private static final Object        lock   = new Object();

    private PropertyManager() {

    }

    public static void load(Class c) {
        LOGGER.info("Init the Property");
        synchronized (lock) {
            initFromResource(c);
            initFromAppConfig();
        }
    }

    public static void destroy() {
        synchronized (lock) {
            map = new HashMap<>();
        }
    }

    public static void reload(Class c) {
        synchronized (lock) {
            map = new HashMap<>();
            initFromResource(c);
            initFromAppConfig();
        }
    }

    private static void initFromResource(Class c) {
        String path = c.getResource("/").getPath();
        updateMap(initFromProperty(path));
    }

    private static Map<String, String> initFromProperty(String path) {
        List<File> files = getPropertyFiles(path);
        Map<String, String> tempMap = new HashMap<>();
        for (File f : files) {
            Properties properties = new Properties();
            try {
                properties.load(new FileInputStream(f));
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
                throw new PropertyManagerInitException(e);
            }
            for (String key : properties.stringPropertyNames()) {
                tempMap.put(key, properties.getProperty(key));
            }
        }
        return tempMap;
    }

    private static void initFromAppConfig() {
        String path = System.getProperty("APP_CONFIG_PATH");
        updateMap(initFromProperty(path));
    }

    public static String getProperty(String key) {
        if (map.containsKey(key)) {
            return map.get(key).trim();
        } else {
            throw new PropertyNotFoundException(
                String.format("Property \"%s\" does not exist.", key));
        }
    }

    private static List<File> getPropertyFiles(String path) {
        if (path == null) {
            return Collections.emptyList();
        }
        File dir = new File(path);
        List<File> fileList = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files == null) {
            return Collections.emptyList();
        }
        for (File file : dir.listFiles()) {
            if (file.getName().matches("sapphire_.+.properties")) {
                fileList.add(file);
            }
        }
        return fileList;
    }

    private static void updateMap(Map<String, String> tempMap) {
        for (Map.Entry<String, String> entry : tempMap.entrySet()) {
            if (map.containsKey(entry.getKey())) {
                LOGGER.warn("Key \"%s\" is overwrote, origin is \"%s\", now is \"%s\"",
                    entry.getKey(), map.get(entry.getKey()), entry.getValue());
            }
            map.put(entry.getKey(), entry.getValue());
        }
    }
}