/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.task.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.sapphire.common.task.SapphireTask;
import com.sapphire.common.task.SapphireTaskManager;
import com.sapphire.common.task.stock.constant.StockConstants;
import com.sapphire.common.utils.annotation.Job;

/**
 *
 * @author yunpeng.byp
 * @version $Id: StockTask.java, v 0.1 2017年10月18日 下午6:30 yunpeng.byp Exp $
 */
@Job
public class StockTask implements SapphireTask {

    private SapphireTaskManager taskManager;

    /**
     * 调度任务的具体执行.
     */
    @Scheduled(cron = "0 0 20 * * ?")
    @Override
    public void execute() {
        SapphireTask task = taskManager.getTask(StockConstants.STOCK_ITEM_TASK_NAME);
        task.execute();

        task = taskManager.getTask(StockConstants.STOCK_STATISTIC_TASK_NAME);
        task.execute();
    }

    /**
     * Setter method for property <tt>taskManager</tt>.
     *
     * @param taskManager  value to be assigned to property taskManager
     */
    @Autowired
    public void setTaskManager(SapphireTaskManager taskManager) {
        this.taskManager = taskManager;
    }
}