/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.biz.stock.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.common.dal.stock.domain.Stock;
import com.sapphire.common.dal.stock.domain.StockItem;
import com.sapphire.common.dal.stock.domain.StockStatistics;
import com.sapphire.common.utils.JsonUtil;
import com.sapphire.common.utils.TimeUtil;
import com.sapphire.common.utils.annotation.Algorithm;

/**
 *
 * @author yunpeng.byp
 * @version $Id: StockAlgorithm.java, v 0.1 2017年10月14日 下午10:17 yunpeng.byp Exp $
 */
@Algorithm
public class StockAlgorithm {

    private JsonUtil jsonUtil;

    private TimeUtil timeUtil;

    /**
     * 根据已有的Stock数据,计算对应的StockStatistics数据
     * @param stock, 包含全部的StockItem的集合
     * @return
     */
    public StockStatistics calculate(Stock stock) {
        StockStatistics stat = new StockStatistics();

        stat.setCode(stock.getCode());
        stat.setName(stock.getName());

        fillProcessData(stock, stat);

        return stat;
    }

    private void fillProcessData(Stock stock, StockStatistics stat) {
        List<StockItem> stockItems = stock.getStockItems();

        if (stockItems.isEmpty()) {
            return;
        }

        //region 计算最高价和最低Macd值
        double highPrice = 0;
        double lowest = 0;
        for (StockItem item : stockItems) {
            if (item.getHighestPrice() > highPrice) {
                highPrice = item.getHighestPrice();
            }

            if (item.getMacd() < lowest) {
                lowest = item.getMacd();
            }
        }

        stat.setHighestPrice(highPrice);
        stat.setLowestMacd(lowest);
        //endregion

        //region 计算当前股票的信息
        StockItem lastItem = stockItems.get(stockItems.size() - 1);

        stat.setCurrentMacd(lastItem.getMacd());
        stat.setStop(lastItem.isStop());
        stat.setCurrentDiff(lastItem.getMacdDiff());
        //endregion

        //region 计算金叉的累计增幅和平均持续时间
        double origin = 1.0;
        int days = 0;
        List<MacdCycle> cycles = group(stockItems);
        if (cycles.isEmpty()) {
            return;
        }

        for (MacdCycle cycle : cycles) {
            origin *= (1 + cycle.increaseRate / 100);
            days += cycle.consistDays;
        }

        int average = 0;
        if (cycles.isEmpty()) {
            return;
        }

        average = days / cycles.size();
        stat.setAverageGoldDays(average);
        stat.setIncreaseTotal(origin);

        stat.setDesc(jsonUtil.toJson(cycles));
        //endregion
    }

    private List<MacdCycle> group(List<StockItem> items) {
        List<List<StockItem>> complexItems = new ArrayList<>();

        //region 将日线进行金叉分组
        List<StockItem> tempList = new ArrayList<>();
        for (StockItem item : items) {
            if (item.getMacd() > 0) {
                tempList.add(item);
            } else {
                if (tempList.isEmpty()) {
                    continue;
                }
                complexItems.add(tempList);
                tempList = new ArrayList<>();
            }
        }

        if (!tempList.isEmpty()) {
            complexItems.add(tempList);
        }
        //endregion

        if (!complexItems.isEmpty()
            && complexItems.get(0).get(0).getLogDate().equals(items.get(0).getLogDate())) {
            complexItems = complexItems.subList(1, complexItems.size());
        }

        List<MacdCycle> statics = new ArrayList<>();
        for (List<StockItem> list : complexItems) {
            MacdCycle macdCycle = new MacdCycle();
            StockItem start = list.get(0);
            StockItem end = list.get(list.size() - 1);

            macdCycle.setStartDate(timeUtil.formatStockDate(start.getLogDate()));
            macdCycle.setEndDate(timeUtil.formatStockDate(end.getLogDate()));
            macdCycle.setOverZero(start.getMacdDiff() > 0);
            macdCycle.setConsistDays(list.size());
            macdCycle.setFirstDiff(start.getMacdDiff());
            macdCycle.setIncreaseRate((end.getEndPrice() / start.getEndPrice() - 1) * 100);
            macdCycle.setDiff(String.format("%.3f", macdCycle.getFirstDiff()));
            macdCycle.setIncrease(String.format("%.3f", macdCycle.getIncreaseRate()));

            statics.add(macdCycle);
        }

        return statics;
    }

