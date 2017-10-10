package com.sapphire.common.dal.stock.domain;

import java.util.List;

import com.sapphire.biz.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.BaseTest;

/**
 * StockStatics Tester.
 * 
 * @author EthanPark
 * @since <pre>
 * ���� 10, 2016
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
   //   @Test
   public void testGetMacdBelowZero() throws Exception {
      StockStatics stockStatics = stockService.getLastMonthStockStatics();

      List<Stock> stocks = stockStatics.getMacdBelowZero();

      for (Stock stock : stocks) {
         System.out.println(stock.getCode() + ", " + stock.getCurrentMacd());
      }
   }

   //   @Test
   public void testGetMacdUpZero() {
      StockStatics stockStatics = stockService.getLastMonthStockStatics();

      List<Stock> stocks = stockStatics.getMacdUpZero();

      for (Stock stock : stocks) {
         System.out.println(stock.getCode() + ", " + stock.getCurrentDiff());
      }
   }
}
