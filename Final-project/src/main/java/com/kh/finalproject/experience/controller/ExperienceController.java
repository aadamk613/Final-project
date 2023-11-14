package com.kh.finalproject.experience.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.common.teplate.Pagination;
import com.kh.finalproject.experience.model.service.ExperienceService;

@Controller
public class ExperienceController {
	
	private ExperienceService experienceService;
	
	@RequestMapping("yrlist.exp")
	public String seleceExperienceList(@RequestParam(value="page", defaultValue="1") int currentPage){
		
		PageInfo pi = Pagination.getPageInfo(experienceService.selectListCount(), currentPage, 5, 5);
		
		experienceService.selectExperienceList(pi);
		
		
		
		
		
		
		
		return "experience/experienceListView";
	}
	
	
	
	

}
