package com.sapphire.listener;

import com.sapphire.common.PropertyManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/19.<br/>
 * Email: byp5303628@hotmail.com
 */
public class PreloadListener implements ServletContextListener {
   public void contextInitialized(ServletContextEvent servletContextEvent) {
      PropertyManager.load(this.getClass());
   }

   public void contextDestroyed(ServletContextEvent servletContextEvent) {

   }
}
