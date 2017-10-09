package com.sapphire.common.utils.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Author: EthanPark <br/>
 * Date: 2016/4/27<br/>
 * Email: byp5303628@hotmail.com
 */
public class CacheService {
    public static final long                          ONE_MINUTE = 60 * 1000L;

    public static final long                          ONE_HOUR   = 60 * 60 * 1000L;

    public static final long                          ONE_DAY    = 24 * 60 * 60 * 1000L;

    private static Map<Class<? extends Cache>, Cache> cacheMap   = new HashMap<>();

    private static ScheduledExecutorService           executor   = Executors
        .newScheduledThreadPool(1);

    private CacheService() {
    }

    public static void register(Cache cache) {
        cacheMap.put(cache.getClass(), cache);

        executor.scheduleAtFixedRate(() -> cache.refresh(), 0, cache.interval(),
            TimeUnit.MILLISECONDS);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Cache> T getCache(Class<? extends T> clazz) {
        if (cacheMap.containsKey(clazz))
            return (T) cacheMap.get(clazz);
        throw new CacheNotExistException(clazz.toString());
    }
}
