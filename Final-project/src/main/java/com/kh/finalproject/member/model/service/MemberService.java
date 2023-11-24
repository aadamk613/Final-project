package com.kh.finalproject.member.model.service;

import com.kh.finalproject.member.model.vo.Member;
import com.kh.finalproject.member.model.vo.NaverLogin;
import com.kh.finalproject.ticket.model.vo.Ticket;
import java.util.ArrayList;

public interface MemberService {

  Member loginMember(Member m);

  int joinMember(Member m);

  int idCheck(String checkId);

  int updateMember(Member m);

  int setLastLogin(Member m);

  ArrayList<Member> ajaxGetMemberList();

  Member selectMember(int memNo);

  int editMember(Member m);

  int addNaverProfile(NaverLogin fromJson);

  Member selectNaverProfile(String memId);

  int addKaKaoProfile(Member m);

  int loadImg(String inputFile);

  int addGoogleProfile(Member m);

  ArrayList<Ticket> getTicketListByMemId(Member loginUser);
}
