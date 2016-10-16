package com.sapphire.stock.service;

import java.util.List;

import com.sapphire.stock.domain.StockStatistics;

/**
 * Author: EthanPark <br/>
 * Date: 2016/4/22<br/>
 * Email: byp5303628@hotmail.com
 */
public interface StockStatisticsService {
   void update(List<StockStatistics> stats);

   StockStatistics findByCode(String code);

   List<StockStatistics> getAll();
}
