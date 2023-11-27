package com.kh.finalproject.board.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class BoardComment {

	private int commentNo;
	private String commentContent;
	private String commentStatus;
	private String commentCreateDate;
	private String commentUpdateDate;
	private int boardTopComment;
	private int boardNo;
	private String memNo;
	
}
