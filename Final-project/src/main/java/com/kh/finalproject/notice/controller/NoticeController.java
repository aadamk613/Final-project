package com.kh.finalproject.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {

	@RequestMapping("main.co")
	public String community() {
		return "notice/community";
	}
	
	@RequestMapping("main.no")
	public String selectNotice() {
		return "notice/noticeListView";
	
	}
	
}
