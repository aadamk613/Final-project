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

	@RequestMapping("main.bl")
	public String selectListBlog() {
		return "blog/blogMainView";
	}
	
	@RequestMapping("insertForm.bl")
	public String insertFormBlog() {
		return "blog/insertBlogForm";
	}
	
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
	
	@ResponseBody
	@RequestMapping(value="select.bl", produces="application/json; charset=UTF-8")
	public ModelAndView selectBlog(int blogNo, ModelAndView mv) {
		Blog blog = (Blog)blogService.selectBlog(blogNo);
		mv.addObject("blog", blog);
		mv.setViewName("blog/blogView");
		return mv;
	}
	
	
	
}
