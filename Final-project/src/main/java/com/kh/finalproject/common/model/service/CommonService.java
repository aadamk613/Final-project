package com.kh.finalproject.common.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.finalproject.common.model.vo.Files;

public interface CommonService {
	
	// 첨부파일조회
	ArrayList<Files> selectFiles(HashMap map);
	
	// 첨부파일등록
	

}
