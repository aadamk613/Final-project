package com.kh.finalproject.blog.model.vo;


import java.util.List;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class Plant {

	private int plantNo;
	private Integer blogNo;
	private String plantName;
	private String plantNickName;
	private String plantComment;
	private Date createDate;
	private String status;
	private int view;
	private String plantCategory;
	private String plantLogDate;
	
	private String filePath;
	private String updateName;
	
	private List<PlantReport> plantReport;
	
}
