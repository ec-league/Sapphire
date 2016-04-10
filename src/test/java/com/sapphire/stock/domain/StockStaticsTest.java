package com.sapphire.stock.domain;

import com.sapphire.stock.service.StockService;
import org.junit.Test;

import com.sapphire.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * StockStatics Tester.
 * 
 * @author EthanPark
 * @since <pre>
 * ËÄÔÂ 10, 2016
 * </pre>
 * @version 1.0
 */
public class StockStaticsTest extends BaseTest {

   @Autowired
   private StockService stockService;

   /**
    * 
    * Method: getMacdBelowZero()
    * 
    */
   @Test
   public void testGetMacdBelowZero() throws Exception {
      StockStatics stockStatics = stockService.getLastMonthStockStatics();

      List<Stock> stocks = stockStatics.getMacdBelowZero();

      for (Stock stock : stocks) {
         System.out.println(stock.getCode() + ", " + stock.getCurrentMacd());
      }
   }
}
