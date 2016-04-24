package com.sapphire.stock.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.BaseTest;
import com.sapphire.common.TimeUtil;
import com.sapphire.stock.domain.Stock;
import com.sapphire.stock.domain.StockStatistics;
import com.sapphire.stock.repository.StockStatisticsRepository;
import com.sapphire.stock.service.StockService;
import com.sapphire.stock.service.StockStatisticsService;

/**
 * StockStatisticsServiceImpl Tester.
 * 
 * @author EthanPark
 * @since <pre>
 * ���� 22, 2016
 * </pre>
 * @version 1.0
 */
public class StockStatisticsServiceImplTest extends BaseTest {

   @Autowired
   private StockStatisticsRepository stockStatisticsRepository;

   @Autowired
   private StockStatisticsService stockStatisticsService;

   @Autowired
   private StockService stockService;

   /**
    * 
    * Method: update(List<StockStatistics> stats)
    * 
    */
   @Test
   public void testUpdate() throws Exception {
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
         stats.add(stat);
      }

      stockStatisticsService.update(stats);
   }

   @Test
   public void testFind() {
      Long uidPk = stockStatisticsRepository.findStatByCode("60000000");

      Assert.assertNull(uidPk);

      List<String> codes = stockStatisticsRepository.findByIncrease();

      Assert.assertTrue(codes.size() == 200);
   }
}
