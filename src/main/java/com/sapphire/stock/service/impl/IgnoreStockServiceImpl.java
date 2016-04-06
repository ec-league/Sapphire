package com.sapphire.stock.service.impl;

import com.sapphire.stock.constant.IgnoreStockFlag;
import com.sapphire.stock.repository.IgnoreStockRepository;
import com.sapphire.stock.service.IgnoreStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author: Ethan
 * Date: 2016/4/5
 */
@Service("ignoreStockService")
public class IgnoreStockServiceImpl implements IgnoreStockService {

   @Autowired
   private IgnoreStockRepository ignoreStockRepository;

   @Override
   public Set<String> getCodeByFlag(IgnoreStockFlag flag) {
      List<String> codes = ignoreStockRepository.getCodeByType(flag.getCode());

      return new HashSet<>(codes);
   }
}
