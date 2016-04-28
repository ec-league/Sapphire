package com.sapphire.stock.controller;

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
