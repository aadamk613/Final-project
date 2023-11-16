package com.kh.finalproject.board.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Board {

	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private int views;
	private String boardCreateDate;
	private String boardUpdateDate;
	private String status;
	private int likeCount;
	private int commentCount;
	private String boardReportCount;
	private int memNo;
	private String isBlind;
	
}
