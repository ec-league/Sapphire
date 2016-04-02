package com.sapphire.stock.domain;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.BaseTest;
import com.sapphire.stock.repository.StockItemRepository;

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
      Timestamp from = new Timestamp(new SimpleDateFormat("MM/dd/yyyy").parse("07/20/2015").getTime());
      Timestamp to = new Timestamp(new SimpleDateFormat("MM/dd/yyyy").parse("09/10/2015").getTime());
      
      List<StockItem> stockItems = stockItemRepository.getStockByCodeAndTime(code, from, to);

      Assert.assertNotNull(stockItems);
   }
}
