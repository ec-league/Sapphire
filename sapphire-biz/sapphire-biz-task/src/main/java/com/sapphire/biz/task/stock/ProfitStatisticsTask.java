/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.sapphire.biz.task.stock;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sapphire.biz.stock.service.StockService;
import com.sapphire.common.dal.stock.domain.Stock;
import com.sapphire.common.dal.stock.domain.Trade;
import com.sapphire.common.dal.stock.repository.TradeRepository;
import com.sapphire.common.integration.dingtalk.constant.DingTalkMessageType;
import com.sapphire.common.integration.dingtalk.pusher.DingTalkMessagePusher;
import com.sapphire.common.task.domain.SapphireTask;
import com.sapphire.common.utils.annotation.Task;

/**
 * 收益统计计算,该收益忽略印花税以及手续费的计算
 *  <li>1. 计算交易完结股票收益总值</li>
 *  <li>2. 计算新增股票当日收益</li>
 * @author yunpeng.byp
 * @version $Id: ProfitStatisticsTask.java, v 0.1 2018年01月13日 下午7:48 yunpeng.byp Exp $
 */
@Task
public class ProfitStatisticsTask implements SapphireTask {
    private static final Logger   logger   = LoggerFactory.getLogger(ProfitStatisticsTask.class);

    private static final String   JOB_NAME = "收益统计计算任务";

    private TradeRepository       tradeRepository;

    private StockService          stockService;

    private DingTalkMessagePusher pusher;

    /**
     * 调度任务的具体执行.
     */
    @Override
    public void execute() {
        if (logger.isInfoEnabled()) {
            logger.info("ProfitStatisticsTask Begin");
        }

        long start = System.currentTimeMillis();

        double outProfit = 0d;
        double origin = 0d;
        // 计算已经完成的交易的总值
        List<Trade> trades = tradeRepository.getAllFinishTrades();

        for (Trade trade : trades) {
            origin += trade.getInPrice() * trade.getCount();
            outProfit += (trade.getOutPrice() - trade.getInPrice()) * trade.getCount();
        }

        double inProfit = 0d;
        // 计算买入还未卖出收益总值
        List<Trade> inTrades = tradeRepository.getAllHoldStockCodes();

        for (Trade trade : inTrades) {
            Stock stock = stockService.getStockByCode(trade.getStockCode());

            inProfit += trade.getCount() * (stock.getEndPrice() - trade.getInPrice());
            origin += trade.getInPrice() * trade.getCount();
        }

        long end = System.currentTimeMillis();

        String msg = constructMsg(outProfit, inProfit, origin, end - start);

        pusher.push(JOB_NAME, msg, DingTalkMessageType.MARKDOWN);
    }

    private String constructMsg(double outProfit, double inProfit, double origin, long duration) {
        StringBuilder sb = new StringBuilder();
        sb.append("## 每日收益统计: \n").append("* 以交易的股票,收益为: ").append(String.format("%.2f", outProfit))
            .append(" 元\n");
        sb.append("* 未交易的股票,收益为: ").append(String.format("%.2f", inProfit)).append(" 元\n");
        sb.append("* 总投入资金: ").append(String.format("%.2f", origin)).append(" 元\n");
        sb.append("* 收益总计: ").append(String.format("%.2f", outProfit + inProfit)).append(" 元\n");
        sb.append("* 收益百分比: ").append(String.format("%.2f", (outProfit + inProfit) / origin * 100))
            .append("%\n");
        sb.append("> ").append("任务执行时间 :").append(duration).append(" ms.");
        return sb.toString();
    }

    /**
     * Setter method for property <tt>tradeRepository</tt>.
     *
     * @param tradeRepository  value to be assigned to property tradeRepository
     */
    public void setTradeRepository(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    /**
     * Setter method for property <tt>stockService</tt>.
     *
     * @param stockService  value to be assigned to property stockService
     */
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * Setter method for property <tt>pusher</tt>.
     *
     * @param pusher  value to be assigned to property pusher
     */
    public void setPusher(DingTalkMessagePusher pusher) {
        this.pusher = pusher;
    }
}