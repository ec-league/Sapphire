package com.sapphire.stock.cache;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.common.cache.Cache;
import com.sapphire.common.cache.CacheService;
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
   private static StockStatics increaseTotalStat;

   private final Lock lock = new ReentrantLock();

   public StockCache() {
   }

   @PostConstruct
   public void registerCache() {
      CacheService.register(this);
   }

   @Override
   @RequestMapping("/refresh")
   @ResponseBody
   public boolean refresh() {
      try {
         logger.info("Refresh the Stock Cache!");
         init();
         logger.info("Refresh Cache Succeed!");
         return true;
      } catch (Exception ex) {
         logger.error("Init StockCache Error", ex);
         return false;
      }
   }

   @Override
   public long interval() {
      return CacheService.ONE_HOUR;
   }

   private void init() {
      lock.lock();
      try {
         StockStatics stat = stockService.getLastYearStockStatics();
         StockStatics stat1 = stockService.getStocksByIncreaseTotal();
         stockStatics = stat;
         increaseTotalStat = stat1;
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

   public StockStatics getIncreaseTotalStat() {
      if (increaseTotalStat == null && !refresh()) {
         return null;
      }
      return increaseTotalStat;
   }
}
