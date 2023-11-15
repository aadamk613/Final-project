package com.kh.finalproject.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

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
		  return sqlSession.insert("memberMapper.joinMember",m);
	  }
	
	public int idCheck(SqlSessionTemplate sqlSession, String checkId) {
		return sqlSession.selectOne("memberMapper.idCheck");
	}
}
