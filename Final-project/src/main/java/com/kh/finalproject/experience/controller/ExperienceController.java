package com.kh.finalproject.experience.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.finalproject.common.model.service.CommonService;
import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.common.teplate.Pagination;
import com.kh.finalproject.experience.model.service.ExperienceService;
import com.kh.finalproject.experience.model.vo.ExperienceReply;

@Controller
public class ExperienceController {
	
	@Autowired
	private ExperienceService experienceService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private ExperienceReply expReply;
	
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
			HashMap map = new HashMap();
			map.put("refNo", expNo);
			map.put("refType", "EXPERIENCE");
			model.addAttribute("files", commonService.selectFiles(map));
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
		return new Gson().toJson(experienceService.selectExpReplyList(expNo));
	}
	
	@ResponseBody
	@PostMapping("yrinsertExpReply.exp")
	public String insertExpReply(@RequestBody String newReply) throws ParseException {
		// System.out.println(expNo);
		System.out.println(newReply);
		
		// 버전 2.8.6
		//JsonObject jobj = JsonParser.parseString(expReply).getAsJsonObject();
		// 버전 2.8.5
		JsonObject jobj = new JsonParser().parse(newReply).getAsJsonObject();
		System.out.println(jobj);
		
		System.out.println(jobj.get("expNo").getAsInt());
		System.out.println(jobj.get("replyContent"));
		
		// set해서 넣어줄 수 밖에 없음 (null일수도 있으니까 이렇게 해주는게 맞음)
		expReply.setExpNo(jobj.get("expNo").getAsInt());
		expReply.setReplyContent(jobj.get("replyContent").getAsString());
		
		
		
		
		return "success";
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
