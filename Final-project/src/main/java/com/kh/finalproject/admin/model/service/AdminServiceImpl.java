package com.kh.finalproject.admin.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.finalproject.admin.model.dao.AdminDao;
import com.kh.finalproject.ticket.model.vo.Ticket;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public ArrayList<Ticket> getTicketListView() {
		return adminDao.getTicketListView(sqlSession);
	}

}
