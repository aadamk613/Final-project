package com.kh.finalproject.blog.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.finalproject.blog.model.vo.Blog;

@Repository
public class BlogDao {

	public int insertBlog(SqlSessionTemplate sqlSession, Blog b) {
		return sqlSession.insert("bolgMapper.insertBlog", b);
	}

	public int updateMemberBlogNo(SqlSessionTemplate sqlSession, Blog b) {
		return sqlSession.update("bolgMapper.updateMemberBlogNo", b);
	}

	public Blog selectBlog(SqlSessionTemplate sqlSession, int blogNo) {
		return sqlSession.selectOne("bolgMapper.selectBlog", blogNo);
	}
	
	public int updateBlog(SqlSessionTemplate sqlSession, Blog blog) {
		System.out.println("블로그 업데이트 dao" + blog);
		return sqlSession.update("bolgMapper.updateBlog", blog);
	}

	public int insertCategory(SqlSessionTemplate sqlSession, int blogNo) {
		System.out.println(blogNo);
		return sqlSession.insert("blogMapper.insertCategory", blogNo);
	}

}
