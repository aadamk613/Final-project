package com.kh.finalproject.ticket.model.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class Ticket {
	private int ticketNo;
	private String ticketWriter;
	private String ticketTitle;
	private String ticketContent;
	private String createDate;
	private String modifyDate;
	private String answerWriter;
	private String answerTitle;
	private String answerContent;
	private String answerCreateDate;
	private String answerModifyDate;
	private String status;
}
