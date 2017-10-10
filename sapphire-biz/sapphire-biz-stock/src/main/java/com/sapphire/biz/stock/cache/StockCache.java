package com.sapphire.biz.stock.cache;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;

import com.sapphire.common.dal.stock.domain.StockStatics;
import com.sapphire.biz.stock.service.StockService;
import com.sapphire.common.utils.cache.Cache;
import com.sapphire.common.utils.cache.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: Ethan Date: 2016/4/10
 */
@Controller
@RequestMapping("/stock/cache")
public class StockCache implements Cache {
    private static final Logger logger = LoggerFactory.getLogger(StockCache.class);

    @Autowired
    private StockService stockService;

    private StockStatics stockStatics;

    private final Lock          lock   = new ReentrantLock();

    @PostConstruct
    public void registerCache() {
        CacheService.register(this);
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

    private void init() {
        lock.lock();
        StockStatics stat = stockService.getLastMonthStockStatics();
        try {
            stockStatics = stat;
        } catch (Exception ex) {
            logger.error("Init Stock Cache failed!", ex.getMessage(), ex);
        } finally {
            lock.unlock();
        }
    }

    public StockStatics getStockStatics() {
        if (stockStatics == null && !refresh()) {
            return null;
        }
        return stockStatics;
    }
}
