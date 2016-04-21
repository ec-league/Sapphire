package com.sapphire.stock.domain;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.sapphire.common.dto.Dto;


/**
 * Created by Ethan on 2016/3/30.
 */
public class Stock implements Dto {
   private List<StockItem> stockItems;
   private double averageGoldDays;
   private double increaseTotal;
   private double highestPrice;
   private double endPrice;
   private String name;

   /**
    * 股票代码
    */
   private String code;
   private double firstDiff;
   private double averageIncreaseRate;
   private boolean shouldPass = false;
   /**
    * 最后一天的股票macd值
    */
   private double currentMacd;

   /**
    * 最后一天的股票macd diff值
    */
   private double currentDiff;

   /**
    * 股票统计数据
    */
   private List<StockStatic> statics;

   private boolean stop;

   public Stock() {
   }

   public Stock(List<StockItem> stockItems) {
      update(stockItems);
   }

   private void init() {
      StockItem item = this.stockItems.get(0);
      item.setEma12(item.getEndPrice());
      item.setEma26(item.getEndPrice());
   }

   public String getCode() {
      return code;
   }

   public void calculateMacd(int small, int big) {
      init();
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

   public boolean isTodayPlus() {
      int size = stockItems.size();
      if (size >= 2) {
         return stockItems.get(size - 2).getMacd() < 0 && currentMacd >= 0;
      }
      return false;
   }

   public void update(List<StockItem> items) {
      stockItems = items;
      code = stockItems.get(0).getCode();
      name = stockItems.get(0).getName();
      StockItem last = stockItems.get(stockItems.size() - 1);
      endPrice = last.getEndPrice();
      currentMacd = last.getMacd();
      currentDiff = last.getMacdDiff();

      stop =
            Double.compare(stockItems.get(stockItems.size() - 1).getTrading(),
                  0) == 0;
      double temp = 0;
      for (StockItem item : stockItems) {
         if (item.getHighestPrice() > temp) {
            temp = item.getHighestPrice();
         }
      }
      highestPrice = temp;
   }

   public boolean isUpper() {
      int size = stockItems.size();
      if (size < 3)
         return false;
      StockItem beforeYesterday = stockItems.get(size - 3);
      StockItem yesterday = stockItems.get(size - 2);
      StockItem today = stockItems.get(size - 1);

      return Double.compare(beforeYesterday.getMacd(), yesterday.getMacd()) < 0
            && Double.compare(yesterday.getMacd(), today.getMacd()) < 0;
   }

   public List<StockItem> getStockItems() {
      return stockItems;
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

   public double getCurrentDiff() {
      return currentDiff;
   }

   public boolean isStop() {
      return stop;
   }

   public double getHighestPrice() {
      return highestPrice;
   }

   public double getEndPrice() {
      return endPrice;
   }

   public String getName() {
      return name;
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
