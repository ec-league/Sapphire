package com.sapphire.biz.stock.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapphire.biz.stock.algorithm.StockAlgorithm;
import com.sapphire.biz.stock.service.StockStatisticsService;
import com.sapphire.common.dal.stock.domain.StockStatistics;
import com.sapphire.common.dal.stock.repository.StockStatisticsRepository;

/**
 * @author: EthanPark <br/>
 * Date: 2016/4/22<br/>
 * Email: byp5303628@hotmail.com
 */
@Service("stockStatisticsService")
public class StockStatisticsServiceImpl implements StockStatisticsService {
    private StockStatisticsRepository stockStatisticsRepository;

    private StockAlgorithm            algorithm;

    @Override
    public void update(StockStatistics stat) {
        if (stat == null || "".equals(stat.getCode())) {
            return;
        }

        stockStatisticsRepository.save(stat);
    }

    @Override
    public StockStatistics findByCode(String code) {
        return stockStatisticsRepository.findByCode(code);
    }

    @Override
    public List<StockStatistics> getAll() {
        List<StockStatistics> statistics = new ArrayList<>(
            stockStatisticsRepository.getAllOrderByCode());

        for (StockStatistics s : statistics) {
            algorithm.fillRiskModel(s);
        }
        return statistics;
    }

    /**
     * Setter method for property <tt>stockStatisticsRepository</tt>.
     *
     * @param stockStatisticsRepository  value to be assigned to property stockStatisticsRepository
     */
    @Autowired
    public void setStockStatisticsRepository(StockStatisticsRepository stockStatisticsRepository) {
        this.stockStatisticsRepository = stockStatisticsRepository;
    }

    /**
     * Setter method for property <tt>algorithm</tt>.
     *
     * @param algorithm  value to be assigned to property algorithm
     */
    @Autowired
    public void setAlgorithm(StockAlgorithm algorithm) {
        this.algorithm = algorithm;
    }
}
