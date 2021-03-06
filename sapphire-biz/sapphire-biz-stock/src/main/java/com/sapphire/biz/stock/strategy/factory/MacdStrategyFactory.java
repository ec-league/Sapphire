package com.sapphire.biz.stock.strategy.factory;

import com.sapphire.biz.stock.strategy.StrategyFactory;
import com.sapphire.biz.stock.strategy.filter.DeadFilterStrategy;
import com.sapphire.biz.stock.strategy.filter.IncreaseTotalFilterStrategy;
import com.sapphire.biz.stock.strategy.filter.StopFilterStrategy;

/**
 * Author: EthanPark <br/>
 * Date: 2016/8/11<br/>
 * Email: byp5303628@hotmail.com
 */
public class MacdStrategyFactory extends StrategyFactory {
    /**
    * 添加Fill策略连
    */
    @Override
    protected void addFillStrategies() {
    }

    @Override
    protected void addFilterStrategies() {
        strategies.add(new IncreaseTotalFilterStrategy());
        strategies.add(new StopFilterStrategy());
        strategies.add(new DeadFilterStrategy());
    }
}
