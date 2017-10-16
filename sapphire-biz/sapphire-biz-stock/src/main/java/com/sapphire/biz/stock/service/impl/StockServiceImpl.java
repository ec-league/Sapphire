package com.sapphire.biz.stock.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapphire.biz.stock.service.StockService;
import com.sapphire.common.dal.stock.domain.Stock;
import com.sapphire.common.dal.stock.domain.StockItem;
import com.sapphire.common.dal.stock.domain.StockStatics;
import com.sapphire.common.dal.stock.repository.StockItemRepository;
import com.sapphire.common.utils.TimeUtil;

/**
 * Created by Ethan on 2016/3/30.
 */
@Service("stockService")
public class StockServiceImpl implements StockService {
    @Autowired
    private StockItemRepository stockItemRepository;

    @Override
    public List<String> getAllCodes() {
        return stockItemRepository.getCodes();
    }

    @Override
    public Stock getStockByCode(String code) {
        List<StockItem> items = stockItemRepository.getStocksByCode(code);

        if (items.isEmpty()) {
            return null;
        }

        return new Stock(items);
    }

    @Override
    public Stock getStockByCodeAndTime(String code, Timestamp dateFrom, Timestamp dateTo) {
        List<StockItem> items = stockItemRepository.getStockByCodeAndTime(code, dateFrom, dateTo);
        if (!items.isEmpty()) {
            return new Stock(items);
        }
        return null;
    }

    /**
    * Get latest one month's stock statics of specified industry.
    * 
    * @param industry
    *           , specified industry
    * @return
    */
    @Override
    public StockStatics getLastMonthStockStaticsByIndustry(String industry) {
        List<String> codes = stockItemRepository.getCodeByIndustry(industry);

        List<Stock> stocks = new ArrayList<>(codes.size());

        Timestamp from = TimeUtil.oneMonthAgo();
        Timestamp to = TimeUtil.now();

        for (String code : codes) {
            Stock stock = getStockByCodeAndTime(code, from, to);
            if (stock != null && !stock.isStop()) {
                stocks.add(stock);
            }
        }
        return new StockStatics(stocks);
    }

    @Override
    public List<String> getIndustries() {
        return stockItemRepository.getIndustries();
    }

    @Override
    public void saveAll(List<StockItem> items) {
        stockItemRepository.save(items);
    }

    @Override
    public StockItem save(StockItem item) {
        return stockItemRepository.save(item);
    }

    @Override
    public StockItem getLatestStockItemByCode(String code) {
        return stockItemRepository.getLatestStockItem(code);
    }

    @Override
    public List<StockItem> getLast30Stock(String code) {
        List<StockItem> items = stockItemRepository.getLast30Items(code);

        List<StockItem> result = new ArrayList<>(items);
        Collections.sort(result, (o1, o2) -> Long.compare(o1.getUidPk(), o2.getUidPk()));

        return result;
    }

    /**
     * 获取所有最后一天的股票项目
     * @return
     */
    @Override
    public List<StockItem> getAllLastStockItem() {
        return stockItemRepository.getLatestItems();
    }
}
