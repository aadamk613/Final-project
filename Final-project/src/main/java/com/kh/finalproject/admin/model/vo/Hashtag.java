package com.kh.finalproject.admin.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class Hashtag {
	private int tagNo;
	private String tagName;
	private String tagDate;
	private int tagUsage;
}
