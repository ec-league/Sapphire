/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.utils.aware;

/**
 *
 * @author yunpeng.byp
 * @version $Id: NumberAware.java, v 0.1 2017年10月21日 下午9:22 yunpeng.byp Exp $
 */
public interface NumberAware<T> {
    /**
     * 获取参与比较的数字
     * @param t
     * @return
     */
    double getNumber(T t);
}