package com.kh.finalproject.board.controller;

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
import com.kh.finalproject.board.model.service.BoardService;
import com.kh.finalproject.board.model.vo.Board;
import com.kh.finalproject.board.model.vo.BoardComment;
import com.kh.finalproject.board.model.vo.BoardLike;
import com.kh.finalproject.board.model.vo.BoardReport;
import com.kh.finalproject.board.model.vo.CommentReport;
import com.kh.finalproject.common.model.vo.Attachment;
import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.common.model.vo.Search;
import com.kh.finalproject.common.teplate.Pagination;
import com.kh.finalproject.member.model.vo.Member;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	// 일반게시글 리스트조회
	@RequestMapping("list.bo")
	public String selectBoardList(@RequestParam(value="cPage", defaultValue="1") int currentPage, Model model) {
		
		PageInfo pi = Pagination.getPageInfo(boardService.selectBoardListCount(),
											 currentPage,
											 10,
											 5);
		
		model.addAttribute("list", boardService.selectBoardList(pi));
		model.addAttribute("pi", pi);
		

		return "board/boardListView";
	}

	// 일반게시글 작성폼 이동
	@RequestMapping("enrollForm.bo")
	public String boardEnrollForm() {
		return "board/boardEnrollForm";
	}
	
	// 일반게시글 작성
		@RequestMapping("insert.bo")
		public String insertBoard(Board b, Attachment f, Model model, MultipartFile upfile, HttpSession session) {
			if(!upfile.getOriginalFilename().equals("")) {
				saveFile(upfile, session);
				int lastNo = boardService.selectLastBoardNo();
				f.setOriginalName(upfile.getOriginalFilename());
				f.setUpdateName(saveFile(upfile, session));
				f.setRefNo(lastNo);
				boardService.insertFile(f);
			} 
			if(boardService.insertBoard(b) > 0) { 
				return "redirect:list.bo";
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

			String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/board/");

			try {
				upfile.transferTo(new File(savePath + updateName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			return updateName;
		}

		
	// 일반게시글 상세조회
		@ResponseBody
		@GetMapping("detail.bo")
		public ModelAndView selectBoard(BoardLike bl, BoardReport br, int bno, ModelAndView mv, Attachment f, MultipartFile upfile, HttpSession session)   {
			
			

			
			if(boardService.increaseCount(bno) > 0 ) {
				
				if(!boardService.selectFile(bno).isEmpty()) {
					ArrayList<Attachment> fileList = boardService.selectFile(bno);
					for(Attachment file : fileList) {
						f.setRefNo(file.getRefNo());
						f.setRefType(file.getRefType());
					}
						mv.addObject("f", boardService.selectFile(bno)).setViewName("board/boardDetailView");
				}
				if(session.getAttribute("loginUser") != null) {
					Member loginUser = (Member) session.getAttribute("loginUser");
					bl.setMemNo(loginUser.getMemNo());
					bl.setBoardNo(bno);
					mv.addObject("like", (boardService.selectLike(bl)));
				}
				//session.getAttribute(loginUser.memNo)
				mv.addObject("br", boardService.selectBoardReport(bno))
				  .addObject("cList", boardService.selectComment(bno))
				  .addObject("b", boardService.selectBoard(bno))
				  .setViewName("board/boardDetailView");
			} else {
				mv.addObject("errorMsg", "게시글 조회 실패").setViewName("common/errorPage");
			}
			return mv;
		}
		
		
		// 일반게시글 삭제
		@PostMapping("delete.bo")
		public String deleteNotice(int bno, HttpSession session) {

			if(boardService.deleteBoard(bno) > 0) {
				boardService.deleteFile(bno);
				session.setAttribute("alertMsg", "ㅎㅎㅎ");
				return "redirect:list.bo";
			} else {
				session.setAttribute("errorMsg", "실패했습니다");
				return "common/errorPage";
			}
		}


		// 일반게시글 수정폼
		@PostMapping("updateForm.bo")
		public ModelAndView updateNotice(int bno, ModelAndView mv) {
			mv.addObject("b", boardService.selectBoard(bno)).setViewName("board/boardUpdateForm");
			mv.addObject("f", boardService.selectFile(bno)).setViewName("board/boardUpdateForm");
			return mv;
		}
		
		// 일반게시글 수정
		@PostMapping("update.bo")
		public String updateNotice(Board b, Attachment f, Model model, MultipartFile reUpfile, HttpSession session) {
			
			if(!reUpfile.getOriginalFilename().equals("")) {
				
				if(f.getOriginalName() != null) {
					new File(session.getServletContext().getRealPath("resources/uploadFiles/board/"+f.getUpdateName())).delete();
					f.setOriginalName(reUpfile.getOriginalFilename());
					f.setUpdateName(saveFile(reUpfile, session));
					
				}
			}
			boardService.updateFiles(f);
			if(boardService.updateBoard(b) > 0 ) {
				session.setAttribute("alertMsg", "성공");
				return "redirect:detail.bo?bno=" + b.getBoardNo();
			} else {
				session.setAttribute("errorMsg", "실패!");
				return "common/errorPage";
			}
		}

		
		@RequestMapping("report.bo")
		public String insertReport(BoardReport br , HttpSession session) {
			
			if(boardService.insertReport(br) > 0) {
				session.setAttribute("alertMsg", "성공");
				return "redirect:detail.bo?bno=" + br.getRefBoardNo();
			} else {
				return "common/errorPage";
			}
		}
		
		@ResponseBody
		@GetMapping(value = "disabled.btn", produces="application/json; charset=UTF-8")
		public String disabledBtn(CommentReport cr) {
			return new Gson().toJson(boardService.selectCommentReport(cr));
		}
		
		@ResponseBody
		@RequestMapping(value="report.co")
		public String insertCommentReport(CommentReport cr) {
			return boardService.insertCommentReport(cr) > 0 ? "success" : "fail";
		}
		
		@PostMapping(value = "rinsert.do")
		public String InsertComment(BoardComment bc, HttpSession session, int boardNo) {
			 if(boardService.insertComment(bc) > 0) {
				    boardService.commentCount(boardNo);
					session.setAttribute("alertMsg", "성공");
				 return "redirect:detail.bo?bno=" + bc.getBoardNo();
			 } else {
				 return "common/errorPage";
			 }
			 
		}
		
		@PostMapping("update.co")
		public String updateComment(BoardComment bc, HttpSession session) {
			if(boardService.updateComment(bc) > 0) {
			session.setAttribute("alertMsg", "성공");
		 return "redirect:detail.bo?bno=" + bc.getBoardNo();
	 } else {
		 return "common/errorPage";
	 }
	 
}
		@PostMapping("delete.co")
		public String deleteComment(BoardComment bc, HttpSession session) {
			System.out.println(bc);
			if(boardService.deleteComment(bc.getCommentNo()) > 0) {
				session.setAttribute("alertMsg", "성공");
				return "redirect:detail.bo?bno=" + bc.getBoardNo();
			} else {
				 return "common/errorPage";
			}
		}
		
		// 일반게시글 검색 리스트조회
		@RequestMapping("search.bo")
		public String selectSearchList(@RequestParam(value="cPage", defaultValue="1") int currentPage, Model model, Search s) {
			PageInfo pi = Pagination.getPageInfo(boardService.selectSearchCount(s),
												 currentPage,
												 10,
												 5);
			model.addAttribute("pi", pi).addAttribute("list", boardService.selectSearchBoardList(pi,s))
			.addAttribute("s", s);
			return "board/boardListView";
		}

		// 좋아요 insert
		@ResponseBody
		@RequestMapping(value="insertBoardLike.do", produces="application/json; charset=UTF-8")
		public String ajaxInsertLike(BoardLike bl) {
			boardService.plusLikeCount(bl.getBoardNo());
			return new Gson().toJson(boardService.insertBoardLike(bl));
		}
		
		// 좋아요 delete
		@ResponseBody
		@RequestMapping(value="deleteBoardLike.do", produces="application/json; charset=UTF-8")
		public String ajaxDeleteLike(BoardLike bl) {
			boardService.minusLikeCount(bl.getBoardNo());
			return new Gson().toJson(boardService.deleteBoardLike(bl));
		}
		
		// 좋아요 update
		@ResponseBody
		@RequestMapping(value="updateBoardLike.do", produces="application/json; charset=UTF-8")
		public String ajaxUpdatetLike(BoardLike bl) {
			boardService.plusLikeCount(bl.getBoardNo());
			return new Gson().toJson(boardService.updateBoardLike(bl));
		}
		
		/*
		@ResponseBody
		@RequestMapping(value="selectLike.do", produces="application/json; charset=UTF-8")
		public String ajaxSelectLike(BoardLike bl) {
			return new Gson().toJson((boardService.selectList(bl)));
		}
		*/
}
