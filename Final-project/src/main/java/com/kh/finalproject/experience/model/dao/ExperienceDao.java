package com.kh.finalproject.experience.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.finalproject.common.model.vo.Files;
import com.kh.finalproject.experience.model.vo.Experience;

@Repository
public class ExperienceDao {
	
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

	
	
	

}
