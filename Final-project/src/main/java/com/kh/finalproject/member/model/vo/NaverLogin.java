package com.kh.finalproject.member.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NaverLogin {
  private String email;
  private String nickname;
  private String profile_image;
  private String age;
  private String gender;
  private String id;
  private String name;
  private String birthday;
  private String birthyear;
  private String mobile;
}
