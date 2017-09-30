package com.sapphire.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sapphire.blog.domain.Blog;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/19.<br/>
 * Email: byp5303628@hotmail.com
 */
public interface BlogRepository extends CrudRepository<Blog, Long> {
    @Query(value = "select b.* from BLOG b where b.user_uid = ?1", nativeQuery = true)
    List<Blog> getAllBlogsByUserId(long userId);

    @Query(value = "select sum(b.blogHit) from Blog as b where b.user.uidPk=?1")
    int getUserHitById(long userId);
}
