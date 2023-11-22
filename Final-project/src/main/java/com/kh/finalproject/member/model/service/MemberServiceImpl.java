package com.kh.finalproject.member.model.service;

import com.kh.finalproject.member.model.dao.MemberDao;
import com.kh.finalproject.member.model.vo.Member;
import com.kh.finalproject.member.model.vo.NaverLogin;
import java.util.ArrayList;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {
  @Autowired private MemberDao memberDao;
  @Autowired private SqlSessionTemplate sqlSession;

  @Override
  public Member loginMember(Member m) {
    return memberDao.loginMember(sqlSession, m);
  }

  @Override
  @Transactional
  public int joinMember(Member m) {
    return memberDao.joinMember(sqlSession, m);
  }

  @Override
  public int idCheck(String checkId) {
    return memberDao.idCheck(sqlSession, checkId);
  }

  @Override
  public int setLastLogin(Member m) {
    return memberDao.setLastLogin(sqlSession, m);
  }

  @Override
  public ArrayList<Member> ajaxGetMemberList() {
    return memberDao.ajaxGetMemberList(sqlSession);
  }

  @Override
  public Member selectMember(int memNo) {
    return memberDao.selectMember(sqlSession, memNo);
  }

  @Override
  public int editMember(Member m) {
    return memberDao.editMember(sqlSession, m);
  }

  @Override
  public int addNaverProfile(NaverLogin nv) {
    return memberDao.addNaverProfile(sqlSession, nv);
  }

  @Override
  public Member selectNaverProfile(String memId) {
    return memberDao.selectNaverProfile(sqlSession, memId);
  }

  @Override
  public int addKaKaoProfile(Member m) {
    return memberDao.addKaKaoProfile(sqlSession, m);
  }
}
