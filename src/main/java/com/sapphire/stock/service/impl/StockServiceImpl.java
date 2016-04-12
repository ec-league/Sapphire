package com.sapphire.stock.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapphire.common.TimeUtil;
import com.sapphire.stock.domain.Stock;
import com.sapphire.stock.domain.StockItem;
import com.sapphire.stock.domain.StockStatics;
import com.sapphire.stock.repository.StockItemRepository;
import com.sapphire.stock.service.StockService;

/**
 * Created by Ethan on 2016/3/30.
 */
@Service("stockService")
public class StockServiceImpl implements StockService {
   @Autowired
   private StockItemRepository stockItemRepository;

   @PersistenceContext
   private EntityManager entityManager;

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
   public Stock getStockByCodeAndTime(String code, Timestamp dateFrom,
         Timestamp dateTo) {
      List<StockItem> items =
            stockItemRepository.getStockByCodeAndTime(code, dateFrom, dateTo);
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
            stocks.add(stock);
         }
      }
      StockStatics result = new StockStatics(stocks);
      return result;
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
      StockStatics result = new StockStatics(stocks);
      return result;
   }
}
