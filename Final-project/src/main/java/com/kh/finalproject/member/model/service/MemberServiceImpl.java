package com.kh.finalproject.member.model.service;

import com.kh.finalproject.member.model.dao.MemberDao;
import com.kh.finalproject.member.model.vo.Member;
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
  public int loadImg(String inputFile) {
	return memberDao.loadImg(sqlSession, inputFile);
}


  @Override
	public int updateMember(Member m) {
		return memberDao.updateMember(sqlSession, m);
	}
}

