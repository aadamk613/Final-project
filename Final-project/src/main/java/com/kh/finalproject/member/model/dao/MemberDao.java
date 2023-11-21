package com.kh.finalproject.member.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.finalproject.common.model.vo.Files;
import com.kh.finalproject.member.model.vo.Member;

@Repository
public class MemberDao {

  public Member loginMember(SqlSessionTemplate sqlSession, Member m) {
    return sqlSession.selectOne("memberMapper.loginMember", m);
  }

  public Member selectMember(SqlSessionTemplate sqlSession, int memNo) {
    Member m = sqlSession.selectOne("memberMapper.selectMember", memNo);
    System.out.println(m);
    return m;
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

  @SuppressWarnings("unchecked")
  public ArrayList<Member> ajaxGetMemberList(SqlSessionTemplate sqlSession) {
    return (ArrayList) sqlSession.selectList("memberMapper.ajaxGetMemberList");
  }
  
  public int loadImg(SqlSessionTemplate sqlSession, String inputFile) {
	return sqlSession.update("memberMapper.loadImg");
	  
  }
}
