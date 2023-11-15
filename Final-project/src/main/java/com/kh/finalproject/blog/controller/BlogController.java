package com.kh.finalproject.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.finalproject.blog.model.service.BlogService;
import com.kh.finalproject.blog.model.vo.Blog;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;

	@RequestMapping("main.bl") // 블로그 메인 화면으로 이동
	public String selectListBlog() {
		return "blog/blogMainView";
	}
	
	@RequestMapping("insertForm.bl") // 블로그 생성 화면으로 이동
	public String insertFormBlog() {
		return "blog/insertBlogForm";
	}
	
	@RequestMapping("insert.bl") // 블로그 생성하기
	public String insertBlog(Blog b, HttpSession session) {
		System.out.println(b);
		if(blogService.insertBlog(b) > 0) {
			session.setAttribute("alertMsg", "블로그 생성에 실패했습니다.");
			return "blog/blogMainView";
		} else {
			return "blog/blogMainView";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="select.bl", produces="application/json; charset=UTF-8") // 블로그 정보 불러오기
	public ModelAndView selectBlog(int blogNo, ModelAndView mv) {
		Blog blog = (Blog)blogService.selectBlog(blogNo);
		mv.addObject("blog", blog);
		mv.setViewName("blog/blogView");
		System.out.println("블로그 섹렉트 컨트롤러 : " + blog);
		return mv;
	}
	
	@RequestMapping("updateForm.bl") // 블로그 업데이트 화면으로 이동
	public ModelAndView updateFormBlog(int blogNo, ModelAndView mv) {
		Blog blog = (Blog)blogService.selectBlog(blogNo);
		mv.addObject("blog", blog);
		mv.setViewName("blog/updateFormBlog");
		return mv;
	}
	
	@RequestMapping("update.bl") // 블로그 업데이트하기
	public ModelAndView updateBlog(int blogNo, Blog beforeBlog, ModelAndView mv) {
		System.out.println("블로그 업데이트 하기 전 블로그 정보 셀렉트 : " + beforeBlog);
		
		if(blogService.updateBlog(beforeBlog) > 0) { // 블로그 정보 수정 성공
			Blog afterBlog = (Blog)blogService.selectBlog(blogNo);
			System.out.println("블로그 업데이트 한 후블로그 정보 셀렉트 : " + afterBlog);
			mv.addObject("alertMsg", "정보가 수정되었습니다");
			mv.addObject("blog", afterBlog);
		} else { // 정보 수정 실패
			mv.addObject("alertMsg", "정보 수정에 실패 했습니다");
		}
		mv.setViewName("blog/updateFormBlog");
		return mv;
	}
	
	@RequestMapping("updateForm.bl_ct") // 블로그 카테고리 관리 화면으로 이동
	public ModelAndView categoryFormBlog(int blogNo, ModelAndView mv) {
		mv.addObject("blogNo", blogNo);
		mv.setViewName("blog/blogCategoryView");
		return mv;
	}
	
	@RequestMapping("insert.bl_ct") // 블로그 카테고리 생성하기
	public ModelAndView insertCategory(int blogNo, ModelAndView mv) {
		blogService.insertCategory(blogNo);
		
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
