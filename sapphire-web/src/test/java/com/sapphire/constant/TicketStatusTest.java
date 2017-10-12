package com.sapphire.constant;


import org.junit.Assert;
import org.junit.Test;

import com.sapphire.common.dal.manage.constant.TicketStatus;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/14<br/>
 * Email: byp5303628@hotmail.com
 */
public class TicketStatusTest {
   @Test(expected = IllegalArgumentException.class)
   public void testBasic() {
      Assert.assertEquals(TicketStatus.NEED_VERIFY_ON_PRODUCT.getStatus(), 3);
      Assert.assertEquals(TicketStatus.FIXED.getStatus(), 4);

      Assert.assertEquals(TicketStatus.getTicketStatus(0),
            TicketStatus.BUG.toString());
      Assert.assertEquals(TicketStatus.getTicketStatus(1),
            TicketStatus.FIXING.toString());
      Assert.assertEquals(TicketStatus.getTicketStatus(2),
            TicketStatus.NEED_VERIFY_ON_TEST.toString());
      Assert.assertEquals(TicketStatus.getTicketStatus(3),
            TicketStatus.NEED_VERIFY_ON_PRODUCT.toString());
      Assert.assertEquals(TicketStatus.getTicketStatus(4),
            TicketStatus.FIXED.toString());

      TicketStatus.getTicketStatus(5);
   }
}
