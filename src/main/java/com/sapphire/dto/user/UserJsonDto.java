package com.sapphire.dto.user;

import com.sapphire.domain.User;
import com.sapphire.dto.JsonDto;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/7.<br/>
 * Email: byp5303628@hotmail.com
 */
public class UserJsonDto extends JsonDto {
   private User user;

   public UserJsonDto(User user) {
      this.user = user;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }
}
