package com.sapphire.stock.domain;

import com.sapphire.common.utils.dto.Dto;

/**
 * Author: EthanPark <br/>
 * Date: 2017/1/5<br/>
 * Email: byp5303628@hotmail.com
 */
public class HistoryInfo implements Dto {
    /**
    * 股票代码
    */
    private String code;

    /**
    * 股票名字
    */
    private String name;

    /**
    * 平均金叉时间
    */
    private int    averageGoldDays;

    /**
    * 最近250个交易日
    */
    private double increaseTotal;

    /**
    * 最近250个交易日内最高价
    */
    private double highestPrice;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAverageGoldDays() {
        return averageGoldDays;
    }

    public void setAverageGoldDays(int averageGoldDays) {
        this.averageGoldDays = averageGoldDays;
    }

    public double getIncreaseTotal() {
        return increaseTotal;
    }

    public void setIncreaseTotal(double increaseTotal) {
        this.increaseTotal = increaseTotal;
    }

    public double getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(double highestPrice) {
        this.highestPrice = highestPrice;
    }
}
