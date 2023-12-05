package com.kh.finalproject.board.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
	private String memNo;
	private String isBlind;
	private String category;
}
