package com.sapphire.stock.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Author: EthanPark <br/>
 * Date: 2016/4/22<br/>
 * Email: byp5303628@hotmail.com
 */
@Entity
@Table(name = StockStatistics.TABLE_NAME)
public class StockStatistics {
    public static final String TABLE_NAME = "STOCK_STATISTICS";

    @Id
    @Column(name = "CODE")
    private String             code;

    @Column(name = "NAME")
    private String             name;

    @Column(name = "AVERAGE_GOLD_DAYS")
    private int                averageGoldDays;

    @Column(name = "INCREASE_TOTAL", precision = 7, scale = 2)
    private double             increaseTotal;

    @Column(name = "HIGHEST_PRICE", precision = 7, scale = 2)
    private double             highestPrice;

    @Column(name = "LOWEST_MACD", precision = 11, scale = 5)
    private double             lowestMacd;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_MODIFY_DATE")
    private Timestamp          lastModifyDate;

    @Column(name = "IS_STOP")
    private boolean            stop;

    @Column(name = "IS_GOLD_POSSIBLE")
    private boolean            goldPossible;

    @Column(name = "CURRENT_MACD", precision = 11, scale = 6)
    private double             currentMacd;

    @Column(name = "CURRENT_PRICE", precision = 7, scale = 2)
    private double             currentPrice;

    @Column(name = "CURRENT_DIFF", precision = 11, scale = 6)
    private double             currentDiff;

    public double getCurrentDiff() {
        return currentDiff;
    }

    public void setCurrentDiff(double currentDiff) {
        this.currentDiff = currentDiff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean isGoldPossible() {
        return goldPossible;
    }

    public void setGoldPossible(boolean goldPossible) {
        this.goldPossible = goldPossible;
    }

    public double getCurrentMacd() {
        return currentMacd;
    }

    public void setCurrentMacd(double currentMacd) {
        this.currentMacd = currentMacd;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Timestamp getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Timestamp lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public double getLowestMacd() {
        return lowestMacd;
    }

    public void setLowestMacd(double lowestMacd) {
        this.lowestMacd = lowestMacd;
    }
}
