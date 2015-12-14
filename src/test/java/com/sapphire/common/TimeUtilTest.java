package com.sapphire.common;

import com.sapphire.common.exception.DateParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Timestamp;


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
}
