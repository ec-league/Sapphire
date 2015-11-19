package com.sapphire.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/19.<br/>
 * Email: byp5303628@hotmail.com
 */
public class PropertyManager {
   private static Map<String, String> map = new HashMap<String, String>();

   public static void load(Class c) {
      synchronized (map) {
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
      map.putAll(tempMap);
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
      map.putAll(tempMap);
   }

   public static String getProperty(String key) {
      return map.get(key);
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
}
