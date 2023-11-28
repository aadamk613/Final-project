package com.kh.finalproject.experience.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.finalproject.common.model.vo.Files;
import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.experience.model.vo.Experience;
import com.kh.finalproject.experience.model.vo.ExperienceReply;

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

	// 사진조회는 common에 있음
	
	
	
	// 체험학습 게시글 댓글조회
	ArrayList<ExperienceReply> selectExpReplyList(int expNo);
	
	// 체험학습 게시글 작성
	int insertExperience(Experience exp, ArrayList<Files> fileList);
	
	// 체험학습 게시글 수정
	int updateExperience(Experience exp, ArrayList<Files> fileList, String[] oldFiles);
	
	
	
	// 체험학습 게시글 삭제
	int deleteExperience(int expNo);
	
	// 체험학습 게시글 댓글 작성
	int insertExpReply(ExperienceReply expReply);
	
	
	// 체험학습 게시글 좋아요 조회
	int selectExpLike(HashMap map);
	
	// 체험학습 게시글 좋아요 등록
	int insertExpLike(HashMap map);
	
	// 체험학습 게시글 좋아요 취소
	int deleteExpLike(HashMap map);
	
	
	// 체험학습 댓글 삭제
	int deleteExpReply(int expReplyNo);
	
	


}
