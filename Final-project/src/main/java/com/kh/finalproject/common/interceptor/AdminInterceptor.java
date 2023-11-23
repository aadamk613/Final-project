package com.kh.finalproject.common.interceptor;

import com.kh.finalproject.member.model.vo.Member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    // 현재 로그인한 유저가 admin인 경우
    HttpSession session = request.getSession();
    if (session.getAttribute("loginUser") != null) {
      if (((Member) session.getAttribute("loginUser")).getMemStatus().equals("A")) {
        return true;
      } else {
        session.setAttribute("alertMsg", "관리자만 이용가능한 페이지입니다.");
        response.sendRedirect(request.getContextPath());
        return false;
      }
    } else {
      session.setAttribute("alertMsg", "로그인 후 이용가능합니다.");
      response.sendRedirect(request.getContextPath());
      return false;
    }
  }
}
