package com.sapphire.biz.stock.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapphire.biz.stock.service.StockService;
import com.sapphire.common.dal.stock.domain.Stock;
import com.sapphire.common.dal.stock.domain.StockItem;
import com.sapphire.common.dal.stock.repository.StockItemRepository;

/**
 * @author Ethan
 * @date 2016/3/30.
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

        return new ArrayList<>(items);
    }

    @Override
    public Stock getStockForStatistics(String code) {
        List<StockItem> items = stockItemRepository.getLast300Items(code);

        return new Stock(items);
    }
}
