package com.kh.finalproject.experience.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
public class Payment {
	
	// 요청 값
	private String userId;
	private String orderId;
	private String contact;
	private String payStatus;
	
	// 응답 값
	private String tid; // 결제 승인 시 필요 (결제 고유 번호)
	
	private String approvedAt;
	private int quantity;
	private String itemName;
	
	private int totalAmount;
	
	
}
