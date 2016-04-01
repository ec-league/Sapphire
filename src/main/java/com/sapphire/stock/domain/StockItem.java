package com.sapphire.stock.domain;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.*;

/**
 * Created by Ethan on 2016/3/30.
 */
@Entity
@Table(name = StockItem.TABLE_NAME)
public class StockItem {
   public static final String TABLE_NAME = "STOCK_ITEM";
   private static final String FORMAT = "MM/dd/yyyy";

   public StockItem() {

   }

   public StockItem(String line) {
      String[] lines = line.split("\t");

      try {
         setDate(new Timestamp(new SimpleDateFormat(FORMAT).parse(lines[0])
               .getTime()));
      } catch (ParseException e) {
         e.printStackTrace();
      }
      setStartPrice(Double.parseDouble(lines[1]));
      setHighestPrice(Double.parseDouble(lines[2]));
      setLowestPrice(Double.parseDouble(lines[3]));
      setEndPrice(Double.parseDouble(lines[4]));
      setTrading(Double.parseDouble(lines[5]));
      setTradingValue(Double.parseDouble(lines[6]));

      setEma12(getEndPrice());
      setEma26(getEndPrice());
      setIncreaseRate((endPrice / startPrice - 1) * 100);
      setIndustry("");
   }

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

   @Column(name = "INCREASE_RATE", precision = 7, scale = 2)
   private double increaseRate;

   @Basic
   @Column(name = "INDUSTRY")
   private String industry;

   @Column(name = "START_PRICE", precision = 7, scale = 2)
   private double startPrice;

   @Column(name = "END_PRICE", precision = 7, scale = 2)
   private double endPrice;

   @Column(name = "HIGHEST_PRICE", precision = 7, scale = 2)
   private double highestPrice;

   @Column(name = "LOWEST_PRICE", precision = 7, scale = 2)
   private double lowestPrice;

   @Column(name = "CIRCULATION_MARKET_VALUE", precision = 40, scale = 2)
   private double circulationMarketValue;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "LOG_DATE")
   private Timestamp date;

   @Column(name = "TRADING", precision = 40, scale = 2)
   private double trading;

   @Column(name = "TRADING_VALUE", precision = 40, scale = 3)
   private double tradingValue;

   @Column(name = "MACD_DEA", precision = 11, scale = 6)
   private double macdDea;

   @Column(name = "MACD_DIFF", precision = 11, scale = 6)
   private double macdDiff;

   @Column(name = "MACD", precision = 11, scale = 6)
   private double macd;

   @Column(name = "EMA_12", precision = 11, scale = 6)
   private double ema12;

   @Column(name = "EMA_26", precision = 11, scale = 6)
   private double ema26;

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

   public double getStartPrice() {
      return startPrice;
   }

   public void setStartPrice(double startPrice) {
      this.startPrice = startPrice;
   }

   public double getEndPrice() {
      return endPrice;
   }

   public void setEndPrice(double endPrice) {
      this.endPrice = endPrice;
   }

   public double getHighestPrice() {
      return highestPrice;
   }

   public void setHighestPrice(double highestPrice) {
      this.highestPrice = highestPrice;
   }

   public double getLowestPrice() {
      return lowestPrice;
   }

   public void setLowestPrice(double lowestPrice) {
      this.lowestPrice = lowestPrice;
   }

   public double getCirculationMarketValue() {
      return circulationMarketValue;
   }

   public void setCirculationMarketValue(double circulationMarketValue) {
      this.circulationMarketValue = circulationMarketValue;
   }

   public Timestamp getDate() {
      return date;
   }

   public void setDate(Timestamp date) {
      this.date = date;
   }

   public double getTrading() {
      return trading;
   }

   public void setTrading(double trading) {
      this.trading = trading;
   }

   public double getTradingValue() {
      return tradingValue;
   }

   public void setTradingValue(double tradingValue) {
      this.tradingValue = tradingValue;
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
}
