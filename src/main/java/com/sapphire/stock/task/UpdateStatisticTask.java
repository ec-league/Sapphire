package com.sapphire.stock.task;

import java.util.ArrayList;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.sapphire.common.TimeUtil;
import com.sapphire.stock.domain.Stock;
import com.sapphire.stock.domain.StockStatistics;
import com.sapphire.stock.service.StockService;
import com.sapphire.stock.service.StockStatisticsService;

/**
 * Author: EthanPark <br/>
 * Date: ${date}<br/>
 * Email: byp5303628@hotmail.com
 */
public class UpdateStatisticTask extends QuartzJobBean {
   private static final Logger logger = LoggerFactory
         .getLogger(UpdateStatisticTask.class);

   private static StockService stockService;
   private static StockStatisticsService stockStatisticsService;

   private void init(JobExecutionContext context) throws SchedulerException {
      stockService =
            (StockService) context.getScheduler().getContext()
                  .get("stockService");
      stockStatisticsService =
            (StockStatisticsService) context.getScheduler().getContext()
                  .get("stockStatisticsService");
   }

   @Override
   protected void executeInternal(JobExecutionContext context)
         throws JobExecutionException {
      logger.info("Update Stock Statistics Task Begin");

      try {
         init(context);

         List<String> codes = stockService.getAllCodes();
         List<StockStatistics> stats = new ArrayList<>(codes.size());

         for (String code : codes) {
            Stock stock = stockService.getStockByCode(code);

            stock.process();

            StockStatistics stat = new StockStatistics();
            stat.setIncreaseTotal(stock.getIncreaseTotal());
            stat.setAverageGoldDays(stock.getAverageGoldDays());
            stat.setCode(code);
            stat.setHighestPrice(stock.getHighestPrice());
            stat.setLastModifyDate(TimeUtil.now());
            stat.setLowestMacd(stock.getLowestMacd());
            stats.add(stat);
         }
         stockStatisticsService.update(stats);
      } catch (SchedulerException e) {
         logger.error("Init error", e);
      }

      logger.info("Update Stock Statistics Task Finished!");
   }
}
