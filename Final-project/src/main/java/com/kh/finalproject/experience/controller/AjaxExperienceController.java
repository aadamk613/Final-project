package com.kh.finalproject.experience.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.kh.finalproject.common.controller.CommonController;
import com.kh.finalproject.experience.model.service.ExperienceService;
import com.kh.finalproject.experience.model.vo.ExperienceReply;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AjaxExperienceController {
	
	private final ExperienceService experienceService;
	private final CommonController commonController;
	
	// 댓글 조회
	/**
	 * @param expNo : 조회할 댓글의 게시글 번호
	 * @return : 댓글의 리스트 반환
	 */
	@GetMapping(value="yrselectExpReplyList.exp", produces="application/json; charset=UTF-8")
	public String selectExpReplyList(int expNo) {
		System.out.println("하하");
		System.out.println(experienceService.selectExpReplyList(expNo).toString());
		return new Gson().toJson(experienceService.selectExpReplyList(expNo));
	}
	
	// 댓글 작성
	/**
	 * @param newReply : 작성한 댓글의 정보
	 * @return : 댓글작성 성공여부
	 * @throws ParseException
	 */
	@PostMapping("yrinsertExpReply.exp")
	public String insertExpReply(@RequestBody ExperienceReply newReply) throws ParseException {
		
		System.out.println("왜 이건 안들어가지");
		newReply.setReplySecret((Integer.parseInt(newReply.getReplySecret()) > 0) ? "Y" : "N");
		System.out.println(newReply.toString());
		// {"expNo":"61","replyWriter":"user01","replyContent":"ㅁㄴㅇㄹ","replySecret":0}
		
		// 버전 2.8.6
		//JsonObject jobj = JsonParser.parseString(expReply).getAsJsonObject();
		// 버전 2.8.5
		//JsonObject jobj = new JsonParser().parse(newReply).getAsJsonObject();
		// System.out.println(jobj);
		// {"expNo":"61","replyWriter":"user01","replyContent":"ㅁㄴㅇㄹ","replySecret":0}
		
		// System.out.println(jobj.get("expNo").getAsInt()); // 61
		// System.out.println(jobj.get("replyContent")); // "ㅁㄴㅇㄹ"
		
		// set해서 넣어줄 수 밖에 없음 (null일수도 있으니까 이렇게 해주는게 맞음)
//			ExperienceReply expReply = new ExperienceReply();
//			expReply.setExpNo(jobj.get("expNo").getAsInt());
//			expReply.setReplyContent(jobj.get("replyContent").getAsString());
//			expReply.setReplyWriter(jobj.get("replyWriter").getAsString());
//			expReply.setReplySecret((jobj.get("replySecret").getAsInt() > 0) ? "Y" : "N");
		
		return (experienceService.insertExpReply(newReply) > 0) ? "success" : "fail";
	}
	
	// 체험학습 댓글 삭제
	/**
	 * @param expReplyNo : 삭제할 댓글 번호
	 * @return : 성공 / 실패시 결과값 반환
	 */
	@PostMapping("yrdeleteExpReply")
	public String deleteExpReply(int expReplyNo) {
		
		if(experienceService.deleteExpReply(expReplyNo) > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 좋아요 눌려있는지 체크
	/**
	 * @param expNo : 좋아요 확인할 게시글 번호
	 * @param memNo : 좋아요 확인할 회원 번호
	 * @return : 눌렀으면 1, 안눌렀으면 0반환
	 */
	@GetMapping("yrexpLikeCheck")
	public int expLikeCheck(int expNo, int memNo) {
		
		HashMap map = new HashMap();
		map.put("expNo", expNo);
		map.put("memNo", memNo);
		
		return experienceService.selectExpLike(map);
	}

	// 좋아요 등록/취소
	/**
	 * @param expNo : 좋아요 누른 게시글 번호
	 * @param likeVal : 좋아요 눌렀으면 1, 취소하면 0
	 * @param memNo : 좋아요 누른 회원 번호
	 * @return : 
	 */
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
	
	
	
	// 결제
	// 2. 결제 준비 창으로 이동
	@GetMapping("yrreadyForPay.exp" /*, produces="html/text; charset=UTF-8"*/)
	public HashMap<String, Object> readyForPay(HttpSession session) throws IOException, ParseException  {
		// 결제 준비 창으로 가기전 값들 보내줌
		HashMap<String, Object> map = experienceService.readyForPay();
		System.out.println(map.get("nextRedirectPcUrl"));
		//String result = experienceService.payExp(tid);
		System.out.println("먼차이냐"); // 차이없음
		System.out.println(map.get("nextRedirectPcUrl").toString());
		System.out.println((String)map.get("nextRedirectPcUrl"));
		
		session.setAttribute("map", map);
		System.out.println(session.getAttribute("map"));
		System.out.println("잉");
		
		// 결제 창으로 보내줄 url
		session.setAttribute("nextRedirectPcUrl", (String)map.get("nextRedirectPcUrl"));
		return map;
	}
	
	
	@GetMapping("yrtest.exp/{result}")
	public String test(@PathVariable("result") HashMap map) {
		System.out.println("일단 들어옴?");
		System.out.println(map);
		//return map.get("nextRedirectPcUrl").toString();
		return "";
	}
	
	
	// 결제 성공 시 오는 곳
	// http://localhost:8001/final/yrsendPayment.exp?pg_token=b63076e46d6b58fbbea6
	@GetMapping("yrsendPayment.exp")
	public String sendPayment(String pg_token, HttpSession session) {
		
		System.out.println("결제창");
		//System.out.println(session.getAttribute("nextRedirectPcUrl"));
		
		System.out.println("대애박");
		System.out.println(pg_token);
		
		System.out.println(session.getAttribute("map"));
		System.out.println(session.getAttribute("nextRedirectPcUrl"));
		
		experienceService.payExp(pg_token);
		
		
		
		
		
		
		return "";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
