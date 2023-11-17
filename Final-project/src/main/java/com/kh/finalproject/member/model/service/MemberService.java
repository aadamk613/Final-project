package com.kh.finalproject.member.model.service;

import com.google.gson.JsonElement;
import com.kh.finalproject.member.model.vo.Member;
import java.util.ArrayList;

public interface MemberService {

  Member loginMember(Member m);

  int joinMember(Member m);

  int idCheck(String checkId);

  int setLastLogin(Member m);

  ArrayList<Member> ajaxGetMemberList();
}
