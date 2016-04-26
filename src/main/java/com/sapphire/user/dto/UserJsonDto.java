package com.sapphire.user.dto;

import com.sapphire.common.dto.JsonDto;
import com.sapphire.user.domain.User;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/7.<br/>
 * Email: byp5303628@hotmail.com
 */
public class UserJsonDto extends JsonDto {
   private User data;

   public UserJsonDto(User data) {
      this.data = data;
   }

   public User getData() {
      return data;
   }

   public void setData(User data) {
      this.data = data;
   }
}
