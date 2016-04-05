package com.sapphire.common;

import com.sapphire.common.exception.DateParseException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;


/**
 * Author: EthanPark <br/>
 * Date: 2015/12/14<br/>
 * Email: byp5303628@hotmail.com
 */
public class TimeUtilTest {
   @Test(expected = DateParseException.class)
   public void test() {
      String now = TimeUtil.formatTime(TimeUtil.now());

      Assert.assertTrue(now
            .matches("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{2}:[0-9]{2}:[0-9]{2}"));

      String test = "1990-4-13 1:5:5";

      TimeUtil.fromString(test);
   }

   @Test
   public void testNormal() {
      String test = "1991-5-17 20:22:59";
      Timestamp timestamp = TimeUtil.fromString(test);

      Assert.assertNotNull(timestamp);
   }

   @Test
   public void testNormal1() {
      String test = "08/10/2012";

      Timestamp timestamp = TimeUtil.fromStockString(test);

      Assert.assertNotNull(timestamp);
   }
}
