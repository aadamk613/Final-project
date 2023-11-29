package com.kh.finalproject.member.model.dao;

import com.kh.finalproject.member.model.vo.Member;
import com.kh.finalproject.member.model.vo.NaverLogin;
import com.kh.finalproject.ticket.model.vo.Ticket;
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

  public int upateMember(SqlSessionTemplate sqlSession, Member m) {
    return sqlSession.update("memberMapper.updateMember", m);
  }

  public int deleteMember(SqlSessionTemplate sqlSession, String memId) {
    // System.out.print(memId);
    return sqlSession.delete("memberMapper.deleteMember", memId);
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

  public Member selectSocialProfile(SqlSessionTemplate sqlSession, String memId) {
    return sqlSession.selectOne("memberMapper.selectSocialProfile", memId);
  }

  public int loadImg(SqlSessionTemplate sqlSession, String inputFile) {
    return sqlSession.update("memberMapper.loadImg");
  }

  public int addKaKaoProfile(SqlSessionTemplate sqlSession, Member m) {
    return sqlSession.insert("memberMapper.addKakaoProfile", m);
  }

  public int addGoogleProfile(SqlSessionTemplate sqlSession, Member m) {
    return sqlSession.insert("memberMapper.addGoogleProfile", m);
  }

  public ArrayList<Ticket> getTicketListByMemId(SqlSessionTemplate sqlSession, Member loginUser) {
    return (ArrayList) sqlSession.selectList("ticketMapper.getTicketListByMemId", loginUser);
  }

  public Ticket getTicketByTicketNo(SqlSessionTemplate sqlSession, int bno) {
    return sqlSession.selectOne("ticketMapper.selectTicket", bno);
  }

  public int deleteMemberTicket(SqlSessionTemplate sqlSession, int ticketNo) {
    return sqlSession.delete("ticketMapper.deleteTicket", ticketNo);
  }

  public int postNewTicket(SqlSessionTemplate sqlSession, Ticket ticket) {
    return sqlSession.insert("ticketMapper.insertTicket", ticket);
  }

  public int editMemberTicket(SqlSessionTemplate sqlSession, Ticket ticket) {
    return sqlSession.update("ticketMapper.editMemberTicket", ticket);
  }

  public int getAnswerNumber(SqlSessionTemplate sqlSession, Member loginUser) {
    return sqlSession.selectOne("ticketMapper.getAnswerNumber", loginUser);
  }
  
  public int businessPage(SqlSessionTemplate sqlSession, Member memStatus) {
	  return sqlSession.selectOne("memberMapper.businessPage", "B");
  }
}
