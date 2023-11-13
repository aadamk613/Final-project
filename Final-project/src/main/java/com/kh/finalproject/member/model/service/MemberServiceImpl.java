package com.kh.finalproject.member.model.service;

import com.kh.finalproject.member.model.dao.MemberDao;
import com.kh.finalproject.member.model.vo.Member;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
  @Autowired private MemberDao memberDao;
  @Autowired private SqlSessionTemplate sqlSession;

  @Override
  public Member loginMember(Member m) {
    return memberDao.loginMember(sqlSession, m);
  }
}
