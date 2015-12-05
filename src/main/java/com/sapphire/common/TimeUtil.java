package com.sapphire.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.System.currentTimeMillis;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/20<br/>
 * Email: byp5303628@hotmail.com
 */
public class TimeUtil {
   private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

   public static String formatTime(Date date) {
      SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
      return sdf.format(date);
   }

   public static Timestamp now() {
      return new Timestamp(currentTimeMillis());
   }

   public static Timestamp fromString(String time) {
      SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
      if (time
            .matches("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{2}:[0-9]{2}:[0-9]{2}")) {
         try {
            return new Timestamp(sdf.parse(time).getTime());
         } catch (ParseException e) {
            throw new RuntimeException(String.format(
                  "Time format is not right : %s", time));
         }
      }
      throw new RuntimeException(String.format("Time format is not right : %s",
            time));
   }
}
