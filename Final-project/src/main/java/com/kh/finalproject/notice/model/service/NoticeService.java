package com.kh.finalproject.notice.model.service;

import java.util.ArrayList;

import com.kh.finalproject.common.model.vo.Attachment;
import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.notice.model.vo.Notice;
import com.kh.finalproject.notice.model.vo.NoticeLike;

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
	
	// 파일첨부
	int insertFile(Attachment f);
	
	// 조회수 증가
	int increaseCount(int noticeNo);
	
	// 게시글 상세보기
	Notice selectNotice(int noticeNo);
	
	// 게시글 삭제하기
	int deleteNotice(int noticeNo);
	
	// 파일 삭제
	int deleteFile(int noticeNo);
	
	// 게시글 수정하기
	int updateNotice(Notice n);

	// 마지막번호 가져오기
	int selectLastNoticeNo();
	
	// 파일 가져오기
	ArrayList<Attachment> selectFile(int noticeNo);
	
	// 파일 수정
	int updateFiles(Attachment f);
	
	// 좋아요 검색
    NoticeLike selectNoticeLike(NoticeLike nl);
	
	// 좋아요 삽입
	int insertNoticeLike(NoticeLike nl);

	// 좋아요 수정
	int updateNoticeLike(NoticeLike nl);

	// 좋아요 삭제
	int deleteNoticeLike(NoticeLike nl);
	
	// 좋아요 수 증가
	int plusNoticeLikeCount(int boardNo);
	
	// 종아요 수 감소
	int minusNoticeLikeCount(int boardNo);

}
