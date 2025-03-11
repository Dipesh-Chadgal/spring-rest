package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.BlogEntity;
import com.exception.ResourceNotFoundException;
import com.repository.BlogRepository;
import com.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Override
	public BlogEntity createBlog(BlogEntity blogEntity) {
		
		System.out.println("create Blog service Ran");
		
		BlogEntity blog = blogRepository.save(blogEntity);
		
		
		if(blog == null) {
			System.out.println("Blog is null");
		}else {
			System.out.println("Correct blog is sent from here");
		}
		
		return blog ;
	}

	@Override
	public BlogEntity getBlogById(Long id) {
		BlogEntity blog = blogRepository.getBlogById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Blog Not Found"));
		return blog;
	}

	@Override
	public BlogEntity updateBlog(BlogEntity blogEntity) {
		BlogEntity blog = blogRepository.getBlogById(blogEntity.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Blog Not Found"));
		blog.setTitle(blogEntity.getTitle());
		blog.setContent(blogEntity.getContent());
		return blogRepository.save(blog);
	}

	@Override
	public Boolean deleteBlog(Long id) {
		System.out.println("Delete service ran");
		BlogEntity blog = blogRepository.getBlogById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Blog Not Found"));
		blogRepository.delete(blog);

		return blogRepository.existsById(id);
	}

	@Override
	public List<BlogEntity> findAll() {
		return blogRepository.findAll();
	}

}
