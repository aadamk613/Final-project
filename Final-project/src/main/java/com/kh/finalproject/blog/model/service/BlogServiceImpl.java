package com.kh.finalproject.blog.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.blog.model.dao.BlogDao;
import com.kh.finalproject.blog.model.vo.Blog;
import com.kh.finalproject.blog.model.vo.BlogBoard;
import com.kh.finalproject.blog.model.vo.BlogCategorySetting;
import com.kh.finalproject.blog.model.vo.Plant;
import com.kh.finalproject.common.model.dao.CommonDao;
import com.kh.finalproject.common.model.vo.Files;
import com.kh.finalproject.common.model.vo.PageInfo;

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

	@Transactional
	@Override
	public int updateBlog(Blog blog, Files file) {
		int result = 0;
		result = blogDao.updateBlog(sqlSession, blog);
		if(file.getOriginalName() != null) {
			result *= commonDao.insertFiles(sqlSession, file);
		}
		return result;
	}
	
	
	// 카테고리 -----------------------------------------
	@Override
	public int insertCategory(BlogCategorySetting blogCateSet) {
		return blogDao.insertCategory(sqlSession, blogCateSet);
	}

	@Override
	public ArrayList<BlogCategorySetting> selectCatogory(int blogNo) {
		return (ArrayList<BlogCategorySetting>)blogDao.selectCatogory(sqlSession, blogNo);
	}
	
	@Override
	public int updateCatogory(BlogCategorySetting blogCateSet) {
		return blogDao.updateCatogory(sqlSession, blogCateSet);
	}

	@Override
	public int deleteCatogory(int categorySettingNo) {
		return blogDao.deleteCatogory(sqlSession, categorySettingNo);
	}
	
	// 식물 -----------------------------------------
	@Transactional
	@Override
	public int insertBlogPlant(Plant plant, Files file) {
		blogDao.insertBlogPlant(sqlSession, plant);
		return commonDao.insertFiles(sqlSession, file);
	}

	@Override
	public int selectListCountPlant(int blogNo) {
		return blogDao.selectListCountPlant(sqlSession, blogNo);
	}
	
	@Override
	public ArrayList<Plant> selectListPlant(PageInfo pi, int blogNo) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit()); // offset부터 pi.getBoardLimit()개 조회 
		return blogDao.selectListPlant(sqlSession, blogNo, rowBounds);
	}
	
	// 게시판 -----------------------------------------
	@Override
	public int insertBlogBoard(BlogBoard blogBoard) {
		return blogDao.insertBlogBoard(sqlSession, blogBoard);
	}



}
