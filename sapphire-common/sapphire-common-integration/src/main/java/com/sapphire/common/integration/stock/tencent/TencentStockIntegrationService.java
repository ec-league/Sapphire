/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.sapphire.common.integration.stock.tencent;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sapphire.common.dal.stock.domain.StockItem;
import com.sapphire.common.integration.exception.IntegrationException;
import com.sapphire.common.integration.stock.StockIntegrationService;
import com.sapphire.common.utils.TimeUtil;
import com.sapphire.common.utils.annotation.Integration;

/**
 * 腾讯股票获取接口
 * @author yunpeng.byp
 * @version $Id: TencentStockIntegrationService.java, v 0.1 2018年02月23日 上午9:41 yunpeng.byp Exp $
 */
@Integration("tencentStockIntegrationService")
public class TencentStockIntegrationService implements StockIntegrationService {
    private static final String URL_FORMAT = "http://qt.gtimg.cn/q=%s%s";

    /** 当PE和PB值为负数的情况下,使用的默认值 */
    private static final double HIGH_RATE  = 99999d;

    /**
     * 根据股票代码获取股票信息
     * @param code
     */
    @Override
    public StockItem getStock(String code) {
        String url = getUrl(code);
        String output = null;
        try {
            HttpResponse<String> response = Unirest.get(url).asString();

            if (response == null) {
                throw new IntegrationException(
                    String.format("Tencent response is NULL, code: %s", code));
            }
            output = response.getBody();

            StockItem item = toDomain(output);
            item.setCode(code);

            return item;
        } catch (UnirestException e) {
            String errorMsg = String.format("Stock Code: %s, url: %s, output: %s", code, url,
                output);
            throw new IntegrationException(errorMsg, e);
        }
    }

    private String getUrl(String code) {
        if (code.indexOf("60") == 0) {
            return String.format(URL_FORMAT, "sh", code);
        } else {
            return String.format(URL_FORMAT, "sz", code);
        }
    }

    /**
     * 将腾讯股票内容,转换成域模型
     * @param output
     * @return
     */
    private StockItem toDomain(String output) {
        StockItem item = new StockItem();

        String data = output.substring(output.indexOf('"') + 1, output.indexOf(';') - 1);

        String[] datas = data.split("~");

        item.setName(datas[1]);
        item.setStartPrice(Double.parseDouble(datas[5]));
        item.setStop(Double.compare(0d, item.getStartPrice()) == 0);
        item.setLast(true);
        item.setLogDate(TimeUtil.now());

        if (item.isStop()) {
            item.setTrading(0d);
            item.setTradingValue(0d);
            item.setStartPrice(Double.parseDouble(datas[4]));
            item.setEndPrice(Double.parseDouble(datas[4]));
            item.setHighestPrice(Double.parseDouble(datas[4]));
            item.setLowestPrice(Double.parseDouble(datas[4]));
            item.setIncreaseRate(0d);
            item.setPe(HIGH_RATE);
            item.setPb(HIGH_RATE);
        } else {
            item.setTrading(Double.parseDouble(datas[6]));
            item.setTradingValue(Double.parseDouble(datas[37]));
            item.setStartPrice(Double.parseDouble(datas[5]));
            item.setEndPrice(Double.parseDouble(datas[3]));
            item.setHighestPrice(Double.parseDouble(datas[41]));
            item.setLowestPrice(Double.parseDouble(datas[42]));
            item.setIncreaseRate((item.getEndPrice() / Double.parseDouble(datas[2]) - 1) * 100);
            item.setPe(Double.parseDouble(datas[39]));
            item.setPb(Double.parseDouble(datas[46]));
            // 亏损情况
            if (item.getPe() < 0) {
                item.setPe(HIGH_RATE);
            }
            if (item.getPb() < 0) {
                item.setPb(HIGH_RATE);
            }
        }

        item.setEma12(item.getEndPrice());
        item.setEma26(item.getEndPrice());

        return item;
    }
}