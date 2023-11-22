package com.kh.finalproject.board.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.finalproject.board.model.dao.BoardDao;
import com.kh.finalproject.board.model.vo.Board;
import com.kh.finalproject.common.model.vo.Files;
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
	public int insertBoard(Board n) {
		return 0;
	}

	@Override
	public int insertFile(Files f) {
		return 0;
	}

	@Override
	public int increaseCount(int boardNo) {
		return 0;
	}

	@Override
	public Board selectBoard(int boardNo) {
		return null;
	}

	@Override
	public int deleteBoard(int boardNo) {
		return 0;
	}

	@Override
	public int updateBoard(Board n) {
		return 0;
	}

	@Override
	public int selectLastBoardNo() {
		return 0;
	}

	@Override
	public ArrayList<Files> selectFile(int boardNo) {
		return null;
	}

	@Override
	public int updateFiles(Files f) {
		return 0;
	}

}
