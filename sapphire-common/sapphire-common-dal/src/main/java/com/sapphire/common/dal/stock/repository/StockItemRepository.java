package com.sapphire.common.dal.stock.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sapphire.common.dal.stock.domain.StockItem;

/**
 * @author : Ethan
 */
public interface StockItemRepository extends JpaRepository<StockItem, Long> {
    @Query("select s from StockItem as s where s.code=?1 order by s.uidPk")
    List<StockItem> getStocksByCode(String code);

    @Query(value = "SELECT DISTINCT CODE FROM STOCK_ITEM", nativeQuery = true)
    List<String> getCodes();

    @Query(value = "select s from StockItem as s where s.code = :code and s.logDate >= :dateFrom and s.logDate <= :dateTo")
    List<StockItem> getStockByCodeAndTime(@Param("code") String code,
                                          @Param("dateFrom") Timestamp from,
                                          @Param("dateTo") Timestamp to);

    @Query(value = "SELECT DISTINCT INDUSTRY FROM STOCK_ITEM WHERE INDUSTRY != ''", nativeQuery = true)
    List<String> getIndustries();

    @Query(value = "SELECT * FROM STOCK_ITEM WHERE CODE = ?1 ORDER BY UIDPK DESC LIMIT 250", nativeQuery = true)
    List<StockItem> getLast250Items(String code);

    @Query(value = "SELECT * FROM STOCK_ITEM WHERE CODE = ?1 ORDER BY UIDPK DESC LIMIT 30", nativeQuery = true)
    List<StockItem> getLast30Items(String code);

    @Query(value = "SELECT * FROM STOCK_ITEM WHERE CODE = ?1 ORDER BY UIDPK DESC LIMIT 300", nativeQuery = true)
    List<StockItem> getLast300Items(String code);
}
