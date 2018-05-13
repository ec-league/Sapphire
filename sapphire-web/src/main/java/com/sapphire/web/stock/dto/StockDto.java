/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.web.stock.dto;

import com.sapphire.common.dal.stock.domain.StockStatistics;
import com.sapphire.common.utils.dto.Dto;

/**
 *
 * @author yunpeng.byp
 * @version $Id: StockDto.java, v 0.1 2017年10月15日 下午3:52 yunpeng.byp Exp $
 */
public class StockDto implements Dto {

    public StockDto() {

    }

    public StockDto(StockStatistics statistics) {
        this.code = statistics.getCode();
        this.name = statistics.getName();
        this.goldPossible = statistics.isGoldPossible();
        this.currentDiff = statistics.getCurrentDiff();
        this.currentMacd = statistics.getCurrentMacd();
        this.currentPrice = statistics.getCurrentPrice();
        this.highestPrice = statistics.getHighestPrice();
        this.increaseTotal = (statistics.getIncreaseTotal() - 1) * 100;
        this.averageGoldDays = statistics.getAverageGoldDays();
        this.rate = statistics.getCurrentPrice() / statistics.getHighestPrice() * 100;
    }

    private String  code;
    private String  name;
    private boolean goldPossible;
    private double  currentMacd;
    private double  currentPrice;
    private double  highestPrice;
    private double  increaseTotal;
    private double  currentDiff;
    private double  averageGoldDays;
    private double  rate;

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

    /**
     * Getter method for property <tt>currentPrice</tt>.
     *
     * @return property value of currentPrice
     */
    public double getCurrentPrice() {
        return currentPrice;
    }

    /**
     * Setter method for property <tt>currentPrice</tt>.
     *
     * @param currentPrice  value to be assigned to property currentPrice
     */
    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
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
     * Getter method for property <tt>rate</tt>.
     *
     * @return property value of rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * Setter method for property <tt>rate</tt>.
     *
     * @param rate  value to be assigned to property rate
     */
    public void setRate(double rate) {
        this.rate = rate;
    }
}