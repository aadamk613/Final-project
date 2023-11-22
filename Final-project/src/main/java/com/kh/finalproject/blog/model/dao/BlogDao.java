package com.kh.finalproject.blog.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.finalproject.blog.model.vo.Blog;
import com.kh.finalproject.blog.model.vo.BlogCategorySetting;
import com.kh.finalproject.blog.model.vo.Plant;

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




}
