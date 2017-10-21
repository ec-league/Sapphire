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
    /**
     * 获取全部的股票代码
     * @return
     */
    List<String> getAllCodes();

    /**
     * 根据股票代码获取对应的股票
     * @param code
     * @return
     */
    Stock getStockByCode(String code);

    /**
     * 根据股票和时间参数获取对应的股票
     * @param code
     * @param dateFrom
     * @param dateTo
     * @return
     */
    Stock getStockByCodeAndTime(String code, Timestamp dateFrom, Timestamp dateTo);

    List<String> getIndustries();

    /**
     * 持久化所有K线数据
     * @param items
     */
    void saveAll(List<StockItem> items);

    /**
     * 持久化一个K线数据
     * @param item
     * @return
     */
    StockItem save(StockItem item);

    /**
     * 根据股票代码获取指定股票最后30个交易日的股票信息
     * @param code
     * @return
     */
    Stock getLast30Stock(String code);

    /**
     * 根据K线最后300条数据来计算对应的股票数据
     * @param code
     * @return
     */
    Stock getStockForStatistics(String code);
}
