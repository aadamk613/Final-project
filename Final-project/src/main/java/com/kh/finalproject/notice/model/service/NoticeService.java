package com.kh.finalproject.notice.model.service;

import java.util.ArrayList;

import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.notice.model.vo.Notice;

public interface NoticeService {

	// 게시글 총 개수 조회
	// 페이징처리
	int selectNoticeListCount();
	
	// 게시글 리스트 조회
	ArrayList<Notice> selectNoticeList(PageInfo pi);
	
	// 베스트 게시글 조회
	ArrayList<Notice> selectBestNoticeList();
	
	// 게시글 작성
	int insertNotice(Notice n);
	
	// 조회수 증가
	int increaseCount(int noticeNo);
	
	// 게시글 상세보기
	Notice selectNotice(int noticeNo);
	
	// 게시글 삭제하기
	int deleteNotice(int noticeNo);
	
	// 게시글 수정하기
	int updateNotice(int noticeNo);
	
	
	
	
}
