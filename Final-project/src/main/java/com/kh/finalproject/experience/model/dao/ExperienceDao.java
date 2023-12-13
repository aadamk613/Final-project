package com.kh.finalproject.experience.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.finalproject.common.controller.CommonController;
import com.kh.finalproject.common.model.vo.Attachment;
import com.kh.finalproject.experience.model.service.ExperienceService;
import com.kh.finalproject.experience.model.vo.Experience;
import com.kh.finalproject.experience.model.vo.ExperienceReply;
import com.kh.finalproject.experience.model.vo.Payment;

import lombok.RequiredArgsConstructor;

@Repository
public class ExperienceDao {
	
	// 게시글
	public int selectListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("experienceMapper.selectListCount");
	}

	public ArrayList<Experience> selectExperienceList(SqlSessionTemplate sqlSession, RowBounds rowBounds) {
		return (ArrayList)sqlSession.selectList("experienceMapper.selectExperienceList", null, rowBounds);
	}

	public int increaseCount(SqlSessionTemplate sqlSession, int exp) {
		return sqlSession.update("experienceMapper.increaseCount", exp);
	}

	public Experience selectExperience(SqlSessionTemplate sqlSession, int expNo) {
		return sqlSession.selectOne("experienceMapper.selectExperience", expNo);
	}
	
	public int insertExperience(SqlSessionTemplate sqlSession, Experience exp) {
		return sqlSession.insert("experienceMapper.insertExperience", exp);
	}
	
	public int insertFiles(SqlSessionTemplate sqlSession, ArrayList<Attachment> fileList) {
		return sqlSession.insert("experienceMapper.insertFiles", fileList);
	}
	
	public int updateExperience(SqlSessionTemplate sqlSession, Experience exp) {
		return sqlSession.update("experienceMapper.updateExperience", exp);
	}
	
	public int updateFiles(SqlSessionTemplate sqlSession, ArrayList<Attachment> fileList) {
		return sqlSession.insert("experienceMapper.updateFiles", fileList);
	}
	
	public int deleteExperience(SqlSessionTemplate sqlSession, int expNo) {
		return sqlSession.update("experienceMapper.deleteExperience", expNo);
	}
	
	public int deleteFiles(SqlSessionTemplate sqlSession, String oldFile) {
		return sqlSession.delete("experienceMapper.deleteExpFiles", oldFile);
	}

	// 댓글
	public ArrayList<ExperienceReply> selectExpReplyList(SqlSessionTemplate sqlSession, int expNo) {
		return (ArrayList)sqlSession.selectList("experienceMapper.selectReplyList", expNo);
	}
	
	public int insertExpReply(SqlSessionTemplate sqlSession, ExperienceReply expReply) {
		return sqlSession.insert("experienceMapper.insertExpReply", expReply);
	}
	
	public int deleteExpReply(SqlSessionTemplate sqlSession, int expReplyNo) {
		return sqlSession.delete("experienceMapper.deleteExpReply", expReplyNo);
	}

	// 좋아요
	public int selectExpLike(SqlSessionTemplate sqlSession, HashMap map) {
		return sqlSession.selectOne("experienceMapper.selectExpLike", map);
	}
	
	public int insertExpLike(SqlSessionTemplate sqlSession, HashMap map) {
		return sqlSession.insert("experienceMapper.insertExpLike", map);
	}

	public int deleteExpLike(SqlSessionTemplate sqlSession, HashMap map) {
		return sqlSession.delete("experienceMapper.deleteExpLike", map);
	}

	
	// 결제
	public int insertPayment(SqlSessionTemplate sqlSession, Payment payment) {
		return sqlSession.insert("experienceMapper.insertPayment", payment);
	}

	public Payment selectPayment(SqlSessionTemplate sqlSession, HashMap map) {
		return sqlSession.selectOne("experienceMapper.selectPayment", map);
	}

	public int updatePayment(SqlSessionTemplate sqlSession, Payment payment) {
		return sqlSession.update("experienceMapper.updatePayment", payment);
	}
	

}
