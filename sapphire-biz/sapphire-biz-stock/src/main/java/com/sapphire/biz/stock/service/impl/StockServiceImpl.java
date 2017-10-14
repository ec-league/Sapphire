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
import com.sapphire.common.dal.stock.domain.StockStatistics;
import com.sapphire.common.dal.stock.repository.StockItemRepository;
import com.sapphire.common.dal.stock.repository.StockStatisticsRepository;
import com.sapphire.common.utils.TimeUtil;

/**
 * Created by Ethan on 2016/3/30.
 */
@Service("stockService")
public class StockServiceImpl implements StockService {
    @Autowired
    private StockItemRepository       stockItemRepository;

    @Autowired
    private StockStatisticsRepository stockStatisticsRepository;

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
    * Get latest one month's all stock info.
    * 
    * @return
    */
    @Override
    public StockStatics getLastMonthStockStatics() {
        List<String> codes = stockItemRepository.getCodes();

        List<Stock> stocks = new ArrayList<>(codes.size());

        Timestamp from = TimeUtil.oneMonthAgo();
        Timestamp to = TimeUtil.now();

        for (String code : codes) {
            Stock stock = getStockByCodeAndTime(code, from, to);
            if (stock != null && !stock.isStop()) {
                StockStatistics stockStatistics = stockStatisticsRepository.findByCode(code);
                stock.update(stockStatistics);
                stocks.add(stock);
            }
        }
        return new StockStatics(stocks);
    }

    @Override
    public StockStatics getLastYearStockStatics() {
        List<String> codes = stockItemRepository.getCodes();

        List<Stock> stocks = new ArrayList<>(codes.size());

        Timestamp from = TimeUtil.oneYearAgo();
        Timestamp to = TimeUtil.now();

        for (String code : codes) {
            Stock stock = getStockByCodeAndTime(code, from, to);
            if (stock != null && !stock.isStop()) {
                stocks.add(stock);
            }
        }
        return new StockStatics(stocks);
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

    /**
    *
    * @return
    */
    @Override
    public StockStatics getStocksByIncreaseTotal() {
        List<String> codes = stockStatisticsRepository.findByIncrease();

        List<Stock> stocks = new ArrayList<>(codes.size());
        Timestamp from = TimeUtil.oneMonthAgo();
        Timestamp to = TimeUtil.now();
        for (String code : codes) {
            Stock stock = getStockByCodeAndTime(code, from, to);
            if (stock == null) {
                continue;
            }
            stocks.add(stock);
        }

        return new StockStatics(stocks);
    }

    /**
    *
    * @return
    */
    @Override
    public List<StockItem> getLatestStockItems() {
        return stockItemRepository.getLatestItems();
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
}
