package com.kh.finalproject.chat;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketBasicServer extends TextWebSocketHandler {
	/*
	 * 웹 소켓의 기본적인 이해를 돕기위해 만든 서버
	 * TextWebSocketHandler 상속 필요 
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		/*
		 * 이 메소드는 클라이언트가 접속하면 즉각 호출된다. 
		 * session : 접속한 사용자의 web socket 정보 (HttpSession이 아니다. )
		 */
		System.out.println("접속");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		/*
		 * 메시지 수신 시 호출되는 메소드.
		 * Session : 사용자(전송한 사람)의 웹소켓 정보. (HttpSession이 아니다. )
		 * message : 
		 * 		payload : 메시지 내용 
		 * 		byteCount : 메시지 크기 (Byte 단위) 
		 * 		last : 메시지 종료 여부 
		 */
		System.out.println("message: " + message);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 접속 종료시 호출되는 메소드
		System.out.println("접속종료");
	}
	
} 
