package com.sapphire.biz.stock.repository;

import com.sapphire.biz.stock.domain.StockStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2016/4/22<br/>
 * Email: byp5303628@hotmail.com
 */
public interface StockStatisticsRepository extends JpaRepository<StockStatistics, String> {

    @Query(value = "SELECT CODE FROM STOCK_STATISTICS ORDER BY INCREASE_TOTAL DESC LIMIT 200", nativeQuery = true)
    List<String> findByIncrease();

    @Query("select s from StockStatistics as s where s.code = ?1")
    StockStatistics findByCode(String code);
}
