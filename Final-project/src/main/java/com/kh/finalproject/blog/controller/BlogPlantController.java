package com.kh.finalproject.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.finalproject.blog.model.service.BlogService;
import com.kh.finalproject.blog.model.vo.Plant;

@Controller
public class BlogPlantController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("insertForm.bl_pl")
	public ModelAndView insertFormBlogPlant(int blogNo, ModelAndView mv) {
		mv.addObject("blogNo", blogNo)
		  .setViewName("blog/plantInsertForm");
		return mv;
	}
	
	@RequestMapping("insert.bl_pl")
	public String insertBlogPlant(Plant plant) {
		System.out.println(plant);
		blogService.insertBlogPlant(plant);
		System.out.println( "insertBlogPlant 결과"+blogService.insertBlogPlant(plant));
		return "blog/plantView";
	}
	
	
	@RequestMapping("insertForm.bl_pr")
	public String insertFormBlogPlantReport() {
		return "blog/reportInsertForm";
	}
}
