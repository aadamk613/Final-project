package com.kh.finalproject.board.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.finalproject.board.model.dao.BoardDao;
import com.kh.finalproject.board.model.vo.Board;
import com.kh.finalproject.board.model.vo.BoardComment;
import com.kh.finalproject.common.model.vo.Attachment;
import com.kh.finalproject.common.model.vo.PageInfo;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	
	@Override
	public int selectBoardListCount() {
		return boardDao.selectBoardCount(sqlSession);
	}

	@Override
	public ArrayList<Board> selectBoardList(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return boardDao.selectBoardList(sqlSession, rowBounds);
	}

	@Override
	public ArrayList<Board> selectBestBoardList() {
		return boardDao.selectBestBoardList(sqlSession);
	}

	@Override
	public int increaseCount(int boardNo) {
		return boardDao.increaseCount(sqlSession, boardNo);
	}
	
	@Override
	public Board selectBoard(int boardNo) {
		return boardDao.selectBoard(sqlSession, boardNo);
	}
	
	@Override
	public int insertBoard(Board b) {
		return boardDao.insertBoard(sqlSession, b);
	}

	@Override
	public int insertFile(Attachment f) {
		return boardDao.insertFile(sqlSession, f);
	}

	@Override
	public int selectLastBoardNo() {
		return boardDao.selectLastBoardNo(sqlSession);
	}
	
	@Override
	public int deleteBoard(int boardNo) {
		return boardDao.deleteBoard(sqlSession, boardNo);
	}
	
	@Override
	public int deleteFile(int boardNo) {
		return boardDao.deleteFile(sqlSession, boardNo);
	}

	@Override
	public int updateBoard(Board b) {
		return boardDao.updateBoard(sqlSession, b);
	}


	@Override
	public ArrayList<Attachment> selectFile(int boardNo) {
		return boardDao.selectFile(sqlSession, boardNo);
	}

	@Override
	public int updateFiles(Attachment f) {
		return boardDao.updateFiles(sqlSession, f);
	}

	@Override
	public ArrayList<BoardComment> selectComment(int boardNo) {
		return boardDao.selectComment(sqlSession, boardNo);
	}

}
