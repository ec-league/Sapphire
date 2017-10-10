package com.sapphire.common.dal.stock.strategy.filter;

import com.sapphire.common.dal.stock.domain.Stock;
import com.sapphire.common.dal.stock.strategy.FilterStrategy;

import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2016/8/1<br/>
 * Email: byp5303628@hotmail.com
 */
public abstract class AbstractFilterStrategy implements FilterStrategy {

    /**
    * 根据条件，如果该过滤掉，返回True，否则返回False
    * 
    * @param stock
    * @return
    */
    protected abstract boolean shouldFilter(Stock stock);

    /**
    * According to the given Stocks, do filter job and filter all stocks we
    * don't want.
    *
    * @param stockList
    * @return
    */
    @Override
    public void executeStrategy(List<Stock> stockList) {
        for (int index = stockList.size() - 1; index >= 0; index--) {
            if (shouldFilter(stockList.get(index))) {
                stockList.remove(index);
            }
        }
    }
}
