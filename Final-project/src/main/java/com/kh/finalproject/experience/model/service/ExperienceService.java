package com.kh.finalproject.experience.model.service;

import java.util.ArrayList;

import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.experience.model.vo.Experience;

public interface ExperienceService {
	
	// 체험학습 게시판 목록 수
	int selectListCount();
	
	// 체험학습 게시판 목록 조회
	ArrayList<Experience> selectExperienceList(PageInfo pi);


}
