/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.dal.stock.domain;

/**
 *
 * @author yunpeng.byp
 * @version $Id: MacdRiskModel.java, v 0.1 2017年10月19日 下午11:23 yunpeng.byp Exp $
 */
public class MacdRiskModel {

    /**
     * 每个MACD周期的平均增幅
     */
    private double averageRate;

    /**
     * 当前价格和上个统计周期内的最高价的百分比
     */
    private double pricePercentage;

    /**
     * Getter method for property <tt>averageRate</tt>.
     *
     * @return property value of averageRate
     */
    public double getAverageRate() {
        return averageRate;
    }

    /**
     * Setter method for property <tt>averageRate</tt>.
     *
     * @param averageRate  value to be assigned to property averageRate
     */
    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    /**
     * Getter method for property <tt>pricePercentage</tt>.
     *
     * @return property value of pricePercentage
     */
    public double getPricePercentage() {
        return pricePercentage;
    }

    /**
     * Setter method for property <tt>pricePercentage</tt>.
     *
     * @param pricePercentage  value to be assigned to property pricePercentage
     */
    public void setPricePercentage(double pricePercentage) {
        this.pricePercentage = pricePercentage;
    }
}