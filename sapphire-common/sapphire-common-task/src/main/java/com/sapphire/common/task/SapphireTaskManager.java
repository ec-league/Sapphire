/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.task;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 *
 * @author yunpeng.byp
 * @version $Id: SapphireTaskManager.java, v 0.1 2017年10月18日 下午6:26 yunpeng.byp Exp $
 */
@Service
public class SapphireTaskManager {
    private Map<String, SapphireTask> taskMap = new HashMap<>();

    public SapphireTask getTask(String name) {
        return taskMap.get(name);
    }

    public void register(String name, SapphireTask sapphireTask) {
        taskMap.put(name, sapphireTask);
    }

}