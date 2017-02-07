package com.sapphire.stock.domain;

import javax.persistence.*;

import com.sapphire.stock.constant.IgnoreStockFlag;

/**
 * Author: Ethan Date: 2016/4/5
 */
@Entity
@Table(name = IgnoreStock.TABLE_NAME)
public class IgnoreStock {
   public static final String TABLE_NAME = "IGNORE_STOCK";

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "UIDPK")
   private long uidPk;

   @Basic
   @Column(name = "FLAG")
   private int flag;

   @Basic
   @Column(name = "CODE")
   private String code;

   public IgnoreStockFlag getFlag() {
      return IgnoreStockFlag.toIgnoreStockFlag(flag);
   }

   public void setFlag(IgnoreStockFlag flag) {
      this.flag = flag.getCode();
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public long getUidPk() {
      return uidPk;
   }

   public void setUidPk(long uidPk) {
      this.uidPk = uidPk;
   }
}
