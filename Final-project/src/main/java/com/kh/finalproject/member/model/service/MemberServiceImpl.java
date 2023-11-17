package com.kh.finalproject.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.member.model.dao.MemberDao;
import com.kh.finalproject.member.model.vo.Member;

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
<<<<<<< Updated upstream
  
  @Override
  public int idCheck(String checkId) {
		return memberDao.idCheck(sqlSession, checkId);
	}
=======
    
  @Override
    public int idCheck(String checkId) {
    	return memberDao.idCheck(sqlSession, checkId);
  }
>>>>>>> Stashed changes

  @Override
  public int setLastLogin(Member m) {
    return memberDao.setLastLogin(sqlSession, m);
  }



}
