package com.sapphire.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapphire.stock.domain.StockStatistics;

/**
 * Author: EthanPark <br/>
 * Date: 2016/4/22<br/>
 * Email: byp5303628@hotmail.com
 */
public interface StockStatisticsRepository extends
      JpaRepository<StockStatistics, Long> {

   @Query(value = "SELECT UIDPK from STOCK_STATISTICS WHERE CODE = ?1", nativeQuery = true)
   Long findStatByCode(String code);
}
