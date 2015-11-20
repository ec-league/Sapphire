package com.sapphire.common;

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
 * Email: byp5303628@hotmail.com This class will load two places property, one
 * is src/main/resources, the other is APP_CONFIG_PATH which is wrote in tomcat
 * start script. APP_CONFIG_PATH's property will overwrite the property in war
 * package. Which means, if there are property keys conflict, the
 * APP_CONFIG_PATH's property will work. <br />
 * TODO: Use filter to avoid property overwriting.
 */
public class PropertyManager {
   private static Logger logger = LoggerFactory
         .getLogger(PropertyManager.class);
   private static Map<String, String> map = new HashMap<String, String>();

   public static void load(Class c) {
      synchronized (map) {
         initFromResource(c);
         initFromAppConfig();
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
      List<File> files = getPropertyFiles(path);
      Map<String, String> tempMap = new HashMap<String, String>();
      for (File f : files) {
         Properties properties = new Properties();
         try {
            properties.load(new FileInputStream(f));
         } catch (IOException e) {

            e.printStackTrace();
            throw new RuntimeException();
         }
         for (String key : properties.stringPropertyNames()) {
            tempMap.put(key, properties.getProperty(key));
         }
      }
      updateMap(tempMap);
   }

   private static void initFromAppConfig() {
      String path = System.getProperty("APP_CONFIG_PATH");
      List<File> files = getPropertyFiles(path);
      Map<String, String> tempMap = new HashMap<String, String>();
      for (File f : files) {
         Properties properties = new Properties();
         try {
            properties.load(new FileInputStream(f));
         } catch (IOException e) {

            e.printStackTrace();
            throw new RuntimeException();
         }
         for (String key : properties.stringPropertyNames()) {
            tempMap.put(key, properties.getProperty(key));
         }
      }
      updateMap(tempMap);
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
      File dir = new File(path);
      List<File> files = new ArrayList<File>();
      for (File file : dir.listFiles()) {
         if (file.getName().matches("sapphire_.+.properties")) {
            files.add(file);
         }
      }
      return files;
   }

   private static void updateMap(Map<String, String> tempMap) {
      for (String key : tempMap.keySet()) {
         if (map.containsKey(key)) {
            logger.warn(String.format(
                  "Key \"%s\" is overwrote, origin is \"%s\", now is \"%s\"",
                  key, map.get(key), tempMap.get(key)));
         }
         map.put(key, tempMap.get(key));
      }
   }
}
