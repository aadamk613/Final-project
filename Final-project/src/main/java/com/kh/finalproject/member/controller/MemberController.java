package com.kh.finalproject.member.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.Gson;
import com.kh.finalproject.member.model.service.KakaoLoginService;
import com.kh.finalproject.member.model.service.MemberService;
import com.kh.finalproject.member.model.service.NaverLoginService;
import com.kh.finalproject.member.model.vo.Member;
import com.kh.finalproject.member.model.vo.NaverLogin;
import com.kh.finalproject.ticket.model.vo.Ticket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {

  private final BCryptPasswordEncoder bcryptPasswordEncoder;
  private final MemberService memberService;
  private final NaverLoginService naverLoginService;
  private final KakaoLoginService kakaoLoginService;

  @Autowired
  public MemberController(
      BCryptPasswordEncoder bcryptPasswordEncoder,
      MemberService memberService,
      NaverLoginService naverLoginService,
      KakaoLoginService kakaoLoginService) {
    this.bcryptPasswordEncoder = bcryptPasswordEncoder;
    this.memberService = memberService;
    this.naverLoginService = naverLoginService;
    this.kakaoLoginService = kakaoLoginService;
  }

  @RequestMapping("loginForm.me")
  public String loginForm() {
    return "member/loginForm";
  }

  /**
   * loginForm method - 메인 페이지에서 로그인 버튼 클릭시 로그인 화면 페이지 리디렉션용 메소드
   *
   * @return String 리디렉션할 로그인 페이지 주소
   */
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
        && bcryptPasswordEncoder.matches(m.getMemPwd(), loginUser.getMemPwd())) { 
      memberService.setLastLogin(loginUser);
      session.setAttribute("loginUser", loginUser);
      mv.setViewName("redirect:/");
    } else { // 실패시
      mv.addObject("errorMsg", "로그인 실패....").setViewName("common/errorPage"); 
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
  public String joinMember(
      @RequestParam(name = "local-part", required = false) String localPart,
      @RequestParam(name = "domain-txt", required = false) String domain,
      Member m,
      Model model) {
    if (localPart != null && domain != null) {
      String email = localPart + "@" + domain; // 아이디와 도메인을 조합하여 이메일 주소 생성
      m.setEmail(email); // Member 객체에 이메일 설정
    } else {
      // 파라미터가 제대로 전달되지 않은 경우 처리
      model.addAttribute("errorMsg", "이메일양식 값들이 제대로 전송되지 않았습니다.");
      return "common/errorPage";
    }

    System.out.println(m);
    System.out.println("평문 : " + m.getMemPwd());

    String encPwd = bcryptPasswordEncoder.encode(m.getMemPwd());
    m.setMemPwd(encPwd); // 암호화된 비밀번호를 Member 객체에 저장하여 DB로 전송

    if (memberService.joinMember(m) > 0) {
      if ("B".equals(m.getMemStatus())) { // memStatus가 "B"인지 확인
        return "redirect:businessPage"; // businessPage.jsp로 리다이렉트
      } else {
        return "redirect:/"; // 그 외의 경우는 메인페이지로 리다이렉트
      }
    } else {
      model.addAttribute("errorMsg", "회원가입 실패.");
      return "common/errorPage";
    }
  }

  @RequestMapping("businessPage")
  public String goToBusinessPage() {
    return "member/businessPage"; // businessPage.jsp로 리다이렉트
  }

  @RequestMapping(
      value = "checkBusinessNum",
      produces = "application/json; charset=UTF-8") // 수정예정 공공API로 활용할 예정
  public String businessPageCheck(int pageNo) throws IOException {

    String url = "http://api.odcloud.kr/api/nts-businessman/v1/validate";
    url +=
        "?servicekey="
            + "XSyDrKZA66etAyknXmiWPgDRU%2BSa7u6IkO2Oc%2B3%2Bcwmnwfwdsujh1OvosKadicupI74e88WjfDF4Q0DSh%2B3%2Fxw%3D%3D";
    url += "&numOfRows=10";
    url += "&resultType=json";
    url += "&pageNo=" + pageNo;

    URL requestUrl = new URL(url);
    HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
    urlConnection.setRequestMethod("GET");
    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

    String responseText = br.readLine();
    br.close();
    urlConnection.disconnect();

    return responseText;
  }

  @RequestMapping("myPage.me")
  public ModelAndView myPage(ModelAndView mv, HttpSession session) {
    Member loginUser = (Member) session.getAttribute("loginUser");
    mv.addObject("numAnswer", memberService.getAnswerNumber(loginUser))
        .setViewName("member/mypage");
    return mv;
  }

  @RequestMapping("loadImg.me")
  public String loadImg(String inputFile) {
    int loadImg = memberService.loadImg(inputFile);
    return "succes";
  }

  @RequestMapping("update.me")
  public String updateMember(Member m, Model model, HttpSession session) {

    if (memberService.updateMember(m) > 0) {

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

    Member loginUser = ((Member) session.getAttribute("loginUser"));

    String encPwd = ((Member) session.getAttribute("loginUser")).getMemPwd();
    // 비밃먼호가 사용자가 입력한 평문으로 만든 암호문일 경우
    if (bcryptPasswordEncoder.matches(memPwd, encPwd)) {

      String memId = loginUser.getMemId();

      if (memberService.deleteMember(memId) > 0) {
        // 탈퇴처리 성공 => session에서 loginUser지움, alert문구 담기 => 메인페이지로 잘가라고~~~~
        session.removeAttribute("loginUser");
        session.setAttribute("alertMsg", "안녕히 가세요");
        return "redirect:/";
      } else {
        session.setAttribute("errorMsg", "탈퇴처리 실패");
        return "common/errorPage";
      }

    } else {
      session.setAttribute("alertMsg", "비밀번호가 틀렸어요. 다시 확인해보세요~~~");
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
    Member loginUser = memberService.selectSocialProfile(nv.getId());
    if (loginUser == null) {
      memberService.addNaverProfile(nv);
      loginUser = memberService.selectSocialProfile(nv.getId());
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
    // 지금 로그인한 카카오톡 프로필이 DB에 없으면 DB에 추가.
    Member loginUser = memberService.selectSocialProfile(profileMap.get("id"));
    Member m = new Member();
    m.setMemId(profileMap.get("id"));
    m.setMemNick(profileMap.get("nickname"));
    m.setMemImg(profileMap.get("profile_image"));
    if (loginUser == null) {
      memberService.addKaKaoProfile(m);
      memberService.selectSocialProfile(profileMap.get("id"));
    }
    memberService.setLastLogin(loginUser);
    session.setAttribute("loginUser", loginUser);
    return "redirect:/";
  }

  @RequestMapping("googleLogin.me")
  public String googleLogin(String credential, HttpSession session) throws Exception {
    HttpTransport transport = new NetHttpTransport();
    JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
    GoogleIdTokenVerifier verifier =
        new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
            // Specify the CLIENT_ID of the app that accesses the backend:
            .setAudience(
                Collections.singletonList(
                    "347219723290-po60vc83hnfdh6pac0tfgufblgqmvvbk.apps.googleusercontent.com"))
            // Or, if multiple clients access the backend:
            // .setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
            .build();
    GoogleIdToken idToken = verifier.verify(credential);
    if (idToken != null) {
      Payload payload = idToken.getPayload();
      // Print user identifier; userId = unique identifier
      String userId = payload.getSubject();
      // Get profile information from payload
      String email = payload.getEmail();
      String name = (String) payload.get("name");
      String pictureUrl = (String) payload.get("picture");

      Member loginUser = memberService.selectSocialProfile(userId);
      Member m = new Member();
      m.setMemId(userId);
      m.setMemNick(name);
      m.setMemImg(pictureUrl);
      m.setEmail(email);
      if (loginUser == null) {
        memberService.addGoogleProfile(m);
        loginUser = memberService.selectSocialProfile(userId);
      }
      memberService.setLastLogin(loginUser);
      session.setAttribute("loginUser", loginUser);
      System.out.println(loginUser);
    }
    return "redirect:/";
  }

  @GetMapping("memberTicket.me")
  public ModelAndView memberTicket(HttpSession session, ModelAndView mv) {
    Member loginUser = (Member) session.getAttribute("loginUser");
    mv.addObject("numAnswer", memberService.getAnswerNumber(loginUser));
    mv.addObject("list", memberService.getTicketListByMemId(loginUser))
        .setViewName("member/memberTicketView");
    return mv;
  }

  @GetMapping("memberTicketDetailView.me")
  public ModelAndView memberTicketDetailView(int bno, ModelAndView mv) {
    mv.addObject("ticket", memberService.getTicketByTicketNo(bno))
        .setViewName("member/memberTicketDetailView");
    return mv;
  }

  @PostMapping("deleteMemberTicket.me")
  public ModelAndView deleteMemberTicket(HttpSession session, ModelAndView mv, int ticketNo) {
    if (memberService.deleteMemberTicket(ticketNo) > 0) {
      session.setAttribute("alertMsg", "작성한 문의가 성공적으로 삭제되었습니다!");
    } else {
      mv.addObject("errorMsg", "관리자 문의 삭제 실패!");
    }
    Member loginUser = (Member) session.getAttribute("loginUser");
    mv.addObject("list", memberService.getTicketListByMemId(loginUser))
        .setViewName("member/memberTicketView");
    return mv;
  }

  @GetMapping("addTicket.me")
  public String addTicket() {
    return "member/memberTicketPostForm";
  }

  @PostMapping("postNewTicket.me")
  public ModelAndView postNewTicket(ModelAndView mv, Ticket ticket, HttpSession session) {
    Member loginUser = (Member) session.getAttribute("loginUser");
    ticket.setTicketWriter(loginUser.getMemId());
    if (memberService.postNewTicket(ticket) > 0) {
      session.setAttribute("alertMsg", "관리자 문의를 성공적으로 작성하였습니다!");
    } else {
      mv.addObject("errorMsg", "관리자 문의 작성 실패!");
    }
    mv.addObject("list", memberService.getTicketListByMemId(loginUser))
        .setViewName("member/memberTicketView");
    return mv;
  }

  @PostMapping("editMemberTicketView.me")
  public ModelAndView editMemberTicketView(ModelAndView mv, Ticket ticket) {
    mv.addObject("ticket", ticket).setViewName("member/memberTicketEditForm");
    return mv;
  }

  @PostMapping("editMemberTicket.me")
  public ModelAndView editMemberTicket(ModelAndView mv, Ticket ticket, HttpSession session) {
    Member loginUser = (Member) session.getAttribute("loginUser");
    if (memberService.editMemberTicket(ticket) > 0) {
      session.setAttribute("alertMsg", "문의 수정 성공!");
    } else {
      mv.addObject("alertMsg", "문의 수정 실패!");
    }
    mv.addObject("list", memberService.getTicketListByMemId(loginUser))
        .setViewName("member/memberTicketView");
    return mv;
  }
}
