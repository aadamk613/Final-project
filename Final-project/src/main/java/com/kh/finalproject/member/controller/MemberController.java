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

import com.google.gson.Gson;
import com.kh.finalproject.common.model.vo.Files;
import com.kh.finalproject.member.model.service.MemberService;
import com.kh.finalproject.member.model.vo.Member;

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
  @RequestMapping("idCheck.me")
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
  @RequestMapping("loadImg.me")
  public String loadImg(String inputFile) {
	 int loadImg = memberService.loadImg(inputFile);
	 return "succes";
  }
  
  @RequestMapping("update.me")
  public String updateMember(Member m, Model model, HttpSession session) {
	  if(memberService.updateMember(m) > 0){
		  
		session.setAttribute("loginUser", memberService.loginMember(m));
			
		// session에 일회성 알라문구 띄워주기 
		session.setAttribute("alertMsg", "정보수정에 성공했습니다~~");
			
		// 마이페이지 화면이 띄워지도록~  유지보수를 용이하게 하기 위해
		return "redirect:myPage.me";
			
		} else { // 수정 실패 => 에러문구를 담아서 에러페이지로 포워딩
			model.addAttribute("errorMsg", "정보수정에 실패했습니다.");
			// /WEB-INF/views/ 		common/errorPage		.jsp
			return "common/errorPage";
		}
  }
  
  @RequestMapping("delete.me")
  public String deleteMember(String memPwd, HttpSession session) {
	  
	  Member loginUser = ((Member)session.getAttribute("loginUser"));
		
		String encPwd = ((Member)session.getAttribute("loginUser")).getMemPwd();
		// 비밃먼호가 사용자가 입력한 평문으로 만든 암호문일 경우
		if(bcryptPasswordEncoder.matches(memPwd, encPwd)) {
			
			String memId = loginUser.getMemId();
			
			if(memberService.deleteMember(memId) > 0) {
				// 탈퇴처리 성공 => session에서 loginUser지움, alert문구 담기 => 메인페이지로 잘가라고~~~~
				session.removeAttribute("loginUser");
				session.setAttribute("alertMsg", "잘가고~~~ 다신 보지말자~~~~");
				return "redirect:/";
			 } else {
				 session.setAttribute("errorMsg", "탈퇴처리 실패");
				 return "common/errorPage";
			 }
			
		} else {
			session.setAttribute("alertMsg", "비밀번호가 틀렸어요!!틀렸다구요!!!! 정말 제대로 입력한게 맞아요? 다시 확인해보세요~~~");
			return "redirect:myPage.me";
		}
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
