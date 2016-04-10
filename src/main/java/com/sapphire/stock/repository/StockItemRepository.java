package com.sapphire.stock.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sapphire.stock.domain.StockItem;


/**
 * Created by Ethan on 2016/3/30.
 */
public interface StockItemRepository extends JpaRepository<StockItem, Long> {
   @Query("select s from StockItem as s where s.code=?1 order by s.uidPk")
   List<StockItem> getStocksByCode(String code);

   @Query(value = "SELECT DISTINCT CODE FROM STOCK_ITEM", nativeQuery = true)
   List<String> getCodes();

   @Query(value = "select s from StockItem as s where s.code = :code and s.date >= :dateFrom and s.date <= :dateTo")
   List<StockItem> getStockByCodeAndTime(@Param("code") String code,
         @Param("dateFrom") Timestamp from, @Param("dateTo") Timestamp to);
}
