package com.sapphire.stock.job.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.common.TimeUtil;
import com.sapphire.common.annotation.Job;
import com.sapphire.stock.domain.Stock;
import com.sapphire.stock.domain.StockStatistics;
import com.sapphire.stock.job.SingleThreadJob;
import com.sapphire.stock.job.StockStatisticJob;
import com.sapphire.stock.service.StockService;
import com.sapphire.stock.service.StockStatisticsService;

/**
 * Author: EthanPark <br/>
 * Date: 2016/10/16<br/>
 * Email: byp5303628@hotmail.com
 */
@Job
public class StockStaticsticJobImpl extends SingleThreadJob implements
      StockStatisticJob {

   private static final Logger logger = LoggerFactory
         .getLogger(StockStaticsticJobImpl.class);

   @Autowired
   private StockService stockService;

   @Autowired
   private StockStatisticsService statisticsService;

   @Override
   public void updateStatistic() {
      submit(new Runnable() {
         @Override
         public void run() {
            updateStatisticInternal();
         }
      });
   }


   private void updateStatisticInternal() {
      logger.info("Update Stock Statistics Task Begin");

      List<String> codes = stockService.getAllCodes();

      for (String code : codes) {
         handleStat(code);
      }

      logger.info("Update Stock Statistics Task Finished!");
   }

   private void handleStat(String code){
      logger.info(String.format("Update Stock Statistics : %s", code));
      Stock stock =
            stockService.getStockByCodeAndTime(code, TimeUtil.oneYearAgo(),
                  TimeUtil.now());

      if (stock == null)
         return;

      stock.process();

      StockStatistics stat = new StockStatistics();
      stat.setIncreaseTotal(stock.getIncreaseTotal());
      stat.setAverageGoldDays(stock.getAverageGoldDays());
      stat.setCode(code);
      stat.setHighestPrice(stock.getHighestPrice());
      stat.setLastModifyDate(TimeUtil.now());
      stat.setLowestMacd(stock.getLowestMacd());
      stat.setEarlyDate(stock.getEarlyDate());

      statisticsService.update(stat);
   }
}
