package com.sapphire.biz.stock.strategy;

import com.sapphire.biz.stock.strategy.factory.MacdStrategyFactory;

import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2016/8/10<br/>
 * Email: byp5303628@hotmail.com
 */
public abstract class StrategyFactory {
    protected List<Strategy> strategies;

    public static StrategyFactory getFactory(StrategyCategory category) {
        StrategyFactory sf;

        switch (category) {
            case MACD:
                sf = new MacdStrategyFactory();
                break;
            default:
                sf = new MacdStrategyFactory();
                break;
        }
        return sf;
    }

    public List<Strategy> buildStrategy() {
        addFillStrategies();
        addFilterStrategies();

        return strategies;
    }

    /**
     * 添加Fill策略连
     */
    protected abstract void addFillStrategies();

    /**
     * 添加Filter策略连
     */
    protected abstract void addFilterStrategies();
}
