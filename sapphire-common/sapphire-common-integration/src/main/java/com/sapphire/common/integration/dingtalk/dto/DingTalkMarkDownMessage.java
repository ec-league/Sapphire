/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.integration.dingtalk.dto;

import java.util.HashMap;
import java.util.Map;

import com.sapphire.common.integration.dingtalk.constant.DingTalkMessageType;

/**
 *
 * @author yunpeng.byp
 * @version $Id: DingTalkTxtMessage.java, v 0.1 2017年10月10日 下午10:59 yunpeng.byp Exp $
 */
public class DingTalkMarkDownMessage extends DingTalkMessage {

    private Map<String, String> markdown;

    public DingTalkMarkDownMessage() {
        setMsgtype(DingTalkMessageType.MARKDOWN.getCode());
        markdown = new HashMap<>();
    }

    public DingTalkMarkDownMessage(String topic, String msg) {
        this();
        markdown.put("title", topic);
        markdown.put("text", msg);
    }

    /**
     * Getter method for property <tt>markdown</tt>.
     *
     * @return property value of markdown
     */
    public Map<String, String> getMarkdown() {
        return markdown;
    }

    /**
     * Setter method for property <tt>markdown</tt>.
     *
     * @param markdown  value to be assigned to property markdown
     */
    public void setMarkdown(Map<String, String> markdown) {
        this.markdown = markdown;
    }
}