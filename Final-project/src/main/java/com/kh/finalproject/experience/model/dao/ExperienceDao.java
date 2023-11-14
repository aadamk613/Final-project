package com.kh.finalproject.experience.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.finalproject.experience.model.vo.Experience;

@Repository
public class ExperienceDao {
	
	public int selectListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("experienceMapper.selectListCount");
	}

	public ArrayList<Experience> selectExperienceList(SqlSessionTemplate sqlSession, RowBounds rowBounds) {
		return (ArrayList)sqlSession.selectList("experienceMapper.selectExperienceList", null, rowBounds);
	}
	
	

}
