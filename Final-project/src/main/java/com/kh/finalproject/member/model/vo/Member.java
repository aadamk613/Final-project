package com.kh.finalproject.member.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member {

  private int memNo;
  private String memId;
  private String memPwd;
  private String memNick;
  private double memTemp;
  private String email;
  private String memImg;
  private String regBusiness;
  private String memStatus;
  private int blogNo;
  private String lastLogin;
  private String blogAddress;
  private String introduceMyself;
}
