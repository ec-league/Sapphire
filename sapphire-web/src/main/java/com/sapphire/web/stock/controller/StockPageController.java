package com.sapphire.web.stock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: EthanPark <br/>
 * Date: 2016/10/19<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/stock")
public class StockPageController {

    @RequestMapping("/stock.html")
    public String stockPage() {
        return "stock/stock";
    }

    @RequestMapping("/stock-detail.html")
    public String stockDetailPage() {
        return "stock/stock-detail";
    }
}
