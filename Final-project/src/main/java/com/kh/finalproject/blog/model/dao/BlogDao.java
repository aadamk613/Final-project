package com.kh.finalproject.blog.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.finalproject.blog.model.vo.Blog;

@Repository
public class BlogDao {

	public int insertBlog(SqlSessionTemplate sqlSession, Blog b) {
		return sqlSession.insert("blogMapper.insertBlog", b);
	}


}
