package com.sapphire.common.dal.stock.service.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.BaseTest;
import com.sapphire.biz.stock.service.StockService;

/**
 * StockServiceImpl Tester.
 *
 * @author EthanPark
 * @since <pre>
 * ���� 2, 2016
 * </pre>
 * @version 1.0
 */
public class StockServiceImplTest extends BaseTest {

    @Autowired
    private StockService stockService;

    /**
    * 
    * Method: getAllCodes()
    * 
    */
    @Test
    public void testGetAllCodes() throws Exception {
        List<String> codes = stockService.getAllCodes();

        Assert.assertNotNull(codes);
    }
}
