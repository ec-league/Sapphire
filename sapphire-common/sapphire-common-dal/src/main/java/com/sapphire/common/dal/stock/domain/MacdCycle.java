/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.dal.stock.domain;

/**
 *
 * Macd一个周期的统计数据(金叉)
 * @author yunpeng.byp
 * @version $Id: MacdCycle.java, v 0.1 2017年10月19日 下午11:28 yunpeng.byp Exp $
 */
public class MacdCycle {
    private String  startDate;
    private String  endDate;
    private boolean overZero;

    /**
     * Macd单个周期增幅(收盘价/第一天收盘价)
     */
    private double  increaseRate;
    /**
     * Macd单个周期最大增幅(最高价/第一天收盘价)
     */
    private double  highIncreaseRate;

    private int     consistDays;

    /**
     * Macd周期的第一次Diff的值.(用于计算)
     */
    private double  firstDiff;

    /**
     * Getter method for property <tt>startDate</tt>.
     *
     * @return property value of startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Setter method for property <tt>startDate</tt>.
     *
     * @param startDate  value to be assigned to property startDate
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter method for property <tt>endDate</tt>.
     *
     * @return property value of endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Setter method for property <tt>endDate</tt>.
     *
     * @param endDate  value to be assigned to property endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Getter method for property <tt>isOverZero</tt>.
     *
     * @return property value of isOverZero
     */
    public boolean isOverZero() {
        return overZero;
    }

    /**
     * Setter method for property <tt>isOverZero</tt>.
     *
     * @param overZero  value to be assigned to property isOverZero
     */
    public void setOverZero(boolean overZero) {
        this.overZero = overZero;
    }

    /**
     * Getter method for property <tt>increaseRate</tt>.
     *
     * @return property value of increaseRate
     */
    public double getIncreaseRate() {
        return increaseRate;
    }

    /**
     * Setter method for property <tt>increaseRate</tt>.
     *
     * @param increaseRate  value to be assigned to property increaseRate
     */
    public void setIncreaseRate(double increaseRate) {
        this.increaseRate = increaseRate;
    }

    /**
     * Getter method for property <tt>consistDays</tt>.
     *
     * @return property value of consistDays
     */
    public int getConsistDays() {
        return consistDays;
    }

    /**
     * Setter method for property <tt>consistDays</tt>.
     *
     * @param consistDays  value to be assigned to property consistDays
     */
    public void setConsistDays(int consistDays) {
        this.consistDays = consistDays;
    }

    /**
     * Getter method for property <tt>firstDiff</tt>.
     *
     * @return property value of firstDiff
     */
    public double getFirstDiff() {
        return firstDiff;
    }

    /**
     * Setter method for property <tt>firstDiff</tt>.
     *
     * @param firstDiff  value to be assigned to property firstDiff
     */
    public void setFirstDiff(double firstDiff) {
        this.firstDiff = firstDiff;
    }

    /**
     * Getter method for property <tt>highIncreaseRate</tt>.
     *
     * @return property value of highIncreaseRate
     */
    public double getHighIncreaseRate() {
        return highIncreaseRate;
    }

    /**
     * Setter method for property <tt>highIncreaseRate</tt>.
     *
     * @param highIncreaseRate  value to be assigned to property highIncreaseRate
     */
    public void setHighIncreaseRate(double highIncreaseRate) {
        this.highIncreaseRate = highIncreaseRate;
    }
}