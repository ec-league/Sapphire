package com.sapphire.domain.blog;

import com.sapphire.constant.BlogStatus;
import com.sapphire.domain.User;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/19.<br/>
 * Email: byp5303628@hotmail.com
 */
@Entity
@Table(name = Blog.TABLE_NAME)
public class Blog {
   public static final String TABLE_NAME = "BLOG";
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "UIDPK")
   private long uidPk;

   @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
         CascadeType.MERGE, CascadeType.REFRESH })
   @JoinColumn(name = "USER_UID")
   private User user;

   @Basic
   @Column(name = "BLOG_TITLE")
   private String blogTitle;

   @Basic
   @Column(name = "BLOG_CONTENT")
   private String blogContent;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "CREATE_TIME")
   private Timestamp createTime;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "LAST_MODIFY_TIME")
   private Timestamp lastModifyTime;

   @Basic
   @Column(name = "BLOG_STATUS")
   private BlogStatus blogStatus;

   public BlogStatus getBlogStatus() {
      return blogStatus;
   }

   public void setBlogStatus(BlogStatus blogStatus) {
      this.blogStatus = blogStatus;
   }

   public long getUidPk() {
      return uidPk;
   }

   public void setUidPk(long uidPk) {
      this.uidPk = uidPk;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public String getBlogTitle() {
      return blogTitle;
   }

   public void setBlogTitle(String blogTitle) {
      this.blogTitle = blogTitle;
   }

   public String getBlogContent() {
      return blogContent;
   }

   public void setBlogContent(String blogContent) {
      this.blogContent = blogContent;
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
