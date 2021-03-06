package com.sapphire.common.utils.cache;

/**
 * @author : Ethan
 * @date: 2016/4/10
 */
public interface Cache {
    /**
    * Refresh the cache. If succeed, return true, else return false;
    * 
    * @return
    */
    boolean refresh();

    /**
    * How much time it will refresh the cache.
    * 
    * @return
    */
    long interval();
}
