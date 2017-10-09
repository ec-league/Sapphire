package com.sapphire.biz.stock.domain;

import com.sapphire.common.utils.dto.Dto;

/**
 * Author: EthanPark <br/>
 * Date: 2017/1/5<br/>
 * Email: byp5303628@hotmail.com
 */
public class CurrentInfo implements Dto {
    /**
    * 当前价格
    */
    private double price;

    /**
    * 当前Diff值
    */
    private double diff;

    /**
    * 当前MACD值
    */
    private double macd;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiff() {
        return diff;
    }

    public void setDiff(double diff) {
        this.diff = diff;
    }

    public double getMacd() {
        return macd;
    }

    public void setMacd(double macd) {
        this.macd = macd;
    }
}
