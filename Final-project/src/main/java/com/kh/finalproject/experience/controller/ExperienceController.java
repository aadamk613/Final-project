package com.kh.finalproject.experience.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.finalproject.common.controller.CommonController;
import com.kh.finalproject.common.model.vo.Files;
import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.common.teplate.Pagination;
import com.kh.finalproject.experience.model.service.ExperienceService;
import com.kh.finalproject.experience.model.vo.Experience;
import com.kh.finalproject.experience.model.vo.ExperienceReply;

@Controller
public class ExperienceController {
	
	@Autowired
	private ExperienceService experienceService;
	@Autowired
	private CommonController commonController;
	
	@RequestMapping("yrlist.exp")
	public String seleceExperienceList(@RequestParam(value="page", defaultValue="1") int currentPage, Model model){
		// 페이지는 기본값 1로 설정
		// System.out.println(success);
		PageInfo pi = Pagination.getPageInfo(experienceService.selectListCount(), currentPage, 5, 5);
		model.addAttribute("experienceList", experienceService.selectExperienceList(pi));
		model.addAttribute("pi", pi);
		return "experience/experienceListView";
	}
	
	@RequestMapping("yrdetail.exp")
	public String selectExperience(int expNo, Model model, HttpSession session) {
		System.out.println(expNo);
		
		// 조회수 증가 성공 시 
		if(experienceService.increaseCount(expNo) > 0 ) { 
			// 게시글 상세조회
			model.addAttribute("exp", experienceService.selectExperience(expNo));
			// 첨부파일 조회
			System.out.println("왜 안나오냐구요");
			
			System.out.println(((Experience)model.getAttribute("exp")).getExpNo());
			
			
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
	
	@ResponseBody
	@RequestMapping(value="yrselectExpReplyList.exp", produces="application/json; charset=UTF-8")
	public String selectExpReplyList(int expNo) {
		System.out.println("하하");
		System.out.println(experienceService.selectExpReplyList(expNo).toString());
		return new Gson().toJson(experienceService.selectExpReplyList(expNo));
	}
	
	@ResponseBody
	@PostMapping("yrinsertExpReply.exp")
	public String insertExpReply(@RequestBody String newReply) throws ParseException {
		
		// {"expNo":"61","replyWriter":"user01","replyContent":"ㅁㄴㅇㄹ","replySecret":0}
		
		// 버전 2.8.6
		//JsonObject jobj = JsonParser.parseString(expReply).getAsJsonObject();
		// 버전 2.8.5
		JsonObject jobj = new JsonParser().parse(newReply).getAsJsonObject();
		// System.out.println(jobj);
		// {"expNo":"61","replyWriter":"user01","replyContent":"ㅁㄴㅇㄹ","replySecret":0}
		
		// System.out.println(jobj.get("expNo").getAsInt()); // 61
		// System.out.println(jobj.get("replyContent")); // "ㅁㄴㅇㄹ"
		
		// set해서 넣어줄 수 밖에 없음 (null일수도 있으니까 이렇게 해주는게 맞음)
		ExperienceReply expReply = new ExperienceReply();
		expReply.setExpNo(jobj.get("expNo").getAsInt());
		expReply.setReplyContent(jobj.get("replyContent").getAsString());
		expReply.setReplyWriter(jobj.get("replyWriter").getAsString());
		expReply.setReplySecret((jobj.get("replySecret").getAsInt() > 0) ? "Y" : "N");
		
		return (experienceService.insertExpReply(expReply) > 0) ? "success" : "fail";
	}
	
	@GetMapping("yrinsertExpForm.exp")
	public String insertExperienceoForm() {
		return "experience/experienceWrite";
	}
	
	@PostMapping("yrinsertExp.exp")
	public String insertExperience(Experience exp, ArrayList<MultipartFile> upfiles, String[] anno, HttpSession session) {
		// for(MultipartFile upfile : upfiles) {
		ArrayList<Files> fileList = new ArrayList();
		for(int i = 0; i < upfiles.size(); i++) {
			if(!upfiles.get(i).getOriginalFilename().equals("")) {
				Files file = commonController.setFile(upfiles.get(i), session, "experience");
				file.setRefNo(exp.getExpNo());
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
	
	@GetMapping("yrupdateExp.exp")
	public String updateExperienceForm(int expNo) {
		System.out.println("여긴 체험학습 게시글 수정하기");
		System.out.println(expNo);
		return "";
	}
	
	@RequestMapping("yrdeleteExp.exp")
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
	
	@ResponseBody
	@GetMapping("yrexpLikeCheck")
	public int expLikeCheck(int expNo, int memNo) {
		
		HashMap map = new HashMap();
		map.put("expNo", expNo);
		map.put("memNo", memNo);
		
		return experienceService.selectExpLike(map);
	}

	@ResponseBody
	@GetMapping(value="yrexpLike")
	public int expLike(int expNo, int likeVal, int memNo) {
		
		HashMap map = new HashMap();
		map.put("expNo", expNo);
		map.put("memNo", memNo);
		
		// 좋아요라면 
		if(likeVal > 0) {
			// 좋아요 등록
			return experienceService.insertExpLike(map);
			
		} else {
			// 좋아요 취소
			return experienceService.deleteExpLike(map);
		}
	}
	
	// 체험학습 댓글 삭제
	@ResponseBody
	@PostMapping("yrdeleteExpReply")
	public String deleteExpReply(int expReplyNo) {
		
		if(experienceService.deleteExpReply(expReplyNo) > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	
	
	
	
	
	
	
	
	
	

}
