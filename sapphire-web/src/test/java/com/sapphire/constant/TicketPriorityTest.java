package com.sapphire.constant;


import org.junit.Assert;
import org.junit.Test;

import com.sapphire.common.dal.manage.constant.TicketPriority;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/14<br/>
 * Email: byp5303628@hotmail.com
 */
public class TicketPriorityTest {
   @Test(expected = IllegalArgumentException.class)
   public void testBasicString() {
      Assert.assertEquals(TicketPriority.P3.getCode(), 3);
      Assert.assertEquals(TicketPriority.P0.getCode(), 0);
      Assert.assertEquals(TicketPriority.toTicketPriority("p0"),
            TicketPriority.P0);
      Assert.assertEquals(TicketPriority.toTicketPriority("p1"),
            TicketPriority.P1);
      Assert.assertEquals(TicketPriority.toTicketPriority("p2"),
            TicketPriority.P2);
      Assert.assertEquals(TicketPriority.toTicketPriority("p3"),
            TicketPriority.P3);
      TicketPriority.toTicketPriority("Unknown");
   }

   @Test(expected = IllegalArgumentException.class)
   public void testBasicCode() {
      Assert.assertEquals(TicketPriority.toTicketPriority(0), TicketPriority.P0);
      Assert.assertEquals(TicketPriority.toTicketPriority(1), TicketPriority.P1);
      Assert.assertEquals(TicketPriority.toTicketPriority(2), TicketPriority.P2);
      Assert.assertEquals(TicketPriority.toTicketPriority(3), TicketPriority.P3);
      TicketPriority.toTicketPriority(5);
   }
}
