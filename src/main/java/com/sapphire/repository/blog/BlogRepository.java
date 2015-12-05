package com.sapphire.repository.blog;

import com.sapphire.domain.blog.Blog;
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
}
