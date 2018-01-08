/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.sapphire.common.dal.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapphire.common.dal.stock.domain.Trade;

/**
 *
 * @author yunpeng.byp
 * @version $Id: TradeRepository.java, v 0.1 2018年01月09日 上午12:06 yunpeng.byp Exp $
 */
public interface TradeRepository extends JpaRepository<Trade, Long> {

    @Query("select t.code from Trade as t where t.status = 1")
    List<String> getAllHoldStockCodes();

    @Query("select t from Trade as t where t.status = 2")
    List<Trade> getAllFinishTrades();
}