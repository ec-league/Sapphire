package com.sapphire.stock.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.common.dto.JsonDto;
import com.sapphire.common.dto.ListJsonDto;
import com.sapphire.stock.cache.StockCache;
import com.sapphire.stock.domain.Stock;
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
      JsonDto result = new ListJsonDto<>(codes).formSuccessDto();
      return result;
   }

   @RequestMapping("/industry/statics/below")
   @ResponseBody
   public JsonDto getStaticsByIndustryBelow(
         @RequestParam(value = "industry", required = true) String industry) {
      StockStatics stockStatics =
            stockService.getLastMonthStockStaticsByIndustry(industry);

      JsonDto dto =
            new ListJsonDto<Stock>(stockStatics.getMacdBelowZero())
                  .formSuccessDto();

      return dto;
   }

   @RequestMapping("/industry/statics/upon")
   @ResponseBody
   public JsonDto getStaticsByIndustryUpon(
         @RequestParam(value = "industry", required = true) String industry) {
      StockStatics stockStatics =
            stockService.getLastMonthStockStaticsByIndustry(industry);

      JsonDto dto =
            new ListJsonDto<Stock>(stockStatics.getMacdUpZero())
                  .formSuccessDto();

      return dto;
   }

   @RequestMapping("/statics/below")
   @ResponseBody
   public JsonDto getStaticsBelow() {
      StockStatics stockStatics = stockCache.getStockStatics();

      JsonDto dto =
            new ListJsonDto<>(stockStatics.getMacdBelowZero()).formSuccessDto();

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

      List<Stock> result = new ArrayList<>(stocks.size());
      for (Stock stock : stocks) {
         StockStatistics stat =
               stockStatisticsService.findByCode(stock.getCode());

         if (stat == null)
            continue;

         stock.update(stat);
      }

      JsonDto dto = new ListJsonDto<>(result).formSuccessDto();

      return dto;
   }

   @RequestMapping("/statics/increase.ep")
   @ResponseBody
   public JsonDto getIncreaseStatics() {
      StockStatics stockStatics = stockCache.getIncreasTotalStat();

      JsonDto dto =
            new ListJsonDto<>(stockStatics.getStocks()).formSuccessDto();

      return dto;
   }
}
