package com.kh.finalproject.common.model.vo;


import lombok.Data;


@Data 

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


