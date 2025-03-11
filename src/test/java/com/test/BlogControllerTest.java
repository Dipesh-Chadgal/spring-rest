package com.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.controller.BlogController;
import com.controller.MasterData;
import com.entity.BlogEntity;
import com.service.BlogService;

@WebMvcTest(BlogController.class)
@AutoConfigureMockMvc
public class BlogControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private BlogService blogService;

//	@Test
//	public void testCreateBlog() throws Exception {
//
//		BlogEntity blogEntity = new BlogEntity();
//		blogEntity.setId(1L);
//		blogEntity.setTitle("core java");
//		blogEntity.setContent("this is java course");
//
//		BlogEntity savedBlogEntity = blogEntity;
//		savedBlogEntity.setId(1L);
//
//		when(this.blogService.createBlog(blogEntity)).thenReturn(savedBlogEntity);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/blogs")
//				.content(JsonConverter.asJsonString(blogEntity)).contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		
//		System.out.println("Result : " + result.getResponse().getContentAsString());
//		assertEquals(result.getResponse().getContentAsString(),JsonConverter.asJsonString(savedBlogEntity));
//
//		 
//
//	}
	
	@Test
	public void testCreateBlogIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		
		BlogEntity blogEntity = new BlogEntity();
//		blogEntity.setId(1L);
		blogEntity.setTitle("core java");
		blogEntity.setContent("this is java course");

		
		BlogEntity BlogEntity = blogEntity;
		BlogEntity savedBlogEntity = blogEntity;
		
		
		savedBlogEntity.setId(1L);
		
		
		when(this.blogService.createBlog(BlogEntity)).then(new Answer<BlogEntity>() {
 
			@Override
			public BlogEntity answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				System.out.println("count : " + count[0]);
				return savedBlogEntity;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/blogs")
				.content(MasterData.asJsonString(BlogEntity)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
 
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("Result for service :" + result.getResponse().getContentAsString());
		
		
//		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
 
	}

	
	
	
	
}