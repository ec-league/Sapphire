package com.sapphire.domain;

/**
 * Author: Ethan <br/>
 * Date: 2015/10/26.<br/>
 * Email: byp5303628@hotmail.com
 */
public interface User extends Entity {
   long getUidPk();

   void setUidPk(long uidPk);

   String getUsername();

   void setUsername(String username);

   String getPassword();

   void setPassword(String password);

   String getEmail();

   void setEmail(String email);
}