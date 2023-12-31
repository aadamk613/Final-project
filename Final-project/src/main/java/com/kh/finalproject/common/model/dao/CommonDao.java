package com.kh.finalproject.common.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.finalproject.common.model.vo.Attachment;

@Repository
public class CommonDao {
	
	// 첨부파일 조회
	public ArrayList<Attachment> selectFiles(SqlSessionTemplate sqlSession, HashMap map) {
		System.out.println("첨부파일 조회 dao 받은 map " + map);
		System.out.println("첨부파일 조회 dao 조회 후 결과" +(ArrayList)sqlSession.selectList("commonMapper.selectFiles", map));
		return (ArrayList)sqlSession.selectList("commonMapper.selectFiles", map);
	}
	
	// 첨부파일 등록
	public int insertFiles(SqlSessionTemplate sqlSession, Attachment file) {
		System.out.println("요서문제구나");
		return sqlSession.insert("commonMapper.insertFiles", file);
	}

	// 첨부파일 등록 - 유담 사용
	public int insertAttchment(SqlSessionTemplate sqlSession, Attachment attchment) {
		return sqlSession.insert("commonMapper.insertAttchment", attchment);
	}
	
	// 첨부파일 수정
	public int updateFiles(SqlSessionTemplate sqlSession, Attachment file) {
		return sqlSession.update("commonMapper.updateFiles", file);
	}

	// 첨부파일 삭제
	public int deleteFiles(SqlSessionTemplate sqlSession, Attachment file) {
		System.out.println("삭제 후 결과 " + sqlSession.delete("commonMapper.deleteFiles", file));
		return sqlSession.delete("commonMapper.deleteFiles", file);
	}

	// 첨부파일 수정
	public int updateAttachment(SqlSessionTemplate sqlSession, Attachment file) {
		return sqlSession.update("commonMapper.updateAttachment", file);
	}


}
