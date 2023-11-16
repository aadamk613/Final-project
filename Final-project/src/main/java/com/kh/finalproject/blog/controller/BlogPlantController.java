package com.kh.finalproject.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.finalproject.blog.model.service.BlogService;

@Controller
public class BlogPlantController {

	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("insertForm.bl_pl")
	public String insertFormBlogPlant() {
		return "blog/plantInsertForm";
	}
}
