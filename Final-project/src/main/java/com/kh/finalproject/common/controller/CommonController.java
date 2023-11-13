package com.kh.finalProject.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

	@RequestMapping("main")
	public String mainPage() {
		return "common/main";
	}
}
