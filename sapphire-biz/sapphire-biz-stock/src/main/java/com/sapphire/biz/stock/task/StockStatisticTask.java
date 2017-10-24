package com.sapphire.biz.stock.task;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.biz.stock.algorithm.StockAlgorithm;
import com.sapphire.biz.stock.service.StockService;
import com.sapphire.biz.stock.service.StockStatisticsService;
import com.sapphire.common.dal.stock.domain.Stock;
import com.sapphire.common.dal.stock.domain.StockStatistics;
import com.sapphire.common.integration.dingtalk.constant.DingTalkMessageType;
import com.sapphire.common.integration.dingtalk.pusher.DingTalkMessagePusher;
import com.sapphire.common.task.SapphireTask;
import com.sapphire.common.task.SapphireTaskManager;
import com.sapphire.common.task.stock.constant.StockConstants;
import com.sapphire.common.utils.annotation.Job;

/**
 * @author: EthanPark <br/>
 * Date: 2016/10/16<br/>
 * Email: byp5303628@hotmail.com
 */
@Job
public class StockStatisticTask implements SapphireTask {

    private static final Logger    logger   = LoggerFactory.getLogger(StockStatisticTask.class);

    private StockService           stockService;

    private StockStatisticsService statisticsService;

    private StockAlgorithm         stockAlgorithm;

    private static final String    JOB_NAME = "个股统计信息刷新任务";

    private DingTalkMessagePusher  pusher;

    @PostConstruct
    public void init() {
        SapphireTaskManager.register(StockConstants.STOCK_STATISTIC_TASK_NAME, this);
    }

    @Override
    public void execute() {
        logger.info("Update Stock Statistics Task Begin");

        long start = System.currentTimeMillis();

        StringBuilder finishMsg = new StringBuilder(50);

        List<String> codes = stockService.getAllCodes();
        List<StockStatistics> statistics = new ArrayList<>(codes.size());

        for (String code : codes) {
            handleStat(code, statistics);
        }

        statisticsService.updateAll(statistics);

        logger.info("Update Stock Statistics Task Finished!");
        finishMsg.append("## 个股统计信息: ").append("\n>").append("刷新成功");

        long end = System.currentTimeMillis();

        finishMsg.append("\n").append("> ").append("任务执行时间 : ").append(end - start).append(" ms.");
        pusher.push(JOB_NAME, finishMsg.toString(), DingTalkMessageType.MARKDOWN);
    }

    private void handleStat(String code, List<StockStatistics> statistics) {
        Stock stock = stockService.getStockForStatistics(code);

        if (stock == null) {
            return;
        }

        StockStatistics stat = stockAlgorithm.calculate(stock);

        statistics.add(stat);
    }

    /**
     * Setter method for property <tt>stockService</tt>.
     *
     * @param stockService  value to be assigned to property stockService
     */
    @Autowired
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * Setter method for property <tt>statisticsService</tt>.
     *
     * @param statisticsService  value to be assigned to property statisticsService
     */
    @Autowired
    public void setStatisticsService(StockStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    /**
     * Setter method for property <tt>stockAlgorithm</tt>.
     *
     * @param stockAlgorithm  value to be assigned to property stockAlgorithm
     */
    @Autowired
    public void setStockAlgorithm(StockAlgorithm stockAlgorithm) {
        this.stockAlgorithm = stockAlgorithm;
    }

    /**
     * Setter method for property <tt>pusher</tt>.
     *
     * @param pusher  value to be assigned to property pusher
     */
    @Autowired
    public void setPusher(DingTalkMessagePusher pusher) {
        this.pusher = pusher;
    }
}
