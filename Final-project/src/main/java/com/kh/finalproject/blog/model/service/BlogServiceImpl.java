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
import com.kh.finalproject.blog.model.vo.BlogReply;
import com.kh.finalproject.blog.model.vo.Plant;
import com.kh.finalproject.blog.model.vo.PlantReport;
import com.kh.finalproject.common.model.dao.CommonDao;
import com.kh.finalproject.common.model.vo.Attachment;
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
		
		
		BlogCategorySetting blogCateSet = new BlogCategorySetting();
		// i == 새로 생성 할 기본 블로그 카테고리(10: 일반 게시판, 20: 식물일지, 30: ToDoList)
		// blogNo는 insertBlog에서 생성한 SEQ_BLOG를 이용할 것 => mapper에서 조건식으로 구분하기 위해 일반 생성과 다르게 blogNo를 0으로 세팅
		
		for(int i = 10; i <= 30; i += 10) {
			blogCateSet.setBlogNo(0);
			blogCateSet.setCategoryNo(i);
			blogDao.insertCategory(sqlSession, blogCateSet);
		}
		
		return blogDao.updateMemberBlogNo(sqlSession, b);
	}
	
	public Blog selectBlog(int blogNo) {
		return blogDao.selectBlog(sqlSession, blogNo);
	}

	@Transactional
	@Override
	public int updateBlog(Blog blog, Attachment file) {
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

	public int insertBlogPlant(Plant plant, Attachment file) {
		int result = blogDao.insertBlogPlant(sqlSession, plant);
		if(file.getOriginalName() != null) {
			return commonDao.insertFiles(sqlSession, file);
		}
		return result;
	}

	@Override
	public int updateBlogPlant(Plant plant) {
		int result = 0;
		result = blogDao.updateBlogPlant(sqlSession, plant);
		return result;
	}

	/*
	 * 	@Override
	public int updateBlogPlant(Plant plant, Attachment ) {
		int result = 0;
		result = blogDao.updateBlogPlant(sqlSession, plant);
		if(file.getOriginalName() != null) {
			result *= commonDao.insertFiles(sqlSession, file);
		}
		return result;
	}
	 */
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
	
	@Override
	public Plant selectBlogPlant(int plantNo) {
		Plant plant = blogDao.selectBlogPlant(sqlSession, plantNo);
		return plant;
	}
	
	@Override
	public int deleteBlogPlant(int plantNo) {
		return blogDao.deleteBlogPlant(sqlSession, plantNo);
	}
	
	// 식물 일지 -----------------------------------------
	@Override
	public int insertBlogPlantReport(PlantReport plantReport, Attachment file) {
		
		int result = blogDao.insertBlogPlantReport(sqlSession, plantReport);
		if(file.getOriginalName() != null) {
			return commonDao.insertFiles(sqlSession, file);
		}
		return result;
	}
	
	// 게시판 -----------------------------------------
	@Override
	public int selectListCountBlogBoard(int blogNo) {
		return blogDao.selectListCountBlogBoard(sqlSession, blogNo);
	}
	
	@Override
	public int insertBlogBoard(BlogBoard blogBoard) {
		return blogDao.insertBlogBoard(sqlSession, blogBoard);
	}
	
	@Override
	public ArrayList<BlogBoard> selectBlogBoardList(PageInfo pi, int blogNo){
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());  
		return blogDao.selectBlogBoardList(sqlSession, blogNo, rowBounds);
	}

	@Override
	public BlogBoard selectBlogBoard(int blogBoardNo){
		return blogDao.selectBlogBoard(sqlSession, blogBoardNo);
	}


	// 댓글 -----------------------------------------
	@Override
	public int insertBlogReply(BlogReply blogReply) {
		return blogDao.insertBlogReply(sqlSession, blogReply);
	}

	@Override
	public int selectListCountBlogReply(int blogBoardNo) {
		return blogDao.selectListCountBlogReply(sqlSession, blogBoardNo);
	}

	@Override
	public ArrayList<BlogReply> seletListBlogReply(PageInfo pi, int blogBoardNo) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());  
		return blogDao.selectListBlogReply(sqlSession, blogBoardNo, rowBounds);
	}

	@Override
	public ArrayList<PlantReport> selectBlogPlantReport(int plantNo) {
		return blogDao.selectBlogPlantReport(sqlSession, plantNo);
	}
	


}
