package com.sapphire.biz.stock.job;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.common.integration.dingtalk.pusher.DingTalkMessagePusher;

/**
 * Author: EthanPark <br/>
 * Date: 2016/10/19<br/>
 * Email: byp5303628@hotmail.com
 */
public abstract class SingleThreadJob {
    private static final ExecutorService threadPool = Executors.newSingleThreadExecutor();

    protected DingTalkMessagePusher      pusher;

    /**
     * JOB的名字,完成后进行推送钉钉
     * @return
     */
    protected abstract String jobName();

    /**
     * 任务完成时,推送的消息
     * @return
     */
    protected StringBuilder finishMsg;

    protected void submit(Runnable runnable) {
        finishMsg = new StringBuilder();

        threadPool.execute(runnable);
    }

    /**
     * Setter method for property <tt>pusher</tt>.
     *
     * @param pusher  value to be assigned to property pusher
     */
    @Autowired
    public void setPusher(DingTalkMessagePusher pusher) {
        this.pusher = pusher;
    }
}
