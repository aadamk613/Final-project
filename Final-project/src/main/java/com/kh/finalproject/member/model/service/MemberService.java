package com.kh.finalproject.member.model.service;

import com.kh.finalproject.member.model.vo.Member;

public interface MemberService {

  Member loginMember(Member m);
  
  Member selectMember(int memNo);
}
