package com.sapphire.common.exception;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/11<br/>
 * Email: byp5303628@hotmail.com
 */
public class PropertyManagerInitException extends RuntimeException {
    public PropertyManagerInitException(Throwable throwable) {
        super("PropertyManager Init Failed", throwable);
    }
}
