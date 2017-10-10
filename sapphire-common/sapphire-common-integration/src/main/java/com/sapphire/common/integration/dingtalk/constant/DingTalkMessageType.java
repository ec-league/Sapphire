/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.integration.dingtalk.constant;

/**
 *
 * @author yunpeng.byp
 * @version $Id: DingTalkMessageType.java, v 0.1 2017年10月10日 下午10:18 yunpeng.byp Exp $
 */
public enum DingTalkMessageType {

    /**
     * 链接
     */
    LINK("link"),
    /**
     * 文本
     */
    TEXT("text"),
    /**
     * Markdown
     */
    MARKDOWN("markdown");

    String code;

    DingTalkMessageType(String code) {
        this.code = code;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }
}