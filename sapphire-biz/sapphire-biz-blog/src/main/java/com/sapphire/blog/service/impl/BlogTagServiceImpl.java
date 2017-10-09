package com.sapphire.blog.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapphire.blog.domain.Blog;
import com.sapphire.blog.domain.BlogTag;
import com.sapphire.blog.repository.BlogRepository;
import com.sapphire.blog.repository.BlogTagRepository;
import com.sapphire.blog.service.BlogTagService;

/**
 * Author: EthanPark <br/>
 * Date: 2016/1/14<br/>
 * Email: byp5303628@hotmail.com
 */
@Service("blogTagService")
public class BlogTagServiceImpl implements BlogTagService {
    @Autowired
    private BlogTagRepository blogTagRepository;
    @Autowired
    private BlogRepository    blogRepository;

    public List<Blog> getBlogsByTagName(String tagName) {
        List<BlogTag> tags = blogTagRepository.getBlogTagsByTagName(tagName);

        List<Long> ids = tags.stream().map(BlogTag::getBlogId).collect(toList());
        List<Blog> blogs = new ArrayList<Blog>();
        ids.forEach((i) -> blogs.add(blogRepository.findOne(i)));
        return blogs;
    }

    public List<BlogTag> getBlogTagsByBlogId(long blogId) {
        List<BlogTag> tags = blogTagRepository.getBlogTagsByBlogId(blogId);
        if (tags == null || tags.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return tags;
    }

    @Override
    public long addBlogTag(BlogTag tag) {
        List<BlogTag> tags = blogTagRepository.getBlogTagsByBlogId(tag.getBlogId());
        boolean exist = tags.stream().filter(t -> t.getTagName().equals(tag.getTagName()))
            .count() > 0;
        if (exist) {
            throw new EntityExistsException();
        }
        return blogTagRepository.save(tag).getUidPk();
    }

    @Override
    public void deleteBlogTag(long blogTagId) {
        blogTagRepository.delete(blogTagId);
    }
}
