package com.sapphire.common.dal.stock.domain;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ethan
 * @since 2016/3/30.
 */
@Entity
@Table(name = StockItem.TABLE_NAME)
public class StockItem {
    private static final Logger logger     = LoggerFactory.getLogger(StockItem.class);
    public static final String  TABLE_NAME = "STOCK_ITEM";
    private static final String FORMAT     = "yyyy/MM/dd";

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

    /**
     * 市盈率
     */
    @Column(name = "PE", precision = 17, scale = 6)
    private double              pe;

    /**
     * 市净率
     */
    @Column(name = "PB", precision = 17, scale = 6)
    private double              pb;

    /**
     * 250日均线
     */
    @Column(name = "AVERAGE_250", precision = 7, scale = 2)
    private double              average250;

    public StockItem() {
        this.industry = "Need Update!";
    }

    public StockItem(String line) {
        this();
        String[] lines = line.split(",");

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

        setEma12(endPrice);
        setEma26(endPrice);
        setIndustry("");
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

    /**
     * Getter method for property <tt>pe</tt>.
     *
     * @return property value of pe
     */
    public double getPe() {
        return pe;
    }

    /**
     * Setter method for property <tt>pe</tt>.
     *
     * @param pe  value to be assigned to property pe
     */
    public void setPe(double pe) {
        this.pe = pe;
    }

    /**
     * Setter method for property <tt>pb</tt>.
     *
     * @param pb  value to be assigned to property pb
     */
    public void setPb(double pb) {
        this.pb = pb;
    }

    /**
     * Getter method for property <tt>pb</tt>.
     *
     * @return property value of pb
     */
    public double getPb() {
        return pb;
    }

    /**
     * Getter method for property <tt>average250</tt>.
     *
     * @return property value of average250
     */
    public double getAverage250() {
        return average250;
    }

    /**
     * Setter method for property <tt>average250</tt>.
     *
     * @param average250  value to be assigned to property average250
     */
    public void setAverage250(double average250) {
        this.average250 = average250;
    }
}
