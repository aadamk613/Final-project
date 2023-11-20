package com.kh.finalproject.blog.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.finalproject.blog.model.service.BlogService;
import com.kh.finalproject.blog.model.vo.Plant;
import com.kh.finalproject.common.controller.CommonController;
import com.kh.finalproject.common.model.vo.Files;

@Controller
public class BlogPlantController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CommonController commonController;
	
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
	public String insertBlogPlant(Plant plant,  
								  MultipartFile upfile, 
								  HttpServletRequest request, 
								  HttpSession session,
								  Model model) {
		
		Files file = new Files();
		
		if(!upfile.getOriginalFilename().equals("")) {
			file = commonController.setFile(upfile, session, "plant");
		}
		
		if(blogService.insertBlogPlant(plant, file) > 0) { // 성공
			session.setAttribute("alertMsg", "게시글 작성 성공");
			return "redirect:select.bl_pl";
		} else {
			model.addAttribute("errorMsg", "게시글 작성 실패");
			return "common/errorPage";
		}
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
