package com.kh.finalproject.notice.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	
	// 커뮤니티 페이지 이동
	@RequestMapping("main.co")
	public String community() {
		return "notice/community";
	}
	
	// 공지사항 리스트조회
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
	
	// 공지사항 작성폼 이동
	@RequestMapping("enrollForm.no")
	public String enrollForm() {
		return "notice/noticeEnrollForm";
	}
	
	
	// 공지사항 작성
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


	
	// 파일업로드
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
	
	// 공지사항 상세조회
	@GetMapping("detail.no")
	public ModelAndView selectNotice(int bno, ModelAndView mv, Files f, MultipartFile upfile, HttpSession session)  {
		
		if(noticeService.increaseCount(bno) > 0 ) {
			
			if(!noticeService.selectFile(bno).isEmpty()) {
				ArrayList<Files> fileList = noticeService.selectFile(bno);
				for(Files file : fileList) {
					f.setRefNo(file.getRefNo());
					f.setRefType(file.getRefType());
				}
					mv.addObject("f", noticeService.selectFile(bno)).setViewName("notice/noticeDetailView");
					mv.addObject("n", noticeService.selectNotice(bno)).setViewName("notice/noticeDetailView");
			}

			mv.addObject("n", noticeService.selectNotice(bno)).setViewName("notice/noticeDetailView");
		} else {
			mv.addObject("errorMsg", "게시글 조회 실패").setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 공지사항 삭제
	@PostMapping("delete.no")
	public String deleteNotice(int bno, HttpSession session) {

		if(noticeService.deleteNotice(bno) > 0) {
			noticeService.deleteFile(bno);
			session.setAttribute("alertMsg", "ㅎㅎㅎ");
			return "redirect:list.no";
		} else {
			session.setAttribute("errorMsg", "실패했습니다");
			return "common/errorPage";
		}
	}
	

	// 공제사항 수정폼
	@PostMapping("updateForm.no")
	public ModelAndView updateNotice(int bno, ModelAndView mv) {
		mv.addObject("n", noticeService.selectNotice(bno)).setViewName("notice/noticeUpdateForm");
		mv.addObject("f", noticeService.selectFile(bno)).setViewName("notice/noticeUpdateForm");
		return mv;
	}
	
	
	// 공지사항 수정
	@PostMapping("update.no")
	public String updateNotice(Notice n, Files f, Model model, MultipartFile reUpfile, HttpSession session) {
		
		if(!reUpfile.getOriginalFilename().equals("")) {
			
			if(f.getOriginalName() != null) {
				new File(session.getServletContext().getRealPath("resources/uploadFiles/notice/"+f.getUpdateName())).delete();
				System.out.println(session.getServletContext().getRealPath("resources/uploadFiles/notice"+f.getUpdateName()));
				f.setOriginalName(reUpfile.getOriginalFilename());
				f.setUpdateName(saveFile(reUpfile, session));
				
			}
		}
		noticeService.updateFiles(f);
		if(noticeService.updateNotice(n) > 0 ) {
			session.setAttribute("alertMsg", "성공");
			return "redirect:detail.no?bno=" + n.getNoticeNo();
		} else {
			session.setAttribute("errorMsg", "실패!");
			return "common/errorPage";
		}
		
	}


}
