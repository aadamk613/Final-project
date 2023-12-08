package com.kh.finalproject.common.model.vo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Attachment {
  private int fileNo;
  private String originalName;
  private String updateName;
  private String filePath;
  private String refType;
  private int refNo;
  private String fileAnnotation;
  private Date uploadDate;
}
