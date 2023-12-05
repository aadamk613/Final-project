package com.kh.finalproject.board.model.vo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class BoardLike {

	private int boardLikeNo;
	private String boardLikeStatus;
	private int boardNo;
	private int memNo;
	private String boardLikeDate;
	
}
