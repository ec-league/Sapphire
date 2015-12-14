package com.sapphire.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/11<br/>
 * Email: byp5303628@hotmail.com
 */
public class ReminderEmailTask extends QuartzJobBean {
   private static final Logger LOGGER = LoggerFactory
         .getLogger(ReminderEmailTask.class);

   @Override
   protected void executeInternal(JobExecutionContext context)
         throws JobExecutionException {
      LOGGER.info("Execute reminder email task");
   }
}
