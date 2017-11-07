/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.task.domain;

import java.util.List;

/**
 *
 * @author yunpeng.byp
 * @version $Id: TaskConfig.java, v 0.1 2017年11月06日 上午12:04 yunpeng.byp Exp $
 */
public class TaskConfig {
    private String       name;

    private List<String> taskNames;

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name  value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>taskNames</tt>.
     *
     * @return property value of taskNames
     */
    public List<String> getTaskNames() {
        return taskNames;
    }

    /**
     * Setter method for property <tt>taskNames</tt>.
     *
     * @param taskNames  value to be assigned to property taskNames
     */
    public void setTaskNames(List<String> taskNames) {
        this.taskNames = taskNames;
    }
}