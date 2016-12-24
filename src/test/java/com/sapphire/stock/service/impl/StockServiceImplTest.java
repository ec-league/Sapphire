package com.sapphire.stock.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.BaseTest;
import com.sapphire.common.TimeUtil;
import com.sapphire.stock.domain.Stock;
import com.sapphire.stock.domain.StockStatics;
import com.sapphire.stock.service.StockService;

/**
 * StockServiceImpl Tester.
 * 
 * @author EthanPark
 * @since <pre>
 * ���� 2, 2016
 * </pre>
 * @version 1.0
 */
public class StockServiceImplTest extends BaseTest {

   @Autowired
   private StockService stockService;

   /**
    * 
    * Method: getAllCodes()
    * 
    */
   @Test
   public void testGetAllCodes() throws Exception {
      List<String> codes = stockService.getAllCodes();

      Assert.assertNotNull(codes);
   }

   /**
    * 
    * Method: getStockByCode(String code)
    * 
    */
   //   @Test
   public void testGetStockByCode() throws Exception {
      String code = "600000";

      Stock stock = stockService.getStockByCode(code);

      Assert.assertNotNull(stock);

      code = "60123";

      stock = stockService.getStockByCode(code);

      Assert.assertNull(stock);
   }

   //   @Test
   public void outputStatics() throws ParseException {
      List<String> codes = stockService.getAllCodes();

      int successCount = 0;
      int totalCount = 0;

      String dateFrom = "11/06/2015";
      String dateTo = "04/02/2016";
      Timestamp from =
            new Timestamp(new SimpleDateFormat("MM/dd/yyyy").parse(dateFrom)
                  .getTime());
      Timestamp to =
            new Timestamp(new SimpleDateFormat("MM/dd/yyyy").parse(dateTo)
                  .getTime());

      for (String code : codes) {
         Stock stock = stockService.getStockByCodeAndTime(code, from, to);
         if (stock == null) {
            continue;
         }
         successCount += stock.macdPlusCount(dateFrom, dateTo);
         totalCount += stock.macdTotalCount(dateFrom, dateTo);
      }

      System.out.printf("Total Success : %d%n", successCount);
      System.out.printf("Total All     : %d%n", totalCount);
      System.out
            .printf("Frequency     : %s%n", successCount * 1.0 / totalCount);
   }

   //   @Test
   public void output1() throws ParseException {
      List<String> codes = stockService.getAllCodes();

      String dateFrom = "07/20/2015";
      String dateTo = "09/10/2015";
      Timestamp from =
            new Timestamp(new SimpleDateFormat("MM/dd/yyyy").parse(dateFrom)
                  .getTime());
      Timestamp to =
            new Timestamp(new SimpleDateFormat("MM/dd/yyyy").parse(dateTo)
                  .getTime());

      List<Stock> stocks = new ArrayList<>(3000);
      for (String code : codes) {
         Stock stock = stockService.getStockByCodeAndTime(code, from, to);

         if (stock == null) {
            continue;
         }

         stock.process();
         stocks.add(stock);
      }

      stocks.sort((o1, o2) -> (int) (o1.getIncreaseTotal() * 100 - o2
            .getIncreaseTotal() * 100));

      for (int i = 0; i < 50; i++) {
         System.out.printf("Code : %s, Increase : %s, Diff: %s%n", stocks
               .get(i).getCode(), stocks.get(i).getIncreaseTotal(),
               stocks.get(i).getFirstDiff());
      }
   }

   /**
    * 统计从2015年7月16日以后的金叉盈利百分比，根据百分比来缩短周期寻求最高盈利率
    *
    * @throws Exception
    */
   //   @Test
   public void output2() {
      Timestamp from = TimeUtil.fromStockString("07/16/2015");
      List<String> codes = stockService.getAllCodes();

      System.out.println(TimeUtil.now());

      Set<String> ignoreSet = new HashSet<>();
      List<Stock> stocks = new ArrayList<>(3000);
      for (String code : codes) {
         Stock stock =
               stockService.getStockByCodeAndTime(code, from, TimeUtil.now());

         if (stock == null) {
            ignoreSet.add(code);
            continue;
         }
         stocks.add(stock);
      }
      System.out.println(stocks.size());
      System.out.println(TimeUtil.now());

      List<StaticItem> staticItems = new ArrayList<>(stocks.size());

      for (int i = 5; i <= 15; i++) {
         for (int j = 15; j < 25; j++) {
            int count = 0;
            double averageIncreaseRate = 0;
            double increaseTotal = 0;
            for (Stock stock : stocks) {
               stock.calculateMacd(12, 26, true);
               stock.calcStatics();
               if (stock.isShouldPass()) {
                  continue;
               }
               averageIncreaseRate += stock.getAverageIncreaseRate();
               increaseTotal += stock.getIncreaseTotal();
               count++;
            }

            if (count == 0) {
               continue;
            }
            StaticItem item = new StaticItem();
            item.setIncreaseTotal(increaseTotal / count);
            item.setAverageIncreaseRate(averageIncreaseRate / count);
            item.setStart(i);
            item.setEnd(j);

            staticItems.add(item);
         }
      }

      staticItems.sort((o1, o2) -> (int) (o2.getIncreaseTotal()
            * o2.getAverageIncreaseRate() * 10000 - o1.getIncreaseTotal()
            * o1.getAverageIncreaseRate() * 10000));

      for (StaticItem item : staticItems) {
         String info =
               String.format(
                     "Start : %d, End : %d, IncreaseTotal : %s, AverageRate : %s",
                     item.getStart(), item.getEnd(), item.getIncreaseTotal(),
                     item.getAverageIncreaseRate());
         System.out.println(info);
      }
   }

   @Test
   public void testGetLastMonthStockStatics(){
      StockStatics statics = stockService.getLastMonthStockStatics();

      Assert.assertNotNull(statics);
   }

   //   @Test
   public void getLastMonthTest() {
      Timestamp now = TimeUtil.now();
      StockStatics stockStatics = stockService.getLastMonthStockStatics();
      System.out.printf("Cost time : %d ms%n",
            TimeUtil.now().getTime() - now.getTime());
      Assert.assertNotNull(stockStatics);
   }

   //   @Test
   public void testMacd() {
      Timestamp from = TimeUtil.fromStockString("07/08/2014");
      Timestamp to = TimeUtil.now();

      Stock stock = stockService.getStockByCodeAndTime("300202", from, to);

      stock.calculateMacd(12, 26, true);

      Assert.assertNotNull(stock);
   }


   private static class StaticItem {
      private int start;
      private int end;
      private double increaseTotal;
      private double averageIncreaseRate;

      public int getStart() {
         return start;
      }

      public void setStart(int start) {
         this.start = start;
      }

      public int getEnd() {
         return end;
      }

      public void setEnd(int end) {
         this.end = end;
      }

      public double getIncreaseTotal() {
         return increaseTotal;
      }

      public void setIncreaseTotal(double increaseTotal) {
         this.increaseTotal = increaseTotal;
      }

      public double getAverageIncreaseRate() {
         return averageIncreaseRate;
      }

      public void setAverageIncreaseRate(double averageIncreaseRate) {
         this.averageIncreaseRate = averageIncreaseRate;
      }
   }
}
