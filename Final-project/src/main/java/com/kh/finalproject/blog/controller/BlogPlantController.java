package com.kh.finalproject.blog.controller;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.finalproject.blog.model.service.BlogService;
import com.kh.finalproject.blog.model.vo.Plant;
import com.kh.finalproject.blog.model.vo.PlantReport;
import com.kh.finalproject.common.controller.CommonController;
import com.kh.finalproject.common.model.service.CommonService;
import com.kh.finalproject.common.model.vo.Attachment;
import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.common.teplate.Pagination;
import com.kh.finalproject.experience.model.service.ExperienceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BlogPlantController {
	
	private final BlogService blogService;
	private final CommonService commonService;
	private final CommonController commonController;
	
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
	@PostMapping("insert.bl_pl")
	public String insertBlogPlant(Plant plant, 
								  @RequestParam(value="blogNo") int blogNo,
								  HttpServletRequest request,
								  HttpSession session,
								  MultipartFile upfile,
								  Model model) {
		Attachment file = new Attachment();
		if(!upfile.getOriginalFilename().equals("")) { 
			file = commonController.setFile(upfile, session, "plant");
		}
		if(blogService.insertBlogPlant(plant, file) > 0) { 
			return "redirect:selectList.bl_pl?blogNo=" + blogNo;
		} else {
			model.addAttribute("alertMsg", "식물 등록에 실패했습니다.");
			return "common/errorPage";
		}
	}
	
	/*
	 * 	// 식물 등록
	@RequestMapping("insert.bl_pl")
	public String insertBlogPlant(Plant plant, 
								  @RequestParam(value="blogNo") int blogNo,
								  HttpServletRequest request,
								  HttpSession session,
								  MultipartFile upfile,
								  Model model) {
		Attachment file = new Attachment();
		
		if(!upfile.getOriginalFilename().equals("")) { // 첨부파일이 있을 경우
			System.out.println("식물 등록 첨부파일 없을 경우");
			file = commonController.setFile(upfile, session, "plant");
			System.out.println(file);
		}
		System.out.println("식물 등록 첨부파일 있을 경우");
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
	 */
	
	
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
	/*
	 * 사진이 원래 있었음
		근데 수정 시 새로 사진을 추가함
		그럼 원래 attchment를 바꿔줘야함
		바꿀 정보 originalName updateName이 두개

	 */
	@PostMapping("update.bl_pl")
	public ModelAndView updateBlogPlant(Plant plant,
									    HttpSession session,
									    MultipartFile upfile,
							      		ModelAndView mv) {
		Attachment file = new Attachment();
		
		if(upfile.getSize() > 0) { // 첨부한 파일이 있을 경우
			System.out.println("수정할때 파일을 첨부함");
			System.out.println(upfile);
			if(plant.getUpdateName() != null) { // 기존에 첨부파일이 있을 경우
				System.out.println("기존 파일 있음");
				new File(session.getServletContext().getRealPath(plant.getUpdateName())).delete();
			} 
			// 새로 파일을 첨부했지만 기존 파일은 없었을 경우 바로 여기로
			file = commonController.setFile(upfile, session, "plant");
			file.setRefNo(plant.getPlantNo());
			file.setRefType("plant");
			commonService.updateAttachment(file);
		}
		if(blogService.updateBlogPlant(plant) > 0) { // 여기는 첨부파일 제외 나머지 정보 업데이트 함
			Plant afterPlant = (Plant)blogService.selectBlogPlant(plant.getPlantNo());
			mv.addObject("plant", afterPlant)
			  .addObject("alertMsg", "식물 정보 수정에 성공하였습니다");
		} else {
			mv.addObject("alertMsg", "식물 정보 수정에 실패하였습니다");
		}
		mv.setViewName("blog/plantUpdateForm");
		return mv;
	}
	
	
	
	/*
	 * 	// 식물 수정
	@RequestMapping("update.bl_pl")
	public ModelAndView updateBlogPlant(Plant plant,
							      		HttpServletRequest request, 
									    HttpSession session,
									    MultipartFile upfile,
							      		ModelAndView mv) {
		System.out.println("식물 수정하기 컨트롤러");
		System.out.println("plant" + plant);
		System.out.println("upfile" + upfile);
		Attachment file = new Attachment();
		
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
	 * 
	 */
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
	@PostMapping("insert.bl_pr")
	public String insertBlogPlantReport(PlantReport plantReport,
										MultipartFile upfile,
			 							HttpSession session) {
		
		Attachment file = new Attachment();
		
		if(upfile != null && !upfile.getOriginalFilename().equals("")) {
			file = commonController.setFile(upfile, session, "plantReport");
		}
		
		if(blogService.insertBlogPlantReport(plantReport, file) > 0) {
			session.setAttribute("alertMsg", "일지 작성 성공");
			return "redirect:select.bl_pl?plantNo=" + plantReport.getTopPlantNo();
		} else {
			session.setAttribute("errorMsg", "일지 작성 실패");
			return "common/errorPage";
		}
		
	}
	
	
	
	/*
	 * 	// 식물 일지 등록하기
	@RequestMapping("insert.bl_pr")
	public String insertBlogPlantReport(PlantReport plantReport,
			 							HttpServletRequest request, 
			 							HttpSession session,
			 							MultipartFile upfile,
			 							ModelAndView mv) {
		System.out.println("식물 일지 작성 plantReport : " + plantReport);
		Attachment file = new Attachment();
		System.out.println("식물 일지 작성 file : " + file);
		System.out.println("식물 일지 작성 upfile " + upfile);
		
		if(upfile != null && !upfile.getOriginalFilename().equals("")) { // 첨부파일이 있을 경우
			System.out.println("식물 등록 첨부파일 없을 경우");
			file = commonController.setFile(upfile, session, "plantReport");
			System.out.println(file);
		}
		
		
		System.out.println("식물 일지 등록 첨부파일 없을 경우");
		if(blogService.insertBlogPlantReport(plantReport, file) > 0) { // 성공
			session.setAttribute("alertMsg", "일지 작성 성공");
			return "redirect:selectList.bl_pl?blogNo=" + plantReport.getTopPlantNo();
		} else {
			session.setAttribute("errorMsg", "일지 작성 실패");
			return "common/errorPage";
		}
		
		
	}
	 */
}
