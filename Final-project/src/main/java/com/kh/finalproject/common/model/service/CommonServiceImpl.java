package com.kh.finalproject.common.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.finalproject.common.model.dao.CommonDao;
import com.kh.finalproject.common.model.vo.Attachment;

@Service
public class CommonServiceImpl implements CommonService{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Autowired
	private CommonDao commonDao;
	
	// 첨부파일 조회
	@Override
	public ArrayList<Attachment> selectFiles(HashMap map) {
		System.out.println("첨부파일 조회 service " + map);
		return commonDao.selectFiles(sqlSession, map);
	}
	
	// 첨부파일 등록
	@Override
	public int insertFiles(Attachment file) {
		return commonDao.insertFiles(sqlSession, file);
	}
	
	// 첨부파일 등록 - 유담 사용
	@Override
	public int insertAttchment(Attachment attchment) {
		return commonDao.insertAttchment(sqlSession, attchment);
	}
	
	
	// 첨부파일 업데이트
	@Override
	public int updateFiles(Attachment file) {
		return commonDao.updateFiles(sqlSession, file);
	}

	// 첨부파일 삭제
	@Override
	public int deleteFiles(Attachment file) {
		return commonDao.deleteFiles(sqlSession, file);
	}

	// 첨부파일 수정
	@Override
	public int updateAttachment(Attachment file) {
		return commonDao.updateAttachment(sqlSession, file);
	}

}
