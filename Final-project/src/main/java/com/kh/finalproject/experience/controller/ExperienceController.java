package com.kh.finalproject.experience.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.common.teplate.Pagination;
import com.kh.finalproject.experience.model.service.ExperienceService;

@Controller
public class ExperienceController {
	
	@Autowired
	private ExperienceService experienceService;
	
	@RequestMapping("yrlist.exp")
	public String seleceExperienceList(@RequestParam(value="page", defaultValue="1") int currentPage, Model model){
		// 페이지는 기본값 1로 설정
		PageInfo pi = Pagination.getPageInfo(experienceService.selectListCount(), currentPage, 5, 5);
		System.out.println(pi);
		model.addAttribute("experienceList", experienceService.selectExperienceList(pi));
		model.addAttribute("pi", pi);
		return "experience/experienceListView";
	}
	
	@RequestMapping("yrdetail.exp")
	public String selectExperience(int expNo, Model model, HttpSession session) {
		System.out.println(expNo);
		
		if(experienceService.increaseCount(expNo) > 0 ) {
			System.out.println("성공");
			model.addAttribute("exp", experienceService.selectExperience(expNo));
			return "experience/experienceDetailView";
		} else {
			System.out.println("에러");
			session.setAttribute("error", "조회수 증가 실패");
			// model.addAttribute("error", "조회수 증가 실패");
			return "redirect:yrlist.exp";
			// return "experience/experienceListView";
		}
		
	}
	
	

}
