/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.integration.dingtalk.dto;

import com.google.gson.Gson;

/**
 *
 * @author yunpeng.byp
 * @version $Id: DingTalkMessage.java, v 0.1 2017年10月10日 下午10:57 yunpeng.byp Exp $
 */
public class DingTalkMessage {
    private String msgtype;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * Getter method for property <tt>msgtype</tt>.
     *
     * @return property value of msgtype
     */
    public String getMsgtype() {
        return msgtype;
    }

    /**
     * Setter method for property <tt>msgtype</tt>.
     *
     * @param msgtype  value to be assigned to property msgtype
     */
    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }
}