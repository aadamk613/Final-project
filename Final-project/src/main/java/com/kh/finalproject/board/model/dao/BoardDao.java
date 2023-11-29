package com.kh.finalproject.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.finalproject.board.model.vo.Board;
import com.kh.finalproject.board.model.vo.BoardComment;

import com.kh.finalproject.common.model.vo.Attachment;

import com.kh.finalproject.board.model.vo.BoardReport;
import com.kh.finalproject.board.model.vo.CommentReport;

@Repository
public class BoardDao {

	public int selectBoardCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("boardMapper.selectBoardCount");
	}
	
	public ArrayList<Board> selectBoardList(SqlSessionTemplate sqlSession, RowBounds rowBouds) {
		return (ArrayList)sqlSession.selectList("boardMapper.selectBoardList", null, rowBouds);
	}


	public ArrayList<Board> selectBestBoardList(SqlSessionTemplate sqlSession) {
		return (ArrayList)sqlSession.selectList("boardMapper.selectBestBoard");
	}

	public int increaseCount(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.update("boardMapper.increaseCount", boardNo);
	}

	public Board selectBoard(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.selectBoard", boardNo);
	}

	public int selectLastBoardNo(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("boardMapper.selectLastBoardNo");
	}

	public int insertFile(SqlSessionTemplate sqlSession, Attachment f) {
		return sqlSession.insert("boardMapper.insertFile", f);
	}

	public int insertBoard(SqlSessionTemplate sqlSession, Board b) {
		return sqlSession.insert("boardMapper.insertBoard", b);
	}

	public ArrayList<Attachment> selectFile(SqlSessionTemplate sqlSession, int boardNo) {
		return (ArrayList)sqlSession.selectList("boardMapper.selectFile", boardNo);
	}

	public int deleteFile(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.delete("boardMapper.deleteFile", boardNo);
	}

	public int deleteBoard(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.update("boardMapper.deleteBoard", boardNo);
	}

	public int updateBoard(SqlSessionTemplate sqlSession, Board b) {
		return sqlSession.update("boardMapper.updateBoard", b);
	}

	public int updateFiles(SqlSessionTemplate sqlSession, Attachment f) {
		return sqlSession.update("noticeMapper.updateFiles", f);
	}

	public ArrayList<BoardComment> selectComment(SqlSessionTemplate sqlSession, int boardNo) {
		return (ArrayList)sqlSession.selectList("boardMapper.selectComment", boardNo);
	}

	public int insertReport(SqlSessionTemplate sqlSession, BoardReport br) {
		return sqlSession.insert("boardMapper.insertReport", br);
	}
	
	public ArrayList<BoardReport> selectBoardReport(SqlSessionTemplate sqlSession, int boardNo) {
		return (ArrayList)sqlSession.selectList("boardMapper.selectBoardReport", boardNo);
	}

	public int insertCommentReport(SqlSessionTemplate sqlSession, CommentReport cr) {
		return sqlSession.insert("boardMapper.insertCommentReport", cr);
	}

	public ArrayList<CommentReport> selectCommentReport(SqlSessionTemplate sqlSession, CommentReport cr) {
		return (ArrayList)sqlSession.selectList("boardMapper.selectCommentReport", cr);
	}


}
