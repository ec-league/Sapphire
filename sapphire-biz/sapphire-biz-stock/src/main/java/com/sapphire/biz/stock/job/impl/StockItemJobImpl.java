package com.sapphire.biz.stock.job.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.biz.stock.algorithm.StockAlgorithm;
import com.sapphire.biz.stock.job.SingleThreadJob;
import com.sapphire.biz.stock.job.StockItemJob;
import com.sapphire.biz.stock.service.StockService;
import com.sapphire.common.dal.stock.domain.Stock;
import com.sapphire.common.dal.stock.domain.StockItem;
import com.sapphire.common.integration.dingtalk.constant.DingTalkMessageType;
import com.sapphire.common.integration.sina.SinaStockIntegrationService;
import com.sapphire.common.utils.annotation.Job;

/**
 * @author yunpeng.byp
 * Date: 2016/10/16<br/>
 * Email: byp5303628@hotmail.com
 */
@Job
public class StockItemJobImpl extends SingleThreadJob implements StockItemJob {

    private static final Logger         logger     = LoggerFactory
        .getLogger(StockItemJobImpl.class);
    private static final int            MACD_START = 12;
    private static final int            MACD_END   = 26;

    private static final String         JOB_NAME   = "个股详情信息刷新任务";

    private StockService                stockService;

    private StockAlgorithm              stockAlgorithm;

    private SinaStockIntegrationService sinaStockIntegrationService;

    private void updateStockInternal() {
        logger.info("Update Stock Items Task Begin");
        finishMsg.append("## 个股详情信息: ").append("\n>");
        StringBuilder sb = new StringBuilder();
        try {
            List<String> codes = stockService.getAllCodes();

            for (String code : codes) {
                logger.info(String.format("Update Stock Code : %s", code));

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

        logger.info("Update Stock Item Task Finished!");
        pusher.push(jobName(), finishMsg.toString(), DingTalkMessageType.MARKDOWN);
    }

    private void handleOneStock(String code, StringBuilder sb) {
        try {
            StockItem item = sinaStockIntegrationService.getStock(code);

            StockItem last = stockService.getLatestStockItemByCode(code);

            if (last.getLogDate().equals(item.getLogDate())) {
                return;
            }

            if (last.isStop()) {
                last.updateItem(item, MACD_START, MACD_END);
                stockService.save(last);
            } else {
                last.setLast(false);

                List<StockItem> items = new ArrayList<>();
                items.add(last);
                items.add(item);

                Stock stock = new Stock(items);

                stockAlgorithm.calculateMacd(stock, false);

                if (item.isStop()) {
                    item.setMacdDea(last.getMacdDea());
                    item.setMacdDiff(last.getMacdDiff());
                    item.setMacd(last.getMacd());
                    item.setEma12(last.getEma12());
                    item.setEma26(last.getEma26());
                }

                stockService.saveAll(items);
            }
        } catch (Exception ex) {
            String errorMsg = String.format("Code :\"%s\" Not Updated%n", code);
            logger.error(errorMsg, ex);
            sb.append(errorMsg).append(";");
        }
    }

    @Override
    public void updateStock() {
        submit(this::updateStockInternal);
    }

    /**
     * JOB的名字,完成后进行推送钉钉
     * @return
     */
    @Override
    protected String jobName() {
        return JOB_NAME;
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
}
