package com.sapphire.stock.task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.sapphire.common.PropertyManager;
import com.sapphire.common.constant.PropertyFlag;
import com.sapphire.stock.domain.Stock;
import com.sapphire.stock.domain.StockItem;
import com.sapphire.stock.service.StockService;

/**
 * Author: EthanPark <br/>
 * Date: ${date}<br/>
 * Email: byp5303628@hotmail.com
 */
public class UpdateStockItemTask extends QuartzJobBean {
   private static final Logger logger = LoggerFactory
         .getLogger(UpdateStatisticTask.class);

   private static StockService stockService;
   private static final int MACD_START = 7;
   private static final int MACD_END = 18;

   private void init(JobExecutionContext context) throws SchedulerException {
      stockService =
            (StockService) context.getScheduler().getContext()
                  .get("stockService");
   }

   @Override
   protected void executeInternal(JobExecutionContext context)
         throws JobExecutionException {

      logger.info("Update Stock Items Task Begin");

      try {
         init(context);

         String dirStr = PropertyManager.getProperty(PropertyFlag.DATA_FOLDER);
         File dir = new File(dirStr);

         for (File f : dir.listFiles()) {
            BufferedReader br =
                  new BufferedReader(new InputStreamReader(new FileInputStream(
                        f), "GBK"));

            String temp = br.readLine();
            String code = temp.split(" ")[0];
            String name = temp.split(" ")[1];

            StockItem lastItem = stockService.getLatestStockItemByCode(code);

            if (lastItem == null) {
               logger.info(String.format(
                     "For code \"%s\", There is no last item", code));
               insertNew(br, code, name);
               continue;
            } else {
               lastItem.setLast(false);
            }

            List<StockItem> items = goThrough(br, code, name);

            if (items.isEmpty()) {
               continue;
            }
            stockService.save(lastItem);

            Stock stock = new Stock(items);
            stock.calculateMacd(MACD_START, MACD_END, false);

            items.get(items.size() - 1).setLast(true);
            stockService.saveAll(items);
         }

      } catch (Exception ex) {
         logger.error("Init error", ex);
      }
      logger.info("Update Stock Item Task Finished!");
   }

   /**
    * 初始化不存在StockItem的股票，用于更新新股票
    * 
    * @param br
    * @param code
    * @param name
    * @throws IOException
    */
   private void insertNew(BufferedReader br, String code, String name)
         throws IOException {
      List<StockItem> stockItems = goThrough(br, code, name);

      if (stockItems.isEmpty())
         return;
      Stock stock = new Stock(stockItems);
      stock.calculateMacd(MACD_START, MACD_END, true);

      stockItems.get(stockItems.size() - 1).setLast(true);

      stockService.saveAll(stockItems);
   }

   private List<StockItem> goThrough(BufferedReader br, String code, String name)
         throws IOException {
      String temp = br.readLine();
      List<StockItem> stockItems = new ArrayList<>();
      while (temp != null) {
         temp = br.readLine();
         if (!temp.matches("[0-9].*"))
            break;

         StockItem item = new StockItem(temp);
         item.setCode(code);
         item.setName(name);

         stockItems.add(item);
      }

      return stockItems;
   }
}
