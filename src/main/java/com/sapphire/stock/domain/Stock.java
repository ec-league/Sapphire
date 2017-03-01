package com.sapphire.stock.domain;

import com.sapphire.common.dto.Dto;

import java.beans.Transient;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ethan on 2016/3/30.
 */
public class Stock implements Dto {
   private transient List<StockItem> stockItems;
   private double increaseTotal;
   private double highestPrice;
   private double endPrice;
   private String name;
   private boolean goldPossible;
   private double averageGoldDays;

   /**
    * Stock中的一些具体信息
    */
   private StockDetail stockDetail;

   /**
    * 股票代码
    */
   private String code;
   private double firstDiff;
   private double averageIncreaseRate;
   private boolean shouldPass = false;
   private double lowestMacd;

   /**
    * 最后一天的股票macd值
    */
   private double currentMacd;

   /**
    * 最后一天的股票macd diff值
    */
   private double currentDiff;

   private boolean stop;

   /**
    * Used when construct a item only with setter method.
    */
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

   /**
    * 计算5日均线，10日均线，20日均线，以及30日均线
    */
   public void processAverage() {
      if (stockItems == null || stockItems.isEmpty())
         return;

      for (int i = 0; i < stockItems.size(); i++) {
         if (i >= 4)
            stockItems.get(i).setAverage5(calculateAverage(i, 5));
         if (i >= 9)
            stockItems.get(i).setAverage10(calculateAverage(i, 10));
         if (i >= 19)
            stockItems.get(i).setAverage20(calculateAverage(i, 20));
         if (i >= 29)
            stockItems.get(i).setAverage30(calculateAverage(i, 30));
      }
   }

   private double calculateAverage(int fromIndex, int t) {
      double d = 0;

      for (int i = fromIndex - t + 1; i <= fromIndex; i++) {
         d += stockItems.get(i).getEndPrice();
      }

      return d / t;
   }

   public String getCode() {
      return code;
   }

