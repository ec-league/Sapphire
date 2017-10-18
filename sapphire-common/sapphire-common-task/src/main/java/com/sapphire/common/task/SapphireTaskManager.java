/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.task;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author yunpeng.byp
 * @version $Id: SapphireTaskManager.java, v 0.1 2017年10月18日 下午6:26 yunpeng.byp Exp $
 */
@Service
public class SapphireTaskManager {
    private static final Logger       logger  = LoggerFactory.getLogger(SapphireTaskManager.class);
    private Map<String, SapphireTask> taskMap = new HashMap<>();

    public SapphireTask getTask(String name) {
        return taskMap.get(name);
    }

    public void register(String name, SapphireTask sapphireTask) {
        logger.info("Register Task: " + name + ", Task: " + sapphireTask.getClass());
        taskMap.put(name, sapphireTask);
    }

}