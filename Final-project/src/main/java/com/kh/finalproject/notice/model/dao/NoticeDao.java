package com.kh.finalproject.notice.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.finalproject.common.model.vo.Files;
import com.kh.finalproject.notice.model.vo.Notice;

@Repository
public class NoticeDao {

	public int selectNoticeCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("noticeMapper.selectNoticeCount");
	}
	
	public ArrayList<Notice> selectNoticeList(SqlSessionTemplate sqlSession, RowBounds rowBouds) {
		return (ArrayList)sqlSession.selectList("noticeMapper.selectNoticeList", null, rowBouds);
	}
	
	public ArrayList<Notice> selectBestNoticeList(SqlSessionTemplate sqlSession) {
		return (ArrayList)sqlSession.selectList("noticeMapper.selectBestNotice");
	}
	
	public int insertNotice(SqlSessionTemplate sqlSession, Notice n) {
		return sqlSession.insert("noticeMapper.insertNotice", n);
	}
	public int insertFile(SqlSessionTemplate sqlSession, Files f) {
		return sqlSession.insert("noticeMapper.insertFile", f);
	}

	public int increaseCount(SqlSessionTemplate sqlSession, int noticeNo) {
		return sqlSession.update("noticeMapper.increaseCount", noticeNo);
	}

	public Notice selectNotice(SqlSessionTemplate sqlSession, int noticeNo) {

		return sqlSession.selectOne("noticeMapper.selectNotice", noticeNo);
	}

	public int deleteNotice(SqlSessionTemplate sqlSession, int noticeNo) {
		return sqlSession.update("noticeMapper.deleteNotice", noticeNo);
	}

	public int updateNotice(SqlSessionTemplate sqlSession, Notice n) {
		return sqlSession.update("noticeMapper.updateNotice", n);
	}
	
	public int selectLastNoticeNo(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("noticeMapper.selectLastNoticeNo");
	}

	public ArrayList<Files> selectFile(SqlSessionTemplate sqlSession, int noticeNo) {
		return (ArrayList)sqlSession.selectList("noticeMapper.selectFile", noticeNo);
	}

	public int updateFiles(SqlSessionTemplate sqlSession, Files f) {
		return sqlSession.update("noticeMapper.updateFiles");
	}
	
	
	
}
