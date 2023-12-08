package com.kh.finalproject.blog.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.kh.finalproject.blog.model.service.BlogService;
import com.kh.finalproject.blog.model.vo.BlogReply;
import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.common.teplate.Pagination;

import lombok.RequiredArgsConstructor;

@RequestMapping("/blog")
@RequiredArgsConstructor
@RestController
public class AjaxBlogController {
	
	private final BlogService blogService;

	@PostMapping("/insert")
	public String insertBlogReply(BlogReply blogReply) {
		System.out.println("여기");
		return (blogService.insertBlogReply(blogReply)) > 0 ? "success" : "fail" ;
	}
	
	@GetMapping(value="/selectList", produces="application/json; charset=UTF-8")
	public String selectBlogReplyList(@RequestParam(value="currentPage", defaultValue="1")int currentPage, 
									  int blogBoardNo) {
		System.out.println("댓글 불러오기");
		PageInfo pi = Pagination.getPageInfo(blogService.selectListCountBlogReply(blogBoardNo), currentPage, 50, 10);
		ArrayList<BlogReply> list = blogService.seletListBlogReply(pi, blogBoardNo);
		System.out.println("pi: " + pi);
		System.out.println("댓글: " + list);
		return new Gson().toJson(list);
	}
	
	

}
