package com.kh.finalproject.admin.model.service;

import com.kh.finalproject.ticket.model.vo.Ticket;
import java.util.ArrayList;
import org.springframework.web.servlet.ModelAndView;

public interface AdminService {
  ArrayList<Ticket> getTicketListView();

  Ticket selectTicket(int bno);

  int getTicketNumber();

  int resolveTicket(Ticket ticket);

  ArrayList<Ticket> getResolvedTicketListView();

  int editResolvedTicket(Ticket ticket);
}
