package com.sapphire.stock.strategy.filter;

import java.util.List;

import com.sapphire.stock.domain.Stock;
import com.sapphire.stock.strategy.FilterStrategy;

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
