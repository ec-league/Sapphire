/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.sapphire.biz.stock.algorithm.exec;

import java.util.List;

/**
 *
 * @author yunpeng.byp
 * @version $Id: ActionBuilder.java, v 0.1 2018年01月22日 上午12:07 yunpeng.byp Exp $
 */
public class ActionBuilder {

    private List<String> shouldBuyList;

    private List<String> shouldSellList;

    /**
     * Getter method for property <tt>shouldBuyList</tt>.
     *
     * @return property value of shouldBuyList
     */
    public List<String> getShouldBuyList() {
        return shouldBuyList;
    }

    /**
     * Setter method for property <tt>shouldBuyList</tt>.
     *
     * @param shouldBuyList  value to be assigned to property shouldBuyList
     */
    public void setShouldBuyList(List<String> shouldBuyList) {
        this.shouldBuyList = shouldBuyList;
    }

    /**
     * Getter method for property <tt>shouldSellList</tt>.
     *
     * @return property value of shouldSellList
     */
    public List<String> getShouldSellList() {
        return shouldSellList;
    }

    /**
     * Setter method for property <tt>shouldSellList</tt>.
     *
     * @param shouldSellList  value to be assigned to property shouldSellList
     */
    public void setShouldSellList(List<String> shouldSellList) {
        this.shouldSellList = shouldSellList;
    }
}