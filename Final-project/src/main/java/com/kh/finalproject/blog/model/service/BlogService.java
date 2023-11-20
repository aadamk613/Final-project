package com.kh.finalproject.blog.model.service;

import java.util.ArrayList;

import com.kh.finalproject.blog.model.vo.Blog;
import com.kh.finalproject.blog.model.vo.BlogCategorySetting;
import com.kh.finalproject.blog.model.vo.Plant;
import com.kh.finalproject.common.model.vo.Files;
import com.kh.finalproject.common.model.vo.PageInfo;
import com.kh.finalproject.experience.model.vo.Experience;

public interface BlogService {

	// 블로그 메인 화면 리스트 보기

	// 블로그 select
	Blog selectBlog(int blogNo);
	
	// 블로그 생성
	int insertBlog(Blog b);
	
	// 블로그 정보 수정
	int updateBlog(Blog b);

	// 블로그 카테고리 select
	ArrayList<BlogCategorySetting> selectCatogory(int blogNo);
	
	// 블로그 카테고리 생성
	int insertCategory(BlogCategorySetting blogCateSet);
	
	// 블로그 식물 수 조회
	int selectListCountPlant(int blogNo);
	
	// 식물 전체 리스트로 이동
	ArrayList<Plant> selectListPlant(PageInfo pi, int blogNo);
	
	// 식물 등록
	int insertBlogPlant(Plant plant, Files file);
	
	// 블로그 카테고리 수정
	
	
	// 블로그 카테고리 삭제
	
	
	// 블로그 일반 글 쓰기
	
	
	// 블로그 일반 글 삭제
	
	
	// 블로그 일반 글 수정
	
	
	// 블로그 게시글 좋아요 / 취소
	
	
}
