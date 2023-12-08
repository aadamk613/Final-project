package com.kh.finalproject.blog.model.service;

import java.util.ArrayList;

import com.kh.finalproject.blog.model.vo.Blog;
import com.kh.finalproject.blog.model.vo.BlogBoard;
import com.kh.finalproject.blog.model.vo.BlogCategorySetting;
import com.kh.finalproject.blog.model.vo.BlogReply;
import com.kh.finalproject.blog.model.vo.Plant;
import com.kh.finalproject.blog.model.vo.PlantReport;
import com.kh.finalproject.common.model.vo.Attachment;
import com.kh.finalproject.common.model.vo.PageInfo;

public interface BlogService {

	// 블로그 메인 화면 리스트 보기

	// 블로그 select
	Blog selectBlog(int blogNo);
	
	// 블로그 생성
	int insertBlog(Blog b);
	
	// 블로그 정보 수정

	int updateBlog(Blog b, Attachment file);



	// 블로그 카테고리 select
	ArrayList<BlogCategorySetting> selectCatogory(int blogNo);
	
	// 블로그 카테고리 생성
	int insertCategory(BlogCategorySetting blogCateSet);
	
	// 블로그 카테고리 수정
	int updateCatogory(BlogCategorySetting blogCateSet);
	
	// 블로그 카테고리 삭제
	int deleteCatogory(int categorySettingNo);
	
	// --------------------------------------------------------
	
	// 블로그 식물 수 조회
	int selectListCountPlant(int blogNo);
	
	// 식물 전체 리스트 조회
	ArrayList<Plant> selectListPlant(PageInfo pi, int blogNo);
	
	// 식물 조회
	Plant selectBlogPlant(int plantNo);
	
	// 식물 수정
	int updateBlogPlant(Plant plant);
	
	/*
	 * 	// 식물 수정
	int updateBlogPlant(Plant plant, Attachment file);
	 */
	
	// 식물 등록
	int insertBlogPlant(Plant plant, Attachment file);

	// 식물 삭제
	int deleteBlogPlant(int plantNo);
	
	// --------------------------------------------------------
	// 식물 일지 등록
	int insertBlogPlantReport(PlantReport plantReport, Attachment file);
	
	ArrayList<PlantReport> selectBlogPlantReport(int plantNo);
	
	// --------------------------------------------------------
	// 블로그 일반 글 쓰기
	int insertBlogBoard(BlogBoard blogBoard);

	// 블로그 일반 글 리스트 카운트
	int selectListCountBlogBoard(int blogNo);
	
	// 블로그 일반 글 리스트 조회
	ArrayList<BlogBoard> selectBlogBoardList(PageInfo pi, int blogNo);

	// 블로그 일반 글 조회
	BlogBoard selectBlogBoard(int blogBoardNo);

	
	// 블로그 일반 글 삭제
	
	
	// 블로그 일반 글 수정
	
	
	// 블로그 게시글 좋아요 / 취소
	
	// --------------------------------------------------------
	// 블로그 댓글 작성
	int insertBlogReply(BlogReply blogReply);
	
	// 블로그 댓글 수 조회
	int selectListCountBlogReply(int blogBoardNo);
	
	// 블로그 댓글 조회
	ArrayList<BlogReply> seletListBlogReply(PageInfo pi, int blogBoardNo);
	
	
	
	
}
