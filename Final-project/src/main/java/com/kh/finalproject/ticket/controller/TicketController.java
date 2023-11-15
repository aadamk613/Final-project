package com.kh.finalproject.ticket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TicketController {
	
	@RequestMapping("createTicket.tk")
	public String ticketRequest() {
		return "ticket/ticketWriteForm";
	}
}
