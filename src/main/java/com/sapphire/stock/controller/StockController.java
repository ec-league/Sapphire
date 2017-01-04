package com.sapphire.stock.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.common.dto.DataJsonDto;
import com.sapphire.common.dto.JsonDto;
import com.sapphire.common.dto.ListJsonDto;
import com.sapphire.stock.cache.StockCache;
import com.sapphire.stock.domain.Stock;
import com.sapphire.stock.domain.StockItem;
import com.sapphire.stock.domain.StockStatics;
import com.sapphire.stock.domain.StockStatistics;
import com.sapphire.stock.service.StockService;
import com.sapphire.stock.service.StockStatisticsService;

/**
 * Author: Ethan Date: 2016/4/17
 */
@Controller
@RequestMapping("/stock")
public class StockController {

   private static final Logger logger = LoggerFactory
         .getLogger(StockController.class);

   @Autowired
   private StockService stockService;

   @Autowired
   private StockCache stockCache;

   @Autowired
   private StockStatisticsService stockStatisticsService;

   @RequestMapping("/industries")
   @ResponseBody
   public JsonDto getIndustries() {
      List<String> codes = stockService.getIndustries();
      return new ListJsonDto<>(codes).formSuccessDto();
   }

   @RequestMapping("/industry/statics/below")
   @ResponseBody
   public JsonDto getStaticsByIndustryBelow(
         @RequestParam(value = "industry", required = true) String industry) {
      StockStatics stockStatics =
            stockService.getLastMonthStockStaticsByIndustry(industry);

      List<Stock> stocks = stockStatics.getMacdBelowZero();

      update(stocks);

      return new ListJsonDto<>(stocks);
   }

   @RequestMapping("/industry/statics/upon")
   @ResponseBody
   public JsonDto getStaticsByIndustryUpon(
         @RequestParam(value = "industry", required = true) String industry) {
      StockStatics stockStatics =
            stockService.getLastMonthStockStaticsByIndustry(industry);

      JsonDto dto =
            new ListJsonDto<>(stockStatics.getMacdUpZero()).formSuccessDto();

      return dto;
   }

   @RequestMapping("/statics/below")
   @ResponseBody
   public JsonDto getStaticsBelow() {
      StockStatics stockStatics = stockCache.getStockStatics();

      JsonDto dto = new ListJsonDto<>(stockStatics.pick()).formSuccessDto();

      return dto;
   }

   @RequestMapping("/statics/upon")
   @ResponseBody
   public JsonDto getStaticsUpon() {
      StockStatics stockStatics = stockCache.getStockStatics();

      JsonDto dto =
            new ListJsonDto<>(stockStatics.getMacdUpZero()).formSuccessDto();

      return dto;
   }

   @RequestMapping("/statics/lowest.ep")
   @ResponseBody
   public JsonDto getStaticsLowest() {
      StockStatics stockStatics = stockCache.getStockStatics();

      List<Stock> stocks = stockStatics.getLowestMacd();

      update(stocks);

      JsonDto dto = new ListJsonDto<>(stocks).formSuccessDto();

      return dto;
   }

   @RequestMapping("/statics/increase.ep")
   @ResponseBody
   public JsonDto getIncreaseStatics() {
      StockStatics stockStatics = stockCache.getStockStatics();
      List<Stock> stocks = stockStatics.getIncreaseTop100();

      update(stocks);

      JsonDto dto = new ListJsonDto<>(stocks).formSuccessDto();

      return dto;
   }

   @RequestMapping("/statics/dead.ep")
   @ResponseBody
   public JsonDto getDeadStatics() {
      StockStatics stockStatics = stockCache.getStockStatics();
      List<Stock> stocks = stockStatics.getDeadMacd();

      update(stocks);

      JsonDto dto = new ListJsonDto<>(stocks).formSuccessDto();

      return dto;
   }

   @RequestMapping("/statics/gold.ep")
   @ResponseBody
   public JsonDto getGoldPossible() {
      StockStatics stockStatics = stockCache.getStockStatics();
      List<Stock> stocks = stockStatics.getGoldPossible();

      update(stocks);

      JsonDto dto = new ListJsonDto<>(stocks).formSuccessDto();

      return dto;
   }

   @RequestMapping("/{code}/info.ep")
   @ResponseBody
   public JsonDto getStockByCode(@PathVariable String code) {
      try {
         StockItem item = stockService.getLatestStockItemByCode(code);
         StockStatistics statistics = stockStatisticsService.findByCode(code);

         Stock stock = new Stock();

         stock.setHighestPrice(statistics.getHighestPrice());
         stock.setIncreaseTotal(statistics.getIncreaseTotal());
         stock.setFirstDiff(item.getMacdDiff());
         stock.setCode(item.getCode());
         stock.setCurrentMacd(item.getMacd());
         stock.setLowestMacd(statistics.getLowestMacd());
         stock.setName(item.getName());
         stock.setEndPrice(item.getEndPrice());

         JsonDto dto = new DataJsonDto<>(stock);

         return dto.formSuccessDto();
      } catch (Exception ex) {
         logger.error("Get Stock By Code Failed!", ex);
         return new JsonDto().formFailureDto(ex);
      }
   }

   private void update(List<Stock> stocks) {
      for (Stock stock : stocks) {
         StockStatistics stat =
               stockStatisticsService.findByCode(stock.getCode());

         if (stat == null)
            continue;

         stock.update(stat);
      }
   }
}
