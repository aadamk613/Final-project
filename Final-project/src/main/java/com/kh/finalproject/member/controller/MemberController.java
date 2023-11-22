package com.kh.finalproject.member.controller;

import com.google.gson.Gson;
import com.kh.finalproject.member.model.service.KakaoLoginService;
import com.kh.finalproject.member.model.service.MemberService;
import com.kh.finalproject.member.model.service.NaverLoginService;
import com.kh.finalproject.member.model.vo.Member;
import com.kh.finalproject.member.model.vo.NaverLogin;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {

  @Autowired private BCryptPasswordEncoder bcryptPasswordEncoder;
  @Autowired private MemberService memberService;
  @Autowired private NaverLoginService naverLoginService;
  @Autowired private KakaoLoginService kakaoLoginService;

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

    // System.out.println(checkId);
    int count = memberService.idCheck(checkId);
    System.out.println(count);
    return count > 0 ? "NNNNN" : "NNNNY";
  }

  @RequestMapping("myPage.me")
  public String myPage() {
    return "member/mypage";
  }

  @GetMapping("naverLogin.me")
  public String naverLogin() {
    return "member/naverLoginCallback";
  }

  /**
   * @param accessToken
   * @param session
   * @return String
   */
  @GetMapping("naverLoginCallback.me")
  public String naverLoginCallback(String accessToken, HttpSession session) throws Exception {
    String header = "Bearer " + accessToken;
    String apiURL = "https://openapi.naver.com/v1/nid/me";
    Map<String, String> requestHeaders = new HashMap<>();
    requestHeaders.put("Authorization", header);
    String profile = naverLoginService.get(apiURL, requestHeaders);
    NaverLogin nv = new NaverLogin();
    JSONParser parser = new JSONParser();
    Object obj = parser.parse(profile);
    JSONObject jsonObj = (JSONObject) obj;
    JSONObject responseObj = (JSONObject) jsonObj.get("response");
    nv.setId((String) responseObj.get("id"));
    nv.setNickname((String) responseObj.get("nickname"));
    nv.setEmail((String) responseObj.get("email"));
    nv.setProfile_image((String) responseObj.get("profile_image"));
    // 지금 로그인한 네이버 프로필이 DB에 없으면 DB에 추가
    Member loginUser = memberService.selectNaverProfile(nv.getId());
    if (loginUser == null) {
      memberService.addNaverProfile(nv);
      loginUser = memberService.selectNaverProfile(nv.getId());
    }
    memberService.setLastLogin(loginUser);
    session.setAttribute("loginUser", loginUser);
    return "redirect:/";
  }

  @GetMapping("kakaoLogin.me")
  public String kakaoLogin(String code, HttpSession session) throws Exception {
    String accessToken = kakaoLoginService.getToken(code);
    // 토큰으로 긁어온 유저 정보를 맵에 받아왔음
    Map<String, String> profileMap = kakaoLoginService.getUserInfo(accessToken);
    System.out.println("printing map" + profileMap.toString());
    // 지금 로그인한 카카오톡 프로필이 DB에 없으면 DB에 추가.
    // 네이버용으로 작성한 select문이지만 카카오 계정에도 적용가능.
    Member loginUser = memberService.selectNaverProfile(profileMap.get("id"));
    Member m = new Member();
    m.setMemId(profileMap.get("id"));
    m.setMemNick(profileMap.get("nickname"));
    m.setMemImg(profileMap.get("profile_image"));
    if (loginUser == null) {
      memberService.addKaKaoProfile(m);
      memberService.selectNaverProfile(profileMap.get("id"));
    }
    memberService.setLastLogin(loginUser);
    session.setAttribute("loginUser", loginUser);
    System.out.println(loginUser);
    return "redirect:/";
  }
}
