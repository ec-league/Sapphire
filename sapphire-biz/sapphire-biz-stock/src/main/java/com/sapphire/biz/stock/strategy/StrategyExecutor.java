package com.sapphire.biz.stock.strategy;

import com.sapphire.common.dal.stock.domain.Stock;

import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2016/8/1<br/>
 * Email: byp5303628@hotmail.com
 */
public class StrategyExecutor {

    public static void execute(List<Stock> stocks) {
        StrategyFactory sf = StrategyFactory.getFactory(StrategyCategory.MACD);

        List<Strategy> strategies = sf.buildStrategy();

        for (Strategy strategy : strategies) {
            strategy.executeStrategy(stocks);
        }
    }
}
