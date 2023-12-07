package com.kh.finalproject.common.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.finalproject.common.model.vo.Attachment;

public interface CommonService {
	
	// 첨부파일 조회
	ArrayList<Attachment> selectFiles(HashMap map);
	
	// 첨부파일 업데이트
	int updateFiles(Attachment file);

	// 첨부파일 삭제
	int deleteFiles(Attachment file);
	
	// 첨부파일 수정
	int updateAttachment(Attachment file);
	
}
