package com.kh.finalproject.blog.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.finalproject.blog.model.service.BlogService;
import com.kh.finalproject.blog.model.vo.Plant;
import com.kh.finalproject.blog.model.vo.PlantReport;

@Controller
public class BlogPlantController {
	
	@Autowired
	private BlogService blogService;
	
	// 식물 전체 리스트로 이동
	@RequestMapping("select.bl_pl")
	public ModelAndView selectListPlant(int blogNo, ModelAndView mv) {
		
		ArrayList<Plant> list = blogService.selectListPlant(blogNo);
		System.out.println("식물 리스트 : "+list);
		mv.addObject("list", list)
		  .setViewName("blog/plantView");
		return mv;
	}
	
	// 식물 등록 페이지로 이동
	@RequestMapping("insertForm.bl_pl")
	public ModelAndView insertFormBlogPlant(int blogNo, ModelAndView mv) {
		mv.addObject("blogNo", blogNo)
		  .setViewName("blog/plantInsertForm");
		return mv;
	}
	
	// 식물 등록
	@RequestMapping("insert.bl_pl")
	public String insertBlogPlant(Plant plant, HttpServletRequest request) {
		
		if(ServletFileUpload.isMultipartContent(request)) {
			System.out.println("멀티 파일 있어요");
			
			int maxSize = 1024 * 1024 * 1000; // 파일 용량 1000mb
			String savePath = "resources/uploadFiles/plant";
			
			
		}
		
		
		System.out.println(plant);
		blogService.insertBlogPlant(plant);
		return "blog/plantView";
	}
	
	@RequestMapping("insertForm.bl.pr/{plantNo}/{category}/{plantNickName}")
	public String insertFormPlantReport(@PathVariable("plantNo") @ModelAttribute int plantNo,
									    @PathVariable("category") @ModelAttribute int category,
									    @PathVariable("plantNickName") @ModelAttribute String plantNickName) {
		
		System.out.println(plantNo + category + plantNickName);
		return "blog/reportInsertForm";
	}
	
	
	
	// 식물 일지 등록 페이지로 이동
	@RequestMapping("insertForm.bl_pr")
	public String insertFormBlogPlantReport() {
		return "blog/reportInsertForm";
	}
}
