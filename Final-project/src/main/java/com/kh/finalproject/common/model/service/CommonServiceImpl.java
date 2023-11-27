package com.kh.finalproject.common.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.finalproject.common.model.dao.CommonDao;
import com.kh.finalproject.common.model.vo.Files;

@Service
public class CommonServiceImpl implements CommonService{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Autowired
	private CommonDao commonDao;
	
	// 첨부파일 조회
	@Override
	public ArrayList<Files> selectFiles(HashMap map) {
		System.out.println("첨부파일 조회 service " + map);
		return commonDao.selectFiles(sqlSession, map);
	}
	
	// 첨부파일 업데이트
	@Override
	public int updateFiles(Files file) {
		return commonDao.updateFiles(sqlSession, file);
	}

	// 첨부파일 삭제
	@Override
	public int deleteFiles(Files file) {
		return commonDao.deleteFiles(sqlSession, file);
	}

}
