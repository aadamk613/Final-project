package com.kh.finalproject.admin.controller;

import com.google.gson.Gson;
import com.kh.finalproject.admin.model.service.AdminService;
import com.kh.finalproject.admin.model.vo.Hashtag;
import com.kh.finalproject.member.model.service.MemberService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AjaxAdminController {
  private final AdminService adminService;
  private final MemberService memberService;

  @Autowired
  public AjaxAdminController(AdminService adminService, MemberService memberService) {
    this.adminService = adminService;
    this.memberService = memberService;
  }

  @RequestMapping(value = "ajaxHashtagList.admin", produces = "application/json; charset=UTF-8")
  public String ajaxGetHashtagList() {
    return new Gson().toJson(adminService.getHashtagList());
  }

  @RequestMapping(value = "ajaxGetHashtag.admin", produces = "application/json; charset=UTF-8")
  public String ajaxGetHashtag() {
    ArrayList<Hashtag> list = adminService.getHashtagList();
    ArrayList<String> returnMe = new ArrayList<>();
    for (Hashtag tag : list) {
      returnMe.add("'" + tag.getTagName() + "'");
    }
    return new Gson().toJson(returnMe);
  }

  @RequestMapping(value = "ajaxSelectMember.admin", produces = "application/json; charset=UTF-8")
  public String ajaxSelectMember(int memNo) {
    return new Gson().toJson(memberService.selectMember(memNo));
  }
}
