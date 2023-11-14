package com.kh.finalproject.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {
	@RequestMapping("chatMain.ch")
	public String chatMain() {
		return "chat/chatListView";
	}
	@GetMapping("group")
	public String group() {
		return "group";
	}
}