package com.sapphire.domain.user;

import javax.persistence.*;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/22<br/>
 * Email: byp5303628@hotmail.com
 */
@Entity
@Table(name = UserInfo.TABLE_NAME)
public class UserInfo {
   public static final String TABLE_NAME = "USER_INFO";

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "UIDPK")
   private long uidPk;

   @Basic
   @Column(name = "NAME")
   private String name;

   @Basic
   @Column(name = "IMG_SRC", nullable = false)
   private String imgSrc;

   @Basic
   @Column(name = "USER_UID", unique = true)
   private long userId;

   public long getUserId() {
      return userId;
   }

   public void setUserId(long userId) {
      this.userId = userId;
   }

   public long getUidPk() {
      return uidPk;
   }

   public void setUidPk(long uidPk) {
      this.uidPk = uidPk;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getImgSrc() {
      return imgSrc;
   }

   public void setImgSrc(String imgSrc) {
      this.imgSrc = imgSrc;
   }
}
