package com.sapphire.stock.repository;

import com.sapphire.stock.domain.StockItem;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by Ethan on 2016/3/30.
 */
public interface StockItemRepository extends CrudRepository<StockItem, Long> {
//   @Query("select s from StockItem as s where s.code=?1 order by s.uidPk")
//   List<StockItem> getStocksByCode(String code);

//   @Query("select s from StockItem as s where s.code=?1 order by s.uidPk desc")
//   StockItem getLastestStockByCode(String code);
}
