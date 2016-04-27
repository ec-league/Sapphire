package com.sapphire.stock.cache;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.common.cache.Cache;
import com.sapphire.stock.domain.StockStatics;
import com.sapphire.stock.service.StockService;

/**
 * Author: Ethan Date: 2016/4/10
 */
@Controller
@RequestMapping("/stock/cache")
public class StockCache implements Cache {
   private static final Logger logger = LoggerFactory
         .getLogger(StockCache.class);

   @Autowired
   private StockService stockService;

   private static StockStatics stockStatics;
   private static StockStatics increasTotalStat;

   private final Lock lock = new ReentrantLock();


   @Override
   @RequestMapping("/refresh")
   @ResponseBody
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
      StockStatics stat1 = stockService.getStocksByIncreaseTotal();

      lock.lock();
      try {
         stockStatics = stat;
         increasTotalStat = stat1;
      } catch (Exception ex) {
         logger.error("Init Stock Cache failed!", ex);
      } finally {
         lock.unlock();
      }
   }

   public StockStatics getStockStatics() {
      if (stockStatics == null && !refresh()) {
         return null;
      }
      return stockStatics;
   }

   public StockStatics getIncreasTotalStat() {
      if (increasTotalStat == null && !refresh()) {
         return null;
      }
      return increasTotalStat;
   }
}
