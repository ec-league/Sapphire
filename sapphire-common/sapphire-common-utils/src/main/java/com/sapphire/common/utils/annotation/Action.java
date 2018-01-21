/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.sapphire.common.utils.annotation;

import org.springframework.stereotype.Component;

/**
 *
 * @author yunpeng.byp
 * @version $Id: Action.java, v 0.1 2018年01月21日 下午8:50 yunpeng.byp Exp $
 */
@Component
public @interface Action {
    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     * @return the suggested component name, if any
     */
    String value() default "";
}