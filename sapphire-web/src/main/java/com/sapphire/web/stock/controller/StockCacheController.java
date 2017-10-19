package com.sapphire.web.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.biz.stock.task.StockItemTask;
import com.sapphire.biz.stock.task.StockStatisticTask;
import com.sapphire.common.utils.dto.JsonDto;
import com.sapphire.web.stock.cache.StockCache;

/**
 * Author: EthanPark <br/>
 * Date: ${date}<br/>
 * Email: byp5303628@hotmail.com
 */
@RequestMapping("/stock/data")
@Controller
public class StockCacheController {
    private StockStatisticTask stockStatisticTask;

    private StockItemTask      stockItemTask;

    private StockCache         stockCache;

    @RequestMapping("/update/stat.ep")
    @ResponseBody
    public JsonDto startStatTask() {
        try {
            stockItemTask.execute();

            stockStatisticTask.execute();

            stockCache.refresh();

            return new JsonDto().formSuccessDto();
        } catch (Exception e) {
            return new JsonDto().formFailureDto(e);
        }
    }

    @RequestMapping("/update/item.ep")
    @ResponseBody
    public JsonDto startItemTask() {
        return startStatTask();
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

    /**
     * Setter method for property <tt>stockStatisticTask</tt>.
     *
     * @param stockStatisticTask  value to be assigned to property stockStatisticTask
     */
    @Autowired
    public void setStockStatisticTask(StockStatisticTask stockStatisticTask) {
        this.stockStatisticTask = stockStatisticTask;
    }

    /**
     * Setter method for property <tt>stockItemTask</tt>.
     *
     * @param stockItemTask  value to be assigned to property stockItemTask
     */
    @Autowired
    public void setStockItemTask(StockItemTask stockItemTask) {
        this.stockItemTask = stockItemTask;
    }
}
