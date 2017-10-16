/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.web.stock.dto;

import com.sapphire.common.utils.dto.Dto;

/**
 *
 * @author yunpeng.byp
 * @version $Id: StockDto.java, v 0.1 2017年10月15日 下午3:52 yunpeng.byp Exp $
 */
public class StockDto implements Dto {
    private String code;
    private String name;
    private boolean goldPossible;
    private double currentMacd;
    private double endPrice;
    private double highestPrice;
    private double increaseTotal;
    private double currentDiff;
    private double averageGoldDays;

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter method for property <tt>code</tt>.
     *
     * @param code  value to be assigned to property code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name  value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>goldPossible</tt>.
     *
     * @return property value of goldPossible
     */
    public boolean isGoldPossible() {
        return goldPossible;
    }

    /**
     * Setter method for property <tt>goldPossible</tt>.
     *
     * @param goldPossible  value to be assigned to property goldPossible
     */
    public void setGoldPossible(boolean goldPossible) {
        this.goldPossible = goldPossible;
    }

    /**
     * Getter method for property <tt>currentMacd</tt>.
     *
     * @return property value of currentMacd
     */
    public double getCurrentMacd() {
        return currentMacd;
    }

    /**
     * Setter method for property <tt>currentMacd</tt>.
     *
     * @param currentMacd  value to be assigned to property currentMacd
     */
    public void setCurrentMacd(double currentMacd) {
        this.currentMacd = currentMacd;
    }

    /**
     * Getter method for property <tt>endPrice</tt>.
     *
     * @return property value of endPrice
     */
    public double getEndPrice() {
        return endPrice;
    }

    /**
     * Setter method for property <tt>endPrice</tt>.
     *
     * @param endPrice  value to be assigned to property endPrice
     */
    public void setEndPrice(double endPrice) {
        this.endPrice = endPrice;
    }

    /**
     * Getter method for property <tt>highestPrice</tt>.
     *
     * @return property value of highestPrice
     */
    public double getHighestPrice() {
        return highestPrice;
    }

    /**
     * Setter method for property <tt>highestPrice</tt>.
     *
     * @param highestPrice  value to be assigned to property highestPrice
     */
    public void setHighestPrice(double highestPrice) {
        this.highestPrice = highestPrice;
    }

    /**
     * Getter method for property <tt>increaseTotal</tt>.
     *
     * @return property value of increaseTotal
     */
    public double getIncreaseTotal() {
        return increaseTotal;
    }

    /**
     * Setter method for property <tt>increaseTotal</tt>.
     *
     * @param increaseTotal  value to be assigned to property increaseTotal
     */
    public void setIncreaseTotal(double increaseTotal) {
        this.increaseTotal = increaseTotal;
    }

    /**
     * Getter method for property <tt>currentDiff</tt>.
     *
     * @return property value of currentDiff
     */
    public double getCurrentDiff() {
        return currentDiff;
    }

    /**
     * Setter method for property <tt>currentDiff</tt>.
     *
     * @param currentDiff  value to be assigned to property currentDiff
     */
    public void setCurrentDiff(double currentDiff) {
        this.currentDiff = currentDiff;
    }

    /**
     * Getter method for property <tt>averageGoldDays</tt>.
     *
     * @return property value of averageGoldDays
     */
    public double getAverageGoldDays() {
        return averageGoldDays;
    }

    /**
     * Setter method for property <tt>averageGoldDays</tt>.
     *
     * @param averageGoldDays  value to be assigned to property averageGoldDays
     */
    public void setAverageGoldDays(double averageGoldDays) {
        this.averageGoldDays = averageGoldDays;
    }
}