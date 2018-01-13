/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.sapphire.common.dal.stock.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author yunpeng.byp
 * @version $Id: Trade.java, v 0.1 2018年01月08日 下午11:53 yunpeng.byp Exp $
 */
@Entity
@Table(name = Trade.TABLE_NAME)
public class Trade {

    public static final String TABLE_NAME = "TRADE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long               id;

    @Column(name = "STOCK_CODE")
    private String             stockCode;

    @Column(name = "IN_PRICE", precision = 7, scale = 2)
    private double             inPrice;

    @Column(name = "OUT_PRICE", precision = 7, scale = 2)
    private double             outPrice;

    @Column(name = "TOTAL")
    private int                count;

    @Column(name = "STATUS")
    private int                status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOG_IN_DATE")
    private Timestamp          logInDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOG_OUT_DATE")
    private Timestamp          logOutDate;

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id  value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>stockCode</tt>.
     *
     * @return property value of stockCode
     */
    public String getStockCode() {
        return stockCode;
    }

    /**
     * Setter method for property <tt>stockCode</tt>.
     *
     * @param stockCode  value to be assigned to property stockCode
     */
    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    /**
     * Getter method for property <tt>inPrice</tt>.
     *
     * @return property value of inPrice
     */
    public double getInPrice() {
        return inPrice;
    }

    /**
     * Setter method for property <tt>inPrice</tt>.
     *
     * @param inPrice  value to be assigned to property inPrice
     */
    public void setInPrice(double inPrice) {
        this.inPrice = inPrice;
    }

    /**
     * Getter method for property <tt>outPrice</tt>.
     *
     * @return property value of outPrice
     */
    public double getOutPrice() {
        return outPrice;
    }

    /**
     * Setter method for property <tt>outPrice</tt>.
     *
     * @param outPrice  value to be assigned to property outPrice
     */
    public void setOutPrice(double outPrice) {
        this.outPrice = outPrice;
    }

    /**
     * Getter method for property <tt>status</tt>.
     *
     * @return property value of status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Setter method for property <tt>status</tt>.
     *
     * @param status  value to be assigned to property status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Getter method for property <tt>logDate</tt>.
     *
     * @return property value of logDate
     */
    public Timestamp getLogInDate() {
        return logInDate;
    }

    /**
     * Setter method for property <tt>logDate</tt>.
     *
     * @param logInDate  value to be assigned to property logDate
     */
    public void setLogInDate(Timestamp logInDate) {
        this.logInDate = logInDate;
    }

    /**
     * Getter method for property <tt>count</tt>.
     *
     * @return property value of count
     */
    public int getCount() {
        return count;
    }

    /**
     * Setter method for property <tt>count</tt>.
     *
     * @param count  value to be assigned to property count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Getter method for property <tt>logOutDate</tt>.
     *
     * @return property value of logOutDate
     */
    public Timestamp getLogOutDate() {
        return logOutDate;
    }

    /**
     * Setter method for property <tt>logOutDate</tt>.
     *
     * @param logOutDate  value to be assigned to property logOutDate
     */
    public void setLogOutDate(Timestamp logOutDate) {
        this.logOutDate = logOutDate;
    }
}