package com.sapphire.common.integration.dingtalk.pusher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sapphire.common.integration.dingtalk.constant.DingTalkMessageType;
import com.sapphire.common.integration.dingtalk.dto.DingTalkMarkDownMessage;
import com.sapphire.common.integration.dingtalk.dto.DingTalkMessage;
import com.sapphire.common.integration.dingtalk.dto.DingTalkResponse;
import com.sapphire.common.integration.dingtalk.dto.DingTalkTxtMessage;
import com.sapphire.common.integration.exception.IntegrationException;
import com.sapphire.common.utils.annotation.Integration;

/**
 *
 * @author yunpeng.byp
 * @version $Id: DingTalkMessagePusher.java, v 0.1 2017年10月10日 下午9:18 yunpeng.byp Exp $
 */
@Integration
public class DingTalkMessagePusher {
    private static final Logger logger = LoggerFactory.getLogger(DingTalkMessagePusher.class);
    private static final String WEB_HOOK_URL
                                       = "https://oapi.dingtalk"
            + ".com/robot/send?access_token=f2a46f97b29506f28c3de7974c284c32d38069d2baa500ca3b8191707c47bcc3";

    public void push(String topic, String msg, DingTalkMessageType messageType) {
        DingTalkMessage message = buildMessage(topic, msg, messageType);

        logger.info("Construct DingTalk Message: " + message.toJson());
        HttpResponse<String> response;
        try {
            response = Unirest.post(WEB_HOOK_URL)
                    .header("content-type", "application/json")
                    .body(message.toJson())
                    .asString();
        } catch (UnirestException e) {
            throw new IntegrationException(e);
        }

        if (response == null) {
            throw new IntegrationException("DingTalk response is Null");
        }

        String responseBody = response.getBody();

        logger.info("Get Response from DingTalk:" + responseBody);

        DingTalkResponse resp = DingTalkResponse.toResponse(responseBody);

        if (resp.getErrcode() == 0) {
            return;
        } else {
            throw new IntegrationException("DingTalk Push Failed : " + resp.getErrmsg());
        }
    }

    private DingTalkMessage buildMessage(String topic, String msg,
                                         DingTalkMessageType messageType) {

        switch (messageType) {
            case LINK:
            case TEXT:
                return new DingTalkTxtMessage(msg);
            case MARKDOWN:
                return new DingTalkMarkDownMessage(topic, msg);
            default:
                return new DingTalkTxtMessage(msg);
        }
    }

}