package com.kh.finalproject.blog.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.finalproject.blog.model.service.BlogService;
import com.kh.finalproject.blog.model.vo.Blog;
import com.kh.finalproject.blog.model.vo.BlogCategorySetting;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;

	// 블로그 메인 화면으로 이동
	@RequestMapping("main.bl") 
	public String selectListBlog() {
		return "blog/blogMainView";
	}
	
	// 블로그 생성 화면으로 이동
	@RequestMapping("insertForm.bl")
	public String insertFormBlog() {
		return "blog/blogInsertForm.jsp";
	}
	
	// 블로그 생성하기
	@RequestMapping("insert.bl") 
	public String insertBlog(Blog b, HttpSession session) {
		System.out.println(b);
		if(blogService.insertBlog(b) > 0) {
			session.setAttribute("alertMsg", "블로그 생성에 실패했습니다.");
			return "blog/blogMainView";
		} else {
			return "blog/blogMainView";
		}
	}
	
	// 블로그 정보 불러오기
	@ResponseBody
	@RequestMapping(value="select.bl", produces="application/json; charset=UTF-8") 
	public ModelAndView selectBlog(int blogNo, ModelAndView mv) {
		Blog blog = (Blog)blogService.selectBlog(blogNo);
		ArrayList<BlogCategorySetting> list = blogService.selectCatogory(blogNo);
		
		mv.addObject("blog", blog) // blog정보
		  .addObject("list", list) // 해당 블로그의 BlogCategorySetting정보
		  .setViewName("blog/blogView");
		
		System.out.println("블로그 섹렉트 컨트롤러 : " + blog);
		//System.out.println("selectBlog에서 카테고리 list : " + list);
		
		return mv;
	}
	
	// 블로그 업데이트 화면으로 이동
	@RequestMapping("updateForm.bl") 
	public ModelAndView updateFormBlog(int blogNo, ModelAndView mv) {
		Blog blog = (Blog)blogService.selectBlog(blogNo);
		mv.addObject("blog", blog)
		  .setViewName("blog/blogUpdateForm");
		return mv;
	}
	
	// 블로그 업데이트하기
	@RequestMapping("update.bl") 
	public ModelAndView updateBlog(int blogNo, Blog beforeBlog, ModelAndView mv) {
		//System.out.println("블로그 업데이트 하기 전 블로그 정보 셀렉트 : " + beforeBlog);
		
		if(blogService.updateBlog(beforeBlog) > 0) { // 블로그 정보 수정 성공
			Blog afterBlog = (Blog)blogService.selectBlog(blogNo);
			//System.out.println("블로그 업데이트 한 후블로그 정보 셀렉트 : " + afterBlog);
			mv.addObject("alertMsg", "정보가 수정되었습니다")
			  .addObject("blog", afterBlog);
		} else { // 정보 수정 실패
			mv.addObject("alertMsg", "정보 수정에 실패 했습니다");
		}
		
		mv.setViewName("blog/blogUpdateForm");
		return mv;
	}
	
	// 블로그 카테고리 관리 화면으로 이동
	@RequestMapping("updateForm.bl_ct") 
	public ModelAndView categoryFormBlog(int blogNo, ModelAndView mv) {
		mv.addObject("blogNo", blogNo)
		  .setViewName("blog/blogCategoryView");
		return mv;
	}
	
	// 블로그 카테고리 생성하기
	@ResponseBody
	@RequestMapping(value="insert.bl_ct", produces="application/json; charset=UTF-8") 
	public String insertCategory(int blogNo, int categoryNo, ModelAndView mv) {
		BlogCategorySetting blogCateSet = new BlogCategorySetting();
		blogCateSet.setBlogNo(blogNo);
		blogCateSet.setCategoryNo(categoryNo);
		//System.out.println(blogCateSet);
		
		return new Gson().toJson(blogService.insertCategory(blogCateSet));
	}
	
	// 블로그 카테고리 조회
	@ResponseBody
	@RequestMapping(value="select.bl_ct", produces="application/json; charset=UTF-8") 
	public String selectCatogory(int blogNo, ModelAndView mv) {
		ArrayList<BlogCategorySetting> list = blogService.selectCatogory(blogNo);
		//System.out.println("selectCatogory에서 list : " + list);
		
		
		mv.addObject("list", list) 
		  .setViewName("blog/blogCategoryView");
		
		return new Gson().toJson(list);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
