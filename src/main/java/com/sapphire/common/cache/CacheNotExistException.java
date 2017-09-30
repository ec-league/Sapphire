package com.sapphire.common.cache;

/**
 * Author: EthanPark <br/>
 * Date: 2016/8/10<br/>
 * Email: byp5303628@hotmail.com
 */
public class CacheNotExistException extends RuntimeException {
    public CacheNotExistException(String msg) {
        super(String.format("Cache : \"%s\" Does Not Exist!", msg));
    }
}
