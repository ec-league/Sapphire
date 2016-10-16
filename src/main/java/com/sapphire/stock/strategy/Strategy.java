package com.sapphire.stock.strategy;

import java.util.List;

import com.sapphire.stock.domain.Stock;

/**
 * Author: EthanPark <br/>
 * Date: 2016/8/10<br/>
 * Email: byp5303628@hotmail.com
 */
public interface Strategy {

   /**
    * According to the given Stocks, do strategy job.
    *
    * @param stock
    * @return
    */
   void executeStrategy(List<Stock> stockList);
}
