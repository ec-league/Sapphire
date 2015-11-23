package com.sapphire.domain.manage;

import com.sapphire.domain.User;
import com.sapphire.domain.blog.Comment;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.security.Timestamp;
import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/23<br/>
 * Email: byp5303628@hotmail.com
 */
@Entity
@Table(name = Ticket.TABLE_NAME)
public class Ticket {
   public static final String TABLE_NAME = "TICKET";

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "UIDPK")
   private long uidPk;

   @ManyToOne
   @JoinColumn(name = "CREATE_USER_UID")
   private User createUser;

   @ManyToOne
   @JoinColumn(name = "ASSIGN_USER_UID")
   private User assignUser;

   private List<User> watchUsers;

   @Basic
   @Column(name = "DESCRIPTION")
   private String description;

   @ManyToOne
   @JoinColumn(name = "PROJECT_UID")
   private Project project;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "CREATE_TIME")
   private Timestamp createTime;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "LAST_MODIFY_TIME")
   private Timestamp lastModifyTime;

   @Basic
   @Column(name = "STATUS")
   private int status;

   private List<Comment> comments;

}
