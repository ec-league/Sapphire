package com.sapphire.stock.task;

import org.apache.commons.httpclient.HttpClient;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Author: EthanPark <br/>
 * Date: 2016/6/23<br/>
 * Email: byp5303628@hotmail.com
 */
public class GetStockTask extends QuartzJobBean {
   @Override
   protected void executeInternal(JobExecutionContext context)
         throws JobExecutionException {


   }
}
