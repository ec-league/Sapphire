package com.sapphire.domain.blog;

import com.sapphire.domain.User;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/19.<br/>
 * Email: byp5303628@hotmail.com
 */
@Entity
@Table(name = Comment.TABLE_NAME)
public class Comment {
   public static final String TABLE_NAME = "COMMENT";

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "UIDPK")
   private long UIDPK;

   @ManyToOne
   @JoinColumn(name = "USER_UID")
   private User user;

   @ManyToOne
   @JoinColumn(name = "BLOG_UID")
   private Blog blog;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "CREATE_TIME")
   private Timestamp createTime;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "LAST_MODIFY_TIME")
   private Timestamp lastModifyTime;

   public long getUIDPK() {
      return UIDPK;
   }

   public void setUIDPK(long UIDPK) {
      this.UIDPK = UIDPK;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public Blog getBlog() {
      return blog;
   }

   public void setBlog(Blog blog) {
      this.blog = blog;
   }

   public Timestamp getCreateTime() {
      return createTime;
   }

   public void setCreateTime(Timestamp createTime) {
      this.createTime = createTime;
   }

   public Timestamp getLastModifyTime() {
      return lastModifyTime;
   }

   public void setLastModifyTime(Timestamp lastModifyTime) {
      this.lastModifyTime = lastModifyTime;
   }
}
