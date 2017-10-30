package com.sapphire.common.utils;
/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author yunpeng.byp
 * @version $Id: com.sapphire.common.utils.MonitorLogger.java, v 0.1 2017年10月29日 下午6:08 yunpeng.byp Exp $
 */
public class MonitorLogger implements MethodInterceptor {
    private String logName;

    private Logger logger;

    public void init() {
        logger = LoggerFactory.getLogger(logName);
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String method = invocation.getMethod().getDeclaringClass().getSimpleName() + "."
                        + invocation.getMethod().getName();

        long start = System.currentTimeMillis();

        boolean hasError = false;

        try {
            return invocation.proceed();
        } catch (Throwable ex) {
            hasError = true;
            throw ex;
        } finally {
            if (logger.isInfoEnabled()) {
                long elapseTime = System.currentTimeMillis() - start;

                if (hasError) {
                    logger.info(String.format("([%s]:N,%d)", method, elapseTime));
                } else {
                    logger.info(String.format("([%s]:Y,%d)", method, elapseTime));
                }
            }
        }
    }

    /**
     * Setter method for property <tt>logName</tt>.
     *
     * @param logName  value to be assigned to property logName
     */
    public void setLogName(String logName) {
        this.logName = logName;
    }
}