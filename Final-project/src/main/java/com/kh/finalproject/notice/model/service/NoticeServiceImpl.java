package com.kh.finalproject.notice.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.notice.model.dao.NoticeDao;
import com.kh.finalproject.notice.model.vo.Notice;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int selectNoticeListCount() {
		return noticeDao.selectNoticeCount(sqlSession);
	}

	@Override
	public ArrayList<Notice> selectNoticeList(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return noticeDao.selectNoticeList(sqlSession, rowBounds);
	}
	
	@Override
	public ArrayList<Notice> selectBestNoticeList() {
		return noticeDao.selectBestNoticeList(sqlSession);
	}
	
	
	@Override
	public int insertNotice(Notice n) {
		return noticeDao.insertNotice(sqlSession, n);
	}

	@Override
	public int increaseCount(int noticeNo) {
		return 0;
	}

	@Override
	public Notice selectNotice(int noticeNo) {
		return null;
	}

	@Override
	public int deleteNotice(int noticeNo) {
		return 0;
	}

	@Override
	public int updateNotice(int noticeNo) {
		return 0;
	}

	
	
}
