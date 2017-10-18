/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.sapphire.common.integration.dingtalk.constant.DingTalkMessageType;
import com.sapphire.common.integration.dingtalk.pusher.DingTalkMessagePusher;
import com.sapphire.common.utils.annotation.Job;

/**
 *
 * @author yunpeng.byp
 * @version $Id: CoralineRobot.java, v 0.1 2017年10月18日 上午12:24 yunpeng.byp Exp $
 */
@Job
public class CoralineRobot {
    private static final Logger   logger                  = LoggerFactory
        .getLogger(CoralineRobot.class);

    private static final String   CORALINE_SAY_GOOD_NIGHT = "Coraline's Good Night!";

    private DingTalkMessagePusher pusher;

    @Scheduled(cron = "0 0 23 * * ?")
    public void sayGoodNight() {
        logger.info("Works");
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