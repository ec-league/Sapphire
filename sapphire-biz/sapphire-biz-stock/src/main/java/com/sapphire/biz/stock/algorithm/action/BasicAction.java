/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.sapphire.biz.stock.algorithm.action;

import com.sapphire.biz.stock.algorithm.context.StockContext;
import com.sapphire.common.dal.stock.domain.StockStatistics;
import com.sapphire.common.utils.annotation.Action;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author yunpeng.byp
 * @version $Id: BasicAction.java, v 0.1 2018年01月21日 下午9:32 yunpeng.byp Exp $
 */
@Action("basicAction")
public class BasicAction implements AlgorithmAction {
    /**
     * 具体执行的操作.
     * @param context
     */
    @Override
    public void doAction(StockContext context) {
        List<StockStatistics> statistics = context.getStatistics();

        // 过滤掉所有停牌数据
        statistics = statistics.stream().filter(s -> !s.isStop()).collect(Collectors.toList());

        // 过滤掉所有成长率过低的数据
        statistics = statistics.stream().filter(s -> Double.compare(s.getIncreaseTotal(), 1.6d) > 0).collect(Collectors.toList());

        // 过滤掉创业板
        statistics = statistics.stream().filter(s -> !s.getCode().startsWith("3"))
                .collect(Collectors.toList());

        // 过滤过少的macd周期情况
        statistics = statistics.stream().filter(s -> s.getMacdRiskModel().getCycles().size() > 5).collect(Collectors.toList());

        context.setStatistics(statistics);
    }
}