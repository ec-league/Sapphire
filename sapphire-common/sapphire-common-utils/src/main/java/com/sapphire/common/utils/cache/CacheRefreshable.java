/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.sapphire.common.utils.cache;

/**
 * 通用缓存
 * @author yunpeng.byp
 * @version $Id: CacheRefreshable.java, v 0.1 2018年01月13日 下午9:47 yunpeng.byp Exp $
 */
public interface CacheRefreshable {

    /**
     * 刷新缓存,如果成功返回true,如果失败返回false.
     * @return
     */
    boolean refresh();

    /**
     * 缓存的种类
     * @return
     */
    String category();
}