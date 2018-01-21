/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.sapphire.biz.stock.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.biz.stock.algorithm.context.StockContext;
import com.sapphire.biz.stock.service.StockService;
import com.sapphire.biz.stock.service.StockStatisticsService;
import com.sapphire.common.dal.stock.domain.Stock;
import com.sapphire.common.dal.stock.domain.StockStatistics;
import com.sapphire.common.utils.annotation.Cache;
import com.sapphire.common.utils.cache.CacheRefreshable;

/**
 * 股票缓存
 * @author yunpeng.byp
 * @version $Id: StockCache.java, v 0.1 2018年01月13日 下午9:45 yunpeng.byp Exp $
 */
@Cache
public class StockCache implements CacheRefreshable {

    private static final Logger          logger          = LoggerFactory
        .getLogger(StockCache.class);

    private StockStatisticsService       stockStatisticsService;
    private StockService                 stockService;

    private List<StockStatistics>        stockStatistics = new ArrayList<>();
    private Map<String, StockStatistics> statisticsMap   = new HashMap<>();

    private Map<String, Stock>           stockMap        = new HashMap<>();

    /**
     * 获取非停牌统计数据
     * @return
     */
    public List<StockStatistics> getStockStatistics() {
        return new ArrayList<>(stockStatistics);
    }

    /**
     * 根据股票代码获取股票的详细统计数据
     * @param code
     * @return
     */
    public StockStatistics getStatisticsByCode(String code) {
        return statisticsMap.get(code);
    }

    /**
     * 根据股票代码获取股票数据
     * @param code
     * @return
     */
    public Stock getStockByCode(String code) {
        return stockMap.get(code);
    }

    /**
     * 刷新缓存,如果成功返回true,如果失败返回false.
     * @return
     */
    @Override
    public boolean refresh() {
        try {
            logger.info("Refresh the Stock Cache!");
            init();
            logger.info("Refresh Cache Succeed!");
            return true;
        } catch (Exception ex) {
            logger.error("Init StockCache Error", ex);
            return false;
        }
    }

    /**
     * 缓存的种类
     * @return
     */
    @Override
    public String category() {
        return "StockCache";
    }

    @PostConstruct
    private void init() {
        List<StockStatistics> statistics = stockStatisticsService.getAll();

        Map<String, StockStatistics> tempMap = new HashMap<>(statistics.size() * 2);

        Map<String, Stock> tempStockMap = new HashMap<>(statistics.size() * 2);

        for (StockStatistics s : statistics) {
            tempMap.put(s.getCode(), s);
            tempStockMap.put(s.getCode(), stockService.getLast30Stock(s.getCode()));
        }

        StockContext context = new StockContext();
        context.setStatistics(statistics);

        synchronized (this) {
            stockStatistics = new ArrayList<>(tempMap.values());
            statisticsMap = tempMap;
            stockMap = tempStockMap;
        }
    }

    /**
     * Setter method for property <tt>stockStatisticsService</tt>.
     *
     * @param stockStatisticsService  value to be assigned to property stockStatisticsService
     */
    @Autowired
    public void setStockStatisticsService(StockStatisticsService stockStatisticsService) {
        this.stockStatisticsService = stockStatisticsService;
    }

    /**
     * Setter method for property <tt>stockService</tt>.
     *
     * @param stockService  value to be assigned to property stockService
     */
    @Autowired
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }
}