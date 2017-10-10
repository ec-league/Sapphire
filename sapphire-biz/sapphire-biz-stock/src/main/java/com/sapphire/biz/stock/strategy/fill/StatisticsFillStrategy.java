package com.sapphire.biz.stock.strategy.fill;

import com.sapphire.biz.stock.cache.StockStatisticsCache;
import com.sapphire.common.dal.stock.domain.Stock;
import com.sapphire.common.dal.stock.domain.StockStatistics;
import com.sapphire.biz.stock.strategy.FillStrategy;
import com.sapphire.common.utils.cache.CacheService;

import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2016/8/10<br/>
 * Email: byp5303628@hotmail.com
 */
public class StatisticsFillStrategy implements FillStrategy {

    private static final StockStatisticsCache cache = CacheService
        .getCache(StockStatisticsCache.class);

    /**
    * According to the given Stocks, do strategy job.
    *
    * @param stockList
    * @return
    */
    @Override
    public void executeStrategy(List<Stock> stockList) {
        for (Stock stock : stockList) {
            StockStatistics statistics = cache.getStatisticsByCode(stock.getCode());

            stock.update(statistics);
        }
    }
}
