package com.sapphire.biz.stock.strategy.filter;

import com.sapphire.common.dal.stock.domain.Stock;

/**
 * Author: EthanPark <br/>
 * Date: 2016/8/3<br/>
 * Email: byp5303628@hotmail.com
 */
public class DeadFilterStrategy extends AbstractFilterStrategy {
    /**
    * 根据条件，如果该过滤掉，返回True，否则返回False
    *
    * @param stock
    * @return
    */
    @Override
    protected boolean shouldFilter(Stock stock) {
        if (stock.getCurrentMacd() < 0 && stock.isGoldPossible())
            return false;
        return true;
    }
}
