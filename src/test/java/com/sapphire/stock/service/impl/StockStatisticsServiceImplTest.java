package com.sapphire.stock.service.impl;

import com.sapphire.BaseTest;
import com.sapphire.common.TimeUtil;
import com.sapphire.stock.domain.StockStatistics;
import com.sapphire.stock.repository.StockStatisticsRepository;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * StockStatisticsServiceImpl Tester.
 * 
 * @author EthanPark
 * @since <pre>
 * ËÄÔÂ 22, 2016
 * </pre>
 * @version 1.0
 */
public class StockStatisticsServiceImplTest extends BaseTest {

   @Autowired
   private StockStatisticsRepository stockStatisticsRepository;
   /**
    * 
    * Method: update(List<StockStatistics> stats)
    * 
    */
   @Test
   public void testUpdate() throws Exception {
      StockStatistics statistics = new StockStatistics();
      statistics.setCode("600000");
      statistics.setHighestPrice(12.21);
      statistics.setAverageGoldDays(10);
      statistics.setIncreaseTotal(12);
      statistics.setLastModifyDate(TimeUtil.now());

      StockStatistics item = stockStatisticsRepository.save(statistics);
      item = stockStatisticsRepository.save(statistics);
      Assert.assertTrue(item.getUidPk() >= 0);
   }


}
