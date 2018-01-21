/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.sapphire.biz.stock.algorithm.action;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.biz.stock.algorithm.context.StockContext;
import com.sapphire.biz.stock.cache.StockCache;
import com.sapphire.common.dal.stock.domain.Stock;
import com.sapphire.common.dal.stock.domain.StockItem;
import com.sapphire.common.dal.stock.domain.StockStatistics;
import com.sapphire.common.utils.annotation.Action;

/**
 *
 * @author yunpeng.byp
 * @version $Id: BuyJudgeAction.java, v 0.1 2018年01月21日 下午5:26 yunpeng.byp Exp $
 */
@Action("buyJudgeAction")
public class BuyJudgeAction implements AlgorithmAction {

    private StockCache stockCache;

    /**
     * 具体执行的操作.
     * @param context
     */
    @Override
    public void doAction(StockContext context) {
        List<StockStatistics> statistics = context.getStatistics();

        // 过滤掉刚进入死叉的股票
        statistics.stream().filter(s -> {
            // 如果死叉时间,超过平均死叉时间的一半
            return true;
        }).collect(Collectors.toList());

        statistics.stream().filter(s -> stays(s)).collect(Collectors.toList());

        context.setStatistics(statistics);
    }

    /**
     * 股票是否保留
     * @return
     */
    private boolean stays(StockStatistics statistics) {
        // 最新macd周期内的增幅
        double increase = statistics.getMacdRiskModel().getCycles().get(0).getIncreaseRate();

        // 收益满足要求,可以考虑抛售
        if (increase > statistics.getMacdRiskModel().getAverageRate()) {
            return true;
        }

        // 收益不满足要求
        String code = statistics.getCode();

        Stock stock = stockCache.getStockByCode(code);

        // 连续两天macd下降
        List<StockItem> items = stock.getStockItems();

        assert items.size() > 3;

        int index = items.size() - 1;
        // 连续2天下降趋势,卖出
        if (items.get(index).getMacd() < items.get(index - 1).getMacd()
            && items.get(index - 1).getMacd() < items.get(index - 2).getMacd()) {
            return true;
        }

        return false;
    }

    /**
     * Setter method for property <tt>stockCache</tt>.
     *
     * @param stockCache  value to be assigned to property stockCache
     */
    @Autowired
    public void setStockCache(StockCache stockCache) {
        this.stockCache = stockCache;
    }
}