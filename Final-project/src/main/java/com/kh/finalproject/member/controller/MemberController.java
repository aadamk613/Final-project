package com.kh.finalproject.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.finalproject.member.model.service.MemberService;
import com.kh.finalproject.member.model.vo.Member;

@Controller
public class MemberController {

  @Autowired private BCryptPasswordEncoder bcryptPasswordEncoder;
  @Autowired private MemberService memberService;

  @RequestMapping("loginForm.me")
  public String loginForm() {
    return "member/loginForm";
  }
  
  @ResponseBody
  @RequestMapping(value="select.me", produces="application/json; charset=UTF-8")
  public String selectMember(int memNo, HttpSession session,ModelAndView mv) {
	  return new Gson().toJson(memberService.selectMember(memNo));
	  
  }

  @RequestMapping("insert.me")
  public String insertMember(Member m, Model model) {
    
    // System.out.println("평문" + m.getUserPwd());
    // String encPwd = bcryptPasswordEncoder.encode(m.getUserPwd());
    // System.out.println("encrypted: " + encPwd);
    // // m.setUserPwd(encPwd); // member 객체의 userPwd 필드에 평문이 아닌 암호문을 담아서 DB로 보내기!
    // if (memberService.insertMember(m) > 0) { // 성공 =>  메인페이지 리디렉션
    //   return "redirect:/";
    // } else { // 실패 => 에러메시지 담아서 에러페이지로 포워딩
    //   model.addAttribute("errorMsg", "회원가입 실패");
    //   return "common/errorPage";
    return null;
  }

  @RequestMapping("login.me")
  public ModelAndView loginMember(Member m, HttpSession session, ModelAndView mv) {
    // login 시 DB에 저장된 암호화된 암호의 솔트값을 알아내면
    // 사용자가 입력한 암호에 솔트값을 더해 암호화하면 같은 값이 나온다. 이 두 암호를 비교하여 로그인 처리
    // BCryptPasswordEncoder의 matches 메서드를 사용하면 된다. (rawpassword, encodedpassword) 를 넘기면 됨
    Member loginUser = memberService.loginMember(m);
    if (loginUser != null
        && bcryptPasswordEncoder.matches(m.getMemPwd(), loginUser.getMemPwd())) { // 성공시 :
      session.setAttribute("loginUser", loginUser);
      mv.setViewName("redirect:/");
    } else { // 실패시
      mv.addObject("errorMsg", "로그인 실패....").setViewName("common/errorPage"); // mv 객체는 메소드 체이닝가능
      mv.setViewName("common/errorPage");
    }
    return mv;
  }

  @RequestMapping("logout.me")
  public String logoutMember(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }
  
  @RequestMapping("joinForm.me")
  public String joinForm() {
	  return "member/memberJoinForm";
  }
  
  @RequestMapping("join.me")
  public String joinMember(Member m, Model model) {
	  System.out.println(m);
	  System.out.println("평문 : " + m.getMemPwd());
	  
	  String encPwd = bcryptPasswordEncoder.encode(m.getMemPwd());
	  
	  m.setMemPwd(encPwd); // Member객체의 MemPwd 필드에 평문이 아닌 암호문을 담아서 DB로 보내기
	  
	  if(memberService.joinMember(m) > 0) { // 성공하면 메인페이지로
		  return "redirect:/";
	  } else {
		  model.addAttribute("errorMsg", "회원가입 실패");
		  return "../common/errorPage.jsp";
	  }
	  
  }
}
