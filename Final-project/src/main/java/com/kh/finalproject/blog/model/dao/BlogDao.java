package com.kh.finalproject.blog.model.dao;

import java.util.ArrayList;

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

	public int insertCategory(SqlSessionTemplate sqlSession, BlogCategorySetting blogCateSet) {
		return sqlSession.insert("blogMapper.insertCategory", blogCateSet);
	}
	
	public ArrayList<BlogCategorySetting> selectCatogory(SqlSessionTemplate sqlSession, int blogNo) {
		return (ArrayList)sqlSession.selectList("blogMapper.selectCatogory", blogNo);
	}
	
	public int insertBlogPlant(SqlSessionTemplate sqlSession, Plant plant) {
		System.out.println(plant);
		return sqlSession.insert("blogMapper.insertBlogPlant", plant);
	}

}
