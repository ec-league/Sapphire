/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.web.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.common.task.SapphireTaskManager;
import com.sapphire.common.utils.dto.DataJsonDto;
import com.sapphire.common.utils.dto.JsonDto;

/**
 *
 * @author yunpeng.byp
 * @version $Id: TaskController.java, v 0.1 2017年10月18日 上午11:34 yunpeng.byp Exp $
 */
@Controller
@RequestMapping("/stock")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private SapphireTaskManager sapphireTaskManager;

    @RequestMapping("/{jobName}/trigger.ep")
    @ResponseBody
    public JsonDto trigger(@PathVariable("jobName") String jobName) {
        if (logger.isInfoEnabled()) {
            logger.info("Start to trigger the job: " + jobName);
        }

        try {
            sapphireTaskManager.startNow(jobName);
            return new JsonDto().formSuccessDto();
        } catch (Exception e) {
            logger.error("Not Triggered : " + e.getMessage(), e);
            return new JsonDto().formFailureDto(e);
        }
    }

    @RequestMapping("/list/task.ep")
    @ResponseBody
    public JsonDto getAllTasks() {
        return new DataJsonDto(sapphireTaskManager.getRigesterTasks()).formSuccessDto();
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
}