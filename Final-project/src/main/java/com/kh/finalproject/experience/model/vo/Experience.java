package com.kh.finalproject.experience.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @ToString @AllArgsConstructor
public class Experience {

	private int expNo;
	private String expTitle;
	private String expWriter;
	private int expPeople;
	private String expContent;
	private String expCreateDate;
	private String expWorkDate;
	private double expWorkTime;
	private String expEndDate;
	private String expStatus;
	private int expCount;
	private String expUpdateDate;
	private String expArea;
	private String expAddress;
	private int expCategoryNo;
	private String expCategoryName;
	private long expLikeCount;
	private long expReplyCount;
	private long expSupportCount;

	

}
