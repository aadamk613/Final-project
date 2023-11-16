package com.kh.finalproject.experience.model.service;

import java.util.ArrayList;

import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.experience.model.vo.Experience;

public interface ExperienceService {
	
	// 체험학습 게시판 목록 수
	int selectListCount();
	
	// 체험학습 게시판 목록 조회
	ArrayList<Experience> selectExperienceList(PageInfo pi);
	
	// 체험학습 게시글 상세 조회
	// 조회수 증가
	int increaseCount(int expNo);
	// 상세조회
	Experience selectExperience(int expNo);
	


}
