package com.kh.finalproject.blog.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class BlogReply {

	private int blogReplyNo;
	private int topReplyNo;
	private int blogBoardNo;
	private int writer;
	private String blogReplycontent;
	private String createDate;
	private String replySecret;
	private String status;
	
	private String memNick;
	
}
