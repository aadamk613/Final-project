package com.kh.finalproject.admin.controller;

import com.kh.finalproject.admin.model.service.AdminService;
import com.kh.finalproject.admin.model.vo.Hashtag;
import com.kh.finalproject.member.model.service.MemberService;
import com.kh.finalproject.member.model.vo.Member;
import com.kh.finalproject.ticket.model.vo.Ticket;
import java.util.Arrays;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {
  private final AdminService adminService;
  private final MemberService memberService;

  /**
   * @param mv
   * @return ModelAndView
   */
  @GetMapping("main")
  public ModelAndView mainView(ModelAndView mv) {
    mv.addObject("numTicket", adminService.getTicketNumber()).setViewName("admin/adminMainView");
    return mv;
  }

  @GetMapping("ticket")
  public ModelAndView ticketView(ModelAndView mv) {
    mv.addObject("list", adminService.getTicketListView()).setViewName("admin/adminTicketView");
    mv.addObject("numTicket", adminService.getTicketNumber());
    log.trace(null);
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

  @GetMapping("memberView.admin")
  public ModelAndView memberView(ModelAndView mv) {
    mv.addObject("numTicket", adminService.getTicketNumber()).setViewName("admin/adminMemberView");
    return mv;
  }

  @GetMapping("hashtag.admin")
  public ModelAndView hashtagView(ModelAndView mv) {
    mv.addObject("numTicket", adminService.getTicketNumber()).setViewName("admin/adminHashtag");
    return mv;
  }

  /**
   * deleteHashtag method : this method deletes the specified hashtags.
   *
   * @param chk the array of integer contains value of tagNo of the hashtag to be deleted
   * @param m the Model object to add message attributes
   * @return String the mapping value for redirection
   */
  @DeleteMapping("deleteHashtags.admin")
  public String deleteHashtag(int[] chk, Model m) {
    log.info(Arrays.toString(chk));
    int result = 0;
    for (int i = 0; i < chk.length; i++) {
      Hashtag h = new Hashtag();
      h.setTagNo(chk[i]);
      if (adminService.deleteHashtag(h) > 0) {
        result += 1;
      }
    }
    if (result > 1) {
      m.addAttribute("alertMsg", "해시태그를 성공적으로 삭제하였습니다!");
    } else {
      m.addAttribute("alertMsg", "해시태그 삭제 실패하였습니다!");
    }
    return "redirect:hashtag.admin";
  }

  @PostMapping("addHashtag.admin")
  public String addHashtag(Hashtag h, Model m) {
    if (adminService.addHashtag(h) > 0) {
      m.addAttribute("alertMsg", "해시태그를 성공적으로 추가하였습니다!");
    } else {
      m.addAttribute("alertMsg", "해시태그 추가 실패!!!");
    }
    return "redirect:hashtag.admin";
  }

  @PutMapping("editMember.admin")
  public ModelAndView editMember(Member m, ModelAndView mv, HttpSession session) {
    mv.addObject("numTicket", adminService.getTicketNumber()).setViewName("admin/adminMemberView");
    if (memberService.editMember(m) > 0) {
      session.setAttribute("alertMsg", "회원 정보를 성공적으로 수정하였습니다!");
    } else {
      session.setAttribute("errorMsg", "회원 정보 수정 실패!");
    }
    return mv;
  }

  @GetMapping("reportedArticleView.admin")
  public ModelAndView reportedArticleView(ModelAndView mv) {
    mv.addObject("numTicket", adminService.getTicketNumber())
        .setViewName("admin/adminReportedBoard");
    return mv;
  }
}
