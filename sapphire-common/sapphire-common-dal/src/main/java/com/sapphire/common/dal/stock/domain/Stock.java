package com.sapphire.common.dal.stock.domain;

import java.beans.Transient;
import java.util.Collections;
import java.util.List;

import com.sapphire.common.utils.dto.Dto;

/**
 * Created by Ethan on 2016/3/30.
 */
public class Stock implements Dto {
    private transient List<StockItem> stockItems;
    private double                    increaseTotal;
    private double                    highestPrice;
    private double                    endPrice;
    private String                    name;
    private boolean                   goldPossible;
    private double                    averageGoldDays;

    /**
    * 股票代码
    */
    private String                    code;
    private double                    firstDiff;
    private boolean                   shouldPass = false;
    private double                    lowestMacd;

    /**
    * 最后一天的股票macd值
    */
    private double                    currentMacd;

    /**
    * 最后一天的股票macd diff值
    */
    private double                    currentDiff;

    private boolean                   stop;

    /**
    * Used when construct a item only with setter method.
    */
    public Stock() {
    }

    public Stock(List<StockItem> stockItems) {
        update(stockItems);
    }

    public String getCode() {
        return code;
    }

    /**
    * 第二天变成金叉，所需要的收盘价格
    * 
    * @param lastItem
    * @return
    */
    private static double goldPrice(StockItem lastItem) {
        return (76 * 17.0) / (19 * 11) * lastItem.getEma26() - 57.0 / 11 * lastItem.getEma12()
               + 76.0 / 11 * lastItem.getMacdDea();
    }

    /**
    * 第二天是否可能变成金叉
    * 
    * @param lastItem
    * @return
    */
    private static boolean isGoldPossible(StockItem lastItem) {
        double price = goldPrice(lastItem);

        if ((price - lastItem.getEndPrice()) / lastItem.getEndPrice() > 0.1) {
            return false;
        }
        return true;
    }

    public double getAverageGoldDays() {
        return averageGoldDays;
    }

    public void setAverageGoldDays(double averageGoldDays) {
        this.averageGoldDays = averageGoldDays;
    }

    public void setGoldPossible(boolean goldPossible) {
        this.goldPossible = goldPossible;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean isTodayPlus() {
        int size = stockItems.size();
        if (size >= 2) {
            return stockItems.get(size - 2).getMacd() < 0 && currentMacd >= 0;
        }
        return false;
    }

    /**
    * 只计算不受总Stock数目影响的属性值
    * 
    * @param items
    */
    private void update(List<StockItem> items) {
        if (items == null || items.isEmpty()) {
            stockItems = Collections.emptyList();
            return;
        }
        stockItems = items;
        code = stockItems.get(0).getCode();
        name = stockItems.get(0).getName();
        StockItem last = stockItems.get(stockItems.size() - 1);
        endPrice = last.getEndPrice();
        currentMacd = last.getMacd();
        currentDiff = last.getMacdDiff();
        stop = last.isStop();
        goldPossible = isGoldPossible(last);
    }

    public boolean isUpper() {
        int size = stockItems.size();
        if (size < 3)
            return false;
        StockItem beforeYesterday = stockItems.get(size - 3);
        StockItem yesterday = stockItems.get(size - 2);
        StockItem today = stockItems.get(size - 1);

        return Double.compare(beforeYesterday.getMacd(), yesterday.getMacd()) < 0
               && Double.compare(yesterday.getMacd(), today.getMacd()) < 0;
    }

    public double getIncreaseFromStart() {
        if (stockItems == null || stockItems.isEmpty())
            return 0d;
        double start = stockItems.get(0).getEndPrice();
        double end = stockItems.get(stockItems.size() - 1).getEndPrice();

        return (end - start) / start * 100;
    }

    @Transient
    public List<StockItem> getStockItems() {
        return stockItems;
    }

    public boolean isShouldPass() {
        return shouldPass;
    }

    public double getIncreaseTotal() {
        return increaseTotal;
    }

    public void setIncreaseTotal(double increaseTotal) {
        this.increaseTotal = increaseTotal;
    }

    public void setHighestPrice(double highestPrice) {
        this.highestPrice = highestPrice;
    }

    public double getFirstDiff() {
        return firstDiff;
    }

    public void setFirstDiff(double firstDiff) {
        this.firstDiff = firstDiff;
    }

    public double getCurrentMacd() {
        return currentMacd;
    }

    public void setEndPrice(double endPrice) {
        this.endPrice = endPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCurrentMacd(double currentMacd) {
        this.currentMacd = currentMacd;
    }

    public void setCurrentDiff(double currentDiff) {
        this.currentDiff = currentDiff;
    }

    public void setLowestMacd(double lowestMacd) {
        this.lowestMacd = lowestMacd;
    }

    public double getCurrentDiff() {
        return currentDiff;
    }

    public boolean isStop() {
        return stop;
    }

    public double getHighestPrice() {
        return highestPrice;
    }

    public double getEndPrice() {
        return endPrice;
    }

    public String getName() {
        return name;
    }

    public double getLowestMacd() {
        return lowestMacd;
    }

    public boolean isGoldPossible() {
        return goldPossible;
    }
}
