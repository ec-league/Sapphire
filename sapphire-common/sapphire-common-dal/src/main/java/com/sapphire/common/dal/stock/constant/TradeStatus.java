/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.sapphire.common.dal.stock.constant;

/**
 *
 * @author yunpeng.byp
 * @version $Id: TradeStatus.java, v 0.1 2018年01月09日 上午12:07 yunpeng.byp Exp $
 */
public enum TradeStatus {
    HOLD("持有中", 1),
    SOLD("卖出", 2),;

    private String desc;
    private int status;

    TradeStatus(String desc, int status) {
        this.desc = desc;
        this.status = status;
    }

    /**
     * Getter method for property <tt>desc</tt>.
     *
     * @return property value of desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Getter method for property <tt>status</tt>.
     *
     * @return property value of status
     */
    public int getStatus() {
        return status;
    }
}