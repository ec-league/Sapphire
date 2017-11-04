/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.task;

/**
 *
 * @author yunpeng.byp
 * @version $Id: CronSapphireTask.java, v 0.1 2017年10月25日 下午5:13 yunpeng.byp Exp $
 */
public class CronSapphireTask implements SapphireTask {

    private String       name;

    private String       cronExpression;

    private SapphireTask task;

    /**
     * 调度任务的具体执行.
     */
    @Override
    public void execute() {

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
     * Getter method for property <tt>cronExpression</tt>.
     *
     * @return property value of cronExpression
     */
    public String getCronExpression() {
        return cronExpression;
    }

    /**
     * Setter method for property <tt>cronExpression</tt>.
     *
     * @param cronExpression  value to be assigned to property cronExpression
     */
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    /**
     * Getter method for property <tt>task</tt>.
     *
     * @return property value of task
     */
    public SapphireTask getTask() {
        return task;
    }

    /**
     * Setter method for property <tt>task</tt>.
     *
     * @param task  value to be assigned to property task
     */
    public void setTask(SapphireTask task) {
        this.task = task;
    }
}