package com.kh.finalproject.board.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class BoardReport {

	private int reportNo;
	private String reportContent;
	private String reportDate;
	private int refBoardNo;
	private String refMemberNo;
	private String memNo;
}
