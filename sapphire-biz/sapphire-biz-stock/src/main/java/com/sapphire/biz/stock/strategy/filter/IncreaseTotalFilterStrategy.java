package com.sapphire.biz.stock.strategy.filter;

import com.sapphire.biz.stock.domain.Stock;

/**
 * Author: EthanPark <br/>
 * Date: 2016/8/1<br/>
 * Email: byp5303628@hotmail.com
 */
public class IncreaseTotalFilterStrategy extends AbstractFilterStrategy {

    private static final double STANDARD_INCREASE_TOTAL = 1.6 * 1.6 * 1.6 * 1.6;

    @Override
    protected boolean shouldFilter(Stock stock) {
        if (stock.getIncreaseTotal() < STANDARD_INCREASE_TOTAL)
            return true;
        return false;
    }
}
