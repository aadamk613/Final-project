package com.kh.finalproject.member.model.service;

import java.util.ArrayList;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.member.model.dao.MemberDao;
import com.kh.finalproject.member.model.vo.Member;
import com.kh.finalproject.member.model.vo.NaverLogin;
import com.kh.finalproject.ticket.model.vo.Ticket;

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
  public int updateMember(Member m) {
    return memberDao.upateMember(sqlSession, m);
  }

  @Override
  public int deleteMember(String memId) {
    return memberDao.deleteMember(sqlSession, memId);
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
  public Member selectSocialProfile(String memId) {
    return memberDao.selectSocialProfile(sqlSession, memId);
  }

  @Override
  public int addKaKaoProfile(Member m) {
    return memberDao.addKaKaoProfile(sqlSession, m);
  }

  @Override
  public int addGoogleProfile(Member m) {
    return memberDao.addGoogleProfile(sqlSession, m);
  }

  @Override
  public ArrayList<Ticket> getTicketListByMemId(Member loginUser) {
    return memberDao.getTicketListByMemId(sqlSession, loginUser);
  }

  @Override
  public Ticket getTicketByTicketNo(int bno) {
    return memberDao.getTicketByTicketNo(sqlSession, bno);
  }

  @Override
  public int deleteMemberTicket(int ticketNo) {
    return memberDao.deleteMemberTicket(sqlSession, ticketNo);
  }

  @Override
  public int postNewTicket(Ticket ticket) {
    return memberDao.postNewTicket(sqlSession, ticket);
  }

  @Override
  public int editMemberTicket(Ticket ticket) {
    return memberDao.editMemberTicket(sqlSession, ticket);
  }

  @Override
  public int getAnswerNumber(Member loginUser) {
    return memberDao.getAnswerNumber(sqlSession, loginUser);
  }
  
  @Override
  public int businessPage1(Member memStatus) {
	  return memberDao.businessPage(sqlSession, memStatus);
		  
	  }
}
