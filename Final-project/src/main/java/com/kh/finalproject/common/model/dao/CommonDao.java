package com.kh.finalproject.common.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.finalproject.common.model.vo.Files;

@Repository
public class CommonDao {
	
	// 첨부파일 조회
	public ArrayList<Files> selectFiles(SqlSessionTemplate sqlSession, HashMap map) {
		return (ArrayList)sqlSession.selectList("commonMapper.selectFiles", map);
	}

}
