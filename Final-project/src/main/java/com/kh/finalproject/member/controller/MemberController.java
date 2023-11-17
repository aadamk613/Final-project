package com.kh.finalproject.member.controller;

import com.google.gson.Gson;
import com.kh.finalproject.member.model.service.MemberService;
import com.kh.finalproject.member.model.vo.Member;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {

  @Autowired private BCryptPasswordEncoder bcryptPasswordEncoder;
  @Autowired private MemberService memberService;

  @RequestMapping("loginForm.me")
  public String loginForm() {
    return "member/loginForm";
  }

  //  @ResponseBody
  //  @RequestMapping(value = "select.me", produces = "application/json; charset=UTF-8")
  //  public String selectMember(int memNo, HttpSession session, ModelAndView mv) {
  //    return new Gson().toJson(memberService.selectMember(memNo));
  //  }

  
  @ResponseBody
  @GetMapping(value = "getMemberList.me", produces = "application/json; charset=UTF-8")
  public String ajaxGetMemberList() {
    return new Gson().toJson(memberService.ajaxGetMemberList());
  }

  @RequestMapping("login.me")
  public ModelAndView loginMember(Member m, HttpSession session, ModelAndView mv) {
    // login 시 DB에 저장된 암호화된 암호의 솔트값을 알아내면
    // 사용자가 입력한 암호에 솔트값을 더해 암호화하면 같은 값이 나온다. 이 두 암호를 비교하여 로그인 처리
    // BCryptPasswordEncoder의 matches 메서드를 사용하면 된다. (rawpassword, encodedpassword) 를 넘기면 됨
    Member loginUser = memberService.loginMember(m);
    if (loginUser != null
        && bcryptPasswordEncoder.matches(m.getMemPwd(), loginUser.getMemPwd())) { // 성공시 :
      memberService.setLastLogin(loginUser);
      session.setAttribute("loginUser", loginUser);
      mv.setViewName("redirect:/");
    } else { // 실패시
      mv.addObject("errorMsg", "로그인 실패....").setViewName("common/errorPage"); // mv 객체는 메소드 체이닝가능
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

    if (memberService.joinMember(m) > 0) { // 성공하면 메인페이지로
      return "redirect:/";
    } else {
      model.addAttribute("errorMsg", "회원가입 실패");
      return "../common/errorPage.jsp";
    }
  }
  @ResponseBody // 포워딩 해줄게 아니라서
  @RequestMapping
  public String idCheck(String checkId) {

	  //System.out.println(checkId);
	  int count = memberService.idCheck(checkId);
	  System.out.println(count);
	  return count > 0 ? "NNNNN" : "NNNNY";
  }

  @RequestMapping("myPage.me")
  public String myPage() {
	  return "member/mypage";

    int count = memberService.idCheck(checkId);
    return count > 0 ? "NNNNN" : "NNNNY";


  }
}
