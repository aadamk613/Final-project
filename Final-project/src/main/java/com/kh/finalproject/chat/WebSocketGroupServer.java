package com.kh.finalproject.chat;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/*
 * 사용자들을 기억하기 위한 저장소
 * 중복 불가
 * 동기화 지원
 */
public class WebSocketGroupServer extends TextWebSocketHandler{
	// a thread-safe, concurrent set
	// to save multiple users simultaneously
	private Set<WebSocketSession> users = new CopyOnWriteArraySet();
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		users.add(session);
		System.out.println("사용자 접속 : 현재 " + users.size() + "명");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// 메시지를 모든 사용자에게 전송 (사용자 수만큼 반복해서 전송) 
		TextMessage newMessage = new TextMessage((CharSequence)message.getPayload());
		System.out.println(newMessage);
		for (WebSocketSession ws: users) {
			ws.sendMessage(newMessage);
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		users.remove(session);
		System.out.println("사용자 종료 ! 현재 " + users.size() + "명");
	}

}
