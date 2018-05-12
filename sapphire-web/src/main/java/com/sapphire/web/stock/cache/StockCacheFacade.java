package com.sapphire.web.stock.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.biz.stock.cache.StockCache;

/**
 * @author: Ethan
 * @date: 2016/4/10
 */
@Controller
@RequestMapping("/stock/cache")
public class StockCacheFacade {
    private static final Logger          logger = LoggerFactory.getLogger(StockCacheFacade.class);

    private StockCache stockCache;

    @RequestMapping("/refresh.ep")
    @ResponseBody
    public boolean refresh() {
        return stockCache.refresh();
    }

    /**
     * Setter method for property <tt>stockCache</tt>.
     *
     * @param stockCache  value to be assigned to property stockCache
     */
    @Autowired
    public void setStockCache(StockCache stockCache) {
        this.stockCache = stockCache;
    }
}
