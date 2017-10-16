package com.sapphire.web.stock.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.biz.stock.service.StockService;
import com.sapphire.biz.stock.service.StockStatisticsService;
import com.sapphire.common.dal.stock.domain.Stock;
import com.sapphire.common.dal.stock.domain.StockItem;
import com.sapphire.common.dal.stock.domain.StockStatistics;
import com.sapphire.common.utils.dto.DataJsonDto;
import com.sapphire.common.utils.dto.JsonDto;
import com.sapphire.common.utils.dto.ListJsonDto;
import com.sapphire.web.stock.cache.StockCache;
import com.sapphire.web.stock.dto.StockDto;

/**
 * Author: Ethan Date: 2016/4/17
 */
@Controller
@RequestMapping("/stock")
public class StockController {

    private static final Logger    logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockService           stockService;

    @Autowired
    private StockCache             stockCache;

    @Autowired
    private StockStatisticsService stockStatisticsService;

    @RequestMapping("/industries")
    @ResponseBody
    public JsonDto getIndustries() {
        List<String> codes = stockService.getIndustries();
        return new ListJsonDto<>(codes).formSuccessDto();
    }

    @RequestMapping("/statics/lowest.ep")
    @ResponseBody
    public JsonDto getStaticsLowest() {
        List<StockStatistics> stockStatics = stockCache.getStockStatistics();

        Collections.sort(stockStatics, new Comparator<StockStatistics>() {
            @Override
            public int compare(StockStatistics o1, StockStatistics o2) {
                return Double.compare(o1.getCurrentMacd(), o2.getCurrentMacd());
            }
        });

        List<StockDto> dtos = new ArrayList<>(30);

        int max = stockStatics.size() > 30 ? 30 : stockStatics.size();

        for (StockStatistics statistics : stockStatics.subList(0, max)) {
            dtos.add(new StockDto(statistics));
        }

        return new ListJsonDto(dtos).formSuccessDto();
    }

    @RequestMapping("/statics/increase.ep")
    @ResponseBody
    public JsonDto getIncreaseStatics() {
        List<StockStatistics> stockStatics = stockCache.getStockStatistics();
        Collections.sort(stockStatics, new Comparator<StockStatistics>() {
            @Override
            public int compare(StockStatistics o1, StockStatistics o2) {
                return Double.compare(o2.getIncreaseTotal(), o1.getIncreaseTotal());
            }
        });

        List<StockDto> dtos = new ArrayList<>(30);

        int max = stockStatics.size() > 30 ? 30 : stockStatics.size();

        for (StockStatistics statistics : stockStatics.subList(0, max)) {
            dtos.add(new StockDto(statistics));
        }

        return new ListJsonDto(dtos).formSuccessDto();
    }

    @RequestMapping("/statics/dead.ep")
    @ResponseBody
    public JsonDto getDeadStatics() {
        List<StockStatistics> stockStatics = stockCache.getStockStatistics();

        stockStatics = stockStatics.stream().filter(s -> s.getCurrentMacd() < 0)
            .collect(Collectors.toList());

        Collections.sort(stockStatics, new Comparator<StockStatistics>() {
            @Override
            public int compare(StockStatistics o1, StockStatistics o2) {
                return Double.compare(o2.getIncreaseTotal(), o1.getIncreaseTotal());
            }
        });

        List<StockDto> dtos = new ArrayList<>(30);

        int max = stockStatics.size() > 30 ? 30 : stockStatics.size();

        for (StockStatistics statistics : stockStatics.subList(0, max)) {
            dtos.add(new StockDto(statistics));
        }

        return new ListJsonDto(dtos).formSuccessDto();
    }

    @RequestMapping("/statics/gold.ep")
    @ResponseBody
    public JsonDto getGoldPossible() {
        List<StockStatistics> stockStatics = stockCache.getStockStatistics();

        stockStatics = stockStatics.stream().filter(s -> s.isGoldPossible())
            .collect(Collectors.toList());

        Collections.sort(stockStatics, new Comparator<StockStatistics>() {
            @Override
            public int compare(StockStatistics o1, StockStatistics o2) {
                return Double.compare(o2.getIncreaseTotal(), o1.getIncreaseTotal());
            }
        });

        List<StockDto> dtos = new ArrayList<>(30);

        int max = stockStatics.size() > 30 ? 30 : stockStatics.size();

        for (StockStatistics statistics : stockStatics.subList(0, max)) {
            dtos.add(new StockDto(statistics));
        }

        return new ListJsonDto(dtos).formSuccessDto();
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

            JsonDto dto = new DataJsonDto(stock);

            return dto.formSuccessDto();
        } catch (Exception ex) {
            logger.error("Get Stock By Code Failed!", ex);
            return new JsonDto().formFailureDto(ex);
        }
    }

    private void update(List<Stock> stocks) {
        for (Stock stock : stocks) {
            StockStatistics stat = stockStatisticsService.findByCode(stock.getCode());

            if (stat == null)
                continue;
        }
    }
}
