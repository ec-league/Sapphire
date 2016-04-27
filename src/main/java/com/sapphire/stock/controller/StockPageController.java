package com.sapphire.stock.controller;

import com.sapphire.stock.domain.StockStatics;
import com.sapphire.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: EthanPark <br/>
 * Date: ${date}<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/stock")
public class StockPageController {

   @Autowired
   private static StockService stockService;

   @RequestMapping("/stock.html")
   public String lowestPage() {
      return "stock/lowest";
   }

   @RequestMapping("/increase.html")
   public String increasePage() {
      return "stock/increase";
   }

   @RequestMapping("/dead.html")
   public String deadPage() {
      return "stock/dead";
   }

   @RequestMapping("/gold.html")
   public String goldPage() {
      return "stock/gold";
   }
}
