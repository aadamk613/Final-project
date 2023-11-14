package com.kh.finalproject.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.finalproject.blog.model.service.BlogService;
import com.kh.finalproject.blog.model.vo.Blog;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;

	@RequestMapping("main.bl")
	public String selectListBlog() {
		return "blog/blogMainView";
	}
	
	@RequestMapping("insertForm.bl")
	public String insertFormBlog() {
		return "blog/insertBlogForm";
	}
	
	@RequestMapping("insert.bl")
	public String insertBlog(Blog b) {
		return "blog/blogMainView";
	}
	
	
	
}
