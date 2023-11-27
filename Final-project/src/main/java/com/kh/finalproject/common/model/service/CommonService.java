package com.kh.finalproject.common.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.finalproject.common.model.vo.Files;

public interface CommonService {
	
	// 첨부파일 조회
	ArrayList<Files> selectFiles(HashMap map);
	
	// 첨부파일 업데이트
	int updateFiles(Files file);

	// 첨부파일 삭제
	int deleteFiles(Files file);
	
}
