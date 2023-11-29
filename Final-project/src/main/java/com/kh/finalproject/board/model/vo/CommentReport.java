package com.kh.finalproject.board.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class CommentReport {

	private int reportNo;
	private String reportContent;
	private String reportDate;
	private int refCommentNo;
	private String refMemberNo;
	private int memNo;
	private int refBoardNo;
	
}
