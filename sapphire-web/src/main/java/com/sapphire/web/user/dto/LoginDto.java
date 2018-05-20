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
    private String currentAuthority;

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
     * Getter method for property <tt>currentAuthority</tt>.
     *
     * @return property value of currentAuthority
     */
    public String getCurrentAuthority() {
        return currentAuthority;
    }

    /**
     * Setter method for property <tt>currentAuthority</tt>.
     *
     * @param currentAuthority  value to be assigned to property currentAuthority
     */
    public void setCurrentAuthority(String currentAuthority) {
        this.currentAuthority = currentAuthority;
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