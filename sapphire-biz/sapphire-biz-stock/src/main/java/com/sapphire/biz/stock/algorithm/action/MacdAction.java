/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.biz.stock.algorithm.action;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.sapphire.biz.stock.algorithm.context.StockContext;
import com.sapphire.common.dal.stock.domain.StockStatistics;

/**
 *
 * @author yunpeng.byp
 * @version $Id: MacdAction.java, v 0.1 2017年10月21日 下午5:05 yunpeng.byp Exp $
 */
public class MacdAction implements AlgorithmAction {

    /**
     * 具体执行的操作.
    
     * @param context
     */
    @Override
    public void doAction(StockContext context) {
        List<StockStatistics> statistics = context.getStatistics();
        // 过滤掉所有停牌数据
        statistics = statistics.stream().filter(s -> !s.isStop()).collect(Collectors.toList());

        // 过滤掉所有金叉数据
        statistics = statistics.stream().filter(s -> Double.compare(s.getCurrentMacd(), 0d) < 0)
            .collect(Collectors.toList());

        // 过滤掉所有成长率过低的数据
        statistics = statistics.stream().filter(s -> Double.compare(s.getIncreaseTotal(), 1.6d) > 0)
            .collect(Collectors.toList());

        // 过滤掉历史比例过高的数据
        statistics = statistics.stream()
            .filter(s -> Double.compare(s.getMacdRiskModel().getPricePercentage(), 0.7) < 0)
            .collect(Collectors.toList());

        // 过滤掉diff > 0 的数据
        statistics = statistics.stream().filter(s -> Double.compare(s.getCurrentDiff(), 0d) < 0)
            .collect(Collectors.toList());

        // 筛选可金叉数据
        statistics = statistics.stream().filter(s -> s.isGoldPossible())
            .collect(Collectors.toList());

        // 过滤掉上一个macd周期大涨的数据(可能处于周线死叉)
        statistics = statistics.stream()
            .filter(s -> Double.compare(s.getMacdRiskModel().lastCycle().getIncreaseRate(),
                s.getMacdRiskModel().getAverageRate()) < 0)
            .collect(Collectors.toList());

        Collections.sort(statistics, new Comparator<StockStatistics>() {
            @Override
            public int compare(StockStatistics o1, StockStatistics o2) {
                return Double.compare(o2.getIncreaseTotal(), o1.getIncreaseTotal());
            }
        });

        context.setStatistics(statistics);
    }
}