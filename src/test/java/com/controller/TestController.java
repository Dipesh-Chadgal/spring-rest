package com.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
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

import com.entity.BlogEntity;
import com.service.BlogService;

@WebMvcTest(BlogController.class)
@AutoConfigureMockMvc
public class TestController {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BlogService blogService;

	@AfterAll
	public static void afterAll() {
		TestUtils.testReport();
	}

	@Test
	public void testCreateBlog() throws Exception {
		BlogEntity blogEntity = MasterData.getBlogEntity();
		BlogEntity savedBlogEntity = MasterData.getBlogEntity();

		savedBlogEntity.setId(1L);
//		System.out.println(savedBlogEntity.getContent());
		when(this.blogService.createBlog(blogEntity)).thenReturn(savedBlogEntity);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/blogs")
				.content(MasterData.asJsonString(blogEntity)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		

//		System.out.println("Expected: " + MasterData.asJsonString(savedBlogEntity));
////		System.out.println("Actual: " + result.getResponse().getContentLength());
//		System.out.println("Actual: " + result.getResponse().getContentAsString());

		
		TestUtils.yakshaAssert(TestUtils.currentTest(),
				result.getResponse().getContentAsString().equals(MasterData.asJsonString(savedBlogEntity)),
				TestUtils.businessTestFile);

	}

	@Test
	public void testCreateBlogIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		BlogEntity blogEntity = MasterData.getBlogEntity();
		BlogEntity savedBlogEntity = MasterData.getBlogEntity();

		savedBlogEntity.setId(1L);
		when(this.blogService.createBlog(blogEntity)).then(new Answer<BlogEntity>() {

			@Override
			public BlogEntity answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println("MOCKED METHOD CALELD");
				count[0]++;
				return savedBlogEntity;
			}
		});
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/blogs")
				.content(MasterData.asJsonString(blogEntity)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		System.out.println(result);
		TestUtils.yakshaAssert(TestUtils.currentTest(), count[0] == 1, TestUtils.businessTestFile);


	}

	@Test
	public void testGetBlogById() throws Exception {
		BlogEntity BlogEntity = MasterData.getBlogEntity();
		BlogEntity.setId(1L);
		when(this.blogService.getBlogById(1L)).thenReturn(BlogEntity);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/blogs/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		TestUtils.yakshaAssert(TestUtils.currentTest(),
			    result.getResponse().getContentAsString().equals(MasterData.asJsonString(BlogEntity)),
			    TestUtils.businessTestFile);


	}

	@Test
	public void testGetBlogByIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		BlogEntity BlogEntity = MasterData.getBlogEntity();
		when(this.blogService.getBlogById(1L)).then(new Answer<BlogEntity>() {

			@Override
			public BlogEntity answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return BlogEntity;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/blogs/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		TestUtils.yakshaAssert(TestUtils.currentTest(), count[0] == 1 ? true : false, TestUtils.businessTestFile);

	}

	@Test
	public void testUpdateBlog() throws Exception {
		BlogEntity BlogEntity = MasterData.getBlogEntity();
		BlogEntity savedBlogEntity = MasterData.getBlogEntity();

		savedBlogEntity.setId(1L);

		when(this.blogService.updateBlog(BlogEntity)).thenReturn(savedBlogEntity);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/blogs")
				.content(MasterData.asJsonString(BlogEntity)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		TestUtils.yakshaAssert(TestUtils.currentTest(),
			    result.getResponse().getContentAsString().equals(MasterData.asJsonString(savedBlogEntity)),
			    TestUtils.businessTestFile);

	}

	@Test
	public void testUpdateBlogIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		BlogEntity BlogEntity = MasterData.getBlogEntity();
		BlogEntity savedBlogEntity = MasterData.getBlogEntity();

		savedBlogEntity.setId(1L);
		when(this.blogService.updateBlog(BlogEntity)).then(new Answer<BlogEntity>() {

			@Override
			public BlogEntity answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedBlogEntity;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/blogs")
				.content(MasterData.asJsonString(BlogEntity)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		TestUtils.yakshaAssert(TestUtils.currentTest(), count[0] == 1 ? true : false, TestUtils.businessTestFile);

	}

	@Test
	public void testDeleteBlog() throws Exception {
		BlogEntity BlogEntity = MasterData.getBlogEntity();
		BlogEntity.setId(1L);
		when(this.blogService.deleteBlog(1L)).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/blogs/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		TestUtils.yakshaAssert(TestUtils.currentTest(),
				result.getResponse().getContentAsString().equals(MasterData.asJsonString(true)),
				TestUtils.businessTestFile);

	}

	@Test
	public void testDeleteBlogIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		com.entity.BlogEntity BlogEntity = MasterData.getBlogEntity();
		BlogEntity.setId(1L);
		when(this.blogService.deleteBlog(1L)).then(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return true;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/blogs/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		TestUtils.yakshaAssert(TestUtils.currentTest(), count[0] == 1, TestUtils.businessTestFile);

	}

	@Test
	public void testGetAllBlogs() throws Exception {
		List<BlogEntity> blogs = MasterData.getBlogEntityList();

		when(this.blogService.findAll()).thenReturn(blogs);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/blogs/all")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		TestUtils.yakshaAssert(TestUtils.currentTest(),
				result.getResponse().getContentAsString().equals(MasterData.asJsonString(blogs)),
				TestUtils.businessTestFile);

	}

	@Test
	public void testGetAllProductsIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<BlogEntity> blogs = MasterData.getBlogEntityList();
		when(this.blogService.findAll()).then(new Answer<List<BlogEntity>>() {

			@Override
			public List<BlogEntity> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return blogs;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/blogs/all")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		TestUtils.yakshaAssert(TestUtils.currentTest(), count[0] == 1, TestUtils.businessTestFile);

	}

}