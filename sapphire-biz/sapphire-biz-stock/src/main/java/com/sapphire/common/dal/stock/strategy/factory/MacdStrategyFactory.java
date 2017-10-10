package com.sapphire.common.dal.stock.strategy.factory;

import com.sapphire.common.dal.stock.strategy.StrategyFactory;
import com.sapphire.common.dal.stock.strategy.fill.StatisticsFillStrategy;
import com.sapphire.common.dal.stock.strategy.filter.DeadFilterStrategy;
import com.sapphire.common.dal.stock.strategy.filter.IncreaseTotalFilterStrategy;
import com.sapphire.common.dal.stock.strategy.filter.StopFilterStrategy;

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
        strategies.add(new StatisticsFillStrategy());
    }

    @Override
    protected void addFilterStrategies() {
        strategies.add(new IncreaseTotalFilterStrategy());
        strategies.add(new StopFilterStrategy());
        strategies.add(new DeadFilterStrategy());
    }
}
