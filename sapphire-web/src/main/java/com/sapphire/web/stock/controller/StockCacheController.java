package com.sapphire.web.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.biz.task.stock.StockItemTask;
import com.sapphire.biz.task.stock.StockStatisticTask;
import com.sapphire.common.utils.dto.JsonDto;
import com.sapphire.web.stock.cache.StockCacheFacade;

/**
 * @author: EthanPark <br/>
 * Date: ${date}<br/>
 * Email: byp5303628@hotmail.com
 */
@RequestMapping("/stock/data")
@Controller
public class StockCacheController {
    private StockStatisticTask stockStatisticTask;

    private StockItemTask      stockItemTask;

    private StockCacheFacade stockCacheFacade;

    @RequestMapping("/update/stat.ep")
    @ResponseBody
    public JsonDto startStatTask() {
        try {

            new Thread(() -> {
                stockItemTask.execute();

                stockStatisticTask.execute();

                stockCacheFacade.refresh();
            }).start();

            return new JsonDto().formSuccessDto();
        } catch (Exception e) {
            return new JsonDto().formFailureDto(e);
        }
    }

    /**
     * Setter method for property <tt>stockCache</tt>.
     *
     * @param stockCacheFacade  value to be assigned to property stockCache
     */
    @Autowired
    public void setStockCacheFacade(StockCacheFacade stockCacheFacade) {
        this.stockCacheFacade = stockCacheFacade;
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
