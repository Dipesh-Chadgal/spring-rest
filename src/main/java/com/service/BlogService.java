package com.service;

import java.util.List;

import com.entity.BlogEntity;

public interface BlogService {

	public BlogEntity createBlog(BlogEntity blogEntity);

	public BlogEntity getBlogById(Long id);

	public BlogEntity updateBlog(BlogEntity blogEntity);

	public Boolean deleteBlog(Long id);

	public List<BlogEntity> findAll();
}

