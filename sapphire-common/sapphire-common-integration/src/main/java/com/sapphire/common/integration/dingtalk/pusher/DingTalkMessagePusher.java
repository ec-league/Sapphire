package com.sapphire.common.integration.dingtalk.pusher;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sapphire.common.integration.dingtalk.constant.DingTalkMessageType;
import com.sapphire.common.integration.dingtalk.dto.DingTalkMessage;
import com.sapphire.common.integration.dingtalk.dto.DingTalkResponse;
import com.sapphire.common.integration.dingtalk.dto.DingTalkTxtMessage;
import com.sapphire.common.utils.annotation.Integration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 *
 * @author yunpeng.byp
 * @version $Id: DingTalkMessagePusher.java, v 0.1 2017年10月10日 下午9:18 yunpeng.byp Exp $
 */
@Integration
public class DingTalkMessagePusher {
    private static final Logger logger = LoggerFactory.getLogger(DingTalkMessagePusher.class);

    /**
     * webhooks:为不同主题对应的webhook的URL
     */
    private Map<String, String> webhooks;

    public void push(String topic, String msg, DingTalkMessageType messageType) throws UnirestException {
        DingTalkMessage message = buildMessage(msg, messageType);

        logger.info("Construct DingTalk Message: " + message.toJson());

        HttpResponse<String> response = Unirest.post(
                "https://oapi.dingtalk.com/robot/send?access_token=f2a46f97b29506f28c3de7974c284c32d38069d2baa500ca3b8191707c47bcc3")
                .header("content-type", "application/json")
                .body(message.toJson())
                .asString();

        String responseBody = response.getBody();

        logger.info("Get Response from DingTalk:" + responseBody);

        DingTalkResponse resp = DingTalkResponse.toResponse(responseBody);

        if (resp.getErrcode() == 0) {
            return;
        } else {
            throw new RuntimeException("DingTalk Push Failed");
        }
    }

    private DingTalkMessage buildMessage(String msg, DingTalkMessageType messageType) {

        switch (messageType) {
            case LINK:
            case TEXT:
            case MARKDOWN:
                return new DingTalkTxtMessage(msg);
            default:
                return new DingTalkTxtMessage(msg);
        }
    }

}