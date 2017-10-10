/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.integration.dingtalk.pusher;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.sapphire.common.integration.dingtalk.constant.DingTalkMessageType;
import org.junit.Test;

/**
 *
 * @author yunpeng.byp
 * @version $Id: DingTalkMessagePusherTest.java, v 0.1 2017年10月10日 下午11:29 yunpeng.byp Exp $
 */
public class DingTalkMessagePusherTest {
    @Test
    public void test_push_1() throws UnirestException {
        DingTalkMessagePusher pusher = new DingTalkMessagePusher();

        pusher.push("", "李小宝好好休息哦", DingTalkMessageType.MARKDOWN);
    }
}