/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.task.domain;

import com.sapphire.common.task.SapphireTaskManager;
import com.sapphire.common.task.exception.TaskConfigException;
import com.sapphire.common.task.exception.TaskNotExistException;

/**
 *
 * @author yunpeng.byp
 * @version $Id: CronSapphireTask.java, v 0.1 2017年10月25日 下午5:13 yunpeng.byp Exp $
 */
public class CronSapphireTask implements SapphireTask {
    private final SapphireTaskManager taskManager;

    private String                    name;

    private String                    cronExpression;

    private SapphireTask              task;

    private TaskConfig                taskConfig;

    public CronSapphireTask(SapphireTaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * 调度任务的具体执行.
     */
    @Override
    public void execute() {
        if (taskConfig == null) {
            throw new TaskConfigException();
        }

        if (taskConfig.getTaskNames() == null || taskConfig.getTaskNames().isEmpty()) {
            throw new TaskConfigException("Task Config Not Correct : " + taskConfig.getName());
        }

        for (String taskName : taskConfig.getTaskNames()) {
            SapphireTask task = taskManager.getTask(taskName);

            if (task == null) {
                throw new TaskNotExistException(taskName);
            }

            task.execute();
        }
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

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for property <tt>taskConfig</tt>.
     *
     * @return property value of taskConfig
     */
    public TaskConfig getTaskConfig() {
        return taskConfig;
    }

    /**
     * Setter method for property <tt>taskConfig</tt>.
     *
     * @param taskConfig  value to be assigned to property taskConfig
     */
    public void setTaskConfig(TaskConfig taskConfig) {
        this.taskConfig = taskConfig;
    }
}