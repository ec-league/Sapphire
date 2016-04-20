package com.sapphire.stock.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.common.PropertyManager;
import com.sapphire.common.dto.DataJsonDto;
import com.sapphire.common.dto.JsonDto;
import com.sapphire.stock.domain.Stock;
import com.sapphire.stock.domain.StockItem;
import com.sapphire.stock.service.StockService;

/**
 * Author: EthanPark <br/>
 * Date: ${date}<br/>
 * Email: byp5303628@hotmail.com
 */
@RequestMapping("/stock/data")
@Controller
public class StockCacheController {
   private static final Logger logger = LoggerFactory
         .getLogger(StockCacheController.class);

   @Autowired
   private StockService stockService;

   @RequestMapping("/load")
   @ResponseBody
   public JsonDto load() {
      logger.info("Start to load!");
      stockService.removeAll();
      logger.info("Remove all completed!");

      logger.info("Start to load data");
      File dir = new File(PropertyManager.getProperty("DATA.FOLDER"));

      try {
         for (File f : dir.listFiles()) {
            BufferedReader br =
                  new BufferedReader(new InputStreamReader(new FileInputStream(
                        f), "GBK"));

            List<StockItem> stockItems = new ArrayList<StockItem>(500);
            String temp = br.readLine();
            String code = temp.split(" ")[0];
            String name = temp.split(" ")[1];
            temp = br.readLine();
            while (temp != null) {
               temp = br.readLine();
               if (!temp.matches("[0-9].*"))
                  break;

               StockItem item = new StockItem(temp);
               item.setCode(code);
               item.setName(name);

               stockItems.add(item);
            }

            if (stockItems.isEmpty())
               continue;
            Stock stock = new Stock(stockItems);
            stock.calculateMacd(7, 18);

            stockService.saveAll(stockItems);
         }
      } catch (Exception ex) {
         logger.error("Data load failed!", ex);
         return new JsonDto().formFailureDto(ex);
      }
      logger.info("Load complete!");

      return new DataJsonDto<>(PropertyManager.getProperty("DATA.FOLDER"))
            .formSuccessDto();
   }
}
