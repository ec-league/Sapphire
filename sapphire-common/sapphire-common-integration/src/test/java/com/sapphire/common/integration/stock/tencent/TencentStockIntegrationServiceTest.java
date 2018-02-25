package com.sapphire.common.integration.stock.tencent;

import org.junit.Assert;
import org.junit.Test;

import com.sapphire.common.dal.stock.domain.StockItem;

/** 
* TencentStockIntegrationService Tester. 
* 
* @author EthanPark 
* @since <pre>二月 25, 2018</pre> 
* @version 1.0 
*/
public class TencentStockIntegrationServiceTest {

    /** 
    * 
    * Method: getStock(String code) 
    * 
    */
    @Test
    public void test_getStock_1() throws Exception {
        TencentStockIntegrationService stockIntegrationService = new TencentStockIntegrationService();

        StockItem stockItem = stockIntegrationService.getStock("000655");

        Assert.assertEquals(stockItem.getName(), "天龙股份");
        Assert.assertEquals(stockItem.getCode(), "603266");
    }
}
