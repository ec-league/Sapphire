package com.sapphire.stock.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * Created by Ethan on 2016/3/30.
 */
@Entity
@Table(name = StockItem.TABLE_NAME)
public class StockItem {
   public static final String TABLE_NAME = "STOCK_ITEM";

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "UIDPK")
   private long uidPk;

   @Basic
   @Column(name = "CODE")
   private String code;

   @Basic
   @Column(name = "NAME")
   private String name;

   @Basic
   @Column(name = "INCREASE_RATE")
   private double increaseRate;

   @Basic
   @Column(name = "INDUSTRY")
   private String industry;

   @Basic
   @Column(name = "START_PRICE")
   private BigDecimal startPrice;

   @Basic
   @Column(name = "END_PRICE")
   private BigDecimal endPrice;

   @Basic
   @Column(name = "HIGHEST_PRICE")
   private BigDecimal highestPrice;

   @Basic
   @Column(name = "LOWEST_PRICE")
   private BigDecimal lowestPrice;

   @Basic
   @Column(name = "CIRCULATION_MARKET_VALUE")
   private BigDecimal circulationMarketValue;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "LOG_DATE")
   private Timestamp date;

   @Basic
   @Column(name = "MACD_DEA")
   private double macdDea;

   @Basic
   @Column(name = "MACD_DIFF")
   private double macdDiff;

   @Basic
   @Column(name = "MACD")
   private double macd;

   @Basic
   @Column(name = "EMA_12")
   private double ema12;

   @Basic
   @Column(name = "EMA_26")
   private double ema26;

   public double getEma12() {
      return ema12;
   }

   public void setEma12(double ema12) {
      this.ema12 = ema12;
   }

   public double getEma26() {
      return ema26;
   }

   public void setEma26(double ema26) {
      this.ema26 = ema26;
   }

   public Timestamp getDate() {
      return date;
   }

   public void setDate(Timestamp date) {
      this.date = date;
   }

   public long getUidPk() {
      return uidPk;
   }

   public void setUidPk(long uidPk) {
      this.uidPk = uidPk;
   }

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

   public double getIncreaseRate() {
      return increaseRate;
   }

   public void setIncreaseRate(double increaseRate) {
      this.increaseRate = increaseRate;
   }

   public String getIndustry() {
      return industry;
   }

   public void setIndustry(String industry) {
      this.industry = industry;
   }

   public BigDecimal getStartPrice() {
      return startPrice;
   }

   public void setStartPrice(BigDecimal startPrice) {
      this.startPrice = startPrice;
   }

   public BigDecimal getEndPrice() {
      return endPrice;
   }

   public void setEndPrice(BigDecimal endPrice) {
      this.endPrice = endPrice;
   }

   public BigDecimal getHighestPrice() {
      return highestPrice;
   }

   public void setHighestPrice(BigDecimal highestPrice) {
      this.highestPrice = highestPrice;
   }

   public BigDecimal getLowestPrice() {
      return lowestPrice;
   }

   public void setLowestPrice(BigDecimal lowestPrice) {
      this.lowestPrice = lowestPrice;
   }

   public double getMacdDea() {
      return macdDea;
   }

   public void setMacdDea(double macdDea) {
      this.macdDea = macdDea;
   }

   public double getMacdDiff() {
      return macdDiff;
   }

   public void setMacdDiff(double macdDiff) {
      this.macdDiff = macdDiff;
   }

   public double getMacd() {
      return macd;
   }

   public void setMacd(double macd) {
      this.macd = macd;
   }

   public BigDecimal getCirculationMarketValue() {
      return circulationMarketValue;
   }

   public void setCirculationMarketValue(BigDecimal circulationMarketValue) {
      this.circulationMarketValue = circulationMarketValue;
   }

   public boolean isGold() {
      return macd >= 0;
   }

   public boolean isDead() {
      return macd < 0;
   }
}
