package com.kh.finalproject.experience.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.common.teplate.Pagination;
import com.kh.finalproject.experience.model.service.ExperienceService;
import com.kh.finalproject.experience.model.vo.Experience;

@Controller
public class ExperienceController {
	
	@Autowired
	private ExperienceService experienceService;
	
	@RequestMapping("yrlist.exp")
	public String seleceExperienceList(@RequestParam(value="page", defaultValue="1") int currentPage, Model model){
		PageInfo pi = Pagination.getPageInfo(experienceService.selectListCount(), currentPage, 5, 5);
		System.out.println(pi);
		model.addAttribute("experienceList", experienceService.selectExperienceList(pi));
		model.addAttribute("pi", pi);
		return "experience/experienceListView";
	}
	
	
	

}
