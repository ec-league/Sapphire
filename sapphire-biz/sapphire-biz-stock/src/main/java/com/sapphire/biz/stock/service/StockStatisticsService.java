package com.sapphire.biz.stock.service;

import java.util.List;

import com.sapphire.common.dal.stock.domain.StockStatistics;

/**
 * @author: EthanPark <br/>
 * Date: 2016/4/22<br/>
 * Email: byp5303628@hotmail.com
 */
public interface StockStatisticsService {
    /**
     * 更新股票统计数据
     * @param stat
     */
    void update(StockStatistics stat);

    /**
     * 根据股票代码查找股票数据
     * @param code
     * @return
     */
    StockStatistics findByCode(String code);

    /**
     * 获取所有的股票统计数据,包括风控模型
     * @return
     */
    List<StockStatistics> getAll();
}
