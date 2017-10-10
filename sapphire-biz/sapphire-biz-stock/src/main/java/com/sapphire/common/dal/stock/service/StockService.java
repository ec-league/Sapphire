package com.sapphire.common.dal.stock.service;

import java.sql.Timestamp;
import java.util.List;

import com.sapphire.common.dal.stock.domain.StockStatics;
import com.sapphire.common.dal.stock.domain.Stock;
import com.sapphire.common.dal.stock.domain.StockItem;

/**
 * Created by Ethan on 2016/3/30.
 */
public interface StockService {
    List<String> getAllCodes();

    Stock getStockByCode(String code);

    Stock getStockByCodeAndTime(String code, Timestamp dateFrom, Timestamp dateTo);

    StockStatics getLastMonthStockStatics();

    StockStatics getLastYearStockStatics();

    StockStatics getLastMonthStockStaticsByIndustry(String industry);

    List<String> getIndustries();

    void saveAll(List<StockItem> items);

    StockItem save(StockItem item);

    StockStatics getStocksByIncreaseTotal();

    List<StockItem> getLatestStockItems();

    StockItem getLatestStockItemByCode(String code);

    List<StockItem> getLast30Stock(String code);
}
