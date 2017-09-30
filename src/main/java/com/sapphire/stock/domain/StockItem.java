package com.sapphire.stock.domain;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Ethan on 2016/3/30.
 */
@Entity
@Table(name = StockItem.TABLE_NAME)
public class StockItem {
    private static final Logger logger     = LoggerFactory.getLogger(StockItem.class);
    public static final String  TABLE_NAME = "STOCK_ITEM";
    private static final String FORMAT     = "MM/dd/yyyy";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UIDPK")
    private long                uidPk;

    @Basic
    @Column(name = "CODE")
    private String              code;

    @Basic
    @Column(name = "NAME")
    private String              name;

    @Column(name = "INCREASE_RATE", precision = 7, scale = 2)
    private double              increaseRate;

    @Basic
    @Column(name = "INDUSTRY")
    private String              industry;

    @Column(name = "START_PRICE", precision = 7, scale = 2)
    private double              startPrice;

    @Column(name = "END_PRICE", precision = 7, scale = 2)
    private double              endPrice;

    @Column(name = "HIGHEST_PRICE", precision = 7, scale = 2)
    private double              highestPrice;

    @Column(name = "LOWEST_PRICE", precision = 7, scale = 2)
    private double              lowestPrice;

    @Column(name = "CIRCULATION_MARKET_VALUE", precision = 40, scale = 2)
    private double              circulationMarketValue;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOG_DATE")
    private Timestamp           logDate;

    @Column(name = "TRADING", precision = 40, scale = 2)
    private double              trading;

    @Column(name = "TRADING_VALUE", precision = 40, scale = 3)
    private double              tradingValue;

    @Column(name = "MACD_DEA", precision = 11, scale = 6)
    private double              macdDea;

    @Column(name = "MACD_DIFF", precision = 11, scale = 6)
    private double              macdDiff;

    @Column(name = "MACD", precision = 11, scale = 6)
    private double              macd;

    @Column(name = "EMA_12", precision = 11, scale = 6)
    private double              ema12;

    @Column(name = "EMA_26", precision = 11, scale = 6)
    private double              ema26;

    @Column(name = "AVERAGE_5", precision = 7, scale = 2)
    private double              average5;

    @Column(name = "AVERAGE_10", precision = 7, scale = 2)
    private double              average10;

    @Column(name = "AVERAGE_20", precision = 7, scale = 2)
    private double              average20;

    @Column(name = "AVERAGE_30", precision = 7, scale = 2)
    private double              average30;

    /**
    * 是否为最新数据
    */
    @Column(name = "IS_LAST")
    private boolean             last;

    /**
    * 是否停牌
    */
    @Column(name = "IS_STOP")
    private boolean             stop;

    @Column(name = "IS_NEW")
    private boolean             newStock;

    public StockItem() {
        setEma12(getEndPrice());
        setEma26(getEndPrice());
        setIndustry("");
    }

    public StockItem(String line) {
        String[] lines = line.split("\t");

        try {
            setLogDate(new Timestamp(new SimpleDateFormat(FORMAT).parse(lines[0]).getTime()));
        } catch (ParseException e) {
            logger.error("Parse Date Failed", e);
            return;
        }
        setStartPrice(Double.parseDouble(lines[1]));
        setHighestPrice(Double.parseDouble(lines[2]));
        setLowestPrice(Double.parseDouble(lines[3]));
        setEndPrice(Double.parseDouble(lines[4]));
        setTrading(Double.parseDouble(lines[5]));
        setTradingValue(Double.parseDouble(lines[6]));

        setEma12(getEndPrice());
        setEma26(getEndPrice());
        setIndustry("");
    }

    public static StockItem makeStockItemFromAll(String line) {
        return new StockItem(line);
    }

