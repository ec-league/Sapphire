package com.sapphire.constant;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/5<br/>
 * Email: byp5303628@hotmail.com
 */
public enum TicketType {
   BUG(0), REQUEST(1);

   private int code;

   TicketType(int code) {
      this.code = code;
   }

   public int toCode() {
      return code;
   }

   public static TicketType toTicketType(String tt) {
      if ("request".equalsIgnoreCase(tt)) {
         return REQUEST;
      } else if ("bug".equalsIgnoreCase(tt)) {
         return BUG;
      } else {
         throw new IllegalArgumentException(String.format(
               "Ticket Type illegal : \"%s\".", tt));
      }
   }
}
