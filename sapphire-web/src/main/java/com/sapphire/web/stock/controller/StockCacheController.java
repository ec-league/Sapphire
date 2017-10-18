package com.sapphire.web.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.biz.stock.task.StockItemTask;
import com.sapphire.biz.stock.task.StockStatisticTask;
import com.sapphire.common.utils.dto.JsonDto;

/**
 * Author: EthanPark <br/>
 * Date: ${date}<br/>
 * Email: byp5303628@hotmail.com
 */
@RequestMapping("/stock/data")
@Controller
public class StockCacheController {

    @Autowired
    private StockItemTask      stockItemJob;

    @Autowired
    private StockStatisticTask stockStatisticJob;

    @RequestMapping("/update/stat.ep")
    @ResponseBody
    public JsonDto startStatTask() {
        stockStatisticJob.execute();

        return new JsonDto().formSuccessDto();
    }

    @RequestMapping("/update/item.ep")
    @ResponseBody
    public JsonDto startItemTask() {
        stockItemJob.execute();

        return new JsonDto().formSuccessDto();
    }
}
