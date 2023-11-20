package com.kh.finalproject.experience.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ExperienceReply {
	
	private int expReplyNo;
	private String replyWriter;
	private String replyCreateDate;
	private String replyContent;
	private String replyModifyDate;
	private String replySecret;
	private String expNo;

}
