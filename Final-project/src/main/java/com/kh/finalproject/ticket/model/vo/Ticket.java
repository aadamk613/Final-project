package com.kh.finalproject.ticket.model.vo;

import lombok.Data;

@Data
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
