package com.kh.finalproject.member.model.dao;

import com.kh.finalproject.member.model.vo.Member;
import com.kh.finalproject.member.model.vo.NaverLogin;
import java.util.ArrayList;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {

  public Member loginMember(SqlSessionTemplate sqlSession, Member m) {
    return sqlSession.selectOne("memberMapper.loginMember", m);
  }

  public Member selectMember(SqlSessionTemplate sqlSession, int memNo) {
    return sqlSession.selectOne("memberMapper.selectMember", memNo);
  }

  public int joinMember(SqlSessionTemplate sqlSession, Member m) {
    return sqlSession.insert("memberMapper.joinMember", m);
  }

  public int idCheck(SqlSessionTemplate sqlSession, String checkId) {

    return sqlSession.selectOne("memberMapper.idCheck", checkId);
  }

  public int setLastLogin(SqlSessionTemplate sqlSession, Member m) {
    return sqlSession.update("memberMapper.setLastLogin", m);
  }

  public ArrayList<Member> ajaxGetMemberList(SqlSessionTemplate sqlSession) {
    return (ArrayList) sqlSession.selectList("memberMapper.ajaxGetMemberList");
  }

  public int editMember(SqlSessionTemplate sqlSession, Member m) {
    return sqlSession.update("memberMapper.editMember", m);
  }

  public int addNaverProfile(SqlSessionTemplate sqlSession, NaverLogin nv) {
    return sqlSession.insert("memberMapper.addNaverProfile", nv);
  }

  public Member selectNaverProfile(SqlSessionTemplate sqlSession, String memId) {
    return sqlSession.selectOne("memberMapper.selectNaverProfile", memId);
  }

  public int addKaKaoProfile(SqlSessionTemplate sqlSession, Member m) {
    return sqlSession.insert("memberMapper.addKakaoProfile", m);
  }
}
