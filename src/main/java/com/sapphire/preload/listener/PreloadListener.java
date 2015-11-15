package com.sapphire.preload.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sapphire.preload.JpaEntityManager;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/7.<br/>
 * Email: byp5303628@hotmail.com
 */
public class PreloadListener implements ServletContextListener {
   private static Logger LOGGER = LoggerFactory
         .getLogger(PreloadListener.class);

   public void contextInitialized(ServletContextEvent servletContextEvent) {

//      LOGGER.info("Preload Jpa Properties.");
//      JpaEntityManager.loadProperties();
   }

   public void contextDestroyed(ServletContextEvent servletContextEvent) {

   }
}
