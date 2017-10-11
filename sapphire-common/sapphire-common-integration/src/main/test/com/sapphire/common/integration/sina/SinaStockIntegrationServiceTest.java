/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.integration.sina;

import com.sapphire.common.dal.stock.domain.StockItem;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author yunpeng.byp
 * @version $Id: SinaStockIntegrationServiceTest.java, v 0.1 2017年10月11日 下午5:37 yunpeng.byp Exp $
 */
public class SinaStockIntegrationServiceTest {

    @Test
    public void test_getStock_1() {
        SinaStockIntegrationService service = new SinaStockIntegrationService();

        StockItem item = service.getStock("600000");

        Assert.assertEquals(item.getCode(), "600000");
    }
}