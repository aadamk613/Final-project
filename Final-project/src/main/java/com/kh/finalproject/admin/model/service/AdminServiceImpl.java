package com.kh.finalproject.admin.model.service;

import com.kh.finalproject.admin.model.dao.AdminDao;
import com.kh.finalproject.ticket.model.vo.Ticket;
import java.util.ArrayList;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class AdminServiceImpl implements AdminService {

  @Autowired private AdminDao adminDao;

  @Autowired private SqlSessionTemplate sqlSession;

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
}
