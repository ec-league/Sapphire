/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.sapphire.web.user.dto;

import com.sapphire.common.utils.dto.Dto;

/**
 *
 * @author yunpeng.byp
 * @version $Id: LoginDto.java, v 0.1 2018年02月04日 下午6:11 yunpeng.byp Exp $
 */
public class LoginDto implements Dto {
    private String status;
    private String type;
    private String currentAuthorith;

    /**
     * Getter method for property <tt>status</tt>.
     *
     * @return property value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter method for property <tt>status</tt>.
     *
     * @param status  value to be assigned to property status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter method for property <tt>currentAuthorith</tt>.
     *
     * @return property value of currentAuthorith
     */
    public String getCurrentAuthorith() {
        return currentAuthorith;
    }

    /**
     * Setter method for property <tt>currentAuthorith</tt>.
     *
     * @param currentAuthorith  value to be assigned to property currentAuthorith
     */
    public void setCurrentAuthorith(String currentAuthorith) {
        this.currentAuthorith = currentAuthorith;
    }

    /**
     * Getter method for property <tt>type</tt>.
     *
     * @return property value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     *
     * @param type  value to be assigned to property type
     */
    public void setType(String type) {
        this.type = type;
    }
}