   public void calculateMacd(int small, int big, boolean needInit) {
      if (needInit)
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

         stockItems.get(i).setMacdDea(stockItems.get(i - 1).getMacdDea() * 0.8
               + stockItems.get(i).getMacdDiff() * 0.2);
         stockItems.get(i).setMacd(stockItems.get(i).getMacdDiff()
               - stockItems.get(i).getMacdDea());
         stockItems.get(i).setIncreaseRate((stockItems.get(i).getEndPrice()
               / stockItems.get(i - 1).getEndPrice() - 1) * 100);
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

      averageIncreaseRate = count == 0 ? 1 : plusCount * 1.0 / count;
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

   /**
    * 第二天变成金叉，所需要的收盘价格
    * 
    * @param lastItem
    * @return
    */
   private static double goldPrice(StockItem lastItem) {
      return  (76 * 17.0) / (19 * 11) * lastItem.getEma26()
            - 57.0 / 11 * lastItem.getEma12()
            + 76.0 / 11 * lastItem.getMacdDea();
   }

   /**
    * 第二天是否可能变成金叉
    * 
    * @param lastItem
    * @return
    */
   public static boolean isGoldPossible(StockItem lastItem) {
      double price = goldPrice(lastItem);

      if ((price - lastItem.getEndPrice()) / lastItem.getEndPrice() > 0.1) {
         return false;
      }
      return true;
   }

   public void process() {

      if (stockItems.isEmpty())
         return;

      List<StockStatic> statics = group(stockItems);
      StockItem lastItem = stockItems.get(stockItems.size() - 1);

      setCurrentMacd(lastItem.getMacd());
      setGoldPossible(isGoldPossible(lastItem));
      setStop(lastItem.isStop());
      setCurrentDiff(lastItem.getMacdDiff());
      setEndPrice(lastItem.getEndPrice());


      double origin = 1.0;
      int days = 0;

      for (StockStatic stockStatic : statics) {
         origin *= (1 + stockStatic.increaseRate / 100);
         days += stockStatic.consistDays;
      }
      setIncreaseTotal(origin);

      int average = 0;
      if (statics.isEmpty())
         return;

      average = days / statics.size();
      setAverageGoldDays(average);

      double highPrice = 0;
      double lowest = 0;
      for (StockItem item : stockItems) {
         if (item.getHighestPrice() > highPrice)
            highPrice = item.getHighestPrice();

         if (item.getMacd() < lowest)
            lowest = item.getMacd();
      }

      setHighestPrice(highPrice);
      setLowestMacd(lowest);
   }

   public double getAverageGoldDays() {
      return averageGoldDays;
   }

   public void setAverageGoldDays(double averageGoldDays) {
      this.averageGoldDays = averageGoldDays;
   }

   public void setGoldPossible(boolean goldPossible) {
      this.goldPossible = goldPossible;
   }

   public void setStop(boolean stop) {
      this.stop = stop;
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
         long time = item.getLogDate().getTime();
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

      if (!complexItems.isEmpty() && complexItems.get(0).get(0).getLogDate()
            .equals(items.get(0).getLogDate())) {
         complexItems = complexItems.subList(1, complexItems.size());
      }

      List<StockStatic> statics = new ArrayList<>();
      for (List<StockItem> list : complexItems) {
         StockStatic stockStatic = new StockStatic();
         StockItem start = list.get(0);
         StockItem end = list.get(list.size() - 1);

         stockStatic.setStartDate(start.getLogDate());
         stockStatic.setEndDate(end.getLogDate());
         stockStatic.setIsOverZero(start.getMacdDiff() > 0);
         stockStatic.setConsistDays(list.size());
         stockStatic.setFirstDiff(start.getMacdDiff());
         stockStatic.setIncreaseRate(
               (end.getEndPrice() / start.getEndPrice() - 1) * 100);
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

   /**
    * 只计算不受总Stock数目影响的属性值
    * 
    * @param items
    */
   private void update(List<StockItem> items) {
      stockItems = items;
      code = stockItems.get(0).getCode();
      name = stockItems.get(0).getName();
      StockItem last = stockItems.get(stockItems.size() - 1);
      endPrice = last.getEndPrice();
      currentMacd = last.getMacd();
      currentDiff = last.getMacdDiff();
      stop = last.isStop();
      goldPossible = isGoldPossible(last);
   }

   public void update(StockStatistics statistics) {
      stockDetail.setHistoryInfo(new HistoryInfo());
      stockDetail.getHistoryInfo()
            .setHighestPrice(statistics.getHighestPrice());
      increaseTotal = statistics.getIncreaseTotal();
      lowestMacd = statistics.getLowestMacd();
      firstDiff = stockItems.get(stockItems.size() - 1).getMacdDiff();
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

   public double getIncreaseFromStart() {
      if (stockItems == null || stockItems.isEmpty())
         return 0d;
      double start = stockItems.get(0).getEndPrice();
      double end = stockItems.get(stockItems.size() - 1).getEndPrice();

      return (end - start) / start * 100;
   }


   @Transient
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

   public void setHighestPrice(double highestPrice) {
      this.highestPrice = highestPrice;
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

   public void setEndPrice(double endPrice) {
      this.endPrice = endPrice;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public void setCurrentMacd(double currentMacd) {
      this.currentMacd = currentMacd;
   }

   public void setCurrentDiff(double currentDiff) {
      this.currentDiff = currentDiff;
   }

   public void setLowestMacd(double lowestMacd) {
      this.lowestMacd = lowestMacd;
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

   public double getLowestMacd() {
      return lowestMacd;
   }

   public boolean isGoldPossible() {
      return goldPossible;
   }

   public StockDetail getStockDetail() {
      return stockDetail;
   }

   public void setStockDetail(StockDetail stockDetail) {
      this.stockDetail = stockDetail;
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
