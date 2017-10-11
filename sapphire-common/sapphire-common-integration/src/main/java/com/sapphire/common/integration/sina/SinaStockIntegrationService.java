package com.sapphire.common.integration.sina;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sapphire.common.dal.stock.domain.StockItem;
import com.sapphire.common.integration.exception.IntegrationException;
import com.sapphire.common.utils.TimeUtil;
import com.sapphire.common.utils.annotation.Integration;

/**
 *
 * @author yunpeng.byp
 * @version $Id: SinaStockIntegrationService.java, v 0.1 2017年10月11日 下午5:19 yunpeng.byp Exp $
 */
@Integration
public class SinaStockIntegrationService {
    private static final String URL_FORMAT = "http://hq.sinajs.cn/list=%s%s";

    public StockItem getStock(String code) {
        String url = getUrl(code);
        try {
            HttpResponse<String> response = Unirest.get(url)
                    .asString();

            if (response == null) {
                throw new IntegrationException("Sina response is NULL");
            }
            String output = response.getBody();

            StockItem item = toDomain(output);
            item.setCode(code);

            return item;
        } catch (UnirestException e) {
            throw new IntegrationException(e);
        }
    }

    private String getUrl(String code) {
        if (code.indexOf("60") == 0) { return String.format(URL_FORMAT, "sh", code); } else {
            return String.format(URL_FORMAT, "sz", code);
        }
    }

    private StockItem toDomain(String output) {
        StockItem item = new StockItem();

        String data = output.substring(output.indexOf('"') + 1, output.indexOf(';') - 1);

        String[] datas = data.split(",");

        item.setName(datas[0]);
        item.setStartPrice(Double.parseDouble(datas[1]));
        item.setEndPrice(Double.parseDouble(datas[3]));
        item.setHighestPrice(Double.parseDouble(datas[4]));
        item.setLowestPrice(Double.parseDouble(datas[5]));

        item.setTrading(Double.parseDouble(datas[8]));
        item.setTradingValue(Double.parseDouble(datas[9]));

        item.setStop(Double.compare(0d, item.getStartPrice()) == 0);
        item.setLast(true);
        item.setLogDate(TimeUtil.fromStockWebString(datas[30]));

        item.setEma12(item.getEndPrice());
        item.setEma26(item.getEndPrice());
        if (!item.isStop()) { item.setIncreaseRate((item.getEndPrice() / Double.parseDouble(datas[2]) - 1) * 100); } else {
            item.setStartPrice(item.getEndPrice());
            item.setHighestPrice(item.getEndPrice());
            item.setLowestPrice(item.getEndPrice());
        }
        return item;
    }
}