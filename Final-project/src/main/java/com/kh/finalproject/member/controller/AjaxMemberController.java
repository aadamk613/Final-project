package com.kh.finalproject.member.controller;

import com.google.gson.Gson;
import com.kh.finalproject.member.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AjaxMemberController {

  private final MemberService memberService;

  @Autowired
  public AjaxMemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping(value = "getMemberList.me", produces = "application/json; charset=UTF-8")
  public String ajaxGetMemberList() {
    return new Gson().toJson(memberService.ajaxGetMemberList());
  }

  @RequestMapping("idCheck2.me")
  public String idCheck(String checkId) {
    int count = memberService.idCheck(checkId);
    return count > 0 ? "NNNNN" : "NNNNY";
  }
}
