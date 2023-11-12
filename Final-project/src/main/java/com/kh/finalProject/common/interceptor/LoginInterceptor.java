package com.kh.finalProject.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	
	/*
	 * Interceptor(정확히 HandlerInterceptor)
	 * 
	 * Controller가 호출되기 전, 실행된 후 가로채서 실행할 내용을 작성 가능
	 * 
	 * preHandler(전처리) : 핸들러 호출 전 낚아챔
	 * postHandler(후처리) : 요청 처리 후 낚아챔
	 * 
	 */

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// true 리턴 시 => 기존 요청 호출대로  Handler를 정상 실행(Contoroller메소드 호출)
		// false 리턴 시 => Controller 실행 X
		
		// 현재 요청을 보낸 사람이 로그인이 되어있을 경우 => Controller를 호출
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") != null) {
			return true;
		} else {
			session.setAttribute("alertMsg", "로그인을 해주세요");
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
	}
	
}
