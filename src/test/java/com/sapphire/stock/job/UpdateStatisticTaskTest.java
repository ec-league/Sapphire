package com.sapphire.stock.job;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.BaseTest;
import com.sapphire.common.TimeUtil;
import com.sapphire.stock.domain.Stock;
import com.sapphire.stock.domain.StockStatistics;
import com.sapphire.stock.service.StockService;
import com.sapphire.stock.service.StockStatisticsService;

/**
 * UpdateStatisticTask Tester.
 * 
 * @author EthanPark
 * @since <pre>
 * </pre>
 * @version 1.0
 */
public class UpdateStatisticTaskTest extends BaseTest {

   @Autowired
   private StockService stockService;

   @Autowired
   private StockStatisticsService stockStatisticsService;

   /**
    * 
    * Method: executeInternal(JobExecutionContext context)
    * 
    */
   //   @Test
   public void testExecuteInternal() throws Exception {
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
         stat.setEarlyDate(stock.getEarlyDate());
         stats.add(stat);
      }
      stockStatisticsService.update(stats);
   }
}
