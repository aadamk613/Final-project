package com.kh.finalproject.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController {
	
	@RequestMapping("main.bl")
	public String main() {
		return "blog/blogMainView";
	}
	
	
}
