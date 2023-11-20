package com.kh.finalproject.notice.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
	public String selectNoticeList(@RequestParam(value="cPage", defaultValue="1") int currentPage, Model model) {
		
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
			int lastNo = noticeService.selectLastNoticeNo();
			f.setOriginalName(upfile.getOriginalFilename());
			f.setUpdateName(saveFile(upfile, session));
			f.setRefNo(lastNo);
			noticeService.insertFile(f);
		} 
		if(noticeService.insertNotice(n) > 0) { 
			return "redirect:list.no";
		} else {
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

		String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/notice/");

		try {
			upfile.transferTo(new File(savePath + updateName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return updateName;
	}
	

	@GetMapping("detail.no")
	public ModelAndView selectNotice(int bno, ModelAndView mv, Files f, MultipartFile upfile, HttpSession session) {
		
		if(noticeService.increaseCount(bno) > 0 ) {

			mv.addObject("f", noticeService.selectFile(bno));
			System.out.println(noticeService.selectFile(bno));
			if(bno == Integer.parseInt(f.getRefNo()) && f.getRefType().equals("notice")) {
			}
			mv.addObject("n", noticeService.selectNotice(bno)).setViewName("notice/noticeDetailView");
		} else {
			mv.addObject("errorMsg", "게시글 조회 실패").setViewName("common/errorPage");
		}
		return mv;
	}
	
	@PostMapping("delete.no")
	public String deleteNotice(int bno, HttpSession session) {

		if(noticeService.deleteNotice(bno) > 0) {
			
			session.setAttribute("alertMsg", "ㅎㅎㅎ");
			return "redirect:list.no";
		} else {
			session.setAttribute("errorMsg", "실패했습니다");
			return "common/errorPage";
		}
	}

	@PostMapping("updateForm.no")
	public ModelAndView updateNotice(int bno, ModelAndView mv) {
		mv.addObject("n", noticeService.selectNotice(bno)).setViewName("notice/noticeUpdateForm");
		return mv;
	}
	
	@PostMapping("update.no")
	public String updateNotice(Notice n, HttpSession session) {
		
		if(noticeService.updateNotice(n) > 0 ) {
			session.setAttribute("alertMsg", "성공");
			return "redirect:detail.no?bno=" + n.getNoticeNo();
		} else {
			session.setAttribute("errorMsg", "실패!");
			return "common/errorPage";
		}
		
	}


}
