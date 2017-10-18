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

import com.sapphire.common.task.CoralineRobot;
import com.sapphire.common.utils.dto.JsonDto;

/**
 *
 * @author yunpeng.byp
 * @version $Id: TaskController.java, v 0.1 2017年10月18日 上午11:34 yunpeng.byp Exp $
 */
@Controller
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private CoralineRobot       coralineRobot;

    @RequestMapping("/${jobName}/trigger.ep")
    @ResponseBody
    public JsonDto trigger(@PathVariable("jobName") String jobName) {
        logger.info("Start to trigger the job: " + jobName);

        coralineRobot.sayGoodNight();

        return new JsonDto().formSuccessDto();
    }

    /**
     * Setter method for property <tt>coralineRobot</tt>.
     *
     * @param coralineRobot  value to be assigned to property coralineRobot
     */
    @Autowired
    public void setCoralineRobot(CoralineRobot coralineRobot) {
        this.coralineRobot = coralineRobot;
    }
}