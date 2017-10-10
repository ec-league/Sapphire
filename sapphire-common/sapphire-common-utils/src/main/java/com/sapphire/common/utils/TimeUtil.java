package com.sapphire.common.utils;

import com.sapphire.common.utils.annotation.Util;
import com.sapphire.common.utils.exception.DateParseException;

import static java.lang.System.currentTimeMillis;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Author: EthanPark <br/>
 * Date: 2015/11/20<br/>
 * Email: byp5303628@hotmail.com
 */
@Util
public class TimeUtil {
    private static final String DATE_FORMAT       = "yyyy-MM-dd HH:mm:ss";
    private static final String STOCK_DATE_FORMAT = "MM/dd/yyyy";
    private static final String STOCK_WEB_FORMAT  = "yyyy-MM-dd";

    private static final long   ONE_MONTH         = 30 * 24 * 60 * 60 * 1000l;

    private static final long   ONE_YEAR          = 365 * 24 * 60 * 60 * 1000l;

    public static String formatTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    public static Timestamp now() {
        return new Timestamp(currentTimeMillis());
    }

    public static Timestamp oneMonthAgo() {
        return new Timestamp(currentTimeMillis() - ONE_MONTH);
    }

    public static Timestamp oneYearAgo() {
        return new Timestamp(currentTimeMillis() - ONE_YEAR);
    }

    public static Timestamp fromString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        if (time.matches("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{2}:[0-9]{2}:[0-9]{2}")) {
            try {
                return new Timestamp(sdf.parse(time).getTime());
            } catch (ParseException e) {
                throw new DateParseException();
            }
        }
        throw new DateParseException();
    }

    public static Timestamp fromStockString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(STOCK_DATE_FORMAT);
        if (time.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
            try {
                return new Timestamp(sdf.parse(time).getTime());
            } catch (ParseException e) {
                throw new DateParseException();
            }
        }
        throw new DateParseException();
    }

    public static Timestamp fromStockWebString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(STOCK_WEB_FORMAT);
        if (time.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            try {
                return new Timestamp(sdf.parse(time).getTime());
            } catch (ParseException e) {
                throw new DateParseException();
            }
        }
        throw new DateParseException();
    }

    public static String formatStockWeb(Date date) {
        return new SimpleDateFormat(STOCK_WEB_FORMAT).format(date);
    }
}
