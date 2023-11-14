package com.kh.finalproject.blog.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.blog.model.dao.BlogDao;
import com.kh.finalproject.blog.model.vo.Blog;

@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogDao blogDao;
	
	@Autowired 
	private SqlSessionTemplate sqlSession;
	
	@Transactional
	@Override
	public int insertBlog(Blog b) {
		
		blogDao.insertBlog(sqlSession, b);
		System.out.println(blogDao.insertBlog(sqlSession, b));
		System.out.println(blogDao.updateMemberBlogNo(sqlSession, b));
		return blogDao.updateMemberBlogNo(sqlSession, b);
	}

	@Override
	public int updateBlog(Blog b) {
		// TODO Auto-generated method stub
		return 0;
	}

}
