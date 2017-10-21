/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.utils;

import com.google.gson.Gson;
import com.sapphire.common.utils.annotation.Util;
import com.sapphire.common.utils.exception.JsonParseException;

/**
 *
 * @author yunpeng.byp
 * @version $Id: JsonUtil.java, v 0.1 2017年10月14日 下午10:48 yunpeng.byp Exp $
 */
@Util
public class JsonUtil {

    /**
     * 将对象转成String
     * @param object
     * @return
     */
    public String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    /**
     * 将String转成Object
     * @param jsonStr
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T toObject(String jsonStr, Class<T> tClass) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(jsonStr, tClass);
        } catch (Exception e) {
            throw new JsonParseException(
                String.format("JsonString is : %s, Class is : %s", jsonStr, tClass), e);
        }
    }
}