/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.biz.stock.algorithm.action;

import com.sapphire.biz.stock.algorithm.context.StockContext;

/**
 *
 * @author yunpeng.byp
 * @version $Id: AlgorithmAction.java, v 0.1 2017年10月21日 下午5:03 yunpeng.byp Exp $
 */
public interface AlgorithmAction {

    /**
     * 具体执行的操作.
     * @param context
     */
    void doAction(StockContext context);
}