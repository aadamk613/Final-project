package com.kh.finalproject.board.model.vo;

import com.kh.finalproject.member.model.vo.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardReport {

  private int reportNo;
  private String reportContent;
  private String reportDate;
  private int refBoardNo;
  private String refMemberNo;
  private String memNo;
  private Member member;
}
