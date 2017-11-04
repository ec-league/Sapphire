/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.web.stock.dto;

import java.util.ArrayList;
import java.util.List;

import com.sapphire.common.dal.stock.domain.MacdCycle;
import com.sapphire.common.dal.stock.domain.StockStatistics;
import com.sapphire.common.utils.dto.Dto;

/**
 *
 * @author yunpeng.byp
 * @version $Id: MacdRiskDto.java, v 0.1 2017年10月21日 下午8:00 yunpeng.byp Exp $
 */
public class MacdRiskDto implements Dto {
    /**
     * 每个MACD周期的平均增幅
     */
    private String             averageRate;

    /**
     * 当前价格和上个统计周期内的最高价的百分比
     */
    private String             pricePercentage;

    /**
     * Macd周期
     */
    private List<MacdCycleDto> goldCycles;

    public MacdRiskDto(StockStatistics statistics) {
        this.averageRate = String.format("%.2f%%", statistics.getMacdRiskModel().getAverageRate());
        this.pricePercentage = String.format(".2f%%",
            statistics.getMacdRiskModel().getPricePercentage() * 100);
        List<MacdCycle> cycles = statistics.getMacdRiskModel().getCycles();

        List<MacdCycleDto> cycleDtos = new ArrayList<>(cycles.size());
        for (int index = cycles.size() - 1; index >= 0; index--) {
            cycleDtos.add(new MacdCycleDto(cycles.get(index)));
        }

        this.goldCycles = cycleDtos;
    }

    /**
     * Getter method for property <tt>averageRate</tt>.
     *
     * @return property value of averageRate
     */
    public String getAverageRate() {
        return averageRate;
    }

    /**
     * Setter method for property <tt>averageRate</tt>.
     *
     * @param averageRate  value to be assigned to property averageRate
     */
    public void setAverageRate(String averageRate) {
        this.averageRate = averageRate;
    }

    /**
     * Getter method for property <tt>pricePercentage</tt>.
     *
     * @return property value of pricePercentage
     */
    public String getPricePercentage() {
        return pricePercentage;
    }

    /**
     * Setter method for property <tt>pricePercentage</tt>.
     *
     * @param pricePercentage  value to be assigned to property pricePercentage
     */
    public void setPricePercentage(String pricePercentage) {
        this.pricePercentage = pricePercentage;
    }

    /**
     * Getter method for property <tt>goldCycles</tt>.
     *
     * @return property value of goldCycles
     */
    public List<MacdCycleDto> getGoldCycles() {
        return goldCycles;
    }

    /**
     * Setter method for property <tt>goldCycles</tt>.
     *
     * @param goldCycles  value to be assigned to property goldCycles
     */
    public void setGoldCycles(List<MacdCycleDto> goldCycles) {
        this.goldCycles = goldCycles;
    }
}