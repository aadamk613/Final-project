package com.kh.finalproject.admin.controller;

import com.kh.finalproject.admin.model.service.AdminService;
import com.kh.finalproject.ticket.model.vo.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

  @Autowired private AdminService adminService;

  @GetMapping("main.admin")
  public ModelAndView mainView(ModelAndView mv) {
    mv.addObject("numTicket", adminService.getTicketNumber()).setViewName("admin/adminMainView");
    return mv;
  }

  @GetMapping("ticket.admin")
  public ModelAndView ticketView(ModelAndView mv) {
    mv.addObject("list", adminService.getTicketListView()).setViewName("admin/adminTicketView");
    mv.addObject("numTicket", adminService.getTicketNumber());
    return mv;
  }

  @GetMapping("ticketDetailView.admin")
  public ModelAndView ticketDetailView(int bno, ModelAndView mv) {
    mv.addObject("ticket", adminService.selectTicket(bno))
        .setViewName("admin/adminTicketDetailView");
    mv.addObject("numTicket", adminService.getTicketNumber());
    return mv;
  }

  @GetMapping("resolvedTicket.admin")
  public ModelAndView resolvedTicketView(ModelAndView mv) {
    mv.addObject("list", adminService.getResolvedTicketListView())
        .setViewName("admin/adminResolvedTicketView");
    mv.addObject("numTicket", adminService.getTicketNumber());
    return mv;
  }

  @PostMapping("resolveTicket.admin")
  public String resolveTicket(Ticket ticket, Model m) {
    if (adminService.resolveTicket(ticket) > 0) {
      m.addAttribute("alertMsg", "답변 등록에 성공하였습니다!");
    } else {
      m.addAttribute("errorMsg", "답변 등록에 실패하였습니다!");
    }
    return "redirect:ticket.admin";
  }

  @PostMapping("editResolvedTicket.admin")
  public String editResolvedTicket(Ticket ticket, Model m) {
    if (adminService.editResolvedTicket(ticket) > 0) {
      m.addAttribute("alertMsg", "답변 등록에 성공하였습니다!");
    } else {
      m.addAttribute("errorMsg", "답변 등록에 실패하였습니다!");
    }
    return "redirect:resolvedTicket.admin";
  }

  @GetMapping("ticketEditView.admin")
  public ModelAndView editResolvedTicketView(Ticket ticket, int bno, ModelAndView mv) {
    mv.addObject("ticket", adminService.selectTicket(bno)).setViewName("admin/adminTicketEditView");
    mv.addObject("numTicket", adminService.getTicketNumber());
    return mv;
  }

  @PostMapping("deleteResolvedTicketStatus.admin")
  public String deleteResolvedTicketStatus(Ticket ticket, Model m) {
    if (adminService.deleteResolvedTicketStatus(ticket) > 0) {
      m.addAttribute("alertMsg", "답변을 성공적으로 삭제하였습니다!");
    } else {
      m.addAttribute("errorMsg", "답변 삭제에 실패하였습니다!");
    }
    return "redirect:resolvedTicket.admin";
  }
}
