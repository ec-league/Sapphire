package com.sapphire.dto;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/20<br/>
 * Email: byp5303628@hotmail.com
 */
public class DataJsonDto<T extends Dto> extends JsonDto {
   private T data;

   public DataJsonDto(T data) {
      this.data = data;
   }

   public T getData() {
      return data;
   }

   public void setData(T data) {
      this.data = data;
   }
}
