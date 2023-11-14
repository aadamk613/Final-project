package com.kh.finalproject.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping("main.admin")
	public String mainView() {
		return "admin/adminMainView";
	}
}
