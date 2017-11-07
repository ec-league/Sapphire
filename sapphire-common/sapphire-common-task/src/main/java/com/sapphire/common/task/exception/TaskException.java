/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.task.exception;

/**
 *
 * @author yunpeng.byp
 * @version $Id: TaskException.java, v 0.1 2017年11月06日 上午12:08 yunpeng.byp Exp $
 */
public class TaskException extends RuntimeException {
    TaskException(String msg) {
        super(msg);
    }
}