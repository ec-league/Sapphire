package com.sapphire.constant;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/5<br/>
 * Email: byp5303628@hotmail.com
 */
public enum TicketPriority {
   P0(0), P1(1), P2(2), P3(3);

   private int code;

   TicketPriority(int code) {
      this.code = code;
   }

   public static TicketPriority toTicketPriority(String pr) {
      if ("p0".equalsIgnoreCase(pr)) {
         return TicketPriority.P0;
      } else if ("p1".equalsIgnoreCase(pr)) {
         return TicketPriority.P1;
      } else if ("p2".equalsIgnoreCase(pr)) {
         return TicketPriority.P2;
      } else if ("p3".equalsIgnoreCase(pr)) {
         return TicketPriority.P3;
      } else {
         throw new IllegalArgumentException(String.format(
               "Unknown priority : \"%s\".", pr));
      }
   }
}
