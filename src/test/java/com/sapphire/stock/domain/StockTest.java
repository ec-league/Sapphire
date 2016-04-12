package com.sapphire.stock.domain;

import java.io.*;
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
         BufferedReader br =
               new BufferedReader(new InputStreamReader(new FileInputStream(f),
                     "GBK"));

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

         if (stockItems.isEmpty())
            continue;
         Stock stock = new Stock(stockItems);
         stock.calculateMacd(7, 18);

         stockItemRepository.save(stockItems);
      }
   }
}
