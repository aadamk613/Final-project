package com.kh.finalproject.blog.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.blog.model.dao.BlogDao;
import com.kh.finalproject.blog.model.vo.Blog;
import com.kh.finalproject.blog.model.vo.BlogCategorySetting;
import com.kh.finalproject.blog.model.vo.Plant;
import com.kh.finalproject.common.model.dao.CommonDao;
import com.kh.finalproject.common.model.vo.Files;

@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CommonDao commonDao;
	
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
	public int insertCategory(BlogCategorySetting blogCateSet) {
		return blogDao.insertCategory(sqlSession, blogCateSet);
	}

	@Override
	public ArrayList<BlogCategorySetting> selectCatogory(int blogNo) {
		return (ArrayList<BlogCategorySetting>)blogDao.selectCatogory(sqlSession, blogNo);
	}
	
	@Transactional
	@Override
	public int insertBlogPlant(Plant plant, Files file) {
		commonDao.insertFiles(sqlSession, file);
		return blogDao.insertBlogPlant(sqlSession, plant);
	}

	@Override
	public ArrayList<Plant> selectListPlant(int blogNo) {
		return blogDao.selectListPlant(sqlSession, blogNo);
	}

}
