package com.kh.finalproject.admin.model.dao;

import com.kh.finalproject.admin.model.vo.Hashtag;
import com.kh.finalproject.ticket.model.vo.Ticket;
import java.util.ArrayList;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

@Repository
public class AdminDao {

  public ArrayList<Ticket> getTicketListView(SqlSessionTemplate sqlSession) {
    return (ArrayList) sqlSession.selectList("ticketMapper.getTicketListView");
  }

  public Ticket selectTicket(SqlSessionTemplate sqlSession, int bno) {
    return sqlSession.selectOne("ticketMapper.selectTicket", bno);
  }

  public int getTicketNumber(SqlSessionTemplate sqlSession) {
    return sqlSession.selectOne("ticketMapper.getTicketNumber");
  }

  public int resolveTicket(SqlSessionTemplate sqlSession, Ticket ticket) {
    return sqlSession.update("ticketMapper.resolveTicket", ticket);
  }

  public ArrayList<Ticket> getResolvedTicketListView(SqlSessionTemplate sqlSession) {
    return (ArrayList) sqlSession.selectList("ticketMapper.getResolvedTicketListView");
  }

  public int editResolvedTicket(SqlSessionTemplate sqlSession, Ticket ticket) {
    return sqlSession.update("ticketMapper.resolveTicket", ticket);
  }

  public int deleteResolvedTicketStatus(SqlSessionTemplate sqlSession, Ticket ticket) {
    return sqlSession.update("ticketMapper.deleteResolvedStatus", ticket);
  }

  public ArrayList<Hashtag> getHashtagList(SqlSessionTemplate sqlSession) {
    return (ArrayList) sqlSession.selectList("HashtagMapper.getHashtagList");
  }
}
