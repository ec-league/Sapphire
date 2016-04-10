package com.sapphire.stock.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
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
 * ���� 30, 2016
 * </pre>
 * @version 1.0
 */
public class StockTest extends BaseTest {

   @Autowired
   private StockItemRepository stockItemRepository;


   @Test
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

   @Test
   public void construct1() throws Exception {
      Assert.assertNotNull(stockItemRepository);
      System.out.println(TimeUtil.now());
      File dir = new File("C:\\Users\\Ethan\\Desktop\\script\\export");

      for (File f : dir.listFiles()) {
         BufferedReader br = new BufferedReader(new FileReader(f));

         List<StockItem> stockItems = new ArrayList<StockItem>(500);
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
         for (int i = 1; i < stockItems.size(); i++) {
            stockItems.get(i).setEma12(
                  stockItems.get(i - 1).getEma12() * 11 / 13
                        + stockItems.get(i).getEma12() * 2 / 13);
            stockItems.get(i).setEma26(
                  stockItems.get(i - 1).getEma26() * 25 / 27
                        + stockItems.get(i).getEma26() * 2 / 27);
            stockItems.get(i).setMacdDiff(
                  stockItems.get(i).getEma12() - stockItems.get(i).getEma26());
            stockItems.get(i).setMacdDea(
                  stockItems.get(i - 1).getMacdDea() * 0.8
                        + stockItems.get(i).getMacdDiff() * 0.2);
            stockItems.get(i).setMacd(
                  stockItems.get(i).getMacdDiff()
                        - stockItems.get(i).getMacdDea());
         }

         stockItemRepository.save(stockItems);
      }
   }
}
