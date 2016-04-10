package com.sapphire.stock.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Ethan Date: 2016/4/6
 */
public class StockStatics {
   private List<Stock> stocks;

   public StockStatics(List<Stock> stocks) {
      this.stocks = stocks;
   }

   /**
    * 获取Macd值低于0，但是接近0的20支股票
    * 
    * @return
    */
   public List<Stock> getMacdBelowZero() {
      List<Stock> result = new ArrayList<>(stocks.size());

      result.addAll(stocks.stream()
            .filter(stock -> stock.getCurrentMacd() <= 0)
            .collect(Collectors.toList()));

      Collections.sort(result, (o1, o2) -> Double.compare(o2.getCurrentMacd(),
            o1.getCurrentMacd()));

      return result.subList(0, 20);
   }
}
