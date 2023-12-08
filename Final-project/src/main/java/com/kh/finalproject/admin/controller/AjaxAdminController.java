package com.kh.finalproject.admin.controller;

import com.google.gson.Gson;
import com.kh.finalproject.admin.model.service.AdminService;
import com.kh.finalproject.admin.model.vo.Hashtag;
import com.kh.finalproject.member.model.service.MemberService;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AjaxAdminController {
  private final AdminService adminService;
  private final MemberService memberService;

  @GetMapping(value = "ajaxHashtagList.admin", produces = "application/json; charset=UTF-8")
  public String ajaxGetHashtagList() {
    return new Gson().toJson(adminService.getHashtagList());
  }

  @GetMapping(value = "ajaxGetHashtag.admin", produces = "application/json; charset=UTF-8")
  public String ajaxGetHashtag() {
    ArrayList<Hashtag> list = adminService.getHashtagList();
    ArrayList<String> returnMe = new ArrayList<>();
    for (Hashtag tag : list) {
      returnMe.add(tag.getTagName());
    }
    return new Gson().toJson(returnMe);
  }

  @GetMapping(value = "ajaxSelectMember.admin", produces = "application/json; charset=UTF-8")
  public String ajaxSelectMember(int memNo) {
    return new Gson().toJson(memberService.selectMember(memNo));
  }

  @GetMapping(value = "ajaxReportedArticles.admin", produces = "application/json; charset=UTF-8")
  public String selectReportedArticles() {
    return new Gson().toJson(adminService.selectReportedArticles());
  }
}
