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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.finalproject.board.model.vo.BoardLike;
import com.kh.finalproject.common.model.vo.Attachment;
import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.common.teplate.Pagination;
import com.kh.finalproject.member.model.vo.Member;
import com.kh.finalproject.notice.model.service.NoticeService;
import com.kh.finalproject.notice.model.vo.Notice;
import com.kh.finalproject.notice.model.vo.NoticeLike;

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

		model.addAttribute("list", noticeService.selectNoticeList(pi))
		     .addAttribute("pi", pi);
			 //.addAttribute("best", noticeService.selectBestNoticeList())
		
		return "notice/noticeListView";
	}
	
	// 공지사항 작성폼 이동
	@RequestMapping("enrollForm.no")
	public String noticenrollForm() {
		return "notice/noticeEnrollForm";
	}
	
	
	// 공지사항 작성
	@RequestMapping("insert.no")
	public String insertNotice(Notice n, Attachment f, Model model, MultipartFile upfile, HttpSession session) {
		if(!upfile.getOriginalFilename().equals("")) {
			saveFile(upfile, session);

			
			if(noticeService.selectNoticeListCount() > 0) {
			int lastNo = noticeService.selectLastNoticeNo();
			f.setRefNo(lastNo);
			}
			f.setOriginalName(upfile.getOriginalFilename());
			f.setUpdateName(saveFile(upfile, session));
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
	public ModelAndView selectNotice(NoticeLike nl, int bno, ModelAndView mv, Attachment f, MultipartFile upfile, HttpSession session)  {
		
		if(noticeService.increaseCount(bno) > 0 ) {
			
			if(!noticeService.selectFile(bno).isEmpty()) {
				ArrayList<Attachment> fileList = noticeService.selectFile(bno);
				for(Attachment file : fileList) {
					f.setRefNo(file.getRefNo());
					f.setRefType(file.getRefType());
				}
					mv.addObject("f", noticeService.selectFile(bno)).setViewName("notice/noticeDetailView");
					mv.addObject("n", noticeService.selectNotice(bno)).setViewName("notice/noticeDetailView");
			}
			
			if(session.getAttribute("loginUser") != null) {
				Member loginUser = (Member) session.getAttribute("loginUser");
				nl.setMemNo(loginUser.getMemNo());
				nl.setNoticeNo(bno);
				mv.addObject("like", (noticeService.selectNoticeLike(nl)));
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
	public String updateNotice(Notice n, Attachment f, Model model, MultipartFile reUpfile, HttpSession session) {
		
		if(!reUpfile.getOriginalFilename().equals("")) {
			
			if(f.getOriginalName() != null) {
				new File(session.getServletContext().getRealPath("resources/uploadFiles/notice/"+f.getUpdateName())).delete();
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
	
	// 좋아요 insert
	@ResponseBody
	@RequestMapping(value="insertNoticeLike.do", produces="application/json; charset=UTF-8")
	public String ajaxInsertLike(NoticeLike nl) {
		noticeService.plusNoticeLikeCount(nl.getNoticeNo());
		return new Gson().toJson(noticeService.insertNoticeLike(nl));
	}
	
	// 좋아요 delete
	@ResponseBody
	@RequestMapping(value="deleteNoticeLike.do", produces="application/json; charset=UTF-8")
	public String ajaxDeleteLike(NoticeLike nl) {
		noticeService.minusNoticeLikeCount(nl.getNoticeNo());
		return new Gson().toJson(noticeService.deleteNoticeLike(nl));
	}
	
	// 좋아요 update
	@ResponseBody
	@RequestMapping(value="updateNoticeLike.do", produces="application/json; charset=UTF-8")
	public String ajaxUpdatetLike(NoticeLike nl) {
		noticeService.plusNoticeLikeCount(nl.getNoticeNo());
		return new Gson().toJson(noticeService.updateNoticeLike(nl));
	}



}
