package com.kh.finalproject.notice.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.common.teplate.Pagination;
import com.kh.finalproject.notice.model.service.NoticeService;
import com.kh.finalproject.notice.model.vo.Notice;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("main.co")
	public String community() {
		return "notice/community";
	}
	
	@RequestMapping("list.no")
	public String selectNotice(@RequestParam(value="cPage", defaultValue="1") int currentPage, Model model) {
		
		PageInfo pi = Pagination.getPageInfo(noticeService.selectNoticeListCount(),
											 currentPage,
											 20,
											 5);
		model.addAttribute("best", noticeService.selectBestNoticeList());
		model.addAttribute("list", noticeService.selectNoticeList(pi));
		model.addAttribute("pi", pi);
		
		System.out.println(noticeService.selectNoticeList(pi));
		
		return "notice/noticeListView";
	
	}
	
}
