package com.sapphire.stock.repository;

import com.sapphire.stock.domain.StockStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: EthanPark <br/>
 * Date: 2016/4/22<br/>
 * Email: byp5303628@hotmail.com
 */
public interface StockStatisticsRepository extends
      JpaRepository<StockStatistics, Long> {

}
