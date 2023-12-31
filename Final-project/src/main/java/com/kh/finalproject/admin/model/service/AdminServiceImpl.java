package com.kh.finalproject.admin.model.service;

import com.kh.finalproject.admin.model.dao.AdminDao;
import com.kh.finalproject.admin.model.vo.Hashtag;
import com.kh.finalproject.board.model.vo.BoardReport;
import com.kh.finalproject.ticket.model.vo.Ticket;
import java.util.ArrayList;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

  private final AdminDao adminDao;
  private final SqlSessionTemplate sqlSession;

  @Autowired
  public AdminServiceImpl(AdminDao adminDao, SqlSessionTemplate sqlSession) {
    this.adminDao = adminDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public ArrayList<Ticket> getTicketListView() {
    return adminDao.getTicketListView(sqlSession);
  }

  @Override
  public Ticket selectTicket(int bno) {
    return adminDao.selectTicket(sqlSession, bno);
  }

  @Override
  public int getTicketNumber() {
    return adminDao.getTicketNumber(sqlSession);
  }

  @Override
  public int resolveTicket(Ticket ticket) {
    return adminDao.resolveTicket(sqlSession, ticket);
  }

  @Override
  public ArrayList<Ticket> getResolvedTicketListView() {
    return adminDao.getResolvedTicketListView(sqlSession);
  }

  @Override
  public int editResolvedTicket(Ticket ticket) {
    return adminDao.editResolvedTicket(sqlSession, ticket);
  }

  @Override
  public int deleteResolvedTicketStatus(Ticket ticket) {
    return adminDao.deleteResolvedTicketStatus(sqlSession, ticket);
  }

  @Override
  public ArrayList<Hashtag> getHashtagList() {
    return adminDao.getHashtagList(sqlSession);
  }

  @Override
  public int deleteHashtag(Hashtag h) {
    return adminDao.deleteHashtag(sqlSession, h);
  }

  @Override
  public int addHashtag(Hashtag h) {
    return adminDao.addHashtag(sqlSession, h);
  }

  @Override
  public ArrayList<BoardReport> selectReportedArticles() {
    return adminDao.selectReportedArticles(sqlSession);
  }
}
