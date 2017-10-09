package com.sapphire.biz.stock.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;

import com.sapphire.biz.stock.domain.StockStatistics;
import com.sapphire.biz.stock.service.StockStatisticsService;
import com.sapphire.common.utils.cache.Cache;
import com.sapphire.common.utils.cache.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Author: EthanPark <br/>
 * Date: 2016/8/10<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
public class StockStatisticsCache implements Cache {
    private static final Logger          LOGGER      = LoggerFactory
        .getLogger(StockStatisticsCache.class);

    /**
    * Stock is about 3000, map will resize after threshold of 4000 * 0.75 = 3000
    */
    private static final int             STOCK_COUNT = 4000;
    private final Lock                   lock        = new ReentrantLock();

    @Autowired
    private StockStatisticsService stockStatisticsService;

    private Map<String, StockStatistics> cache;

    @PostConstruct
    public void registerCache() {
        CacheService.register(this);
    }

    /**
    * Refresh the cache. If succeed, return true, else return false;
    *
    * @return
    */
    @Override
    public boolean refresh() {

        List<StockStatistics> statisticses = stockStatisticsService.getAll();

        Map<String, StockStatistics> map = new HashMap<>(STOCK_COUNT);

        for (StockStatistics stat : statisticses) {
            map.put(stat.getCode(), stat);
        }

        lock.lock();

        try {
            cache = map;
            return true;
        } catch (Exception ex) {
            LOGGER.error("Update Statistics Cache Failed!", ex);
            return false;
        } finally {
            lock.unlock();
        }
    }

    /**
    * How much time it will refresh the cache.
    *
    * @return
    */
    @Override
    public long interval() {
        return CacheService.ONE_DAY;
    }

    public StockStatistics getStatisticsByCode(String code) {
        if (cache.containsKey(code))
            return cache.get(code);

        return null;
    }
}
