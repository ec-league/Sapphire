package com.sapphire.stock.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.common.Cache;
import com.sapphire.stock.domain.StockStatics;
import com.sapphire.stock.service.StockService;

/**
 * Author: Ethan Date: 2016/4/10
 */
public class StockCache implements Cache {
   private static final Logger logger = LoggerFactory
         .getLogger(StockCache.class);

   @Autowired
   private StockService stockService;

   private StockStatics stockStatics;


   @Override
   public boolean refresh() {
      try {
         init();
         return true;
      } catch (Exception ex) {
         logger.error("Init StockCache Error", ex);
         return false;
      }
   }

   @Override
   public long interval() {
      return 0;
   }

   private void init() {
      StockStatics stat = stockService.getLastMonthStockStatics();

      synchronized (stockStatics) {
         stockStatics = stat;
      }
   }

   public StockStatics getStockStatics() {
      return stockStatics;
   }
}
