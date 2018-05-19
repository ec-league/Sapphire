package com.sapphire.web.stock.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.biz.stock.algorithm.context.StockContext;
import com.sapphire.biz.stock.algorithm.exec.ActionCategory;
import com.sapphire.biz.stock.algorithm.exec.ActionExecutor;
import com.sapphire.biz.stock.cache.StockCache;
import com.sapphire.biz.stock.service.StockService;
import com.sapphire.common.dal.stock.domain.StockStatistics;
import com.sapphire.common.utils.dto.DataJsonDto;
import com.sapphire.common.utils.dto.JsonDto;
import com.sapphire.common.utils.dto.ListJsonDto;
import com.sapphire.web.stock.dto.StockDetailDto;
import com.sapphire.web.stock.dto.StockDto;

/**
 * @author: Ethan @date: 2016/4/17
 */
@Controller
@RequestMapping("/stock")
public class StockController {

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockService        stockService;

    @Autowired
    private StockCache          stockCache;

    @Autowired
    private ActionExecutor      actionExecutor;

    @RequestMapping("/industries")
    @ResponseBody
    public JsonDto getIndustries() {
        List<String> codes = stockService.getIndustries();
        return new ListJsonDto<>(codes).formSuccessDto();
    }

    @RequestMapping("/today.ep")
    @ResponseBody
    public JsonDto getIncreaseStatics() {
        StockContext context = new StockContext();
        context.setStatistics(stockCache.getStockStatistics());

        actionExecutor.execute(context, ActionCategory.MACD_BUY);

        List<StockStatistics> stockStatistics = context.getStatistics();

        int max = stockStatistics.size() > 30 ? 30 : stockStatistics.size();

        List<StockDto> dtos = new ArrayList<>(max);

        for (StockStatistics statistics : stockStatistics.subList(0, max)) {
            dtos.add(new StockDto(statistics));
        }

        return new ListJsonDto(dtos).formSuccessDto();
    }

    @RequestMapping("/list.ep")
    @ResponseBody
    public JsonDto getListStatistics() {
        List<StockStatistics> domains = stockCache.getStockStatistics();
        List<StockDto> dtos = new ArrayList<>(domains.size());

        for (StockStatistics statistics : domains) {
            dtos.add(new StockDto(statistics));
        }

        return new ListJsonDto<>(dtos).formSuccessDto();
    }

    @RequestMapping("/info.ep")
    @ResponseBody
    public JsonDto getStockByCode(@RequestParam("code") String code) {
        try {
            StockStatistics statistics = stockCache.getStatisticsByCode(code);

            return new DataJsonDto(new StockDetailDto(statistics)).formSuccessDto();
        } catch (Exception ex) {
            logger.error("Get Stock By Code Failed! Code: " + code, ex);
            return new JsonDto().formFailureDto(ex);
        }
    }
}
