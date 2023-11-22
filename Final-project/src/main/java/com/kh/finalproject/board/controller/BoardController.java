package com.kh.finalproject.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.finalproject.board.model.service.BoardService;
import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.common.teplate.Pagination;

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

		model.addAttribute("best", boardService.selectBestBoardList());
System.out.println(boardService.selectBestBoardList());
		model.addAttribute("list", boardService.selectBoardList(pi));
		model.addAttribute("pi", pi);
		

		return "board/boardListView";
		
	}


}
