package com.sapphire.biz.stock.service;

import java.util.Set;

import com.sapphire.biz.stock.constant.IgnoreStockFlag;

/**
 * Author: Ethan Date: 2016/4/5
 */
public interface IgnoreStockService {

    Set<String> getCodeByFlag(IgnoreStockFlag flag);
}
