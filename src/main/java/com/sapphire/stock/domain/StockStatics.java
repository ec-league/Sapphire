package com.sapphire.stock.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Ethan Date: 2016/4/6
 */
public class StockStatics {
   private static final int LIMIT_SIZE = 20;
   private List<Stock> stocks;

   public StockStatics(List<Stock> stocks) {
      this.stocks =
            stocks.stream().filter(stock -> !stock.isStop())
                  .collect(Collectors.toList());
   }

   /**
    * 获取为死叉的股票，按Macd值大小排序
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

      return result.subList(0,
            result.size() > LIMIT_SIZE ? LIMIT_SIZE : result.size());
   }

   /**
    * 获取金叉的股票，按当前的diff值排序
    * 
    * @return
    */
   public List<Stock> getMacdUpZero() {
      List<Stock> result =
            stocks.stream().filter(stock -> stock.isTodayPlus())
                  .collect(Collectors.toList());

      Collections.sort(result, (o1, o2) -> Double.compare(o1.getCurrentDiff(),
            o2.getCurrentDiff()));

      return result.subList(0,
            result.size() > LIMIT_SIZE ? LIMIT_SIZE : result.size());
   }

   /**
    * 根据Macd指标进行排序，寻找最低的20个
    * 
    * @return
    */
   public List<String> getLowestMacd() {
      List<Stock> result =
            stocks.stream().filter(stock -> stock.getCurrentMacd() < 0)
                  .filter(stock -> stock.isUpper())
                  .collect(Collectors.toList());
      Collections.sort(result, (o1, o2) -> Double.compare(o1.getCurrentMacd(),
            o2.getCurrentMacd()));

      int limit = result.size() < 20 ? result.size() : LIMIT_SIZE;

      List<String> resultCodes = new ArrayList<>(limit);

      for (int i = 0; i < limit; i++) {
         resultCodes.add(result.get(i).getCode());
      }

      return resultCodes;
   }
}
