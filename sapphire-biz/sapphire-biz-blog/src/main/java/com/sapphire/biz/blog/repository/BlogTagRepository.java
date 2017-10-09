package com.sapphire.biz.blog.repository;

import java.util.List;

import com.sapphire.biz.blog.domain.BlogTag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Author: EthanPark <br/>
 * Date: 2016/1/14<br/>
 * Email: byp5303628@hotmail.com
 */
public interface BlogTagRepository extends CrudRepository<BlogTag, Long> {

    @Query("select t from BlogTag as t where t.tagName = ?1")
    List<BlogTag> getBlogTagsByTagName(String tagName);

    @Query("select t from BlogTag as t where t.blogId = ?1")
    List<BlogTag> getBlogTagsByBlogId(long blogId);
}
