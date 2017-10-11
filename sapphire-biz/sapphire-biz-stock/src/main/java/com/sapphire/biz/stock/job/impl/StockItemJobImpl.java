package com.sapphire.biz.stock.job.impl;

import com.sapphire.biz.stock.job.SingleThreadJob;
import com.sapphire.biz.stock.job.StockItemJob;
import com.sapphire.biz.stock.service.StockService;
import com.sapphire.common.dal.stock.domain.Stock;
import com.sapphire.common.dal.stock.domain.StockItem;
import com.sapphire.common.integration.sina.SinaStockIntegrationService;
import com.sapphire.common.utils.annotation.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: EthanPark <br/>
 * Date: 2016/10/16<br/>
 * Email: byp5303628@hotmail.com
 */
@Job
public class StockItemJobImpl extends SingleThreadJob implements StockItemJob {

    private static final Logger logger     = LoggerFactory.getLogger(StockItemJobImpl.class);
    private static final int    MACD_START = 12;
    private static final int    MACD_END   = 26;

    @Autowired
    private StockService stockService;

    @Autowired
    private SinaStockIntegrationService sinaStockIntegrationService;

    private void updateStockInternal() {
        logger.info("Update Stock Items Task Begin");

        try {
            List<String> codes = stockService.getAllCodes();

            for (String code : codes) {
                logger.info("Update Stock Code : %s", code);

                handleOneStock(code);
            }

            for (String code : codes) {
                List<StockItem> items = stockService.getLast30Stock(code);
                Stock stock = new Stock(items);
                stock.processAverage();
                stockService.saveAll(items);
            }
        } catch (Exception ex) {
            logger.error("Init error", ex);
        }

        logger.info("Update Stock Item Task Finished!");
    }

    private void handleOneStock(String code) {
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

                stock.calculateMacd(MACD_START, MACD_END, false);

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
            logger.error(String.format("Code :\"%s\" Not Updated%n", code), ex);
        }
    }

    @Override
    public void updateStock() {
        submit(this::updateStockInternal);
    }
}
