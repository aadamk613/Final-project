package com.kh.finalproject.experience.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.common.model.dao.CommonDao;
import com.kh.finalproject.common.model.vo.Files;
import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.experience.model.dao.ExperienceDao;
import com.kh.finalproject.experience.model.vo.Experience;
import com.kh.finalproject.experience.model.vo.ExperienceReply;

@Service 
public class ExperienceServiceImpl implements ExperienceService {
	
	@Autowired
	private ExperienceDao experienceDao;
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Autowired
	private CommonDao commonDao;

	@Override
	public int selectListCount() {
		return experienceDao.selectListCount(sqlSession);
	}
	
	@Override
	public ArrayList<Experience> selectExperienceList(PageInfo pi) {
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return experienceDao.selectExperienceList(sqlSession, rowBounds);
	}

	@Override
	public int increaseCount(int expNo) {
		return experienceDao.increaseCount(sqlSession, expNo);
	}

	@Override
	public Experience selectExperience(int expNo) {
		return experienceDao.selectExperience(sqlSession, expNo);
	}

	@Override
	public ArrayList<ExperienceReply> selectExpReplyList(int expNo) {
		return experienceDao.selectExpReplyList(sqlSession, expNo);
	}

	@Override
	@Transactional
	public int insertExperience(Experience exp, ArrayList<Files> fileList) {
		int result = experienceDao.insertExperience(sqlSession, exp);
		for(Files file : fileList) {
			result *= commonDao.insertFiles(sqlSession, file);
		}
		return result;
	}
	
	
	@Override
	public int deleteExperience(int expNo) {
		return experienceDao.deleteExperience(sqlSession, expNo);
	}

	@Override
	public int insertExpReply(ExperienceReply expReply) {
		return experienceDao.insertExpReply(sqlSession, expReply);
	}

	@Override
	public int selectExpLike(HashMap map) {
		return experienceDao.selectExpLike(sqlSession, map);
	}

	@Override
	public int insertExpLike(HashMap map) {
		return experienceDao.insertExpLike(sqlSession, map);
	}

	@Override
	public int deleteExpLike(HashMap map) {
		return experienceDao.deleteExpLike(sqlSession, map);
	}

	
	

	


	
	

}
