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
		System.out.println("첨부파일 조회 dao 받은 map " + map);
		System.out.println("첨부파일 조회 dao 조회 후 결과" +(ArrayList)sqlSession.selectList("commonMapper.selectFiles", map));
		return (ArrayList)sqlSession.selectList("commonMapper.selectFiles", map);
	}
	
	// 첨부파일 등록
	public int insertFiles(SqlSessionTemplate sqlSession, Files file) {
		System.out.println("요서문제구나");
		return sqlSession.insert("commonMapper.insertFiles", file);
	}

	// 첨부파일 업데이트
	public int updateFiles(SqlSessionTemplate sqlSession, Files file) {
		return sqlSession.update("commonMapper.updateFiles", file);
	}

	// 첨부파일 삭제
	public int deleteFiles(SqlSessionTemplate sqlSession, Files file) {
		System.out.println("삭제 후 결과 " + sqlSession.delete("commonMapper.deleteFiles", file));
		return sqlSession.delete("commonMapper.deleteFiles", file);
	}

}
