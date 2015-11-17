package com.sapphire.dto.user;

import com.sapphire.dto.Dto;

/**
 * Author: EthanPark <br/>
 * Date: ${Date}<br/>
 * Email: byp5303628@hotmail.com
 */
public class AuthDto implements Dto {
   private String username;
   private String password;

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }
}
