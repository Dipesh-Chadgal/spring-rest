package com.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class BlogDTO {
	private Long id;

	@NotEmpty
	@Size(min = 3,max = 100)
	private String title;

	@NotEmpty
	@Size(min = 3,max = 200)
	private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BlogDTO() {
	}

}
