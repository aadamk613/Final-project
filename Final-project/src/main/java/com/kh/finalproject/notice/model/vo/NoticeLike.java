package com.kh.finalproject.notice.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class NoticeLike {

	private int noticeLikeNo;
	private String noticeLikeStatus;
	private int noticeNo;
	private int memNo;
	
}
