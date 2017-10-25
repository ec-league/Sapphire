/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.task;

import com.sapphire.common.dal.mng.task.domain.TaskSetting;
import com.sapphire.common.dal.mng.task.repository.TaskSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yunpeng.byp
 * @version $Id: SapphireTaskFactory.java, v 0.1 2017年10月24日 下午9:22 yunpeng.byp Exp $
 */
@Component
public class SapphireTaskFactory {

    private TaskSettingRepository taskSettingRepository;

    private Map<String, SapphireTask> taskMap = new HashMap<>();

    @PostConstruct
    public void init() {
        List<TaskSetting> taskSettings = taskSettingRepository.findAll();

        for (TaskSetting setting : taskSettings) {
            CronSapphireTask task = new CronSapphireTask();
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
}