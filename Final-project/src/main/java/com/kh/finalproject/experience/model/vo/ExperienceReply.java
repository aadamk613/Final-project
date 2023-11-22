package com.kh.finalproject.experience.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ExperienceReply {
	
	private int expReplyNo;
	private String replyWriter;
	private String replyCreateDate;
	private String replyContent;
	private String replyModifyDate;
	private String replySecret;
	private int expNo;

}
