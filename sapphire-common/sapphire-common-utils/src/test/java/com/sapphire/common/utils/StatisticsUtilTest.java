package com.sapphire.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.sapphire.common.utils.aware.NumberAware;

/** 
* StatisticsUtil Tester. 
* 
* @author EthanPark 
* @since <pre>十月 21, 2017</pre> 
* @version 1.0 
*/
public class StatisticsUtilTest {

    /** 
    * 
    * Method: getAverage(List<T> list, NumberAware<? super T> numberAware) 
    * 
    */
    @Test
    public void test_getAverage_1() throws Exception {
        List<TestObject> objects = new ArrayList<>();

        objects.add(new TestObject(5));
        objects.add(new TestObject(7));

        StatisticsUtil util = new StatisticsUtil();

        double d = util.getAverage(objects, new NumberAware<TestObject>() {
            @Override
            public double getNumber(TestObject testObject) {
                return (double) testObject.getVal();
            }
        });

        Assert.assertEquals(d, 6, 0.0001);
    }

    @Test
    public void test_getMax() {
        List<TestObject> objects = new ArrayList<>();

        objects.add(new TestObject(5));
        objects.add(new TestObject(7));

        StatisticsUtil util = new StatisticsUtil();

        double d = util.getMax(objects, new NumberAware<TestObject>() {
            @Override
            public double getNumber(TestObject testObject) {
                return (double) testObject.getVal();
            }
        });

        Assert.assertEquals(d, 7, 0.0001);
    }

    @Test
    public void test_getMin() {
        List<TestObject> objects = new ArrayList<>();

        objects.add(new TestObject(5));
        objects.add(new TestObject(7));

        StatisticsUtil util = new StatisticsUtil();

        double d = util.getMin(objects, new NumberAware<TestObject>() {
            @Override
            public double getNumber(TestObject testObject) {
                return (double) testObject.getVal();
            }
        });

        Assert.assertEquals(d, 5, 0.0001);
    }

    @Test
    public void test_getStandardDiviation() {
        List<TestObject> objects = new ArrayList<>();

        objects.add(new TestObject(5));
        objects.add(new TestObject(7));

        StatisticsUtil util = new StatisticsUtil();

        double d = util.getStandardDiviation(objects, new NumberAware<TestObject>() {
            @Override
            public double getNumber(TestObject testObject) {
                return (double) testObject.getVal();
            }
        });

        Assert.assertEquals(d, 1, 0.0001);
    }

    private static class TestObject {
        private int val;

        public TestObject(int a) {
            this.val = a;
        }

        /**
         * Getter method for property <tt>val</tt>.
         *
         * @return property value of val
         */
        public int getVal() {
            return val;
        }

        /**
         * Setter method for property <tt>val</tt>.
         *
         * @param val  value to be assigned to property val
         */
        public void setVal(int val) {
            this.val = val;
        }
    }
}
