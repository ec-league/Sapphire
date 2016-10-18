package com.sapphire.stock.task.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.common.TimeUtil;
import com.sapphire.common.annotation.Job;
import com.sapphire.stock.domain.Stock;
import com.sapphire.stock.domain.StockStatistics;
import com.sapphire.stock.service.StockService;
import com.sapphire.stock.service.StockStatisticsService;
import com.sapphire.stock.task.StockStatisticJob;

/**
 * Author: EthanPark <br/>
 * Date: 2016/10/16<br/>
 * Email: byp5303628@hotmail.com
 */
@Job
public class StockStaticsticJobImpl implements StockStatisticJob {
   private static final ExecutorService threadPool = Executors
         .newSingleThreadExecutor();

   private static final Logger logger = LoggerFactory
         .getLogger(StockStaticsticJobImpl.class);

   @Autowired
   private StockService stockService;

   @Autowired
   private StockStatisticsService statisticsService;

   @Override
   public void updateStatistic() {
      threadPool.execute(new Runnable() {
         @Override
         public void run() {
            updateStatisticInternal();
         }
      });
   }


   public void updateStatisticInternal() {
      logger.info("Update Stock Statistics Task Begin");

      List<String> codes = stockService.getAllCodes();
      List<StockStatistics> stats = new ArrayList<>(codes.size());

      for (String code : codes) {
         Stock stock =
               stockService.getStockByCodeAndTime(code, TimeUtil.oneYearAgo(),
                     TimeUtil.now());

         if (stock == null)
            continue;

         stock.process();

         StockStatistics stat = new StockStatistics();
         stat.setIncreaseTotal(stock.getIncreaseTotal());
         stat.setAverageGoldDays(stock.getAverageGoldDays());
         stat.setCode(code);
         stat.setHighestPrice(stock.getHighestPrice());
         stat.setLastModifyDate(TimeUtil.now());
         stat.setLowestMacd(stock.getLowestMacd());
         stat.setEarlyDate(stock.getEarlyDate());
         stats.add(stat);
      }
      statisticsService.update(stats);

      logger.info("Update Stock Statistics Task Finished!");
   }
}
