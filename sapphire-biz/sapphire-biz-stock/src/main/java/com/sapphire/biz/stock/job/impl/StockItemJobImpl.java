package com.sapphire.biz.stock.job.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.sapphire.common.utils.TimeUtil;
import com.sapphire.common.utils.annotation.Job;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.biz.stock.domain.Stock;
import com.sapphire.biz.stock.domain.StockItem;
import com.sapphire.biz.stock.job.SingleThreadJob;
import com.sapphire.biz.stock.job.StockItemJob;
import com.sapphire.biz.stock.service.StockService;

/**
 * Author: EthanPark <br/>
 * Date: 2016/10/16<br/>
 * Email: byp5303628@hotmail.com
 */
@Job
public class StockItemJobImpl extends SingleThreadJob implements StockItemJob {

    private static final Logger logger     = LoggerFactory.getLogger(StockItemJobImpl.class);
    private static final int    MACD_START = 12;
    private static final int    MACD_END   = 26;

    @Autowired
    private StockService        stockService;

    private static final String URL_FORMAT = "http://hq.sinajs.cn/list=%s%s";

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
            String url = getUrl(code);
            ClientRequest request = new ClientRequest(url);
            ClientResponse<String> response = request.get(String.class);
            BufferedReader br = new BufferedReader(
                new InputStreamReader(new ByteArrayInputStream(response.getEntity().getBytes())));
            String output = br.readLine();

            StockItem item = toDomain(output);
            item.setCode(code);

            StockItem last = stockService.getLatestStockItemByCode(code);

            if (last.getLogDate().equals(item.getLogDate()))
                return;

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

    private String getUrl(String code) {
        if (code.indexOf("60") == 0)
            return String.format(URL_FORMAT, "sh", code);
        else
            return String.format(URL_FORMAT, "sz", code);
    }

    private StockItem toDomain(String output) {
        StockItem item = new StockItem();

        String data = output.substring(output.indexOf('"') + 1, output.indexOf(';') - 1);

        String[] datas = data.split(",");

        item.setName(datas[0]);
        item.setStartPrice(Double.parseDouble(datas[1]));
        item.setEndPrice(Double.parseDouble(datas[3]));
        item.setHighestPrice(Double.parseDouble(datas[4]));
        item.setLowestPrice(Double.parseDouble(datas[5]));

        item.setTrading(Double.parseDouble(datas[8]));
        item.setTradingValue(Double.parseDouble(datas[9]));

        item.setStop(Double.compare(0d, item.getStartPrice()) == 0);
        item.setLast(true);
        item.setLogDate(TimeUtil.fromStockWebString(datas[30]));

        item.setEma12(item.getEndPrice());
        item.setEma26(item.getEndPrice());
        if (!item.isStop())
            item.setIncreaseRate((item.getEndPrice() / Double.parseDouble(datas[2]) - 1) * 100);
        else {
            item.setStartPrice(item.getEndPrice());
            item.setHighestPrice(item.getEndPrice());
            item.setLowestPrice(item.getEndPrice());
        }
        return item;
    }

    @Override
    public void updateStock() {
        submit(this::updateStockInternal);
    }
}
