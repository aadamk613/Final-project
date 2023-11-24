package com.kh.finalproject.blog.model.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class BlogBoard {
	
	
	private int blogBoardNo;
	private int blogNo;
	private int cattegorySettingNo;
	private String blogBoardTitle;
	private String blogBoardContent;
	private Date createDate;
	private int view;
	private String status;
	private String writer;
	
	private String blogInterest;
	private int categorySettingNo;

}
