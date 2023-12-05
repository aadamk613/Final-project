package com.kh.finalproject.experience.model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.common.model.dao.CommonDao;
import com.kh.finalproject.common.model.vo.Attachment;
import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.experience.model.dao.ExperienceDao;
import com.kh.finalproject.experience.model.vo.Experience;
import com.kh.finalproject.experience.model.vo.ExperienceReply;
import com.kh.finalproject.experience.model.vo.Payment;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {
	
	private final ExperienceDao experienceDao;
	private final SqlSessionTemplate sqlSession;
	private final CommonDao commonDao;
	
	// 게시글
	@Override
	public int selectListCount() {
		return experienceDao.selectListCount(sqlSession);
	}
	
	@Override
	public ArrayList<Experience> selectExperienceList(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return experienceDao.selectExperienceList(sqlSession, rowBounds);
	}

	@Override
	public int increaseCount(int expNo) {
		return experienceDao.increaseCount(sqlSession, expNo);
	}

	@Override
	public Experience selectExperience(int expNo) {
		return experienceDao.selectExperience(sqlSession, expNo);
	}

	@Override
	@Transactional
	public int insertExperience(Experience exp, ArrayList<Attachment> fileList) {
		int result = experienceDao.insertExperience(sqlSession, exp);
		/*
		for(Attachment file : fileList) {
			result *= commonDao.insertFiles(sqlSession, file);
		}
		*/
		return experienceDao.insertFiles(sqlSession, fileList);
	}
	
	@Override
	@Transactional
	public int updateExperience(Experience exp, ArrayList<Attachment> fileList, String[] oldFiles) {
		/*
		int result = experienceDao.updateExperience(sqlSession, exp);
		System.out.println(result);
		System.out.println("게시글 업데이트 하나");
		if(oldFiles != null) {
			for(String oldFile : oldFiles) {
				System.out.println("비어있니?");
				System.out.println(oldFile.isEmpty());
				System.out.println(oldFile.equals(""));
				if(!oldFile.isEmpty()) result *= experienceDao.deleteFiles(sqlSession, oldFile);
				System.out.println(result);
				System.out.println("문제찾기 파일 딜리트");
			}
		}
		
		for(Attachment file : fileList) {
			result *= commonDao.insertFiles(sqlSession, file);
			System.out.println(result);
			System.out.println("문제찾기 파일 인서트");
		}
		return result;
		*/
		int result = experienceDao.updateExperience(sqlSession, exp);
		System.out.println(result);
		System.out.println("게시글 업데이트 하나");
		if(oldFiles != null) {
			for(String oldFile : oldFiles) {
				System.out.println("비어있니?");
				System.out.println(oldFile.isEmpty());
				System.out.println(oldFile.equals(""));
				if(!oldFile.isEmpty()) result *= experienceDao.deleteFiles(sqlSession, oldFile);
				System.out.println(result);
				System.out.println("문제찾기 파일 딜리트");
			}
		}
		return experienceDao.updateFiles(sqlSession, fileList);
	}
	
	@Override
	public int deleteExperience(int expNo) {
		return experienceDao.deleteExperience(sqlSession, expNo);
	}
	
	// 댓글
	@Override
	public ArrayList<ExperienceReply> selectExpReplyList(int expNo) {
		return experienceDao.selectExpReplyList(sqlSession, expNo);
	}
	
	@Override
	public int deleteExpReply(int expReplyNo) {
		return experienceDao.deleteExpReply(sqlSession, expReplyNo);
	}

	@Override
	public int insertExpReply(ExperienceReply expReply) {
		return experienceDao.insertExpReply(sqlSession, expReply);
	}

	// 좋아요
	@Override
	public int selectExpLike(HashMap map) {
		return experienceDao.selectExpLike(sqlSession, map);
	}

	@Override
	public int insertExpLike(HashMap map) {
		return experienceDao.insertExpLike(sqlSession, map);
	}

	@Override
	public int deleteExpLike(HashMap map) {
		return experienceDao.deleteExpLike(sqlSession, map);
	}

	
	
	
	
	
	
	// 카카오페이
	@Override
	public String readyForPay(HashMap map) throws IOException, ParseException  {
		
		String url = "https://kapi.kakao.com/v1/payment/ready";
		// admin키
		String authorization = "KakaoAK 1f372afd224bea9fbde198f021999cfb";
		String contentType = "application/x-www-form-urlencoded;charset=utf-8";
		
		//StringBuilder sb = new StringBuilder();
		//sb.append("https://kapi.kakao.com/v1/payment/ready?Authorization=");
		//sb.append(serviceKey);
		//sb.append("Content-type=application/x-www-form-urlencoded;charset=utf-8");
		
		//String url = sb.toString();
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		//String responseText = br.readLine();
		
		
		urlConnection.setRequestMethod("POST");
		
		/*
		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("Authorization", serviceKey);
		requestHeaders.put("Content-type", contentType);
		
		for(Map.Entry<String, String> header : requestHeaders.entrySet()) {
			urlConnection.setRequestProperty(header.getKey(), header.getValue());
		}
		*/
		
		// 헤더에 담음
		urlConnection.setRequestProperty("Authorization", authorization);
		urlConnection.setRequestProperty("Content-type", contentType);
		
		//urlConnection.setRequestProperty(key, value);
		urlConnection.setDoOutput(true);
		
		/*
		// 필수 본문 파라미터
		가맹점 코드 : cid=TC0ONETIME
		가맹점 주문번호 : partner_order_id=111111
		가맹점 회원 id : partner_user_id=user01
		상품명 : item_name=expitem
		상품 수량 : quantity=1
		상품 총액 : total_amount=10000
		상품 비과세 금액 : tax_free_amount=10000
		결제 성공 시 redirect url : approval_url=http://localhost:8001/final
		결제 취소 시 redirect url : cancel_url=http://localhost:8001/final
		결제 실패 시 redirect url : fail_url=http://localhost:8001/final
		*/
		
		// 다운캐스팅
		Payment payment = (Payment)map.get("payment");
		Experience exp = (Experience)map.get("exp");
		
		String cid = "TC0ONETIME"; // 고정
		String partnerOrderId = String.valueOf(exp.getExpNo()); // 게시글 번호
		
		String partnerUserId = payment.getUserId(); // 로그인유저 ID
		String itemName = exp.getExpTitle(); // 게시글 제목
		//Integer quantity = 1; // 사용자가 선택한 수량
		Integer totalAmount = exp.getExpPrice(); // 게시글 가격
		//Integer taxFreeAmount = 10000; // 게시글 가격
		
		
		String approvalUrl = "http://localhost:8001/final/yrsendPayment.exp?userId=" + partnerUserId;
		String cancelUrl = "http://localhost:8001/final";
		String failUrl = "http://localhost:8001/final";
		
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("cid=" + cid);
		sb.append("&partner_order_id=" + partnerOrderId);
		sb.append("&partner_user_id=" + partnerUserId);
		sb.append("&item_name=" + itemName);
		sb.append("&quantity=" + payment.getQuantity());
		sb.append("&total_amount=" + totalAmount);
		sb.append("&tax_free_amount=" + totalAmount);
		sb.append("&approval_url=" + approvalUrl);
		sb.append("&cancel_url=" + cancelUrl);
		sb.append("&fail_url=" + failUrl);
		
		System.out.println(sb.toString());
		
		// 요청
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
		bw.write(sb.toString());
		bw.flush();
		
		System.out.println(urlConnection.getResponseCode());
		
		// 응답
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line = "";
		StringBuilder responseData = new StringBuilder();
		while((line = br.readLine()) != null) {
			responseData.append(line);
		}
		System.out.println(responseData.toString());
		
		JSONParser parser = new JSONParser();
		JSONObject element = (JSONObject)parser.parse(responseData.toString());
		
		// DB저장밖에 답이 없다. (session은 유실되고, 쿼리스트링으로는 넘겨줄 방법이 없음)
		String tid = element.get("tid").toString();
		String nextRedirectPcUrl = element.get("next_redirect_pc_url").toString();
		/*
		payment = Payment.builder().orderId(partnerOrderId)
										   .userId(partnerUserId)
										   .contact("01011111111")
										   .tid(tid).build();
		*/
		payment.setOrderId(partnerOrderId);
		payment.setTid(tid);
		payment.setTotalAmount(totalAmount);
		
		System.out.println("이게 바로 payment다");
		System.out.println(payment);
		
		// DB에 저장
		int result = experienceDao.insertPayment(sqlSession, payment);
		
		System.out.println("여기로 결제");
		System.out.println(nextRedirectPcUrl);
		
		// 임시 값 보내주기
		// session으로 보내주던가 Map으로 보내주던가 객체로 보내주던가
		//HashMap<String, Object> map = new HashMap<String, Object>();
		//map.put("payUniqueNo", payUniqueNo); // tid
		//map.put("nextRedirectPcUrl", nextRedirectPcUrl); // 결제 url 이쪽으로 url요청 보내야 함
		
		br.close();
		bw.close();
		
		return nextRedirectPcUrl;
	}
	
	
	// 결제 승인
	public Payment payExp(String pg_token, String userId) throws IOException, ParseException {
		System.out.println("준비완료");
		//System.out.println(tid);
		//T5699c3a122f0603ef95
		
		//next_redirect_pc_url":"https://online-pay.kakao.com/mockup/v1/e39df5dae0c17bee0b5ed834d30261ada5038849748e8ea7b41f51971c569b9d/info"
		// 여기에서 결제를 성공해야 pg_token이 쿼리스트링으로 나옴
		// 갸를 뽑아서 여기를 와야 함
		
		String url = "https://kapi.kakao.com/v1/payment/approve";
		
		String authorization = "KakaoAK 1f372afd224bea9fbde198f021999cfb";
		String contentType = "application/x-www-form-urlencoded;charset=utf-8";
		
		String cid = "TC0ONETIME";
		//String tid = "payUniqueNo";
		//String partnerOrderId = "abcdef"; // ready와 동일
		//String partnerUserId = "user01"; // ready와 동일
		//String pg_token = "dd";
		
		System.out.println("서비스");
		//HashMap map = new HashMap();
		//map.put("orderId", partnerOrderId);
		//map.put("userId", partnerUserId);
		
		//System.out.println("맵");
		//System.out.println(map);
		
		Payment payment = experienceDao.selectPayment(sqlSession, userId);
		System.out.println(payment);
		
		System.out.println(payment.getTid());
		
		// 돌려주는거 next_pc_url,  pg_token, tid => 받아서 돌려줌
		
		// 보냈던 값은 알아서 골라서 보내주면됨
		// session에 담아도 되고 DB에 담아도 됨
		
		// 어차피 데이터는 JSON타입으로 넘어오니까 map이나 vo나 session등에 담아서 가져오면 됨
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		urlConnection.setRequestMethod("POST");
		urlConnection.setRequestProperty("Authorization", authorization);
		urlConnection.setRequestProperty("Content-type", contentType);
		urlConnection.setDoOutput(true);
		
		StringBuilder sb = new StringBuilder();
		sb.append("cid=").append(cid)
		  .append("&tid=").append(payment.getTid())
		  .append("&partner_order_id=").append(payment.getOrderId())
		  .append("&partner_user_id=").append(payment.getUserId())
		  .append("&pg_token=").append(pg_token);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
		bw.write(sb.toString());
		bw.flush();
		
		System.out.println("여기까지 왔으면 대박");
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line = "";
		StringBuilder responseData = new StringBuilder();
		while((line = br.readLine()) != null) {
			responseData.append(line);
		}
		
		System.out.println("음치킨");
		System.out.println(responseData);
		
		JSONParser parser = new JSONParser();
		JSONObject element = (JSONObject)parser.parse(responseData.toString());
		
		// 여기서 업데이트하고 가져갈 데이터 가져가기
		if(element.get("approvedat") != null) {
			// experienceDao.updatePayment(map);
			String approvedAt = element.get("approvedat").toString();
			payment.setApprovedAt(approvedAt);
		} 
		
		
		
		
		return payment;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	

}
