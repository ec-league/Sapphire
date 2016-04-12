package com.sapphire.stock.service;

import java.sql.Timestamp;
import java.util.List;

import com.sapphire.stock.domain.Stock;
import com.sapphire.stock.domain.StockStatics;

/**
 * Created by Ethan on 2016/3/30.
 */
public interface StockService {
   List<String> getAllCodes();

   Stock getStockByCode(String code);

   Stock getStockByCodeAndTime(String code, Timestamp dateFrom, Timestamp dateTo);

   StockStatics getLastMonthStockStatics();

   StockStatics getLastMonthStockStaticsByIndustry(String industry);
}
