/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.sapphire.common.integration.stock;

import com.sapphire.common.dal.stock.domain.StockItem;

/**
 *
 * @author yunpeng.byp
 * @version $Id: StockIntegrationService.java, v 0.1 2018年02月23日 上午9:42 yunpeng.byp Exp $
 */
public interface StockIntegrationService {

    /**
     * 根据股票代码获取股票信息
     * @param code 股票代码
     * @return 股票项目
     */
    StockItem getStock(String code);
}