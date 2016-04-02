package com.sapphire.stock.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapphire.stock.domain.StockItem;
import com.sapphire.stock.model.Stock;
import com.sapphire.stock.repository.StockItemRepository;
import com.sapphire.stock.service.StockService;

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

      if (!items.isEmpty())
         return new Stock(items);
      return null;
   }

   @Override
   public Stock getStockByCodeAndTime(String code, Timestamp dateFrom, Timestamp dateTo) {
      List<StockItem> items =
            stockItemRepository.getStockByCodeAndTime(code, dateFrom, dateTo);
      if (!items.isEmpty()) {
         return new Stock(items);
      }
      return null;
   }
}
