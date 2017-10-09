package com.sapphire.biz.blog.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author: EthanPark <br/>
 * Date: 2016/1/14<br/>
 * Email: byp5303628@hotmail.com
 */
@Entity
@Table(name = BlogTag.TABLE_NAME)
public class BlogTag {
    public static final String TABLE_NAME = "BLOG_TAG";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UIDPK")
    private long               uidPk;

    @Basic
    @Column(name = "BLOG_UID")
    private long               blogId;

    @Basic
    @Column(name = "TAG_NAME")
    private String             tagName;

    public long getUidPk() {
        return uidPk;
    }

    public void setUidPk(long uidPk) {
        this.uidPk = uidPk;
    }

    public String getTagName() {
        return tagName;
    }

    public long getBlogId() {
        return blogId;
    }

    public void setBlogId(long blogId) {
        this.blogId = blogId;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
