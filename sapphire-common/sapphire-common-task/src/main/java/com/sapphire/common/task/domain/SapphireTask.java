/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.task.domain;

/**
 *
 * @author yunpeng.byp
 * @version $Id: SapphireTask.java, v 0.1 2017年10月18日 下午6:10 yunpeng.byp Exp $
 */
public interface SapphireTask {

    /**
     * 调度任务的具体执行.
     */
    void execute();
}