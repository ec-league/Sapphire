package com.sapphire.biz.task.stock;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.biz.stock.algorithm.StockAlgorithm;
import com.sapphire.biz.stock.service.StockService;
import com.sapphire.common.dal.stock.domain.Stock;
import com.sapphire.common.dal.stock.domain.StockItem;
import com.sapphire.common.integration.dingtalk.constant.DingTalkMessageType;
import com.sapphire.common.integration.dingtalk.pusher.DingTalkMessagePusher;
import com.sapphire.common.integration.sina.SinaStockIntegrationService;
import com.sapphire.common.task.domain.SapphireTask;
import com.sapphire.common.utils.annotation.Task;

/**
 * @author yunpeng.byp
 * Date: 2016/10/16<br/>
 * Email: byp5303628@hotmail.com
 */
@Task
public class StockItemTask implements SapphireTask {

    private static final Logger         logger   = LoggerFactory.getLogger(StockItemTask.class);

    private static final String         JOB_NAME = "个股详情信息刷新任务";

    private StockService                stockService;

    private StockAlgorithm              stockAlgorithm;

    private SinaStockIntegrationService sinaStockIntegrationService;
    private DingTalkMessagePusher       pusher;

    /**
     * 调度任务的具体执行.
     */
    @Override
    public void execute() {
        if (logger.isInfoEnabled()) {
            logger.info("Update Stock Items Task Begin");
        }
        StringBuilder finishMsg = new StringBuilder(50);

        long start = System.currentTimeMillis();

        finishMsg.append("## 个股详情信息: \n>");
        StringBuilder sb = new StringBuilder();
        try {
            List<String> codes = stockService.getAllCodes();

            for (String code : codes) {
                handleOneStock(code, sb);
            }
        } catch (Exception ex) {
            logger.error("Init error", ex);
        }

        if (sb.length() == 0) {
            finishMsg.append("个股详情刷新成功");
        } else {
            finishMsg.append(sb.toString());
        }

        long end = System.currentTimeMillis();

        finishMsg.append("\n").append("> ").append("任务执行时间 : ").append(end - start).append(" ms.");

        if (logger.isInfoEnabled()) {
            logger.info("Update Stock Item Task Finished!");
        }
        pusher.push(JOB_NAME, finishMsg.toString(), DingTalkMessageType.MARKDOWN);
    }

    private void handleOneStock(String code, StringBuilder sb) {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("Start to Handle stock : " + code);
            }
            StockItem item = sinaStockIntegrationService.getStock(code);

            Stock stockTemp = stockService.getLast30Stock(code);

            List<StockItem> stockItems = stockTemp.getStockItems();

            StockItem last = stockItems.get(stockItems.size() - 1);

            // 1. 股票当天停盘,直接更新最后一条的LogDate
            if (item.isStop()) {
                last.setLogDate(item.getLogDate());
                stockService.save(last);
            } else {
                // 当天重复的K线数据,重新计算
                if (last.getLogDate().getTime() == item.getLogDate().getTime()) {
                    saveTodayData(item, stockItems);
                    return;
                }

                //region 非当天数据K线计算
                last.setLast(false);

                List<StockItem> stockItems1 = new ArrayList<>(2);

                stockItems1.add(last);
                stockItems1.add(item);

                Stock stock = new Stock(stockItems1);

                stockAlgorithm.calculateMacd(stock, false);

                if (item.isStop()) {
                    item.setMacdDea(last.getMacdDea());
                    item.setMacdDiff(last.getMacdDiff());
                    item.setMacd(last.getMacd());
                    item.setEma12(last.getEma12());
                    item.setEma26(last.getEma26());
                }

                stockService.saveAll(stockItems1);
                //endregion
            }
        } catch (Exception ex) {
            String errorMsg = String.format("Code :\"%s\" Not Updated%n, Exception : %s", code, ex);
            logger.error(errorMsg, ex);
            sb.append(errorMsg).append(";");
        }
    }

    /**
     * 重复计算当天数据的处理.
     * @param current
     * @param stockItems
     */
    private void saveTodayData(StockItem current, List<StockItem> stockItems) {
        StockItem last = stockItems.get(stockItems.size() - 1);
        current.setUidPk(last.getUidPk());
        List<StockItem> items = new ArrayList<>();

        if (stockItems.size() > 1) {
            items.add(stockItems.get(stockItems.size() - 2));
            items.add(current);
            stockAlgorithm.calculateMacd(new Stock(items), false);
        } else {
            items.add(current);
            stockAlgorithm.calculateMacd(new Stock(items), true);
        }
        stockService.save(current);
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
     * Setter method for property <tt>stockAlgorithm</tt>.
     *
     * @param stockAlgorithm  value to be assigned to property stockAlgorithm
     */
    @Autowired
    public void setStockAlgorithm(StockAlgorithm stockAlgorithm) {
        this.stockAlgorithm = stockAlgorithm;
    }

    /**
     * Setter method for property <tt>sinaStockIntegrationService</tt>.
     *
     * @param sinaStockIntegrationService  value to be assigned to property sinaStockIntegrationService
     */
    @Autowired
    public void setSinaStockIntegrationService(SinaStockIntegrationService sinaStockIntegrationService) {
        this.sinaStockIntegrationService = sinaStockIntegrationService;
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
