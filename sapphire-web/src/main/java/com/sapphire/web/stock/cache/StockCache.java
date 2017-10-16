package com.sapphire.web.stock.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.biz.stock.service.StockStatisticsService;
import com.sapphire.common.dal.stock.domain.StockStatistics;
import com.sapphire.common.utils.cache.Cache;
import com.sapphire.common.utils.cache.CacheService;

/**
 * Author: Ethan Date: 2016/4/10
 */
@Controller
@RequestMapping("/stock/cache")
public class StockCache implements Cache {
    private static final Logger          logger = LoggerFactory.getLogger(StockCache.class);

    @Autowired
    private StockStatisticsService       stockStatisticsService;

    private List<StockStatistics>        stocks;
    private Map<String, StockStatistics> stockMap;

    private final Lock                   lock   = new ReentrantLock();

    @PostConstruct
    public void registerCache() {
        CacheService.register(this);
        init();
    }

    private void init() {
        List<StockStatistics> statistics = stockStatisticsService.getAll();

        List<StockStatistics> stockStatistics = new ArrayList<>(statistics.size());
        Map<String, StockStatistics> map = new HashMap<>(statistics.size() * 2);

        for (StockStatistics statistic : statistics) {
            if (!statistic.isStop()) {
                stockStatistics.add(statistic);
            }
            map.put(statistic.getCode(), statistic);
        }

        lock.lock();
        try {
            stocks = stockStatistics;
            stockMap = map;
        } finally {
            lock.unlock();
        }
    }

    @Override
    @RequestMapping("/refresh")
    @ResponseBody
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

    @Override
    public long interval() {
        return CacheService.ONE_HOUR;
    }

    /**
     * 获取非停牌统计数据
     * @return
     */
    public List<StockStatistics> getStockStatistics() {
        return new ArrayList<>(stocks);
    }
}
