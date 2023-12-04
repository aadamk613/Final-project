package com.kh.finalproject.experience.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
public class KakaoPay {
	
	// 요청 값
	private String cid = "TC0ONETIME";
	private String partnerOrderId = "abcdef"; // 결제 승인 시 필요 
	private String partnerUserId = "user01"; // 결제 승인 시 필요 
	private String itemName = "expitem";
	private Integer quantity = 1;
	private Integer totalAmount = 10000;
	private Integer taxFreeAmount = 10000;
	//private String approvalUrl = "http://localhost:8001/final/yrsendPayment.exp";
	//private String cancelUrl = "http://localhost:8001/final";
	//private String failUrl = "http://localhost:8001/final";
	
	
	// 응답 값
	private String tid; // 결제 승인 시 필요 (결제 고유 번호)
	private String nextRedirectPcUrl; // 결제 페이지
	
	

}
