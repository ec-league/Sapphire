package com.sapphire.constant;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/4<br/>
 * Email: byp5303628@hotmail.com
 */
public enum TicketStatus {
   BUG(0), FIXING(1), NEED_VERIFY_ON_TEST(2), NEED_VERIFY_ON_PRODUCT(3), FIXED(
         4);

   TicketStatus(int status) {
      this.status = status;
   }

   private int status;

   public static String getTicketStatus(int status) {
      TicketStatus ticketStatus;
      switch (status) {
      case 0:
         ticketStatus = BUG;
         break;
      case 1:
         ticketStatus = FIXING;
         break;
      case 2:
         ticketStatus = NEED_VERIFY_ON_TEST;
         break;
      case 3:
         ticketStatus = NEED_VERIFY_ON_PRODUCT;
         break;
      case 4:
         ticketStatus = FIXED;
         break;
      default:
         throw new IllegalArgumentException(String.format(
               "Unknown Ticket Status code : \"%d\"", status));
      }
      return ticketStatus.toString();
   }
}