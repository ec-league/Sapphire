/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.integration.dingtalk.dto;

import com.google.gson.Gson;

/**
 *
 * @author yunpeng.byp
 * @version $Id: DingTalkResponse.java, v 0.1 2017年10月10日 下午11:34 yunpeng.byp Exp $
 */
public class DingTalkResponse {
    private Integer errcode;
    private String errmsg;

    /**
     * Getter method for property <tt>errcode</tt>.
     *
     * @return property value of errcode
     */
    public Integer getErrcode() {
        return errcode;
    }

    /**
     * Setter method for property <tt>errcode</tt>.
     *
     * @param errcode  value to be assigned to property errcode
     */
    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    /**
     * Getter method for property <tt>errmsg</tt>.
     *
     * @return property value of errmsg
     */
    public String getErrmsg() {
        return errmsg;
    }

    /**
     * Setter method for property <tt>errmsg</tt>.
     *
     * @param errmsg  value to be assigned to property errmsg
     */
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public static DingTalkResponse toResponse(String response) {
        Gson gson = new Gson();
        return gson.fromJson(response, DingTalkResponse.class);
    }
}