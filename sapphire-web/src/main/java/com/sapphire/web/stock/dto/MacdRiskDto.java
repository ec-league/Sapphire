/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.web.stock.dto;

import java.util.ArrayList;
import java.util.List;

import com.sapphire.common.dal.stock.domain.MacdCycle;
import com.sapphire.common.dal.stock.domain.MacdRiskModel;
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
    private double             averageRate;

    /**
     * 当前价格和上个统计周期内的最高价的百分比
     */
    private double             pricePercentage;

    private double             standardDeviationRate;

    /**
     * Macd周期
     */
    private List<MacdCycleDto> goldCycles;

    public MacdRiskDto(StockStatistics statistics) {
        MacdRiskModel riskModel = statistics.getMacdRiskModel();
        this.averageRate = riskModel.getAverageRate();
        this.pricePercentage = riskModel.getPricePercentage() * 100;
        this.standardDeviationRate = riskModel.getStandardDeviationRate();

        List<MacdCycle> cycles = riskModel.getCycles();

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
    public double getAverageRate() {
        return averageRate;
    }

    /**
     * Setter method for property <tt>averageRate</tt>.
     *
     * @param averageRate  value to be assigned to property averageRate
     */
    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    /**
     * Getter method for property <tt>pricePercentage</tt>.
     *
     * @return property value of pricePercentage
     */
    public double getPricePercentage() {
        return pricePercentage;
    }

    /**
     * Setter method for property <tt>pricePercentage</tt>.
     *
     * @param pricePercentage  value to be assigned to property pricePercentage
     */
    public void setPricePercentage(double pricePercentage) {
        this.pricePercentage = pricePercentage;
    }

    /**
     * Setter method for property <tt>standardDeviationRate</tt>.
     *
     * @param standardDeviationRate  value to be assigned to property standardDeviationRate
     */
    public void setStandardDeviationRate(double standardDeviationRate) {
        this.standardDeviationRate = standardDeviationRate;
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

    /**
     * Getter method for property <tt>standardDeviationRate</tt>.
     *
     * @return property value of standardDeviationRate
     */
    public double getStandardDeviationRate() {
        return standardDeviationRate;
    }
}