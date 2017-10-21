package com.sapphire.common.utils;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/** 
* JsonUtil Tester. 
* 
* @author EthanPark 
* @since <pre>十月 21, 2017</pre> 
* @version 1.0 
*/
public class JsonUtilTest {

    /**
    * 
    * Method: toObject(String jsonStr, Class<T> tClass) 
    * 
    */
    @Test
    public void test_toObject_1() throws Exception {
        JsonUtil jsonUtil = new JsonUtil();
        String testString = "{\"averageRate\":1.883960746509081,\"standardDeviationRate\":3.954000670738423,"
                            + "\"pricePercentage\":0.4252472367655613,\"cycles\":[{\"startDate\":\"2016-09-22\",\"endDate\":\"2016-11-01\","
                            + "\"increase\":\"5.112\",\"highIncrease\":\".3f\",\"consistDays\":24,\"diff\":\"-0.479\"},{\"startDate\":\"2016-11-30\","
                            + "\"endDate\":\"2016-12-01\",\"increase\":\"-2.254\",\"highIncrease\":\".3f\",\"consistDays\":2,\"diff\":\"-0.210\"},"
                            + "{\"startDate\":\"2016-12-22\",\"endDate\":\"2017-02-03\",\"increase\":\"7.910\",\"highIncrease\":\".3f\","
                            + "\"consistDays\":26,\"diff\":\"-0.392\"},{\"startDate\":\"2017-03-03\",\"endDate\":\"2017-03-29\","
                            + "\"increase\":\"1.357\",\"highIncrease\":\".3f\",\"consistDays\":19,\"diff\":\"0.024\"},{\"startDate\":\"2017-06-05\","
                            + "\"endDate\":\"2017-08-11\",\"increase\":\"8.345\",\"highIncrease\":\".3f\",\"consistDays\":50,\"diff\":\"-1.600\"},"
                            + "{\"startDate\":\"2017-09-11\",\"endDate\":\"2017-09-14\",\"increase\":\"-2.628\",\"highIncrease\":\".3f\","
                            + "\"consistDays\":4,\"diff\":\"0.026\"},{\"startDate\":\"2017-09-18\",\"endDate\":\"2017-09-18\",\"increase\":\"0.000\","
                            + "\"highIncrease\":\".3f\",\"consistDays\":1,\"diff\":\"0.023\"},{\"startDate\":\"2017-09-20\","
                            + "\"endDate\":\"2017-09-22\",\"increase\":\"-0.631\",\"highIncrease\":\".3f\",\"consistDays\":3,\"diff\":\"0.028\"},"
                            + "{\"startDate\":\"2017-10-11\",\"endDate\":\"2017-10-13\",\"increase\":\"-0.256\",\"highIncrease\":\".3f\","
                            + "\"consistDays\":3,\"diff\":\"0.007\"}]}";
        TestObject object = jsonUtil.toObject(testString, TestObject.class);

        Assert.assertNotNull(object);
    }

    private static class TestObject {

        /**
         * 每个MACD周期的平均增幅
         */
        private double             averageRate;

        /**
         * MACD增幅的标准差
         */
        private double             standardDeviationRate;

        /**
         * 当前价格和上个统计周期内的最高价的百分比
         */
        private double             pricePercentage;

        private List<TestSubClass> cycles;

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
         * Setter method for property <tt>standardDiviationRate</tt>.
         *
         * @param standardDiviationRate  value to be assigned to property standardDiviationRate
         */
        public void setStandardDeviationRate(double standardDiviationRate) {
            this.standardDeviationRate = standardDiviationRate;
        }

        /**
         * Getter method for property <tt>standardDeviationRate</tt>.
         *
         * @return property value of standardDeviationRate
         */
        public double getStandardDeviationRate() {
            return standardDeviationRate;
        }

        /**
         * Getter method for property <tt>cycles</tt>.
         *
         * @return property value of cycles
         */
        public List<TestSubClass> getCycles() {
            return cycles;
        }

        /**
         * Setter method for property <tt>cycles</tt>.
         *
         * @param cycles  value to be assigned to property cycles
         */
        public void setCycles(List<TestSubClass> cycles) {
            this.cycles = cycles;
        }
    }

    private static class TestSubClass {
        private String            startDate;
        private String            endDate;
        private transient boolean overZero;

        /**
         * Macd单个周期增幅(收盘价/第一天收盘价)
         */
        private transient double  increaseRate;
        /**
         * Macd单个周期增幅(收盘价/第一天收盘价)
         */
        private String            increase;
        /**
         * Macd单个周期最大增幅(最高价/第一天收盘价)
         */
        private transient double  highIncreaseRate;
        /**
         * Macd单个周期最大增幅(最高价/第一天收盘价)
         */
        private String            highIncrease;
        private int               consistDays;

        /**
         * Macd周期的第一次Diff的值.(用于计算)
         */
        private transient double  firstDiff;

        /**
         * Macd周期的第一次Diff的值.(用于持久化)
         */
        private String            diff;

        /**
         * 根据序列化模型来初始化字段值
         */
        public void init() {
            setIncreaseRate(Double.parseDouble(getIncrease()));
            setFirstDiff(Double.parseDouble(getDiff()));
            setHighIncreaseRate(Double.parseDouble(getHighIncrease()));
        }

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

        /**
         * Getter method for property <tt>highIncreaseRate</tt>.
         *
         * @return property value of highIncreaseRate
         */
        public double getHighIncreaseRate() {
            return highIncreaseRate;
        }

        /**
         * Setter method for property <tt>highIncreaseRate</tt>.
         *
         * @param highIncreaseRate  value to be assigned to property highIncreaseRate
         */
        public void setHighIncreaseRate(double highIncreaseRate) {
            this.highIncreaseRate = highIncreaseRate;
        }

        /**
         * Getter method for property <tt>highIncrease</tt>.
         *
         * @return property value of highIncrease
         */
        public String getHighIncrease() {
            return highIncrease;
        }

        /**
         * Setter method for property <tt>highIncrease</tt>.
         *
         * @param highIncrease  value to be assigned to property highIncrease
         */
        public void setHighIncrease(String highIncrease) {
            this.highIncrease = highIncrease;
        }
    }
}
