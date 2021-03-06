package com.sapphire.constant;

import org.junit.Assert;
import org.junit.Test;

import com.sapphire.common.dal.manage.constant.TicketType;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/14<br/>
 * Email: byp5303628@hotmail.com
 */
public class TicketTypeTest {
   @Test(expected = IllegalArgumentException.class)
   public void testBasicString() {
      Assert.assertEquals(TicketType.BUG.getCode(), 0);
      Assert.assertEquals(TicketType.REQUEST.getCode(), 1);

      Assert.assertEquals(TicketType.toTicketType("Request"),
            TicketType.REQUEST);
      Assert.assertEquals(TicketType.toTicketType("bUg"), TicketType.BUG);

      TicketType.toTicketType("Unknown");
   }

   @Test(expected = IllegalArgumentException.class)
   public void testBasicCode() {
      Assert.assertEquals(TicketType.toTicketType(0), TicketType.BUG);
      Assert.assertEquals(TicketType.toTicketType(1), TicketType.REQUEST);

      TicketType.toTicketType(2);
   }
}
