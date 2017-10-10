package com.sapphire.biz.stock.strategy;

import com.sapphire.common.dal.stock.domain.Stock;

import java.util.List;

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
