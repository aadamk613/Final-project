package com.kh.finalproject.notice.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.finalproject.common.model.vo.Attachment;
import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.notice.model.dao.NoticeDao;
import com.kh.finalproject.notice.model.vo.Notice;
import com.kh.finalproject.notice.model.vo.NoticeLike;

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
	public int insertFile(Attachment f) {
		return noticeDao.insertFile(sqlSession, f);
	}

	@Override
	public int increaseCount(int noticeNo) {
		return noticeDao.increaseCount(sqlSession, noticeNo);
	}

	@Override
	public Notice selectNotice(int noticeNo) {
		return noticeDao.selectNotice(sqlSession, noticeNo);
	}

	@Override
	public int deleteNotice(int noticeNo) {
		return noticeDao.deleteNotice(sqlSession, noticeNo);
	}
	@Override
	public int deleteFile(int noticeNo) {
		return noticeDao.deleteFile(sqlSession, noticeNo);
	}
	@Override
	public int updateNotice(Notice n) {
		return noticeDao.updateNotice(sqlSession, n);
	}

	@Override
	public int selectLastNoticeNo() {
		return noticeDao.selectLastNoticeNo(sqlSession);
	}

	@Override
	public ArrayList<Attachment> selectFile(int noticeNo) {
		return noticeDao.selectFile(sqlSession, noticeNo);
	}

	@Override
	public int updateFiles(Attachment f) {
		return noticeDao.updateFiles(sqlSession, f);
	}

	@Override
	public NoticeLike selectNoticeLike(NoticeLike nl) {
		return NoticeDao.selectLike(sqlSession, nl);
	}

	@Override
	public int insertNoticeLike(NoticeLike nl) {
		return NoticeDao.insertNoticeLike(sqlSession, nl);
	}

	@Override
	public int updateNoticeLike(NoticeLike nl) {
		return NoticeDao.updateNoticeLike(sqlSession, nl);
	}

	@Override
	public int deleteNoticeLike(NoticeLike nl) {
		return NoticeDao.deleteNoticeLike(sqlSession, nl);
	}

	@Override
	public int plusNoticeLikeCount(int NoticeNo) {
		return NoticeDao.plusNoticeLikeCount(sqlSession, NoticeNo);
	}

	@Override
	public int minusNoticeLikeCount(int NoticeNo) {
		return NoticeDao.minusNoticeLikeCount(sqlSession, NoticeNo);
	}
	
}
