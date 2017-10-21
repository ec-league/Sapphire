/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.web.stock.dto;

import java.util.List;

import com.sapphire.common.dal.stock.domain.MacdCycle;
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
    private double          averageRate;

    /**
     * 当前价格和上个统计周期内的最高价的百分比
     */
    private double          pricePercentage;

    /**
     * Macd周期
     */
    private List<MacdCycle> cycles;

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
     * Getter method for property <tt>cycles</tt>.
     *
     * @return property value of cycles
     */
    public List<MacdCycle> getCycles() {
        return cycles;
    }

    /**
     * Setter method for property <tt>cycles</tt>.
     *
     * @param cycles  value to be assigned to property cycles
     */
    public void setCycles(List<MacdCycle> cycles) {
        this.cycles = cycles;
    }
}