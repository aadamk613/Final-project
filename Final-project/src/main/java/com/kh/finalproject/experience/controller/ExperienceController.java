package com.kh.finalproject.experience.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.finalproject.common.controller.CommonController;
import com.kh.finalproject.common.model.vo.Attachment;
import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.common.teplate.Pagination;
import com.kh.finalproject.experience.model.service.ExperienceService;
import com.kh.finalproject.experience.model.vo.Experience;
import com.kh.finalproject.experience.model.vo.Payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ExperienceController {
	
	private final ExperienceService experienceService;
	private final CommonController commonController;
	
	// 체험학습 목록조회
	/**
	 * @param currentPage : 체험학습 게시글 현재 페이지 (기본값 1페이지)
	 * @param model : 데이터를 담아 보내줄 Model
	 * @return : 체험학습 게시글 목록으로 이동
	 */
	@GetMapping("yrlist.exp")
	public String seleceExperienceList(@RequestParam(value="page", defaultValue="1") int currentPage, Model model){
		// 페이지는 기본값 1로 설정
		// System.out.println(success);
		PageInfo pi = Pagination.getPageInfo(experienceService.selectListCount(), currentPage, 5, 5);
		model.addAttribute("experienceList", experienceService.selectExperienceList(pi));
		model.addAttribute("pi", pi);
		return "experience/experienceListView";
	}
	
	// 체험학습 게시글 상세조회
	/**
	 * @param expNo : 게시글 번호
	 * @param model : 데이터 담아 보내줄 Model객체
	 * @param session : 데이터 담아 보내줄 Session객체
	 * @return : 성공 시 상세조회 페이지로 이동 / 실패 시 게시글 목록으로 이동
	 */
	@GetMapping("yrdetail.exp")
	public String selectExperience(int expNo, Model model, HttpSession session) {
		System.out.println(expNo);
		
		// 조회수 증가 성공 시 
		if(experienceService.increaseCount(expNo) > 0 ) { 
			// 게시글 상세조회
			//model.addAttribute("exp", experienceService.selectExperience(expNo));
			// 결제때 필요해서 session에 담음
			session.setAttribute("exp", experienceService.selectExperience(expNo));
			
			System.out.println("파일넘버 나와라");
			System.out.println(model.getAttribute("exp"));
			
			// 첨부파일 조회
			// HashMap map = new HashMap();
			// map.put("refNo", expNo);
			// map.put("refType", "EXPERIENCE");
			model.addAttribute("files", commonController.selectFiles(expNo, "experience"));
			System.out.println("왜 아무것도 안나와");
			System.out.println(model.getAttribute("files"));
			
			return "experience/experienceDetailView";
		// 조회수 증가 실패 시	
		} else {
			session.setAttribute("error", "게시글 상세조회에 실패하셨습니다. 다시 이용해 주세요.");
			// model.addAttribute("error", "조회수 증가 실패");
			return "redirect:yrlist.exp";
			// return "experience/experienceListView";
		}
	}
	
	// 게시글 작성 페이지로 이동
	/**
	 * @return : 작성화면으로 이동
	 */
	@GetMapping("yrinsertExpForm.exp")
	public String insertExperienceoForm(HttpSession session) {
		return "experience/experienceWrite";
	}
	
	// 게시글 작성
	/**
	 * @param exp : 작성된 게시글의 정보
	 * @param upfiles : 게시글에 첨부된 파일 배열
	 * @param anno : 게시글에 첨부된 파일의 주석 배열
	 * @param session : Session객체
	 * @return : 게시글 목록으로 이동
	 */
	@PostMapping("yrinsertExp.exp")
	public String insertExperience(Experience exp, 
								   MultipartFile[] upfiles, 
								   String[] anno, 
								   HttpSession session) {
		// for(MultipartFile upfile : upfiles) {
		
		
		
		System.out.println("나오자 좀");
		
		ArrayList<Attachment> fileList = new ArrayList();
		for(int i = 0; i < upfiles.length; i++) {
			
			
			
			if(!upfiles[i].getOriginalFilename().equals("")) {
				
				
				System.out.println(upfiles[i]);
				
				Attachment file = commonController.setFile(upfiles[i], session, "experience");
				System.out.println("엥  여기 번호가 있음?????");
				System.out.println(exp.getExpNo()); // 0
				//file.setRefNo(exp.getExpNo());
				//System.out.println(anno[i]);
				file.setFileAnnotation(anno[i]);
				fileList.add(file);
			} 
		}
		
		if(experienceService.insertExperience(exp, fileList) > 0) {
			session.setAttribute("alertMsg", "게시글이 등록되었습니다.");
		} else {
			session.setAttribute("alertMsg", "게시글 등록에 실패하셨습니다.");
		}
		return "redirect:yrlist.exp";
	}
	
	
	// 게시글 수정 페이지로 이동
	// insert메소드 같이 써도 될거같은데 단일책임의원칙 걸려서 따로 씀
	/**
	 * @param exp : 수정할 때 기본으로 입력되어있을 게시글 정보
	 * @param fileNo : 게시글에 첨부된 파일 번호
	 * @param filePath : 게시글에 첨부된 파일 경로
	 * @param updateName : 게시글에 첨부된 파일 수정된 이름
	 * @param fileAnnotation : 게시글에 첨부된 파일 주석
	 * @param mv : 데이터 담아 보내줄 ModelAndView 객체
	 * @return : ModelAndView객체로 게시글 작성 페이지로 이동
	 */
	@PostMapping("yrupdateExpForm.exp")
	public ModelAndView updateExperienceForm(Experience exp, Integer[] fileNo, String[] filePath, String[] updateName, String[] fileAnnotation, ModelAndView mv) {
		ArrayList<Attachment> files = new ArrayList();
		for(int i = 0; i < filePath.length; i++) {
			
			
			Attachment file = new Attachment();
			file.setFileNo(fileNo[i]);
			file.setFilePath(filePath[i]);
			file.setUpdateName(updateName[i]);
			file.setFileAnnotation(fileAnnotation[i]);
			files.add(file);
		}
		System.out.println("파일이다 받아라");
		System.out.println(files.toString());
		mv.addObject("exp", exp)
		  .addObject("files", files)
		  .setViewName("experience/experienceWrite");
		return mv;
	}
	
	// 게시글 수정
	/**
	 * @param exp : 수정된 게시글 정보
	 * @param upfiles : 게시글에 첨부된 파일 배열
	 * @param anno : 게시글에 첨부된 파일의 주석 배열
	 * @param oldFiles : 게시글에 첨부되었다가 수정되기 전 파일의 (경로 + 수정명)의 배열
	 * @param session : Session 객체
	 * @return : 게시글 목록으로 이동
	 */
	@PostMapping("yrupdateExp.exp")
	public String updateExperience(Experience exp, MultipartFile[] upfiles, String[] anno, String[] oldFiles, HttpSession session) {
		
		// 1. 원래 있던 파일 지우고 oldFileNo delete
		if(oldFiles != null) {
			for(String oldFile : oldFiles) {
				System.out.println("올드파일");
				System.out.println(oldFile);
				System.out.println(oldFile.toString());
				new File(session.getServletContext().getRealPath(oldFile)).delete();
			}
		}
		
		// 2. 새로 들어온 파일 MultipartFile insert 
		ArrayList<Attachment> fileList = new ArrayList();
		for(int i = 0; i < upfiles.length; i++) {
			
			log.info("파일이여~{}", upfiles[i]);
			
			Attachment file = new Attachment();
			if(!upfiles[i].getOriginalFilename().equals("")) {
				System.out.println("그래도 찍어봐야지");
				System.out.println(upfiles[i]);
				System.out.println(anno[i]);
				file = commonController.setFile(upfiles[i], session, "experience");
				file.setRefNo(exp.getExpNo());
				file.setFileAnnotation(anno[i]);
				fileList.add(file);
			} 
			// anno가 disabled였다면 값이 안넘어왔겠지만 readonly라서 비어있으면 빈문자열이 넘어옴
			// else {continue;}
		} 
		
		if(experienceService.updateExperience(exp, fileList, oldFiles) > 0) {
			session.setAttribute("alertMsg", "게시글이 수정되었습니다.");
		} else {
			session.setAttribute("alertMsg", "게시글 수정에 실패하셨습니다.");
		}
		return "redirect:yrlist.exp";
	}
	
	// 게시글 삭제
	/**
	 * @param expNo : 삭제할 게시글 번호
	 * @param session : Alert 데이터 담을 Session객체
	 * @return : 게시글 목록으로 이동
	 */
	@PostMapping("yrdeleteExp.exp")
	public String deleteExperience(int expNo, HttpSession session) {
		if(experienceService.deleteExperience(expNo) > 0) {
			session.setAttribute("success", "체험학습 게시글이 삭제되었습니다.");
		} else {
			session.setAttribute("error", "체험학습 게시글 삭제에 실패하셨습니다.");
		}
		// ResponseEntity<String> re = new ResponseEntity<String>("체험학습 게시글이 삭제되었습니다.", HttpStatus.OK);
		// JSONObject객체로 키-밸류로 넘길수도 있음
		// 이렇게 넘기면 한글은 0~255를 넘기기 때문에 POST방식으로 받아야 함
		// 받을 때 데이터를 @RequestBody String str 이렇게 받음
		return "redirect:yrlist.exp";
	}
	
	
	// -------------------------------------------------------------------------------
	//결제
	// AJAX도 있음
	// 1. 결제하기 버튼 누르러가기
	/**
	 * @return : 지원하기 버튼을 누르면 결제 입력 양식 페이지로 이동
	 */
	@GetMapping("yrpayForm.exp")
	public String payExperienceForm() {
		return "experience/experiencePayView";
	}
	
	// 결제 준비 성공 시 오는 곳
	// http://localhost:8001/final/yrsendPayment.exp?pg_token=b63076e46d6b58fbbea6
	@GetMapping("sendPayment")
	public String sendPayment(String pg_token, String userId, Model model) throws IOException, ParseException {
		
		// 결제 승인 보내기
		//HashMap map = (HashMap)ids;
		//log.info("값이 잘 넘어왔을까={}", ids);
		//log.info("userId={}", ids.get("userId"));
		log.info("성공하면 어디까지?");
		Payment payment = experienceService.payExp(pg_token, userId);
		
		model.addAttribute("payment", payment);
		
		return "experience/experiencePaySuccess";
	}
	
	
	@GetMapping("deletePayment")
	public void deletePayment(String userId) {
		// 취소되거나 실패한 결제는 지워줘야 함
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
