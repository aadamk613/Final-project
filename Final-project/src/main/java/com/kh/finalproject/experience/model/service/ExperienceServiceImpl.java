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
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.common.model.dao.CommonDao;
import com.kh.finalproject.common.model.vo.Attachment;
import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.experience.model.dao.ExperienceDao;
import com.kh.finalproject.experience.model.vo.Experience;
import com.kh.finalproject.experience.model.vo.ExperienceReply;
import com.kh.finalproject.experience.model.vo.Payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@EnableTransactionManagement
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
	@Transactional("transactionManager")
	public int insertExperience(Experience exp, ArrayList<Attachment> fileList) {
		int result = experienceDao.insertExperience(sqlSession, exp);
		result *= !fileList.isEmpty() ? experienceDao.insertFiles(sqlSession, fileList) : 1;
		return result;
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

	
	
	
	
	// ---------------------------------------------------------------------------------------
	
	// 카카오페이
	@Override
	public String readyForPay(HashMap map) throws IOException, ParseException  {
		
		String url = "https://kapi.kakao.com/v1/payment/ready";
		String authorization = "KakaoAK 1f372afd224bea9fbde198f021999cfb";
		String contentType = "application/x-www-form-urlencoded;charset=utf-8";
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		urlConnection.setRequestMethod("POST");
		urlConnection.setRequestProperty("Authorization", authorization);
		urlConnection.setRequestProperty("Content-type", contentType);
		urlConnection.setDoOutput(true);
		
		Payment payment = (Payment)map.get("payment");
		Experience exp = (Experience)map.get("exp");
		
		String cid = "TC0ONETIME"; // 고정
		String partnerOrderId = String.valueOf(exp.getExpNo()); // 게시글 번호
		String partnerUserId = payment.getUserId(); // 로그인유저 ID
		String itemName = exp.getExpTitle(); // 게시글 제목
		Integer quantity = payment.getQuantity(); // 사용자가 선택한 수량
		Integer totalAmount = quantity * exp.getExpPrice(); // 수량 * 게시글 가격
		//Integer taxFreeAmount = 10000; // 게시글 가격
		
		int paymentNo = experienceDao.selectPaymentNo(sqlSession);
		
		String approvalUrl = "http://localhost:8001/final/sendPayment/" + paymentNo;
		String cancelUrl = "http://localhost:8001/final/cancelPayment/";
		String failUrl = "http://localhost:8001/final/failPayment/";
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("cid=").append(cid);
		sb.append("&partner_order_id=").append(partnerOrderId);
		sb.append("&partner_user_id=").append(partnerUserId);
		sb.append("&item_name=").append(itemName);
		sb.append("&quantity=").append(quantity);
		sb.append("&total_amount=").append(totalAmount);
		sb.append("&tax_free_amount=").append(totalAmount);
		sb.append("&approval_url=").append(approvalUrl);
		sb.append("&cancel_url=").append(cancelUrl);
		sb.append("&fail_url=").append(failUrl);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
		bw.write(sb.toString());
		bw.flush();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line = "";
		StringBuilder responseData = new StringBuilder();
		while((line = br.readLine()) != null) {
			responseData.append(line);
		}
		
		JSONParser parser = new JSONParser();
		JSONObject element = (JSONObject)parser.parse(responseData.toString());
		
		String tid = element.get("tid").toString();
		String nextRedirectPcUrl = element.get("next_redirect_pc_url").toString();
		
		payment.setOrderId(partnerOrderId);
		payment.setTid(tid);
		payment.setTotalAmount(totalAmount);
		
		br.close();
		bw.close();
		
		if(experienceDao.insertPayment(sqlSession, payment) > 0) {
			return nextRedirectPcUrl;
		} else {
			return "error";
		}
	}
	
	
	// 결제 승인
	public Payment payExp(String pg_token, int paymentNo) throws IOException, ParseException {
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
		
		//HashMap map = new HashMap();
		//map.put("orderId", partnerOrderId);
		//map.put("userId", partnerUserId);
		
		/*
		String orderId = pk.substring(0, pk.indexOf(","));
		String userId = pk.substring(pk.indexOf(",") + 1);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("orderId", orderId);
		map.put("userId", userId);
		
		log.info("아아악!!!!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@{}{}@@@", orderId, userId);
		log.info("네에에에에ㅔ에에에에에에에에ㅔ엥{}", map);
		
		*/
		// payment null 예외처리 할것
		Payment payment = experienceDao.selectPayment(sqlSession, paymentNo);
		
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
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line = "";
		StringBuilder responseData = new StringBuilder();
		while((line = br.readLine()) != null) {
			responseData.append(line);
		}
		
		JSONParser parser = new JSONParser();
		JSONObject element = (JSONObject)parser.parse(responseData.toString());
		
		String approvedAt = element.get("approved_at").toString();
		payment.setApprovedAt(approvedAt);
		
		if(responseData != null) {
			if(experienceDao.updatePayment(sqlSession, payment) > 0) {
				log.info("업데이트 성공");
			}
		}
		
		return payment;
	}
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	

}
