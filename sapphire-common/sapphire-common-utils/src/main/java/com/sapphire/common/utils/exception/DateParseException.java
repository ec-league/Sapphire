package com.sapphire.common.utils.exception;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/11<br/>
 * Email: byp5303628@hotmail.com
 */
public class DateParseException extends RuntimeException {
    public DateParseException() {
        super("Date Format is Not Correct!");
    }
}
