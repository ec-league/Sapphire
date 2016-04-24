package com.sapphire.stock.service;

import com.sapphire.stock.domain.StockStatistics;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2016/4/22<br/>
 * Email: byp5303628@hotmail.com
 */
public interface StockStatisticsService {
   void update(List<StockStatistics> stats);

   StockStatistics findByCode(String code);
}
