package com.kh.finalproject.notice.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.common.model.vo.Files;


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
											 10,
											 5);

		model.addAttribute("best", noticeService.selectBestNoticeList());
		model.addAttribute("list", noticeService.selectNoticeList(pi));
		model.addAttribute("pi", pi);
		

		return "notice/noticeListView";
	}
	
	@RequestMapping("enrollForm.no")
	public String enrollForm() {
		return "notice/noticeEnrollForm";
	}
	

	
	
	
	@RequestMapping("insert.no")
	public String insertNotice(Notice n, Files f, Model model, MultipartFile upfile, HttpSession session) {

		if(!upfile.getOriginalFilename().equals("")) {
			
			
			
			saveFile(upfile, session);
			
			f.setOriginalName(upfile.getOriginalFilename());
			f.setUpdateName(saveFile(upfile, session));
		} 
		if(noticeService.insertNotice(n) > 0) { 
			System.out.println("성공");
			return "redirect:list.no";
		} else {
			System.out.println("실패");
			return "common/errorPage";
		}
		
	}

	public String saveFile(MultipartFile upfile, HttpSession session) {

		// 파일 명 수정 작업 후 서버에 업로드("bono.jsp" => 2023110332132132.jsp)
		String originName = upfile.getOriginalFilename();

		// "20231103102244"(년월일시분초)
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		// 23432(5자리 랜덤값)
		int ranNum = (int)Math.random() * 90000 + 10000;

		// 확장자
		String ext = originName.substring(originName.lastIndexOf("."));

		String updateName = currentTime + ranNum + ext;

		String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");

		try {
			upfile.transferTo(new File(savePath + updateName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return "/resources/uploadFiles/" + updateName;
	}

	
}
