package com.sapphire.biz.stock.strategy.filter;

import com.sapphire.biz.stock.domain.Stock;

/**
 * Author: EthanPark <br/>
 * Date: 2016/8/3<br/>
 * Email: byp5303628@hotmail.com
 */
public class StopFilterStrategy extends AbstractFilterStrategy {
    @Override
    protected boolean shouldFilter(Stock stock) {
        if (stock.isStop())
            return true;
        return false;
    }
}
