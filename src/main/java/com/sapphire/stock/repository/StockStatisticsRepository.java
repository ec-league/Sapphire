package com.sapphire.stock.repository;

import java.util.List;

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

   @Query(value = "SELECT CODE FROM STOCK_STATISTICS ORDER BY INCREASE_TOTAL DESC LIMIT 200", nativeQuery = true)
   List<String> findByIncrease();

   @Query("select s from StockStatistics as s where s.code = ?1")
   StockStatistics findByCode(String code);
}