    /**
     * Setter method for property <tt>jsonUtil</tt>.
     *
     * @param jsonUtil  value to be assigned to property jsonUtil
     */
    @Autowired
    public void setJsonUtil(JsonUtil jsonUtil) {
        this.jsonUtil = jsonUtil;
    }

    /**
     * Setter method for property <tt>timeUtil</tt>.
     *
     * @param timeUtil  value to be assigned to property timeUtil
     */
    @Autowired
    public void setTimeUtil(TimeUtil timeUtil) {
        this.timeUtil = timeUtil;
    }

    /**
     * Macd一个周期的统计数据(金叉)
     */
    private static class MacdCycle {
        private String            startDate;
        private String            endDate;
        private transient boolean overZero;
        private transient double  increaseRate;
        private String            increase;
        private int               consistDays;
        private transient double  firstDiff;
        private String            diff;

        /**
         * Getter method for property <tt>startDate</tt>.
         *
         * @return property value of startDate
         */
        public String getStartDate() {
            return startDate;
        }

        /**
         * Setter method for property <tt>startDate</tt>.
         *
         * @param startDate  value to be assigned to property startDate
         */
        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        /**
         * Getter method for property <tt>endDate</tt>.
         *
         * @return property value of endDate
         */
        public String getEndDate() {
            return endDate;
        }

        /**
         * Setter method for property <tt>endDate</tt>.
         *
         * @param endDate  value to be assigned to property endDate
         */
        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        /**
         * Getter method for property <tt>isOverZero</tt>.
         *
         * @return property value of isOverZero
         */
        public boolean isOverZero() {
            return overZero;
        }

        /**
         * Setter method for property <tt>isOverZero</tt>.
         *
         * @param overZero  value to be assigned to property isOverZero
         */
        public void setOverZero(boolean overZero) {
            this.overZero = overZero;
        }

        /**
         * Getter method for property <tt>increaseRate</tt>.
         *
         * @return property value of increaseRate
         */
        public double getIncreaseRate() {
            return increaseRate;
        }

        /**
         * Setter method for property <tt>increaseRate</tt>.
         *
         * @param increaseRate  value to be assigned to property increaseRate
         */
        public void setIncreaseRate(double increaseRate) {
            this.increaseRate = increaseRate;
        }

        /**
         * Getter method for property <tt>consistDays</tt>.
         *
         * @return property value of consistDays
         */
        public int getConsistDays() {
            return consistDays;
        }

        /**
         * Setter method for property <tt>consistDays</tt>.
         *
         * @param consistDays  value to be assigned to property consistDays
         */
        public void setConsistDays(int consistDays) {
            this.consistDays = consistDays;
        }

        /**
         * Getter method for property <tt>firstDiff</tt>.
         *
         * @return property value of firstDiff
         */
        public double getFirstDiff() {
            return firstDiff;
        }

        /**
         * Setter method for property <tt>firstDiff</tt>.
         *
         * @param firstDiff  value to be assigned to property firstDiff
         */
        public void setFirstDiff(double firstDiff) {
            this.firstDiff = firstDiff;
        }

        /**
         * Getter method for property <tt>increase</tt>.
         *
         * @return property value of increase
         */
        public String getIncrease() {
            return increase;
        }

        /**
         * Setter method for property <tt>increase</tt>.
         *
         * @param increase  value to be assigned to property increase
         */
        public void setIncrease(String increase) {
            this.increase = increase;
        }

        /**
         * Getter method for property <tt>diff</tt>.
         *
         * @return property value of diff
         */
        public String getDiff() {
            return diff;
        }

        /**
         * Setter method for property <tt>diff</tt>.
         *
         * @param diff  value to be assigned to property diff
         */
        public void setDiff(String diff) {
            this.diff = diff;
        }
    }
}