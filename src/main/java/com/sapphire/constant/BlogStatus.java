package com.sapphire.constant;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/5<br/>
 * Email: byp5303628@hotmail.com
 */
public enum BlogStatus {
   PUBLISHED(0),
   UN_PUBLISHED(1);

   private int code;
   BlogStatus(int code){
      this.code = code;
   }
}
