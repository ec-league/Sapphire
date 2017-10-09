package com.sapphire.biz.stock.job;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: EthanPark <br/>
 * Date: 2016/10/19<br/>
 * Email: byp5303628@hotmail.com
 */
public abstract class SingleThreadJob {
    private static final ExecutorService threadPool = Executors.newSingleThreadExecutor();

    protected void submit(Runnable runnable) {
        threadPool.execute(runnable);
    }
}
