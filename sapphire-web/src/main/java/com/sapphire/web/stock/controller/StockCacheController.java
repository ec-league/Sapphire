package com.sapphire.web.stock.controller;

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

    @RequestMapping("/update/stat.ep")
    @ResponseBody
    public JsonDto startStatTask() {
        stockTask.execute();

        return new JsonDto().formSuccessDto();
    }

    @RequestMapping("/update/item.ep")
    @ResponseBody
    public JsonDto startItemTask() {
        stockTask.execute();

        return new JsonDto().formSuccessDto();
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
}
