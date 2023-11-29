package com.kh.finalproject.blog.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.finalproject.blog.model.service.BlogService;
import com.kh.finalproject.blog.model.vo.Plant;
import com.kh.finalproject.common.controller.CommonController;
import com.kh.finalproject.common.model.service.CommonService;
import com.kh.finalproject.common.model.vo.Attachment;
import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.common.teplate.Pagination;

@Controller
public class BlogPlantController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private CommonController commonController;
	
	
	// ---------- 블로그 식물 관련 메서드 ---------- 
	// 식물 전체 리스트로 이동
	@RequestMapping("selectList.bl_pl")
	public ModelAndView selectListPlant(@RequestParam(value="currentPage", defaultValue="1")int currentPage, 
									    int blogNo, 
									    ModelAndView mv) {

		PageInfo pi = Pagination.getPageInfo(blogService.selectListCountPlant(blogNo), currentPage, 5, 10);
		
		ArrayList<Plant> list = blogService.selectListPlant(pi, blogNo);
		System.out.println("식물 리스트 : "+list);
		System.out.println("식물 리스트 : "+pi);
		
		mv.addObject("list", list)
		  .addObject("pi", pi)
		  .addObject("blogNo", blogNo)
		  .setViewName("blog/plantListView");
		return mv;
	}
	
	// 식물 조회하기
	@RequestMapping("select.bl_pl")
	public ModelAndView selectBlogPlant(int plantNo, ModelAndView mv) {
		Plant plant = blogService.selectBlogPlant(plantNo);
		System.out.println(plant);
		mv.addObject("plant", plant)
		  .setViewName("blog/plantView");
		return mv;
	}
	
	// 식물 등록 페이지로 이동
	@RequestMapping("insertForm.bl_pl")
	public ModelAndView insertFormBlogPlant(int blogNo, ModelAndView mv) {
		mv.addObject("blogNo", blogNo)
		  .setViewName("blog/plantInsertForm");
		return mv;
	}
	
	// 식물 등록
	@RequestMapping("insert.bl_pl")
	public String insertBlogPlant(Plant plant, 
								  @RequestParam(value="blogNo") int blogNo,
								  HttpServletRequest request,
								  HttpSession session,
								  MultipartFile upfile,
								  Model model) {
		plant.setBlogNo(blogNo);
		Attachment file = new Attachment();
		
		if(!upfile.getOriginalFilename().equals("")) { // 첨부파일이 있을 경우
			file = commonController.setFile(upfile, session, "plant");
			System.out.println(file);
			
			if(blogService.insertBlogPlant(plant, file) > 0) { // 성공
				System.out.println(blogService.insertBlogPlant(plant, file));
				session.setAttribute("alertMsg", "게시글 작성 성공");
				return "redirect:selectList.bl_pl?blogNo=" + blogNo;
			} else {
				model.addAttribute("errorMsg", "게시글 작성 실패");
				return "common/errorPage";
			}
		}
		
		// 넘어온 첨부파일이 존재하지 않을 경우: plant(제목, 작성자, 내용)
		// 넘어온 첨부파일이 존재할 경우: plant(제목, 작성자, 내용 ) 
		//					   + file(originalName, updateName, filePath, refType, refNo, fileAnnotation)
		
		// 첨부파일이 없을 경우
		if(blogService.insertBlogPlant(plant, file) > 0) { // 성공
			session.setAttribute("alertMsg", "게시글 작성 성공");
			return "redirect:selectList.bl_pl?blogNo=" + blogNo;
		} else {
			model.addAttribute("errorMsg", "게시글 작성 실패");
			return "common/errorPage";
		}
	}
	
	// 식물 수정 페이지로 이동
	@RequestMapping("updateForm.bl_pl")
	public ModelAndView updateBlogPlantForm(int plantNo,
							      		int blogNo,
							      		ModelAndView mv) {
		Plant plant = blogService.selectBlogPlant(plantNo);
		mv.addObject("plant", plant)
		  .setViewName("blog/plantUpdateForm");
		return mv;
	}
	
	// 식물 수정
	@RequestMapping("update.bl_pl")
	public ModelAndView updateBlogPlant(Plant plant,
							      		HttpServletRequest request, 
									    HttpSession session,
									    MultipartFile upfile,
							      		ModelAndView mv) {
		System.out.println("식물 수정하기 컨트롤러");
		System.out.println("plant" + plant);
		System.out.println("upfile" + upfile);
		Files file = new Files();
		
		if(upfile.getSize() > 0) { // 첨부한 파일이 있을 경우
			
			// 기존의 첨부파일이 있을 경우 있던 첨부파일을 삭제해줌
			if(plant.getUpdateName() != null) {
				new File(session.getServletContext().getRealPath(plant.getUpdateName())).delete();
				file.setRefType("plant");
				file.setRefNo(plant.getPlantNo());
				commonController.deleteFiles(file);
			} 
			// 기존 첨부파일이 없었을 경우 바로 파일을 저장해줌
			file = commonController.setFile(upfile, session, "plant");
			file.setRefNo(plant.getPlantNo());
		}
		
		
		// 첨부 파일 없을 경우 바로 실행
		if(blogService.updateBlogPlant(plant, file) > 0) {
			Plant afterPlant = (Plant)blogService.selectBlogPlant(plant.getPlantNo());
			System.out.println("afterPlant" + afterPlant);
			mv.addObject("plant", afterPlant)
			  .addObject("alertMsg", "식물 정보 수정에 성공했습니다");
		} else {
			mv.addObject("alertMsg", "식물 정보 수정에 실패했습니다");
		}
		mv.setViewName("blog/plantUpdateForm");
		return mv;
	}
	
	// 식물 삭제
	@RequestMapping("delete.bl_pl")
	public String deleteBlogPlant(int plantNo,
							      int blogNo) {
		
		blogService.deleteBlogPlant(plantNo);
		
		return "redirect:selectList.bl_pl?blogNo=" + blogNo + "&currentPage=" + "1";
		//return "redirect:select.bl_pl?blogNo=" + blogNo + "&currentPage=" + currentPage;
	}
	
	
	// ---------- 블로그 식물 일지 관련 메서드 ---------- 
	// 식물 일지 등록 페이지로 이동
	@RequestMapping("insertForm.bl_pr")
	public ModelAndView insertFormPlantReport(int plantNo,
								        String category,
									    String plantNickName,
									    ModelAndView mv) {
		Plant plant = blogService.selectBlogPlant(plantNo);
		mv.addObject("plant", plant)
		  .setViewName("blog/reportInsertForm");
		System.out.println(plantNo + category + plantNickName);
		return mv;
	}
	
	// 식물 일지 등록하기
	@RequestMapping("insert.bl_pr")
	public String insertFormBlogPlantReport() {
		return "blog/reportInsertForm";
	}
}
