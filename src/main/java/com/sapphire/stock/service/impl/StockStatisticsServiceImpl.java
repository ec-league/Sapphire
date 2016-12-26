package com.sapphire.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapphire.stock.domain.StockStatistics;
import com.sapphire.stock.repository.StockStatisticsRepository;
import com.sapphire.stock.service.StockStatisticsService;

/**
 * Author: EthanPark <br/>
 * Date: 2016/4/22<br/>
 * Email: byp5303628@hotmail.com
 */
@Service("stockStatisticsService")
public class StockStatisticsServiceImpl implements StockStatisticsService {
   @Autowired
   private StockStatisticsRepository stockStatisticsRepository;

   @Override
   public void update(List<StockStatistics> stats) {
      for (StockStatistics stat : stats) {
         Long uidPk = stockStatisticsRepository.findStatByCode(stat.getCode());
         if (uidPk == null) {
            continue;
         }
         stat.setUidPk(uidPk);
      }

      stockStatisticsRepository.save(stats);
   }

   @Override
   public void update(StockStatistics stat) {
      if (stat == null)
         return;

      Long uidPk = stockStatisticsRepository.findStatByCode(stat.getCode());
      if (uidPk != null) {
         stat.setUidPk(uidPk);
      }

      stockStatisticsRepository.save(stat);
   }

   @Override
   public StockStatistics findByCode(String code) {
      return stockStatisticsRepository.findByCode(code);
   }

   @Override
   public List<StockStatistics> getAll() {

      return stockStatisticsRepository.findAll();
   }
}
