/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.sapphire.biz.stock.algorithm.action;

import com.sapphire.biz.stock.algorithm.context.StockContext;
import com.sapphire.common.dal.stock.domain.StockStatistics;
import com.sapphire.common.utils.annotation.Action;

import java.util.List;

/**
 * 判断是否该卖出的Action
 * @author yunpeng.byp
 * @version $Id: SellJudgeAction.java, v 0.1 2018年01月21日 下午5:23 yunpeng.byp Exp $
 */
@Action("sellJudgeAction")
public class SellJudgeAction implements AlgorithmAction {
    /**
     * 具体执行的操作.
     * @param context
     */
    @Override
    public void doAction(StockContext context) {
        List<StockStatistics> statistics = context.getStatistics();

        context.setStatistics(statistics);
    }
}