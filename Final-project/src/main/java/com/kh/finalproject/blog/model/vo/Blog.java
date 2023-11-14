package com.kh.finalproject.blog.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class Blog {

	private int blogNo;
	private int memNo;
	private String blogTitle;
	private String blogIntroduce;
	private String blogImg;
	private String blogInterest;
	private String plantPrefer;
	private String blogCreateDate;
	
}
