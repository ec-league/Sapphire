package com.sapphire.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sapphire.common.PropertyManager;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/19.<br/>
 * Email: byp5303628@hotmail.com
 */
public class PreloadListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        PropertyManager.load(this.getClass());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        PropertyManager.destroy();
    }
}
