package com.kh.finalproject.member.model.service;

import com.kh.finalproject.member.model.vo.Member;

public interface MemberService {

  Member loginMember(Member m);

  int joinMember(Member m);

  int setLastLogin(Member m);
}
