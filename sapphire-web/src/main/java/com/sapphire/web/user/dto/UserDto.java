/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.sapphire.web.user.dto;

import com.sapphire.common.utils.dto.Dto;

/**
 *
 * @author 三皇
 * @version $Id: UserDto.java, v 0.1 2018年05月13日 下午11:51 yunpeng.byp Exp $
 */
public class UserDto implements Dto {
    private String name;
    private String uid;

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name  value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>uid</tt>.
     *
     * @return property value of uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * Setter method for property <tt>uid</tt>.
     *
     * @param uid  value to be assigned to property uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }
}