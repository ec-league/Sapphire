package com.sapphire.common.dto;

import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/20<br/>
 * Email: byp5303628@hotmail.com
 */
public class ListJsonDto<T extends Dto> extends JsonDto {
   private List<T> data;

   public ListJsonDto(List<T> data) {
      this.data = data;
   }

   public List<T> getData() {
      return data;
   }

   public void setData(List<T> data) {
      this.data = data;
   }
}