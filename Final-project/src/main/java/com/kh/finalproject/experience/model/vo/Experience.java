package com.kh.finalproject.experience.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Experience {
	
//	EXP_NO	NUMBER
//	EXP_TITLE	VARCHAR2(100 BYTE)
//	EXP_WRITER	VARCHAR2(20 BYTE)
//	EXP_PEOPLE	NUMBER
//	EXP_CONTENT	VARCHAR2(2000 BYTE)
//	EXP_CREATE_DATE	DATE
//	EXP_WORK_DATE	DATE
//	EXP_WORK_TIME	NUMBER
//	EXP_END_DATE	DATE
//	EXP_STATUS	VARCHAR2(1 BYTE)
//	EXP_COUNT	NUMBER
//	EXP_UPDATE_DATE	DATE
//	EXP_AREA	VARCHAR2(100 BYTE)
//	EXP_ADDRESS	VARCHAR2(100 BYTE)
//	EXP_CATEGORY_NO	NUMBER
	
	private int expNo;
	private String expTitle;
	private String expWriter;
	private int expPeople;
	private String expContent;
	private String expCreateDate;
	private String expWorkDate;
	private String expEndDate;
	private String expStatus;
	private int expCount;
	private String expUpdateDate;
	private String expArea;
	private String expAddress;
	private int expCategoryNo;
	private String expCategoryName;
	private double expWorkTime;

}
