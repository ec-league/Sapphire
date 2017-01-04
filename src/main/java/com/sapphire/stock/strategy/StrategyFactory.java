package com.sapphire.stock.strategy;

import java.util.List;

import com.sapphire.stock.strategy.factory.MacdStrategyFactory;

/**
 * Author: EthanPark <br/>
 * Date: 2016/8/10<br/>
 * Email: byp5303628@hotmail.com
 */
public abstract class StrategyFactory {
   protected List<Strategy> strategies;

   public static StrategyFactory getFactory(StrategyCategory category) {
      StrategyFactory sf;

      switch (category) {
      case MACD:
         sf = new MacdStrategyFactory();
         break;
      default:
         sf = new MacdStrategyFactory();
      }
      return sf;
   }

   public List<Strategy> buildStrategy() {
      addFillStrategies();
      addFilterStrategies();

      return strategies;
   }

   /**
    * 添加Fill策略连
    */
   protected abstract void addFillStrategies();

   /**
    * 添加Filter策略连
    */
   protected abstract void addFilterStrategies();
}
