package com.sapphire.common.dal.stock.domain;

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

    @Column(name = "MACD_DESC")
    private String             desc;

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
     * Getter method for property <tt>averageGoldDays</tt>.
     *
     * @return property value of averageGoldDays
     */
    public int getAverageGoldDays() {
        return averageGoldDays;
    }

    /**
     * Setter method for property <tt>averageGoldDays</tt>.
     *
     * @param averageGoldDays  value to be assigned to property averageGoldDays
     */
    public void setAverageGoldDays(int averageGoldDays) {
        this.averageGoldDays = averageGoldDays;
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
     * Getter method for property <tt>lowestMacd</tt>.
     *
     * @return property value of lowestMacd
     */
    public double getLowestMacd() {
        return lowestMacd;
    }

    /**
     * Setter method for property <tt>lowestMacd</tt>.
     *
     * @param lowestMacd  value to be assigned to property lowestMacd
     */
    public void setLowestMacd(double lowestMacd) {
        this.lowestMacd = lowestMacd;
    }

    /**
     * Getter method for property <tt>lastModifyDate</tt>.
     *
     * @return property value of lastModifyDate
     */
    public Timestamp getLastModifyDate() {
        return lastModifyDate;
    }

    /**
     * Setter method for property <tt>lastModifyDate</tt>.
     *
     * @param lastModifyDate  value to be assigned to property lastModifyDate
     */
    public void setLastModifyDate(Timestamp lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    /**
     * Getter method for property <tt>stop</tt>.
     *
     * @return property value of stop
     */
    public boolean isStop() {
        return stop;
    }

    /**
     * Setter method for property <tt>stop</tt>.
     *
     * @param stop  value to be assigned to property stop
     */
    public void setStop(boolean stop) {
        this.stop = stop;
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
     * Getter method for property <tt>desc</tt>.
     *
     * @return property value of desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter method for property <tt>desc</tt>.
     *
     * @param desc  value to be assigned to property desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
