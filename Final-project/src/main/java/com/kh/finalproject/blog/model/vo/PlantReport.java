package com.kh.finalproject.blog.model.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class PlantReport {

	private int plantReportNo;
	private int topPlantNo;
	private int plantReportcategoryNo;
	private String reportComment;
	private Date reportDate;
	private String status;
}
