package com.kh.finalproject.notice.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.finalproject.common.model.vo.Attachment;
import com.kh.finalproject.notice.model.vo.Notice;
import com.kh.finalproject.notice.model.vo.NoticeLike;

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
	public int insertFile(SqlSessionTemplate sqlSession, Attachment f) {
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
	
	public int deleteFile(SqlSessionTemplate sqlSession, int noticeNo) {
		return sqlSession.delete("noticeMapper.deleteFile", noticeNo);
	}

	public int updateNotice(SqlSessionTemplate sqlSession, Notice n) {
		return sqlSession.update("noticeMapper.updateNotice", n);
	}
	
	public int selectLastNoticeNo(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("noticeMapper.selectLastNoticeNo");
	}

	public ArrayList<Attachment> selectFile(SqlSessionTemplate sqlSession, int noticeNo) {
		return (ArrayList)sqlSession.selectList("noticeMapper.selectFile", noticeNo);
	}

	public int updateFiles(SqlSessionTemplate sqlSession, Attachment f) {
		return sqlSession.update("noticeMapper.updateFiles", f);
	}

	public static NoticeLike selectLike(SqlSessionTemplate sqlSession, NoticeLike nl) {
		return sqlSession.selectOne("noticeMapper.selectLike", nl);
	}

	public static int insertNoticeLike(SqlSessionTemplate sqlSession, NoticeLike nl) {
		//System.out.println(nl);
		return sqlSession.insert("noticeMapper.insertNoticeLike", nl);
	}

	public static int updateNoticeLike(SqlSessionTemplate sqlSession, NoticeLike nl) {
		return sqlSession.update("noticeMapper.updateNoticeLike", nl);
	}

	public static int deleteNoticeLike(SqlSessionTemplate sqlSession, NoticeLike nl) {
		return sqlSession.update("noticeMapper.deleteNoticeLike", nl);
	}

	public static int plusNoticeLikeCount(SqlSessionTemplate sqlSession, int noticeNo) {
		return sqlSession.update("noticeMapper.plusLikeCount", noticeNo);
	}

	public static int minusNoticeLikeCount(SqlSessionTemplate sqlSession, int noticeNo) {
		return sqlSession.update("noticeMapper.minusLikeCount", noticeNo);
	}


	
	
	
}
