package com.sapphire.stock.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.sapphire.common.TimeUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.BaseTest;
import com.sapphire.stock.model.Stock;
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
   @Test
   public void testGetStockByCode() throws Exception {
      String code = "600000";

      Stock stock = stockService.getStockByCode(code);

      Assert.assertNotNull(stock);

      code = "60123";

      stock = stockService.getStockByCode(code);

      Assert.assertNull(stock);
   }

   @Test
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

   @Test
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

      stocks.sort((o1, o2) -> (int) (o1.getIncreaseTotal() * 100 - o2.getIncreaseTotal() * 100));

      for (int i = 0; i < 50; i++) {
         System.out.printf("Code : %s, Increase : %s, Diff: %s%n", stocks.get(i).getCode(), stocks.get(i).getIncreaseTotal(), stocks.get(i).getFirstDiff());
      }
   }

   /**
    * 统计从2015年7月16日以后的金叉盈利百分比，根据百分比来缩短周期寻求最高盈利率
    *
    * @throws Exception
    */
   @Test
   public void output2() {
      String dateFrom = "07/20/2015";
      Timestamp from = TimeUtil.fromStockString(dateFrom);

      List<String> codes = stockService.getAllCodes();

      Set<String> ignoreSet = new HashSet<>();
      List<Stock> stocks = new ArrayList<>(3000);
      for (String code : codes) {
         Stock stock = stockService.getStockByCodeAndTime(code, from, TimeUtil.now());

         if (stock == null) {
            ignoreSet.add(code);
            continue;
         }
         stocks.add(stock);
      }

   }
}
