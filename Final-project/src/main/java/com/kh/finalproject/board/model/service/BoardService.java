package com.kh.finalproject.board.model.service;

import java.util.ArrayList;

import com.kh.finalproject.board.model.vo.Board;
import com.kh.finalproject.board.model.vo.BoardComment;
import com.kh.finalproject.board.model.vo.BoardLike;
import com.kh.finalproject.board.model.vo.BoardReport;
import com.kh.finalproject.board.model.vo.CommentReport;
import com.kh.finalproject.common.model.vo.Attachment;
import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.common.model.vo.Search;

public interface BoardService {
	// 게시글 총 개수 조회
	// 페이징처리
	int selectBoardListCount();
	
	// 게시글 리스트 조회
	ArrayList<Board> selectBoardList(PageInfo pi);
	
	// 베스트 게시글 조회
	ArrayList<Board> selectBestBoardList();
	
	// 게시글 작성
	int insertBoard(Board n);
	
	// 파일첨부
	int insertFile(Attachment f);
	
	// 조회수 증가
	int increaseCount(int boardNo);
	
	// 게시글 상세보기
	Board selectBoard(int boardNo);
	
	// 게시글 삭제하기
	int deleteBoard(int boardNo);
	
	// 게시글 수정하기
	int updateBoard(Board n);

	// 마지막번호 가져오기
	int selectLastBoardNo();
	
	// 파일 가져오기
	ArrayList<Attachment> selectFile(int boardNo);
	
	// 파일 수정
	int updateFiles(Attachment f);

	// 파일 삭제
	int deleteFile(int noticeNo);
	
	// 댓글 조회
	ArrayList<BoardComment> selectComment(int boardNo);
	
	// 게시글신고
	int insertReport(BoardReport br);
	
	// 신고조회
	ArrayList<BoardReport> selectBoardReport(int boardNo);
	
	// 댓글신고
	int insertCommentReport(CommentReport cr);
	
	// 댓글신고 조회
	ArrayList<CommentReport> selectCommentReport(CommentReport cr);

	// 댓글작성
	int insertComment (BoardComment bc);
	
	// 게시글 검색 총 개수
	int selectSearchCount(Search s);
	
	// 게시글 검색 리스트
	ArrayList<Board> selectSearchBoardList(PageInfo pi,Search s);
	
	// 해당 게시글 좋아요 조회
	BoardLike selectList(BoardLike bl);
	
	// 좋아요 insert
	int insertBoardLike(BoardLike bl);
	
	// 댓글 수 증가
	int commentCount(int boardNo);
	
	// 댓글 수정
	int updateComment(BoardComment bc);
	
	// 댓글 삭제
	int deleteComment(int commentNo);
}
