package com.sapphire.common;

import com.sapphire.common.exception.PropertyManagerInitException;
import com.sapphire.common.exception.PropertyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/19.<br/>
 * Email: byp5303628@hotmail.com <br/>
 * This class will load two places property, one is src/main/resources, the
 * other is APP_CONFIG_PATH which is wrote in tomcat start script.
 * APP_CONFIG_PATH's property will overwrite the property in war package. Which
 * means, if there are property keys conflict, the APP_CONFIG_PATH's property
 * will work. <br />
 * TODO: Use filter to avoid property overwriting.
 */
public class PropertyManager {
   private static final Logger LOGGER = LoggerFactory
         .getLogger(PropertyManager.class);
   private static Map<String, String> map = new HashMap<String, String>();

   public static void load(Class c) {
      LOGGER.info("Init the Property");
      synchronized (map) {
         initFromResource(c);
         initFromAppConfig();
      }
   }

   public static void destroy() {
      synchronized (map) {
         map = new HashMap<String, String>();
      }
   }

   public static void reload(Class c) {
      synchronized (map) {
         map = new HashMap<String, String>();
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
      Map<String, String> tempMap = new HashMap<String, String>();
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
         throw new PropertyNotFoundException(String.format(
               "Property \"%s\" does not exist.", key));
      }
   }

   private static List<File> getPropertyFiles(String path) {
      if (path == null) {
         return Collections.emptyList();
      }
      File dir = new File(path);
      List<File> fileList = new ArrayList<File>();
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
            LOGGER.warn(String.format(
                  "Key \"%s\" is overwrote, origin is \"%s\", now is \"%s\"",
                  entry.getKey(), map.get(entry.getKey()), entry.getValue()));
         }
         map.put(entry.getKey(), entry.getValue());
      }
   }
}
