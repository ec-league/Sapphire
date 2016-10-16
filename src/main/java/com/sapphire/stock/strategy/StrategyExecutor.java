package com.sapphire.stock.strategy;

import java.util.List;

import com.sapphire.stock.domain.Stock;

/**
 * Author: EthanPark <br/>
 * Date: 2016/8/1<br/>
 * Email: byp5303628@hotmail.com
 */
public class StrategyExecutor {

   public static void execute(List<Stock> stocks) {
      StrategyFactory sf =
            StrategyFactory.getFactory(StrategyFactory.StrategyCategory.MACD);

      List<Strategy> strategies = sf.buildStrategy();

      for (Strategy strategy : strategies) {
         strategy.executeStrategy(stocks);
      }
   }
}
