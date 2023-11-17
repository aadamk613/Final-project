package com.kh.finalproject.admin.model.service;

import com.kh.finalproject.admin.model.vo.Hashtag;
import com.kh.finalproject.ticket.model.vo.Ticket;
import java.util.ArrayList;

public interface AdminService {
  ArrayList<Ticket> getTicketListView();

  Ticket selectTicket(int bno);

  int getTicketNumber();

  int resolveTicket(Ticket ticket);

  ArrayList<Ticket> getResolvedTicketListView();

  int editResolvedTicket(Ticket ticket);

  int deleteResolvedTicketStatus(Ticket ticket);

  ArrayList<Hashtag> getHashtagList();

  int deleteHashtag(Hashtag h);

  ArrayList<String> ajaxGetHashtag();
}
