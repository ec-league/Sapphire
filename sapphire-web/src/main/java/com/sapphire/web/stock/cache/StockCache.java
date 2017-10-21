package com.sapphire.web.stock.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.biz.stock.algorithm.action.MacdAction;
import com.sapphire.biz.stock.algorithm.context.StockContext;
import com.sapphire.biz.stock.service.StockStatisticsService;
import com.sapphire.common.dal.stock.domain.StockStatistics;
import com.sapphire.common.utils.cache.Cache;
import com.sapphire.common.utils.cache.CacheService;

/**
 * @author: Ethan
 * @date: 2016/4/10
 */
@Controller
@RequestMapping("/stock/cache")
public class StockCache implements Cache {
    private static final Logger    logger = LoggerFactory.getLogger(StockCache.class);

    private StockStatisticsService stockStatisticsService;

    private MacdAction             macdAction;

    private List<StockStatistics>  stocks;

    private final Lock             lock   = new ReentrantLock();

    @PostConstruct
    public void registerCache() {
        CacheService.register(this);
        init();
    }

    private void init() {
        List<StockStatistics> statistics = stockStatisticsService.getAll();

        StockContext context = new StockContext();
        context.setStatistics(statistics);

        macdAction.doAction(context);

        lock.lock();
        try {
            stocks = context.getStatistics();
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
     * Setter method for property <tt>macdAction</tt>.
     *
     * @param macdAction  value to be assigned to property macdAction
     */
    @Autowired
    public void setMacdAction(MacdAction macdAction) {
        this.macdAction = macdAction;
    }
}