    /**
    * 更新原来的StockItem，如果新的Item是停牌的，则只更新日期
    * 
    * @param item
    */
    public void updateItem(StockItem item, int small, int big) {
        setLogDate(item.getLogDate());
        if (item.isStop()) {
            return;
        }

        setStartPrice(item.getStartPrice());
        setEndPrice(item.getEndPrice());
        setHighestPrice(item.getHighestPrice());
        setLowestPrice(item.getLowestPrice());
        setIncreaseRate(item.getIncreaseRate());

        setTrading(item.getTrading());
        setTradingValue(item.getTradingValue());

        setStop(false);

        setEma12(getEma12() * (small - 1) / (small + 1) + item.getEma12() * 2 / (small + 1));
        setEma26(getEma26() * (big - 1) / (big + 1) + item.getEma26() * 2 / (big + 1));
        setMacdDiff(getEma12() - getEma26());
        setMacdDea(getMacdDea() * 0.8 + getMacdDiff() * 0.2);
        setMacd(getMacdDiff() - getMacdDea());
    }

    public static StockItem makeStockItemFromDaily(String line) {
        if (StringUtils.isEmpty(line)) {
            return null;
        }
        String[] lines = line.replaceAll("\t\t", "\t").split("\t");
        StockItem item = new StockItem();
        item.setCode(lines[0].substring(2));
        item.setName(lines[1]);
        if (!"--".equals(lines[4])) {
            item.setEndPrice(Double.parseDouble(lines[4]));
            item.setStartPrice(Double.parseDouble(lines[13]));
            item.setHighestPrice(Double.parseDouble(lines[15]));
            item.setLowestPrice(Double.parseDouble(lines[16]));
            item.setIncreaseRate((item.getEndPrice() / item.getStartPrice() - 1) * 100);
            item.setTradingValue(Double.parseDouble(lines[25]));
            item.setEma12(item.getEndPrice());
            item.setEma26(item.getEndPrice());
            if (!"--".equals(lines[32]))
                item.setCirculationMarketValue(Double.parseDouble(lines[32]));
        } else {
            return null;
        }
        item.setIndustry(lines[11]);
        return item;
    }

    public boolean isNewStock() {
        return newStock;
    }

    public double getAverage5() {
        return average5;
    }

    public void setAverage5(double average5) {
        this.average5 = average5;
    }

    public double getAverage10() {
        return average10;
    }

    public void setAverage10(double average10) {
        this.average10 = average10;
    }

    public double getAverage20() {
        return average20;
    }

    public void setAverage20(double average20) {
        this.average20 = average20;
    }

    public double getAverage30() {
        return average30;
    }

    public void setAverage30(double average30) {
        this.average30 = average30;
    }

    public void setNewStock(boolean newStock) {
        this.newStock = newStock;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public long getUidPk() {
        return uidPk;
    }

    public void setUidPk(long uidPk) {
        this.uidPk = uidPk;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getIncreaseRate() {
        return increaseRate;
    }

    public void setIncreaseRate(double increaseRate) {
        this.increaseRate = increaseRate;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(double endPrice) {
        this.endPrice = endPrice;
    }

    public double getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(double highestPrice) {
        this.highestPrice = highestPrice;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public double getCirculationMarketValue() {
        return circulationMarketValue;
    }

    public void setCirculationMarketValue(double circulationMarketValue) {
        this.circulationMarketValue = circulationMarketValue;
    }

    public Timestamp getLogDate() {
        return logDate;
    }

    public void setLogDate(Timestamp logDate) {
        this.logDate = logDate;
    }

    public double getTrading() {
        return trading;
    }

    public void setTrading(double trading) {
        this.trading = trading;
    }

    public double getTradingValue() {
        return tradingValue;
    }

    public void setTradingValue(double tradingValue) {
        this.tradingValue = tradingValue;
    }

    public double getMacdDea() {
        return macdDea;
    }

    public void setMacdDea(double macdDea) {
        this.macdDea = macdDea;
    }

    public double getMacdDiff() {
        return macdDiff;
    }

    public void setMacdDiff(double macdDiff) {
        this.macdDiff = macdDiff;
    }

    public double getMacd() {
        return macd;
    }

    public void setMacd(double macd) {
        this.macd = macd;
    }

    public double getEma12() {
        return ema12;
    }

    public void setEma12(double ema12) {
        this.ema12 = ema12;
    }

    public double getEma26() {
        return ema26;
    }

    public void setEma26(double ema26) {
        this.ema26 = ema26;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
