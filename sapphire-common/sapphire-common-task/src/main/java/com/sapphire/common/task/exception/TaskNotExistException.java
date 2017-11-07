/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.task.exception;

/**
 *
 * @author yunpeng.byp
 * @version $Id: TaskNotExistException.java, v 0.1 2017年11月06日 上午12:09 yunpeng.byp Exp $
 */
public class TaskNotExistException extends TaskException {
    public TaskNotExistException(String taskName) {
        super(String.format("Task Not Exist : %s", taskName));
    }
}