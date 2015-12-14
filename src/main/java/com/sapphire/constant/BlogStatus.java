package com.sapphire.constant;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/5<br/>
 * Email: byp5303628@hotmail.com
 */
public enum BlogStatus {
   PUBLISHED(1), UN_PUBLISHED(0);

   private int code;

   BlogStatus(int code) {
      this.code = code;
   }

   public int getCode() {
      return code;
   }

   public static BlogStatus toBlogStatus(int code) {
      if (code == 0) {
         return BlogStatus.UN_PUBLISHED;
      } else if (code == 1) {
         return BlogStatus.PUBLISHED;
      } else {
         throw new IllegalArgumentException("Wrong code of BlogStatus!");
      }
   }
}
