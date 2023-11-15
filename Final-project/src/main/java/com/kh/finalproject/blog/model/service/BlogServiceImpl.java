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
		return blogDao.updateMemberBlogNo(sqlSession, b);
	}
	
	public Blog selectBlog(int blogNo) {
		return blogDao.selectBlog(sqlSession, blogNo);
	}

	@Override
	public int updateBlog(Blog blog) {
		System.out.println("블로그 업데이트 서비스" + blog);
		return blogDao.updateBlog(sqlSession, blog);
	}
	
	@Override
	public int insertCategory(int blogNo) {
		return blogDao.insertCategory(sqlSession, blogNo);
	}

}
