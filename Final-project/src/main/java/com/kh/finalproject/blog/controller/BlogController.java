package com.kh.finalproject.blog.controller;

import java.util.ArrayList;

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
		ArrayList<BlogCategorySetting> list = blogService.selectCatogory(blogNo);
		
		mv.addObject("blog", blog)
		  .addObject("list", list)
		  .setViewName("blog/blogView");
		
		//System.out.println("블로그 섹렉트 컨트롤러 : " + blog);
		//System.out.println("selectBlog에서 카테고리 list : " + list);
		
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
			mv.addObject("alertMsg", "정보가 수정되었습니다")
			  .addObject("blog", afterBlog);
		} else { // 정보 수정 실패
			mv.addObject("alertMsg", "정보 수정에 실패 했습니다");
		}
		mv.setViewName("blog/updateFormBlog");
		return mv;
	}
	
	@RequestMapping("updateForm.bl_ct") // 블로그 카테고리 관리 화면으로 이동
	public ModelAndView categoryFormBlog(int blogNo, ModelAndView mv) {
		mv.addObject("blogNo", blogNo)
		  .setViewName("blog/blogCategoryView");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="insert.bl_ct", produces="application/json; charset=UTF-8") // 블로그 카테고리 생성하기
	public String insertCategory(int blogNo, int categoryNo, ModelAndView mv) {
		BlogCategorySetting blogCateSet = new BlogCategorySetting();
		blogCateSet.setBlogNo(blogNo);
		blogCateSet.setCategoryNo(categoryNo);
		System.out.println(blogCateSet);
		
		return new Gson().toJson(blogService.insertCategory(blogCateSet));
	}
	
	@ResponseBody
	@RequestMapping(value="select.bl_ct", produces="application/json; charset=UTF-8") // 블로그 카테고리 조회
	public String selectCatogory(int blogNo, ModelAndView mv) {
		ArrayList<BlogCategorySetting> list = blogService.selectCatogory(blogNo);
		System.out.println("selectCatogory에서 list : " + list);
		
		
		mv.addObject("list", list) 
		  .setViewName("blog/blogCategoryView");
		
		return new Gson().toJson(list);
	}

	@RequestMapping("insertForm.bl_bo")
	public String insertBlogBoard(int blogNo, HttpSession session) {
		session.setAttribute("blogNo", blogNo);
		return ("blog/insertFormBlogBoard");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
