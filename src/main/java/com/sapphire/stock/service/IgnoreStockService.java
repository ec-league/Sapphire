package com.sapphire.stock.service;

import com.sapphire.stock.constant.IgnoreStockFlag;

import java.util.Set;

/**
 * Author: Ethan
 * Date: 2016/4/5
 */
public interface IgnoreStockService {

   Set<String> getCodeByFlag(IgnoreStockFlag flag);
}
