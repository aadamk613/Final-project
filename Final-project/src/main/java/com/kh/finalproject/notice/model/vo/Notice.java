package com.kh.finalproject.notice.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class Notice {

	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private int views;
	private String noticeCreateDate;
	private String noticeUpdateDate;
	private String status;
	private int category;
	private int likeCount;
	private String memNo;
	
}
