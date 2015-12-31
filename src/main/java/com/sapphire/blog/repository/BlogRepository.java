package com.sapphire.blog.repository;

import com.sapphire.blog.domain.Blog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/19.<br/>
 * Email: byp5303628@hotmail.com
 */
public interface BlogRepository extends CrudRepository<Blog, Long> {
   @Query(value = "select b.* from BLOG b where b.user_uid = ?1 and b.blog_status = 1", nativeQuery = true)
   List<Blog> getAllBlogsByUserId(long userId);

   @Modifying(clearAutomatically = true)
   @Query(value = "update Blog b set b.blogHit = b.blogHit + 1 where b.uidPk = ?1")
   void increaseHit(long blogId);

   @Query(value = "select sum(b.blogHit) from Blog as b where b.user.uidPk=?1")
   int getUserHitById(long userId);
}
