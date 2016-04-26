package com.sapphire.stock.domain;

import java.sql.Timestamp;

import javax.persistence.*;

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
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "UIDPK")
   private long uidPk;

   @Basic
   @Column(name = "CODE")
   private String code;

   @Column(name = "AVERAGE_GOLD_DAYS")
   private int averageGoldDays;

   @Column(name = "INCREASE_TOTAL", precision = 7, scale = 2)
   private double increaseTotal;

   @Column(name = "HIGHEST_PRICE", precision = 7, scale = 2)
   private double highestPrice;

   @Column(name = "LOWEST_MACD", precision = 11, scale = 5)
   private double lowestMacd;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "LAST_MODIFY_DATE")
   private Timestamp lastModifyDate;

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
