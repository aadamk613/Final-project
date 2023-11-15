package com.kh.finalproject.admin.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.finalproject.ticket.model.vo.Ticket;

@Repository
public class AdminDao {

	public ArrayList<Ticket> getTicketListView(SqlSessionTemplate sqlSession) {
		return (ArrayList)sqlSession.selectList("ticketMapper.getTicketListView");
	}

}
