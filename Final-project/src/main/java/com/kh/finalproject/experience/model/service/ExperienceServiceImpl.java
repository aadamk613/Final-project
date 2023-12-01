package com.kh.finalproject.experience.model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

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
	public String readyForPay() throws IOException, ParseException  {
		
		String url = "https://kapi.kakao.com/v1/payment/ready";
		// admin키
		String serviceKey = "KakaoAK 1f372afd224bea9fbde198f021999cfb";
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
		
		urlConnection.setRequestProperty("Authorization", serviceKey);
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
		
		String cid = "TC0ONETIME";
		String partnerOrderId = "abcdef";
		String partnerUserId = "user01";
		String itemName = "expitem";
		Integer quantity = 1;
		Integer totalAmount = 10000;
		Integer taxFreeAmount = 10000;
		String approvalUrl = "http://localhost:8001/final/yrpay.exp";
		String cancelUrl = "http://localhost:8001/final";
		String failUrl = "http://localhost:8001/final";
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("cid=" + cid);
		sb.append("&partner_order_id=" + partnerOrderId);
		sb.append("&partner_user_id=" + partnerUserId);
		sb.append("&item_name=" + itemName);
		sb.append("&quantity=" + quantity);
		sb.append("&total_amount=" + totalAmount);
		sb.append("&tax_free_amount=" + taxFreeAmount);
		sb.append("&approval_url=" + approvalUrl);
		sb.append("&cancel_url=" + cancelUrl);
		sb.append("&fail_url=" + failUrl);
		
		System.out.println(sb.toString());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
		bw.write(sb.toString());
		bw.flush();
		
		System.out.println(urlConnection.getResponseCode());
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line = "";
		StringBuilder responseData = new StringBuilder();
		while((line = br.readLine()) != null) {
			responseData.append(line);
		}
		System.out.println(responseData.toString());
		
		JSONParser parser = new JSONParser();
		JSONObject element = (JSONObject)parser.parse(responseData.toString());
		
		String accessToken = element.get("tid").toString();
		
		br.close();
		bw.close();
		
		return accessToken;
	}
	
	public String payExp(String tid) {
		System.out.println("준비완료");
		System.out.println(tid);
		//T5699c3a122f0603ef95
		
		
		
		
		
		
		return "";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
