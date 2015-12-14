package com.sapphire.common;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sapphire.common.exception.DateParseException;


/**
 * Author: EthanPark <br/>
 * Date: 2015/12/14<br/>
 * Email: byp5303628@hotmail.com
 */
public class TimeUtilTest {
   @Test(expectedExceptions = DateParseException.class, expectedExceptionsMessageRegExp = "Date Format is Not Correct!")
   public void test() {
      String now = TimeUtil.formatTime(TimeUtil.now());

      Assert.assertTrue(now.matches("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{2}:[0-9]{2}:[0-9]{2}"));

      String test = "1990-4-13 1:5:5";

      TimeUtil.fromString(test);
   }

   @Test
   public void testNormal() {
      String test = "1991-5-17 20:22:59";
      Timestamp timestamp = TimeUtil.fromString(test);
   }
}
