package com.sapphire.domain.manage;

import com.sapphire.common.PropertyManager;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/23<br/>
 * Email: byp5303628@hotmail.com
 */
@Entity
@Table(name = Project.TABLE_NAME)
public class Project {
   public static final String TABLE_NAME = "PROJECT";

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "UIDPK")
   private long uidPk;

   @Basic
   @Column(name = "TITLE")
   private String title;

   @Basic
   @Column(name = "DESCRIPTION")
   private String description;

   @Basic
   @Column(name = "REPO_URL")
   private String repoUrl;

}
