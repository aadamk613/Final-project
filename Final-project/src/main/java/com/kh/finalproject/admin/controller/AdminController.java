package com.kh.finalproject.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.finalproject.admin.model.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("main.admin")
	public String mainView() {
		return "admin/adminMainView";
	}
	
	@GetMapping("ticket.admin")
	public ModelAndView ticketView(ModelAndView mv) {
		mv.addObject("list", adminService.getTicketListView()).setViewName("admin/adminTicketView");;
		return mv;
	}
	
	@GetMapping("ticketDetailView.admin")
	public String ticketDetailView(int bno) {
		return "ticket/TicketDetailView";
				
	}
}
