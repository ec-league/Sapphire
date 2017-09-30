package com.sapphire.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.common.dto.JsonDto;
import com.sapphire.stock.job.StockItemJob;
import com.sapphire.stock.job.StockStatisticJob;

/**
 * Author: EthanPark <br/>
 * Date: ${date}<br/>
 * Email: byp5303628@hotmail.com
 */
@RequestMapping("/stock/data")
@Controller
public class StockCacheController {

    @Autowired
    private StockItemJob      stockItemJob;

    @Autowired
    private StockStatisticJob stockStatisticJob;

    @RequestMapping("/update/stat.ep")
    @ResponseBody
    public JsonDto startStatTask() {
        stockStatisticJob.updateStatistic();

        return new JsonDto().formSuccessDto();
    }

    @RequestMapping("/update/item.ep")
    @ResponseBody
    public JsonDto startItemTask() {
        stockItemJob.updateStock();

        return new JsonDto().formSuccessDto();
    }
}
