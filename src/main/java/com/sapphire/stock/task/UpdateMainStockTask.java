package com.sapphire.stock.task;

import com.sapphire.stock.domain.StockItem;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.sapphire.stock.service.StockService;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

/**
 * Author: EthanPark <br/>
 * Date: 2016/6/26<br/>
 * Email: byp5303628@hotmail.com
 */
public class UpdateMainStockTask extends QuartzJobBean {

   private static final Logger logger = LoggerFactory
         .getLogger(UpdateMainStockTask.class);

   private static StockService stockService;

   private static String S_H_URL = "http://qt.gtimg.cn/q=sh000001";

   private static void init(JobExecutionContext context) throws SchedulerException {
      stockService =
            (StockService) context.getScheduler().getContext()
                  .get("stockService");
   }

   /**
    * Execute the actual job. The job data map will already have been applied as
    * bean property values by execute. The contract is exactly the same as for
    * the standard Quartz execute method.
    *
    * @param context
    * @see #execute
    */
   @Override
   protected void executeInternal(JobExecutionContext context)
         throws JobExecutionException {
      logger.info("Update SZ Main Stock Task Begin");

      try {
         init(context);

         ClientRequest request = new ClientRequest(S_H_URL);
         ClientResponse<String> response = request.get(String.class);
         BufferedReader br =
               new BufferedReader(new InputStreamReader(
                     new ByteArrayInputStream(response.getEntity()
                           .getBytes())));
         String output = br.readLine();

         StockItem item = toDomain(output);

      } catch (Exception ex) {

      }
   }

   private StockItem toDomain(String output) {
      return null;
   }
}
