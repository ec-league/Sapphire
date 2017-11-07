/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.common.dal.mng.task.domain.TaskSetting;
import com.sapphire.common.dal.mng.task.repository.TaskSettingRepository;
import com.sapphire.common.task.domain.CronSapphireTask;
import com.sapphire.common.task.domain.TaskConfig;
import com.sapphire.common.utils.JsonUtil;

/**
 *
 * @author yunpeng.byp
 * @version $Id: SapphireCronTaskFactory.java, v 0.1 2017年10月24日 下午9:22 yunpeng.byp Exp $
 */
public class SapphireCronTaskFactory {

    private static final Logger   logger = LoggerFactory.getLogger(SapphireCronTaskFactory.class);

    private TaskSettingRepository taskSettingRepository;
    private SapphireTaskManager   sapphireTaskManager;
    private JsonUtil              jsonUtil;

    public void init() {
        if (logger.isInfoEnabled()) {
            logger.info("Start to Init Sapphire Task Factory!");
        }

        List<TaskSetting> taskSettings = taskSettingRepository.findAll();

        for (TaskSetting setting : taskSettings) {
            CronSapphireTask task = new CronSapphireTask(sapphireTaskManager);

            task.setCronExpression(setting.getCronExpression());
            task.setName(setting.getTaskName());
            task.setTaskConfig(jsonUtil.toObject(setting.getTaskConfig(), TaskConfig.class));

            sapphireTaskManager.register(task);
        }
    }

    /**
     * Setter method for property <tt>taskSettingRepository</tt>.
     *
     * @param taskSettingRepository  value to be assigned to property taskSettingRepository
     */
    @Autowired
    public void setTaskSettingRepository(TaskSettingRepository taskSettingRepository) {
        this.taskSettingRepository = taskSettingRepository;
    }

    /**
     * Setter method for property <tt>sapphireTaskManager</tt>.
     *
     * @param sapphireTaskManager  value to be assigned to property sapphireTaskManager
     */
    @Autowired
    public void setSapphireTaskManager(SapphireTaskManager sapphireTaskManager) {
        this.sapphireTaskManager = sapphireTaskManager;
    }

    /**
     * Setter method for property <tt>jsonUtil</tt>.
     *
     * @param jsonUtil  value to be assigned to property jsonUtil
     */
    @Autowired
    public void setJsonUtil(JsonUtil jsonUtil) {
        this.jsonUtil = jsonUtil;
    }
}