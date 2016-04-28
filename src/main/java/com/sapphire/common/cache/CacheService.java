package com.sapphire.common.cache;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Author: EthanPark <br/>
 * Date: 2016/4/27<br/>
 * Email: byp5303628@hotmail.com
 */
public class CacheService {
   public static final long ONE_MINUTE = 60 * 1000;

   private static ScheduledExecutorService executor = Executors
         .newScheduledThreadPool(1);

   public static void register(Cache cache) {
      executor.scheduleAtFixedRate(() -> cache.refresh(), 0, cache.interval(),
            TimeUnit.MILLISECONDS);
   }
}
