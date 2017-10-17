/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.biz.stock.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.sapphire.common.dal.stock.domain.Stock;
import com.sapphire.common.dal.stock.domain.StockItem;
import com.sapphire.common.dal.stock.domain.StockStatistics;
import com.sapphire.common.utils.JsonUtil;
import com.sapphire.common.utils.TimeUtil;

/**
 *
 * @author yunpeng.byp
 * @version $Id: StockAlgorithmTests.java, v 0.1 2017年10月17日 下午11:02 yunpeng.byp Exp $
 */
public class StockAlgorithmTests {
    private StockAlgorithm algorithm;

    public StockAlgorithmTests() {
        algorithm = new StockAlgorithm();
        algorithm.setJsonUtil(new JsonUtil());
        algorithm.setTimeUtil(new TimeUtil());
    }

    @Test
    public void test_calculate_1() {
        List<StockItem> items = new ArrayList<>();
        Stock stock = new Stock(items);

        StockStatistics statistics = algorithm.calculate(stock);

        Assert.assertNull(statistics.getCode());
        Assert.assertNull(statistics.getName());
    }

    @Test
    public void test_calculate_2() {
        List<StockItem> items = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            StockItem item = new StockItem();
            item.setCode("123456");
            item.setName("abcdef");

            item.setMacd(0.1 * i);
            item.setMacdDiff(0.2 * i);
            item.setEndPrice(10 + 0.5 * i);
            items.add(item);
        }

        Stock stock = new Stock(items);

        StockStatistics statistics = algorithm.calculate(stock);

        Assert.assertEquals(statistics.getCode(), "123456");
        Assert.assertEquals(statistics.getName(), "abcdef");
        Assert.assertEquals(statistics.getCurrentDiff(), 2.0d, 0.0001);
        Assert.assertEquals(statistics.getAverageGoldDays(), 0);
        Assert.assertEquals(statistics.getCurrentMacd(), 1.0d, 0.0001);
        Assert.assertEquals(statistics.isGoldPossible(), true);
        Assert.assertEquals(statistics.getCurrentPrice(), 15d, 0.0001);
        Assert.assertEquals(statistics.getIncreaseTotal(), 0d, 0.0001);

        Assert.assertNull(statistics.getDesc());
    }
}