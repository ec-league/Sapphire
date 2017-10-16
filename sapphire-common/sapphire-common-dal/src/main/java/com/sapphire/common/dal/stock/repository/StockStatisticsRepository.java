package com.sapphire.common.dal.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapphire.common.dal.stock.domain.StockStatistics;

/**
 * @author: EthanPark <br/>
 * Date: 2016/4/22<br/>
 * Email: byp5303628@hotmail.com
 */
public interface StockStatisticsRepository extends JpaRepository<StockStatistics, String> {
    /**
     * 根据股票代码获取对应的股票统计数据
     * @param code
     * @return
     */
    @Query("select s from StockStatistics as s where s.code = ?1")
    StockStatistics findByCode(String code);

    /**
     * 获取所有的股票数据,根据Code进行排序
     * @return
     */
    @Query(value = "SELECT * FROM STOCK_STATISTICS ORDER BY CODE", nativeQuery = true)
    List<StockStatistics> getAllOrderByCode();
}
