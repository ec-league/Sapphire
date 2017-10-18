package com.sapphire.web.stock.controller;

import com.sapphire.biz.stock.task.StockItemTask;
import com.sapphire.biz.stock.task.StockStatisticTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.common.task.stock.StockTask;
import com.sapphire.common.utils.dto.JsonDto;

/**
 * Author: EthanPark <br/>
 * Date: ${date}<br/>
 * Email: byp5303628@hotmail.com
 */
@RequestMapping("/stock/data")
@Controller
public class StockCacheController {
    private StockTask stockTask;

    private StockStatisticTask stockStatisticTask;

    private StockItemTask stockItemTask;

    @RequestMapping("/update/stat.ep")
    @ResponseBody
    public JsonDto startStatTask() {
        stockItemTask.execute();

        stockStatisticTask.execute();

        return new JsonDto().formSuccessDto();
    }

    @RequestMapping("/update/item.ep")
    @ResponseBody
    public JsonDto startItemTask() {
        return startStatTask();
    }

    /**
     * Setter method for property <tt>stockTask</tt>.
     *
     * @param stockTask  value to be assigned to property stockTask
     */
    @Autowired
    public void setStockTask(StockTask stockTask) {
        this.stockTask = stockTask;
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
