package com.kh.finalproject.experience.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExperienceController {
	
	@RequestMapping("yrlist.exp")
	public String seleceExperienceList() {
		return "experience/experienceListView";
	}
	
	

}
