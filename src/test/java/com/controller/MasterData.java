package com.controller;

import java.util.Arrays;
import java.util.List;

import com.entity.BlogEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MasterData {

    public static BlogEntity getBlogEntity() {
        BlogEntity blog = new BlogEntity();
        blog.setId(1L);
        blog.setTitle("Sample Blog Title");
        blog.setContent("This is a sample blog content.");
        return blog;
    }
    
    public static BlogEntity getAnotherBlogEntity() {
        BlogEntity blog = new BlogEntity();
        blog.setId(2L);
        blog.setTitle("Another Blog Title");
        blog.setContent("This is another sample blog content.");
        return blog;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static List<BlogEntity> getBlogEntityList() {
        return Arrays.asList(getBlogEntity(), getAnotherBlogEntity());
    }

}
