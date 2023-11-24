package com.kh.finalproject.experience.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ExperienceReply {
	
	private int expReplyNo;
	private String replyWriter;
	private String replyCreateDate;
	private String replyContent;
	private String replyModifyDate;
	private String replySecret;
	private int expNo;

}
