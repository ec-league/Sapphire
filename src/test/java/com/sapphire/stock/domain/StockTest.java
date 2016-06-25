package com.sapphire.stock.domain;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.BaseTest;
import com.sapphire.common.TimeUtil;
import com.sapphire.stock.repository.StockItemRepository;
import com.sapphire.stock.service.StockService;

/**
 * Stock Tester.
 * 
 * @author <Authors name>
 * @since <pre>
 * </pre>
 * @version 1.0
 */
public class StockTest extends BaseTest {

   @Autowired
   private StockItemRepository stockItemRepository;

   @Autowired
   private StockService stockService;

   //   @Test
   public void testStat() {
      List<String> codes = stockItemRepository.getCodes();
      List<CodeIncrease> list = new ArrayList<>(codes.size());

      for( String code : codes){
         Stock stock = stockService.getStockByCodeAndTime(code, TimeUtil.fromStockString("05/26/2016"), TimeUtil.now());

         if (stock == null)
            continue;

         list.add(new CodeIncrease(code, stock.getIncreaseFromStart()));
      }

      Collections.sort(list, (o1, o2) -> Double.compare(o1.getIncrease(), o2.getIncrease()));

      for (int i = 0;i< 100; i++){
         System.out.println(String.format("Code: \"%s\", Increase: %.2f", list.get(i).getCode(), list.get(i).getIncrease()));
      }

      Collections.reverse(list);
      System.out.println("#############################");
      for (int i = 0;i< 100; i++){
         System.out.println(String.format("Code: \"%s\", Increase: %.2f", list.get(i).getCode(), list.get(i).getIncrease()));
      }
   }

   private static class CodeIncrease {
      private String code;
      private double increase;

      public CodeIncrease(String code, double increase) {
         this.code = code;
         this.increase = increase;
      }

      public String getCode() {
         return code;
      }

      public void setCode(String code) {
         this.code = code;
      }

      public double getIncrease() {
         return increase;
      }

      public void setIncrease(double increase) {
         this.increase = increase;
      }
   }

   //   @Test
   public void construct() throws IOException, ParseException {
      String code = "600000";
      Timestamp from =
            new Timestamp(new SimpleDateFormat("MM/dd/yyyy")
                  .parse("07/20/2015").getTime());
      Timestamp to =
            new Timestamp(new SimpleDateFormat("MM/dd/yyyy")
                  .parse("09/10/2015").getTime());

      List<StockItem> stockItems =
            stockItemRepository.getStockByCodeAndTime(code, from, to);

      Assert.assertNotNull(stockItems);
   }

   //   @Test
   public void construct1() throws Exception {
      Assert.assertNotNull(stockItemRepository);
      System.out.println(TimeUtil.now());
      File dir = new File("C:\\Users\\Ethan\\Desktop\\script\\export");

      for (File f : dir.listFiles()) {
         BufferedReader br =
               new BufferedReader(new InputStreamReader(new FileInputStream(f),
                     "GBK"));

         List<StockItem> stockItems = new ArrayList<>(500);
         String temp = br.readLine();
         String code = temp.split(" ")[0];
         String name = temp.split(" ")[1];
         temp = br.readLine();
         while (temp != null) {
            temp = br.readLine();
            if (!temp.matches("[0-9].*"))
               break;

            StockItem item = new StockItem(temp);
            item.setCode(code);
            item.setName(name);

            stockItems.add(item);
         }

         if (stockItems.isEmpty())
            continue;
         Stock stock = new Stock(stockItems);
         stock.calculateMacd(12, 26, true);

         StockItem last = stockItems.get(stockItems.size() - 1);

         last.setLast(true);

         stockItemRepository.save(stockItems);
      }
   }

   /**
    * Daily data insert.
    * 
    * @throws Exception
    */
   //   @Test
   public void construct2() throws Exception {
      BufferedReader br =
            new BufferedReader(new InputStreamReader(new FileInputStream(
                  new File("C:\\Users\\Ethan\\Desktop\\Table.txt")), "GBK"));

      br.readLine();
      String temp = br.readLine();

      List<StockItem> items = new ArrayList<>(3000);
      while (temp != null) {
         temp = br.readLine();
         StockItem item = StockItem.makeStockItemFromDaily(temp);

         if (item == null)
            continue;
         items.add(item);
      }

      stockItemRepository.save(items);
   }

   //   @Test
   public void testCode() {
      List<String> codes = stockItemRepository.getCodeByIndustry("专用");

      Assert.assertNotNull(codes);
   }

   //   @Test
   public void testGetLatestStockItem() {
      StockItem item = stockItemRepository.getLatestStockItem("600000");
      Assert.assertNotNull(item);
   }

   //   @Test
   public void testIsUpper() {
      Stock stock = stockService.getStockByCode("000001");

      Assert.assertTrue(stock.isUpper());
   }

   //   @Test
   public void testLatestStockItem() {
      List<String> codes = stockItemRepository.getCodes();

      for (String code : codes) {
         StockItem item = stockItemRepository.getLatestStockItem(code);
         item.setLast(true);
         stockItemRepository.save(item);
      }
   }

   //   @Test
   public void testLatestItems() {
      List<StockItem> items = stockItemRepository.getLatestItems();

      for (StockItem item : items) {
         item.setLogDate(TimeUtil.fromStockString("04/21/2016"));
      }

      stockItemRepository.save(items);
   }
}
