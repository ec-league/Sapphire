package com.sapphire.domain;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/4.<br/>
 * Email: byp5303628@hotmail.com
 */
@Entity
@Table(name = User.TABLE_NAME)
public class User {
   public static final String TABLE_NAME = "USER";

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "UIDPK")
   private long uidPk;

   @Basic
   @Column(name = "USERNAME")
   private String username;

   @Basic
   @Column(name = "PASSWORD")
   private String password;

   @Basic
   @Column(name = "EMAIL")
   private String email;

   public long getUidPk() {
      return uidPk;
   }

   public void setUidPk(long uidPk) {
      this.uidPk = uidPk;
   }

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

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
}
