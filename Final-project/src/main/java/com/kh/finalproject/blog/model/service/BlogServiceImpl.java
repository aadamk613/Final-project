package com.kh.finalproject.blog.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.finalproject.blog.model.dao.BlogDao;
import com.kh.finalproject.blog.model.vo.Blog;

@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogDao blogDao;
	
	@Autowired 
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertBlog(Blog b) {
		
		return blogDao.insertBlog(sqlSession, b);
	}

	@Override
	public int updateBlog(Blog b) {
		// TODO Auto-generated method stub
		return 0;
	}

}
