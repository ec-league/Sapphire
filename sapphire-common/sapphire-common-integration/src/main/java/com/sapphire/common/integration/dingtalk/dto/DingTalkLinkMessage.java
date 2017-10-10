/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.integration.dingtalk.dto;

import com.sapphire.common.integration.dingtalk.constant.DingTalkMessageType;

import java.util.Map;

/**
 *
 * @author yunpeng.byp
 * @version $Id: DingTalkTxtMessage.java, v 0.1 2017年10月10日 下午10:59 yunpeng.byp Exp $
 */
public class DingTalkLinkMessage extends DingTalkMessage {

    private Map<String, String> link;


    public DingTalkLinkMessage() {
        setMsgtype(DingTalkMessageType.TEXT.getCode());
    }

    /**
     * Getter method for property <tt>link</tt>.
     *
     * @return property value of link
     */
    public Map<String, String> getLink() {
        return link;
    }

    /**
     * Setter method for property <tt>link</tt>.
     *
     * @param link  value to be assigned to property link
     */
    public void setLink(Map<String, String> link) {
        this.link = link;
    }
}