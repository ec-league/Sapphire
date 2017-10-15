/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.biz.stock.algorithm;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.BaseTest;
import com.sapphire.biz.stock.service.StockService;
import com.sapphire.common.dal.stock.domain.Stock;
import com.sapphire.common.dal.stock.domain.StockStatistics;

/**
 *
 * @author yunpeng.byp
 * @version $Id: StockAlgorithmTest.java, v 0.1 2017年10月15日 上午1:12 yunpeng.byp Exp $
 */
public class StockAlgorithmTest extends BaseTest {
    @Autowired
    private StockAlgorithm algorithm;

    @Autowired
    private StockService   stockService;

    @Test
    public void test_calculate_1() {
        Stock stock = stockService.getStockByCode("000001");

        if (stock == null)
            return;

        StockStatistics statistics = algorithm.calculate(stock);

        Assert.assertEquals(statistics.getCode(), "000001");
        Assert.assertEquals(statistics.getName(), "平安银行");

        Assert.assertNotNull(statistics.getDesc());
    }
}