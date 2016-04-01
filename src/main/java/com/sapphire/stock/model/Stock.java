package com.sapphire.stock.model;

import com.sapphire.stock.domain.StockItem;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 2016/3/30.
 */
public class Stock {
   private List<StockItem> stockItems;
   private double averageGoldDays;
   private double increaseTotal;
   private String code;

   public Stock() {
   }

   public Stock(List<StockItem> stockItems) {
      this.stockItems = stockItems;
   }

   public String getCode() {
      return code;
   }

   public void calculateMacd() {
      for (int i = 1; i < stockItems.size(); i++) {
         stockItems.get(i).setEma12(stockItems.get(i - 1).getEma12() * 11 / 13 + stockItems.get(i).getEma12() * 2 / 13);
         stockItems.get(i).setEma26(stockItems.get(i - 1).getEma26() * 11 / 13 + stockItems.get(i).getEma26() * 2 / 13);
         stockItems.get(i).setMacdDiff(stockItems.get(i).getEma12() - stockItems.get(i).getEma26());
         stockItems.get(i).setMacdDea(stockItems.get(i-1).getMacdDea() * 0.8 + stockItems.get(i).getMacdDiff() * 0.2);
         stockItems.get(i).setMacd(stockItems.get(i).getMacdDiff() - stockItems.get(i).getMacdDea());
      }
   }

   private static class StockStatic {
      private Timestamp startDate;
      private Timestamp endDate;
      private boolean isOverZero;
      private double increaseRate;
      private int consistDays;

      public Timestamp getStartDate() {
         return startDate;
      }

      public void setStartDate(Timestamp startDate) {
         this.startDate = startDate;
      }

      public Timestamp getEndDate() {
         return endDate;
      }

      public void setEndDate(Timestamp endDate) {
         this.endDate = endDate;
      }

      public boolean isOverZero() {
         return isOverZero;
      }

      public void setIsOverZero(boolean isOverZero) {
         this.isOverZero = isOverZero;
      }

      public double getIncreaseRate() {
         return increaseRate;
      }

      public void setIncreaseRate(double increaseRate) {
         this.increaseRate = increaseRate;
      }

      public int getConsistDays() {
         return consistDays;
      }

      public void setConsistDays(int consistDays) {
         this.consistDays = consistDays;
      }
   }
}
