/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.integration.dingtalk.dto;

import com.sapphire.common.integration.dingtalk.constant.DingTalkMessageType;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author yunpeng.byp
 * @version $Id: DingTalkTxtMessage.java, v 0.1 2017年10月10日 下午10:59 yunpeng.byp Exp $
 */
public class DingTalkTxtMessage extends DingTalkMessage {

    private Map<String, String> text;

    public DingTalkTxtMessage(String msg) {
        setMsgtype(DingTalkMessageType.TEXT.getCode());
        text = new HashMap<>();
        text.put("content", msg);
    }

    /**
     * Getter method for property <tt>text</tt>.
     *
     * @return property value of text
     */
    public Map<String, String> getText() {
        return text;
    }

    /**
     * Setter method for property <tt>text</tt>.
     *
     * @param text  value to be assigned to property text
     */
    public void setText(Map<String, String> text) {
        this.text = text;
    }
}