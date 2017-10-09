package com.sapphire.biz.stock.service.impl;

import java.util.List;

import com.sapphire.biz.stock.domain.StockStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapphire.biz.stock.repository.StockStatisticsRepository;
import com.sapphire.biz.stock.service.StockStatisticsService;

/**
 * Author: EthanPark <br/>
 * Date: 2016/4/22<br/>
 * Email: byp5303628@hotmail.com
 */
@Service("stockStatisticsService")
public class StockStatisticsServiceImpl implements StockStatisticsService {
    @Autowired
    private StockStatisticsRepository stockStatisticsRepository;

    @Override
    public void update(List<StockStatistics> stats) {
        stockStatisticsRepository.save(stats);
    }

    @Override
    public void update(StockStatistics stat) {
        if (stat == null || "".equals(stat.getCode()))
            return;

        stockStatisticsRepository.save(stat);
    }

    @Override
    public StockStatistics findByCode(String code) {
        return stockStatisticsRepository.findByCode(code);
    }

    @Override
    public List<StockStatistics> getAll() {

        return stockStatisticsRepository.findAll();
    }
}
