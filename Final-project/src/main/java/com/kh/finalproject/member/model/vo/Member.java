package com.kh.finalproject.member.model.vo;

import lombok.Data;

@Data
public class Member {

  private int memNo;
  private String memId;
  private String memPwd;
  private String memNick;
  private double memTemp;
  private String email;
  private String memImg;
  private String regBusiness;
}
