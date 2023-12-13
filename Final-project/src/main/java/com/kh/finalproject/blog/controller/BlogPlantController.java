package com.kh.finalproject.blog.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

@RequestMapping("/blog")
@RequiredArgsConstructor
@Controller
public class BlogPlantController {
	
	private final BlogService blogService;
	private final CommonService commonService;
	private final CommonController commonController;
	
	// ---------- 블로그 식물 관련 메서드 ---------- 
	// 식물 전체 리스트로 이동
	@RequestMapping("/selectList.pl")
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
	@RequestMapping("/select.pl")
	public ModelAndView selectBlogPlant(int plantNo, ModelAndView mv) {
		Plant plant = blogService.selectBlogPlant(plantNo);
		System.out.println(plant);
		mv.addObject("plant", plant)
		  .setViewName("blog/plantView");
		return mv;
	}
	
	// 식물 등록 페이지로 이동
	@RequestMapping("/insertForm.pl")
	public ModelAndView insertFormBlogPlant(int blogNo, ModelAndView mv) {
		mv.addObject("blogNo", blogNo)
		  .setViewName("blog/plantInsertForm");
		return mv;
	}
	
	// 식물 등록
	@PostMapping("/insert.pl")
	public String insertBlogPlant(Plant plant, 
								  @RequestParam(value="blogNo") int blogNo,
								  HttpServletRequest request,
								  HttpSession session,
								  MultipartFile upfile,
								  Model model) {
		System.out.println("여기 오나?");
		System.out.println(plant);
		System.out.println(blogNo);
		Attachment file = new Attachment();
		if(!upfile.getOriginalFilename().equals("")) { // 첨부파일이 있을 경우
			file = commonController.setFile(upfile, session, "plant");
		}
		// 첨부파일이 없을 경우
		if(blogService.insertBlogPlant(plant, file) > 0) { 
			return "redirect:selectList.pl?blogNo=" + blogNo;
		} else {
			model.addAttribute("alertMsg", "식물 등록에 실패했습니다.");
			return "common/errorPage";
		}
	}
	
	// 식물 수정 페이지로 이동
	@RequestMapping("/updateForm.pl")
	public ModelAndView updateBlogPlantForm(int plantNo,
							      		int blogNo,
							      		ModelAndView mv) {
		Plant plant = blogService.selectBlogPlant(plantNo);
		mv.addObject("plant", plant)
		  .setViewName("blog/plantUpdateForm");
		return mv;
	}
	
	// 식물 수정
	@PostMapping("/update.pl")
	public String updateBlogPlant(Plant plant, 
							      HttpSession session, 
							      MultipartFile upfile, 
							      ModelAndView mv) {
		Attachment attchment = new Attachment();
		
		if (!upfile.getOriginalFilename().equals("")) {
			
			attchment = commonController.setFile(upfile, session, "plant");
			attchment.setRefNo(plant.getPlantNo()); 
			
			if (!plant.getUpdateName().equals("")) { 
				new File(session.getServletContext().
						 getRealPath(plant.getFilePath() + plant.getUpdateName())).delete(); 
				commonService.updateAttachment(attchment);
				
			} else {
				commonService.insertAttchment(attchment); 
			}
		}
		
		if (blogService.updateBlogPlant(plant) > 0) { 
			Plant afterPlant = (Plant) blogService.selectBlogPlant(plant.getPlantNo());
			mv.addObject("plant", afterPlant)
			  .addObject("alertMsg", "식물 정보 수정에 성공하였습니다");
		} else {
			mv.addObject("alertMsg", "식물 정보 수정에 실패하였습니다");
		}
		mv.setViewName("blog/plantUpdateForm");
		return "redirect:select.pl?plantNo=" + plant.getPlantNo();
	}
	
	
	/* 수정중
  	@PostMapping("/update.pl")
	public ModelAndView updateBlogPlant(Plant plant, 
									    HttpSession session, 
									    MultipartFile upfile, 
									    ModelAndView mv) {
		System.out.println("upfile어떤지 " + upfile);
		System.out.println(!upfile.getOriginalFilename().equals(""));
		Attachment attchment = new Attachment();
		
		if (!(upfile.getOriginalFilename().equals(""))) { // 사진이 첨부 됨
			System.out.println("수정 시 파일 넣었음 여기 와야하는데..");
			if (!plant.getUpdateName().equals("")) { // 기존 파일 있었음 그럼 파일 삭제하고 디비파일 수정
				System.out.println("여기는 기존 파일도 있어야 하고, 수정 파일도 있어야 함");
				
				System.out.println("삭제할 사진 이름 " + plant.getFilePath() + plant.getUpdateName());
				new File(session.getServletContext().
						getRealPath(plant.getFilePath() + plant.getUpdateName())).delete(); // 여기가 파일 삭제 근데 안되는 듯
				
				attchment = commonController.setFile(upfile, session, "plant");
				//attchment.setRefType("plant"); 
				attchment.setRefNo(plant.getPlantNo()); 
				//attchment.setOriginalName(upfile.getOriginalFilename());
				System.out.println("기존 파일 있을 경우 새 파일로 업데이트 전 attachment " +attchment);
				commonService.updateAttachment(attchment); // 여기가 디비파일 수정 
				
			} else {// 기존 파일 없었음
				    // 그럼 파일도 저장하고 디비에도 저장해야함
				System.out.println("기존 파일 없었음 저장해야지");
				attchment = commonController.setFile(upfile, session, "plant"); // 여기가 파일 저장
				attchment.setRefNo(plant.getPlantNo());
				System.out.println("저장할 file 어떤지 " + attchment);
				commonService.insertAttchment(attchment); // 여기가 디비 저장
			}
			
		}
		// 사진이 첨부 안됨
		if (blogService.updateBlogPlant(plant) > 0) { 
			System.out.println("여기는 수정할 때 다 와야함 글 수정하는 곳 ");
			Plant afterPlant = (Plant) blogService.selectBlogPlant(plant.getPlantNo());
			mv.addObject("plant", afterPlant)
			  .addObject("alertMsg", "식물 정보 수정에 성공하였습니다");
		} else {
			mv.addObject("alertMsg", "식물 정보 수정에 실패하였습니다");
		}
		mv.setViewName("blog/plantUpdateForm");
		return mv;
	}
  	*/
  	
	// 식물 삭제
	@RequestMapping("/delete.pl")
	public String deleteBlogPlant(int plantNo,
							      int blogNo) {
		
		blogService.deleteBlogPlant(plantNo);
		
		return "redirect:selectList.pl?blogNo=" + blogNo + "&currentPage=" + "1";
	}
	
	
	// ---------- 블로그 식물 일지 관련 메서드 ---------- 
	// 식물 일지 등록 페이지로 이동
	@RequestMapping("/insertForm.pr")
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
	@PostMapping("/insert.pr")
	public String insertBlogPlantReport(PlantReport plantReport,
										MultipartFile upfile,
			 							HttpSession session) {
		
		Attachment file = new Attachment();
		
		if(upfile != null && !upfile.getOriginalFilename().equals("")) {
			file = commonController.setFile(upfile, session, "plantReport");
		}
		
		if(blogService.insertBlogPlantReport(plantReport, file) > 0) {
			session.setAttribute("alertMsg", "일지 작성 성공");
			return "redirect:select.pl?plantNo=" + plantReport.getTopPlantNo();
		} else {
			session.setAttribute("errorMsg", "일지 작성 실패");
			return "common/errorPage";
		}
		
	}
	
	
	
	/*
	 * 	// 식물 일지 등록하기
	@RequestMapping("/insert.pr")
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
