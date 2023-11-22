package com.kh.finalproject.blog.controller;

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
import com.kh.finalproject.common.model.vo.Files;
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
	@RequestMapping("select.bl_pl")
	public ModelAndView selectListPlant(@RequestParam(value="currentPage", defaultValue="1")int currentPage, 
									    int blogNo, 
									    ModelAndView mv) {

		PageInfo pi = Pagination.getPageInfo(blogService.selectListCountPlant(blogNo), currentPage, 5, 5);
		
		ArrayList<Plant> list = blogService.selectListPlant(pi, blogNo);
		System.out.println("식물 리스트 : "+list);
		
		ArrayList<HashMap> fileList = new ArrayList();
		
		
		// 여기의 list에 있는 plantNo에 해당 하는 각각의 첨부파일을 다시 ArrayList형태로 조회해야 한다
		// 이렇게 만들어진 첨부파일의 ArrayList를 위의 식물리스트의 ArrayList와 함께 view에 보내줘야한다

		/*
		for(int i = 0; i < list.size(); i++) {
			
			
			HashMap map = new HashMap();
			//plantNoList.add(list.get(i).getPlantNo());
			//System.out.println(plantNoList);
			
			map.put("refNo", list.get(i).getPlantNo());
			map.put("refType", "plant");
			
			ArrayList<Files> file = commonService.selectFiles(map);
			System.out.println(file);
			
			if(file != null) {
				
				map.put("filePath", file.get(0).getFilePath());
				map.put("updateName", file.get(0).getUpdateName());
				fileList.add(map);
			}
		}
		
		System.out.println(fileList);
		/* 첨부파일 조회
		
		
		HashMap map = new HashMap();
		
		map.put("refNo", plantNo);
		map.put("refType", "plant");
		mv.addObject("files", commonService.selectFiles(map));
		
		*/
		
		mv.addObject("list", list)
		  //.addObject("fileList", fileList)
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
		//System.out.println("야가 언제찍히니?");
		plant.setBlogNo(blogNo);
		Files file = new Files();
		
		if(!upfile.getOriginalFilename().equals("")) {
			file = commonController.setFile(upfile, session, "plant");
			System.out.println(file);
		}
		
		// 넘어온 첨부파일이 존재하지 않을 경우: plant(제목, 작성자, 내용)
		// 넘어온 첨부파일이 존재할 경우: plant(제목, 작성자, 내용 ) 
		//					  + file(originalName, updateName, filePath, refType, refNo, fileAnnotation)
		
		if(blogService.insertBlogPlant(plant, file) > 0) { // 성공
			session.setAttribute("alertMsg", "게시글 작성 성공");
			return "redirect:select.bl_pl?blogNo=" + blogNo;
		} else {
			model.addAttribute("errorMsg", "게시글 작성 실패");
			return "common/errorPage";
		}
	}
	
	
	
	// ---------- 블로그 식물 일지 관련 메서드 ---------- 
	@RequestMapping("insertForm.bl.pr/{plantNo}/{category}/{plantNickName}")
	public String insertFormPlantReport(@PathVariable("plantNo") @ModelAttribute int plantNo,
									    @PathVariable("category") @ModelAttribute int category,
									    @PathVariable("plantNickName") @ModelAttribute String plantNickName) {
		
		System.out.println(plantNo + category + plantNickName);
		return "blog/reportInsertForm";
	}
	
	
	
	// 식물 일지 등록 페이지로 이동
	@RequestMapping("insertForm.bl_pr")
	public String insertFormBlogPlantReport() {
		return "blog/reportInsertForm";
	}
}
