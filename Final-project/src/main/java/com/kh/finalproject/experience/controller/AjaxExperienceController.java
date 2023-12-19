package com.kh.finalproject.experience.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.kh.finalproject.common.controller.CommonController;
import com.kh.finalproject.experience.model.service.ExperienceService;
import com.kh.finalproject.experience.model.vo.ExperienceReply;
import com.kh.finalproject.experience.model.vo.Payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AjaxExperienceController {
	
	private final ExperienceService experienceService;
	private final CommonController commonController;
	
	// 댓글 조회
	/**
	 * @param expNo : 조회할 댓글의 게시글 번호
	 * @return : 댓글의 리스트 반환
	 */
	@GetMapping(value="yrselectExpReplyList.exp", produces="application/json; charset=UTF-8")
	public String selectExpReplyList(String expNo) {
		return new Gson().toJson(experienceService.selectExpReplyList(Integer.parseInt(expNo)));
	}
	
	// 댓글 작성
	/**
	 * @param newReply : 작성한 댓글의 정보
	 * @return : 댓글작성 성공여부
	 * @throws ParseException
	 */
	@PostMapping("insertExpReply")
	public String insertExpReply(@RequestBody ExperienceReply newReply) throws ParseException {
		
		newReply.setReplySecret((newReply.getReplySecret().equals("true")) ? "Y" : "N");
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
	public int expLikeCheck(String expNo, String memNo) {
		
		// 자꾸 No없으면 AJAX안돼서 String으로 받았는데
		// String int로 파싱안해도 되네????????
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
	@GetMapping("yrexpLike")
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
	@GetMapping("readyForPay")
	public String readyForPay(Payment payment, HttpSession session) throws IOException, ParseException  {
		// 결제 준비 창으로 가기전 값들 보내줌
		
		System.out.println("결제 가기전 값 가져오기");
		System.out.println(payment.getContact());
		System.out.println(payment.getQuantity());
		System.out.println(payment.getUserId());
		//System.out.println(payment.getOrderId());
		System.out.println(session.getAttribute("exp"));
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("payment", payment);
		map.put("exp", session.getAttribute("exp"));
		
		//Payment payment = Payment.builder().contact(contact).quantity(quantity).build();
		
		
		String redirectUrl = experienceService.readyForPay(map);
		session.setAttribute("url", redirectUrl);
		
		return redirectUrl;
	}
	
	/*
	@GetMapping("yrtest.exp/{result}") // PathVariable을 쓸거면 매핑을 더 직관적으로 쓸 수 있음
	public String test(@PathVariable("result") HashMap map) {
		System.out.println("일단 들어옴?");
		System.out.println(map);
		//return map.get("nextRedirectPcUrl").toString();
		return "";
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
