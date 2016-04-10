package com.sapphire.stock.domain;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
   private double firstDiff;
   private double averageIncreaseRate;
   private boolean shouldPass = false;
   private double currentMacd;

   public Stock() {
   }

   public Stock(List<StockItem> stockItems) {
      this.stockItems = stockItems;
      if (!stockItems.isEmpty()) {
         code = stockItems.get(0).getCode();
         currentMacd = stockItems.get(stockItems.size() - 1).getMacd();
      }
   }

   public String getCode() {
      return code;
   }

   public void calculateMacd(int small, int big) {
      for (int i = 1; i < stockItems.size(); i++) {
         stockItems.get(i).setEma12(
               stockItems.get(i - 1).getEma12() * (small - 1) / (small + 1)
                     + stockItems.get(i).getEma12() * 2 / (small + 1));
         stockItems.get(i).setEma26(
               stockItems.get(i - 1).getEma26() * (big - 1) / (big + 1)
                     + stockItems.get(i).getEma26() * 2 / (big + 1));
         stockItems.get(i).setMacdDiff(
               stockItems.get(i).getEma12() - stockItems.get(i).getEma26());

         stockItems.get(i).setMacdDea(
               stockItems.get(i - 1).getMacdDea() * 0.8
                     + stockItems.get(i).getMacdDiff() * 0.2);
         stockItems.get(i)
               .setMacd(
                     stockItems.get(i).getMacdDiff()
                           - stockItems.get(i).getMacdDea());
      }
   }

   public void calcStatics() {
      List<StockStatic> statics = group(stockItems);

      if (statics.isEmpty()) {
         shouldPass = true;
         return;
      }
      int count = 0;
      int plusCount = 0;
      increaseTotal = 1;
      for (StockStatic stat : statics) {
         increaseTotal *= 1 + stat.getIncreaseRate() / 100;
         count++;
         if (stat.getIncreaseRate() > 0) {
            plusCount++;
         }
      }

      averageIncreaseRate = plusCount * 1.0 / count;
   }

   public int macdPlusCount(String dateFrom, String dateTo)
         throws ParseException {
      List<StockStatic> statics = calcStatics(dateFrom, dateTo);
      int count = 0;
      for (StockStatic stockStatic : statics) {
         if (stockStatic.increaseRate > 0) {
            count++;
         }
      }

      return count;
   }

   public void process() {
      List<StockStatic> statics = group(stockItems);

      double origin = 1.0;

      for (StockStatic stockStatic : statics) {
         origin *= 1 + stockStatic.increaseRate / 100;
      }
      setIncreaseTotal(origin);
      if (statics.isEmpty())
         return;
      setFirstDiff(statics.get(0).getFirstDiff());
   }

   public int macdTotalCount(String dateFrom, String dateTo)
         throws ParseException {
      return calcStatics(dateFrom, dateTo).size();
   }

   private List<StockStatic> calcStatics(String dateFrom, String dateTo)
         throws ParseException {
      List<StockItem> items = new ArrayList<>();

      long from = new SimpleDateFormat("MM/dd/yyyy").parse(dateFrom).getTime();
      long to = new SimpleDateFormat("MM/dd/yyyy").parse(dateTo).getTime();

      for (StockItem item : stockItems) {
         long time = item.getDate().getTime();
         if (from <= time && time <= to) {
            items.add(item);
         }
      }

      return group(items);
   }

   private static List<StockStatic> group(List<StockItem> items) {
      List<List<StockItem>> complexItems = new ArrayList<>();

      List<StockItem> tempList = new ArrayList<>();
      for (StockItem item : items) {
         if (item.getMacd() > 0) {
            tempList.add(item);
         } else {
            if (tempList.isEmpty()) {
               continue;
            }
            complexItems.add(tempList);
            tempList = new ArrayList<>();
         }
      }

      if (!tempList.isEmpty()) {
         complexItems.add(tempList);
      }

      if (!complexItems.isEmpty()) {
         if (complexItems.get(0).get(0).getDate()
               .equals(items.get(0).getDate())) {
            complexItems = complexItems.subList(1, complexItems.size());
         }
      }

      List<StockStatic> statics = new ArrayList<>();
      for (List<StockItem> list : complexItems) {
         StockStatic stockStatic = new StockStatic();
         StockItem start = list.get(0);
         StockItem end = list.get(list.size() - 1);

         stockStatic.setStartDate(start.getDate());
         stockStatic.setEndDate(end.getDate());
         stockStatic.setIsOverZero(start.getMacdDiff() > 0);
         stockStatic.setConsistDays(list.size());
         stockStatic.setFirstDiff(start.getMacdDiff());
         stockStatic
               .setIncreaseRate((end.getEndPrice() / start.getEndPrice() - 1) * 100);
         statics.add(stockStatic);
      }

      return statics;
   }

   public double getAverageIncreaseRate() {
      return averageIncreaseRate;
   }

   public boolean isShouldPass() {
      return shouldPass;
   }

   public double getIncreaseTotal() {
      return increaseTotal;
   }

   public void setIncreaseTotal(double increaseTotal) {
      this.increaseTotal = increaseTotal;
   }

   public double getFirstDiff() {
      return firstDiff;
   }

   public void setFirstDiff(double firstDiff) {
      this.firstDiff = firstDiff;
   }

   public double getCurrentMacd() {
      return currentMacd;
   }

   private static class StockStatic {
      private Timestamp startDate;
      private Timestamp endDate;
      private boolean isOverZero;
      private double increaseRate;
      private int consistDays;
      private double firstDiff;

      public double getFirstDiff() {
         return firstDiff;
      }

      public void setFirstDiff(double firstDiff) {
         this.firstDiff = firstDiff;
      }

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
