package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.BlogEntity;
import com.service.BlogService;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("/api/blogs")
@Validated
public class BlogController {

	@Autowired
	private BlogService blogService;

	@PostMapping
	public ResponseEntity<BlogEntity> createBlog(@Valid @RequestBody BlogEntity blogEntity) {
		System.out.println("Controller create blog ran");
		BlogEntity blog = blogService.createBlog(blogEntity);
		
		return ResponseEntity.ok().body(blogEntity);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BlogEntity> getBlogById(@PathVariable Long id) {
		BlogEntity blog = blogService.getBlogById(id);
		return ResponseEntity.ok(blog);

	}

	@PutMapping
	public ResponseEntity<BlogEntity> updateBlog(@Valid @RequestBody BlogEntity blogEntity) {
		BlogEntity blog = blogService.updateBlog(blogEntity);
		return ResponseEntity.ok(blog);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteBlogById(@PathVariable Long id) {
		boolean response = blogService.deleteBlog(id);
		if (response)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@GetMapping("/all")
	public ResponseEntity<List<BlogEntity>> getAllBlogs() {
		List<BlogEntity> blogs = blogService.findAll();
		return ResponseEntity.ok(blogs);
	}

}
