/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.biz.task.coraline;

import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.common.integration.dingtalk.constant.DingTalkMessageType;
import com.sapphire.common.integration.dingtalk.pusher.DingTalkMessagePusher;
import com.sapphire.common.task.domain.SapphireTask;

/**
 *
 * @author yunpeng.byp
 * @version $Id: CoralineTask.java, v 0.1 2017年11月08日 上午12:25 yunpeng.byp Exp $
 */
public class CoralineTask implements SapphireTask {
    private static final String   CORALINE_SAY_GOOD_NIGHT = "Coraline's Good Night!";

    private DingTalkMessagePusher pusher;

    /**
     * 调度任务的具体执行.
     */
    @Override
    public void execute() {
        pusher.push(CORALINE_SAY_GOOD_NIGHT, "## 已经很晚了,李小宝注意休息哦 \n> 睡觉才能身体好",
            DingTalkMessageType.MARKDOWN);
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