package com.kh.finalproject.member.model.service;

import com.google.gson.JsonElement;
import com.kh.finalproject.member.model.vo.Member;
import com.kh.finalproject.member.model.vo.NaverLogin;
import java.util.ArrayList;
import java.util.Map;

public interface MemberService {

  Member loginMember(Member m);

  int joinMember(Member m);

  int idCheck(String checkId);

  int setLastLogin(Member m);

  ArrayList<Member> ajaxGetMemberList();

  Member selectMember(int memNo);

  int editMember(Member m);

  int addNaverProfile(NaverLogin fromJson);

  Member selectNaverProfile(String memId);
}
