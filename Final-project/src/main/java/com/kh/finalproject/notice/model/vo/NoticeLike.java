package com.kh.finalproject.notice.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class NoticeLike {

	private int noticeLikeNo;
	private String noticeLikeStatus;
	private int noticeNo;
	private int memNo;
	
}
