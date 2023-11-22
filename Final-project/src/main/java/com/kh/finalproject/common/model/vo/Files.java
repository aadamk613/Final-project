package com.kh.finalproject.common.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Files {
  private int fileNo;
  private String originalName;
  private String updateName;
  private String filePath;
  private String refType;
  private int refNo;
  private String fileAnnotation;
  private String uploadDate;
}
