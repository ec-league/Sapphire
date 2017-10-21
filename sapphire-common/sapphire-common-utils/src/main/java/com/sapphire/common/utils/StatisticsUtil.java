/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.utils;

import java.util.Collection;

import com.sapphire.common.utils.annotation.Util;
import com.sapphire.common.utils.aware.NumberAware;

/**
 *
 * @author yunpeng.byp
 * @version $Id: StatisticsUtil.java, v 0.1 2017年10月21日 下午9:10 yunpeng.byp Exp $
 */
@Util
public class StatisticsUtil {

    /**
     * 计算平均值
     * @param list
     * @param numberAware
     * @param <T>
     * @return
     */
    public <T> double getAverage(Collection<T> list, NumberAware<? super T> numberAware) {
        double sum = 0;
        for (T t : list) {
            sum += numberAware.getNumber(t);
        }

        return sum / list.size();
    }

    /**
     * 计算最大值
     * @param list
     * @param numberAware
     * @param <T>
     * @return
     */
    public <T> double getMax(Collection<T> list, NumberAware<? super T> numberAware) {
        double result = 0;
        for (T t : list) {
            if (numberAware.getNumber(t) > result) {
                result = numberAware.getNumber(t);
            }
        }

        return result;
    }

    /**
     * 计算最小值
     * @param list
     * @param numberAware
     * @param <T>
     * @return
     */
    public <T> double getMin(Collection<T> list, NumberAware<? super T> numberAware) {
        double result = 1000;
        for (T t : list) {
            if (numberAware.getNumber(t) < result) {
                result = numberAware.getNumber(t);
            }
        }

        return result;
    }

    /**
     * 获取标准差
     * @param list
     * @param numberAware
     * @param <T>
     * @return
     */
    public <T> double getStandardDiviation(Collection<T> list, NumberAware<? super T> numberAware) {
        double average = getAverage(list, numberAware);

        double sum = 0;
        for (T t : list) {
            double temp = numberAware.getNumber(t);

            sum += (average - temp) * (average - temp);
        }

        return Math.sqrt(sum / list.size());
    }
}