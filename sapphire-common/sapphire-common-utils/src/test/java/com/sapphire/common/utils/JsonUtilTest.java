package com.sapphire.common.utils;

import java.util.ArrayList;
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
        String testString = "[{\"startDate\":\"2016-10-12\",\"endDate\":\"2016-12-06\",\"increase\":\"3.943\",\"consistDays\":40,"
                            + "\"diff\":\"-0.057\"},{\"startDate\":\"2016-12-09\",\"endDate\":\"2016-12-09\",\"increase\":\"0.000\","
                            + "\"consistDays\":1,\"diff\":\"0.096\"},{\"startDate\":\"2017-01-09\",\"endDate\":\"2017-02-28\",\"increase\":\"3.607\","
                            + "\"consistDays\":32,\"diff\":\"-0.057\"},{\"startDate\":\"2017-04-10\",\"endDate\":\"2017-04-11\","
                            + "\"increase\":\"-0.327\",\"consistDays\":2,\"diff\":\"-0.057\"},{\"startDate\":\"2017-04-28\","
                            + "\"endDate\":\"2017-05-02\",\"increase\":\"-0.556\",\"consistDays\":2,\"diff\":\"-0.078\"},"
                            + "{\"startDate\":\"2017-05-12\",\"endDate\":\"2017-06-15\",\"increase\":\"1.573\",\"consistDays\":23,"
                            + "\"diff\":\"-0.116\"},{\"startDate\":\"2017-06-20\",\"endDate\":\"2017-07-26\",\"increase\":\"17.763\","
                            + "\"consistDays\":27,\"diff\":\"0.045\"},{\"startDate\":\"2017-08-25\",\"endDate\":\"2017-09-12\","
                            + "\"increase\":\"3.870\",\"consistDays\":13,\"diff\":\"0.109\"},{\"startDate\":\"2017-10-12\","
                            + "\"endDate\":\"2017-10-20\",\"increase\":\"-0.779\",\"consistDays\":9,\"diff\":\"0.074\"}]";

        List<TestObject> list = jsonUtil.toObject(testString,
            new ArrayList<TestObject>().getClass());

        Assert.assertTrue(list.size() > 0);
    }

    public static class TestObject {
        private String            startDate;
        private String            endDate;
        private transient boolean overZero;
        private transient double  increaseRate;
        private String            increase;
        private int               consistDays;
        private transient double  firstDiff;
        private String            diff;

        /**
         * 根据序列化模型来初始化字段值
         */
        public void init() {
            setIncreaseRate(Double.parseDouble(getIncrease()));
            setFirstDiff(Double.parseDouble(getDiff()));
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
    }

}
