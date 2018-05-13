/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.web.stock.dto;

import com.sapphire.common.dal.stock.domain.MacdCycle;
import com.sapphire.common.utils.dto.Dto;

/**
 *
 * @author yunpeng.byp
 * @version $Id: MacdCycleDto.java, v 0.1 2017年11月04日 下午3:53 yunpeng.byp Exp $
 */
public class MacdCycleDto implements Dto {
    private String startDate;
    private String endDate;
    /**
     * Macd单个周期增幅(收盘价/第一天收盘价)
     */
    private double increase;
    /**
     * Macd单个周期最大增幅(最高价/第一天收盘价)
     */
    private double highIncrease;

    /**
     * Macd周期的第一次Diff的值.(用于持久化)
     */
    private double diff;

    private int    consistDays;

    public MacdCycleDto(MacdCycle cycle) {
        this.startDate = cycle.getStartDate();
        this.endDate = cycle.getEndDate();
        this.increase = cycle.getIncreaseRate();
        this.highIncrease = cycle.getHighIncreaseRate();
        this.consistDays = cycle.getConsistDays();
        this.diff = cycle.getFirstDiff();
    }

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
     * Getter method for property <tt>increase</tt>.
     *
     * @return property value of increase
     */
    public double getIncrease() {
        return increase;
    }

    /**
     * Setter method for property <tt>increase</tt>.
     *
     * @param increase  value to be assigned to property increase
     */
    public void setIncrease(double increase) {
        this.increase = increase;
    }

    /**
     * Getter method for property <tt>highIncrease</tt>.
     *
     * @return property value of highIncrease
     */
    public double getHighIncrease() {
        return highIncrease;
    }

    /**
     * Setter method for property <tt>highIncrease</tt>.
     *
     * @param highIncrease  value to be assigned to property highIncrease
     */
    public void setHighIncrease(double highIncrease) {
        this.highIncrease = highIncrease;
    }

    /**
     * Getter method for property <tt>diff</tt>.
     *
     * @return property value of diff
     */
    public double getDiff() {
        return diff;
    }

    /**
     * Setter method for property <tt>diff</tt>.
     *
     * @param diff  value to be assigned to property diff
     */
    public void setDiff(double diff) {
        this.diff = diff;
    }
}