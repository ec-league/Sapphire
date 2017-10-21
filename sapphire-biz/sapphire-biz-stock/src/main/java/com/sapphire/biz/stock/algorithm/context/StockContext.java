/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.biz.stock.algorithm.context;

import java.util.List;

import com.sapphire.common.dal.stock.domain.StockStatistics;

/**
 *
 * @author yunpeng.byp
 * @version $Id: StockContext.java, v 0.1 2017年10月21日 下午5:09 yunpeng.byp Exp $
 */
public class StockContext {
    List<StockStatistics> statistics;

    /**
     * Getter method for property <tt>statistics</tt>.
     *
     * @return property value of statistics
     */
    public List<StockStatistics> getStatistics() {
        return statistics;
    }

    /**
     * Setter method for property <tt>statistics</tt>.
     *
     * @param statistics  value to be assigned to property statistics
     */
    public void setStatistics(List<StockStatistics> statistics) {
        this.statistics = statistics;
    }
}