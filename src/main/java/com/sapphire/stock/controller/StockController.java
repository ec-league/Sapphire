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
import com.sapphire.stock.service.StockService;

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

      List<String> codes = stockStatics.getLowestMacd();

      List<Stock> result = new ArrayList<>(codes.size());
      for (String code : codes) {
         Stock stock = stockService.getStockByCode(code);
         stock.process();
         result.add(stock);
      }

      JsonDto dto = new ListJsonDto<>(result).formSuccessDto();

      return dto;
   }
}
