/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.task.exception;

/**
 *
 * @author yunpeng.byp
 * @version $Id: TaskConfigException.java, v 0.1 2017年11月06日 上午12:12 yunpeng.byp Exp $
 */
public class TaskConfigException extends TaskException {
    public TaskConfigException(String msg) {
        super(msg);
    }

    public TaskConfigException() {
        super("Task Config Not Exist.");
    }

}