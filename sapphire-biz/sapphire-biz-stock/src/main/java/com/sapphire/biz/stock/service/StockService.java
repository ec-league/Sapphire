package com.sapphire.biz.stock.service;

import java.sql.Timestamp;
import java.util.List;

import com.sapphire.common.dal.stock.domain.Stock;
import com.sapphire.common.dal.stock.domain.StockItem;

/**
 * @author Ethan
 * @date 2016/3/30.
 */
public interface StockService {
    List<String> getAllCodes();

    Stock getStockByCode(String code);

    Stock getStockByCodeAndTime(String code, Timestamp dateFrom, Timestamp dateTo);

    List<String> getIndustries();

    void saveAll(List<StockItem> items);

    StockItem save(StockItem item);

    StockItem getLatestStockItemByCode(String code);

    List<StockItem> getLast30Stock(String code);

    /**
     * 根据K线最后300条数据来计算对应的股票数据
     * @param code
     * @return
     */
    Stock getStockForStatistics(String code);

    /**
     * 获取所有最后一天的股票项目
     * @return
     */
    List<StockItem> getAllLastStockItem();
}
