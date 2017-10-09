package com.sapphire.biz.stock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: EthanPark <br/>
 * Date: 2016/10/19<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/stock")
public class StockPageController {

    @RequestMapping("/stock.html")
    public String lowestPage() {
        return "stock/stock";
    }
}
