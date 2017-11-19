/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.task;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import com.sapphire.common.task.exception.TaskNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import com.sapphire.common.task.domain.CronSapphireTask;
import com.sapphire.common.task.domain.SapphireTask;
import com.sapphire.common.task.exception.TaskConfigException;

/**
 *
 * @author yunpeng.byp
 * @version $Id: SapphireTaskManager.java, v 0.1 2017年10月18日 下午6:26 yunpeng.byp Exp $
 */
public class SapphireTaskManager {
    private static final Logger                   logger        = LoggerFactory
        .getLogger(SapphireTaskManager.class);

    private final Map<String, CronSapphireTask>   cronTaskMap   = new HashMap<>();

    private final Map<String, ScheduledFuture<?>> resultTaskMap = new HashMap<>();

    private BeanFactory                           beanFactory;

    private TaskScheduler                         scheduler;

    public SapphireTask getTask(String name) {
        Object result = beanFactory.getBean(name);

        if (result instanceof SapphireTask) {
            return (SapphireTask) result;
        }

        logger.error(String.format("Bean name : \"%s\" is not a SapphireTask, class is :%s", name,
            result.getClass().getSimpleName()));
        throw new TaskConfigException("Bean name not correct!");
    }

    /**
     * 注册定时任务
     * @param task
     */
    void register(CronSapphireTask task) {
        if (logger.isInfoEnabled()) {
            logger.info("Register Cron Task : " + task.getName() + ", Cron Expr : "
                        + task.getCronExpression());
        }

        cronTaskMap.put(task.getName(), task);
        resultTaskMap.put(task.getName(), scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                task.execute();
            }
        }, new CronTrigger(task.getCronExpression())));
    }

    /**
     * 启动指定任务名字的任务.
     * @param taskName
     */
    public void startNow(String taskName) {
        if (cronTaskMap.containsKey(taskName)) {
            cronTaskMap.get(taskName).execute();
        }
        else {
            throw new TaskNotExistException(taskName);
        }
    }

    /**
     * 获取所有注册的任务
     * @return
     */
    public Map<String, CronSapphireTask> getRigesterTasks() {
        return Collections.unmodifiableMap(cronTaskMap);
    }

    /**
     * Setter method for property <tt>scheduler</tt>.
     *
     * @param scheduler  value to be assigned to property scheduler
     */
    @Autowired
    public void setScheduler(TaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * Setter method for property <tt>beanFactory</tt>.
     *
     * @param beanFactory  value to be assigned to property beanFactory
     */
    @Autowired
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}