package com.sapphire.stock.job.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.BaseTest;
import com.sapphire.stock.job.StockStatisticJob;

/**
 * StockItemJobImpl Tester.
 * 
 * @author EthanPark
 * @since <pre>
 * ʮ�� 17, 2016
 * </pre>
 * @version 1.0
 */
public class StockItemJobImplTest extends BaseTest {

   @Autowired
   private StockStatisticJob job;

   /**
    * 
    * Method: updateStock()
    * 
    */
   //   @Test
   public void testUpdateStock() throws Exception {
      job.updateStatistic();
   }
}
