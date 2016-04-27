package com.sapphire.common.cache;

import java.util.concurrent.ConcurrentHashMap;

import com.sapphire.stock.cache.StockCache;

/**
 * Author: EthanPark <br/>
 * Date: 2016/4/27<br/>
 * Email: byp5303628@hotmail.com
 */
public class CacheFactory {
   private static ConcurrentHashMap<CacheCategory, Cache> caches =
         new ConcurrentHashMap<>();

   public static Cache getCache(CacheCategory category) {
      return caches.get(category);
   }

   private static void register(CacheCategory cacheCategory, Cache cache) {
      caches.put(cacheCategory, cache);
   }

   public static void registerAll() {
      register(CacheCategory.STOCK_CACHE, new StockCache());
   }

   public static void update() {
      caches.values().forEach(com.sapphire.common.cache.Cache::refresh);
   }
}
