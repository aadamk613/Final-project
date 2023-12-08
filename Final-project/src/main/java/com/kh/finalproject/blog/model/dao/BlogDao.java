package com.kh.finalproject.blog.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.finalproject.blog.model.vo.Blog;
import com.kh.finalproject.blog.model.vo.BlogBoard;
import com.kh.finalproject.blog.model.vo.BlogCategorySetting;
import com.kh.finalproject.blog.model.vo.BlogReply;
import com.kh.finalproject.blog.model.vo.Plant;
import com.kh.finalproject.blog.model.vo.PlantReport;

@Repository
public class BlogDao {

	public int insertBlog(SqlSessionTemplate sqlSession, Blog b) {
		return sqlSession.insert("blogMapper.insertBlog", b);
	}

	public int updateMemberBlogNo(SqlSessionTemplate sqlSession, Blog b) {
		return sqlSession.update("blogMapper.updateMemberBlogNo", b);
	}

	public Blog selectBlog(SqlSessionTemplate sqlSession, int blogNo) {
		return sqlSession.selectOne("blogMapper.selectBlog", blogNo);
	}
	
	public int updateBlog(SqlSessionTemplate sqlSession, Blog blog) {
		System.out.println("블로그 업데이트 dao" + blog);
		return sqlSession.update("blogMapper.updateBlog", blog);
	}

	// 블로그 카테고리 관련-----------------------------------------
	public int insertCategory(SqlSessionTemplate sqlSession, BlogCategorySetting blogCateSet) {
		return sqlSession.insert("blogMapper.insertCategory", blogCateSet);
	}
	
	public ArrayList<BlogCategorySetting> selectCatogory(SqlSessionTemplate sqlSession, int blogNo) {
		return (ArrayList)sqlSession.selectList("blogMapper.selectCatogory", blogNo);
	}
	
	public int updateCatogory(SqlSessionTemplate sqlSession, BlogCategorySetting blogCateSet) {
		System.out.println("카테고리 바꾸기 dao 결과 "+sqlSession.update("blogMapper.updateCatogory", blogCateSet));
		return sqlSession.update("blogMapper.updateCatogory", blogCateSet);
	}

	public int deleteCatogory(SqlSessionTemplate sqlSession, int categorySettingNo) {
		return sqlSession.delete("blogMapper.deleteCatogory", categorySettingNo);
	}
	
	// 블로그 식물 관련 -----------------------------------------
	public int insertBlogPlant(SqlSessionTemplate sqlSession, Plant plant) {
		System.out.println(plant);
		return sqlSession.insert("blogMapper.insertBlogPlant", plant);
	}
	
	public int selectListCountPlant(SqlSessionTemplate sqlSession, int blogNo) {
		return sqlSession.selectOne("blogMapper.selectListCountPlant", blogNo);
	}

	public ArrayList<Plant> selectListPlant(SqlSessionTemplate sqlSession, int blogNo, RowBounds rowBounds) {
		return (ArrayList)sqlSession.selectList("blogMapper.selectListPlant", blogNo, rowBounds);
	}

	public Plant selectBlogPlant(SqlSessionTemplate sqlSession, int plantNo) {
		return sqlSession.selectOne("blogMapper.selectBlogPlant", plantNo);
	}
	
	public int updateBlogPlant(SqlSessionTemplate sqlSession, Plant plant) {
		return sqlSession.update("blogMapper.updateBlogPlant", plant);
	}
	
	public int deleteBlogPlant(SqlSessionTemplate sqlSession, int plantNo) {
		return sqlSession.delete("blogMapper.deleteBlogPlant", plantNo);
	}
	
	// 블로그 식물 일지 관련 -----------------------------------------
	public int insertBlogPlantReport(SqlSessionTemplate sqlSession, PlantReport plantReport) {
		return sqlSession.insert("blogMapper.insertBlogPlantReport", plantReport);
	}

	public ArrayList<PlantReport> selectBlogPlantReport(SqlSessionTemplate sqlSession, int plantNo){
		return (ArrayList)sqlSession.selectList("blogMapper.selectBlogPlantReport", plantNo);
	}
	
	// 블로그 일반 게시글 관련 -----------------------------------------
	public int insertBlogBoard(SqlSessionTemplate sqlSession, BlogBoard blogBoard) {
		return sqlSession.insert("blogMapper.insertBlogBoard", blogBoard);
	}
	
	public int selectListCountBlogBoard(SqlSessionTemplate sqlSession, int blogNo) {
		return sqlSession.selectOne("blogMapper.selectListCountBlogBoard", blogNo);
	}

	public ArrayList<BlogBoard> selectBlogBoardList(SqlSessionTemplate sqlSession, int blogNo, RowBounds rowBounds) {
		return (ArrayList)sqlSession.selectList("blogMapper.selectBlogBoardList", blogNo, rowBounds);
	}

	public BlogBoard selectBlogBoard(SqlSessionTemplate sqlSession, int blogBoardNo) {
		return sqlSession.selectOne("blogMapper.selectBlogBoard", blogBoardNo);
	}
	
	// 블로그 댓글 관련 -----------------------------------------
	// 댓글 작성
	public int insertBlogReply(SqlSessionTemplate sqlSession, BlogReply blogReply) {
		return sqlSession.insert("blogMapper.insertBlogReply", blogReply);
	}

	// 댓글 수 조회
	public int selectListCountBlogReply(SqlSessionTemplate sqlSession, int blogBoardNo) {
		System.out.println("댓글 수 조회 " + sqlSession.selectOne("blogMapper.selectListCountBlogReply", blogBoardNo));
		return sqlSession.selectOne("blogMapper.selectListCountBlogReply", blogBoardNo);
	}

	// 댓글 리스트 조회
	public ArrayList<BlogReply> selectListBlogReply(SqlSessionTemplate sqlSession, int blogBoardNo, RowBounds rowBounds) {
		System.out.println("댓글 조회 리스트 : " + (ArrayList)sqlSession.selectList("blogMapper.selectListBlogReply", blogBoardNo, rowBounds));
		return (ArrayList)sqlSession.selectList("blogMapper.selectListBlogReply", blogBoardNo, rowBounds);
	}



	





}
