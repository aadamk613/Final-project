<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>

<link rel="stylesheet" href="resources/css/board/boardListView.css">

<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">


</head>
<body>

	<header id="pageHeader">
		<jsp:include page="../common/header.jsp" />
	</header> 
	<main>

		<jsp:include page="../common/boardSideBar.jsp" />

		
		<section id="section">
			
			<div id="contentTitle">
				<h2>공지게시판</h2>
			</div>
			
			<div id="content">
				<article>
					<table id="boardTable" class="table table-hover">
					  <thead>
					    <tr>
					      <th scope="col" width="10%" style="text-align: center">게시글 번호</th>
					      <th scope="col" width="20%" style="text-align: center">제목</th>
					      <th scope="col" width="10%" style="text-align: center">작성자</th>
					      <th scope="col" width="20%" style="text-align: center">작성일</th>
					      <th scope="col" width="10%" style="text-align: center">조회수</th>
					      <th scope="col" width="10%" style="text-align: center">좋아요</th>
					    </tr>
					  </thead>
					  
					  <tbody>
					  <c:choose>
						  <c:when test="${ list eq null }">
							  <tr>
							  <td colspan="5" style="text-align: center">작성한 게시글이 없습니다</td>
							  <tr>
						  </c:when>
						  <c:otherwise>
						  	<c:forEach var="n" items="${ requestScope.list }">
						  	  <tr>
						      <td scope="row" style="text-align: center">${ n.noticeNo }</td>
						      <c:choose>
							      <c:when test="${ loginUser ne null }">
							      	<td scope="row" style="text-align: center">
							      		<a href="detail.bo?boardNo=${ n.noticeNo }&memNo=${ loginUser.memNo }">${ n.noticeTitle }</a>
							      	</td>
							      </c:when>
							      <c:otherwise>
							      	<td scope="row" style="text-align: center" id="boardTitleTd">
							      		<a href="detail.bo?boardNo=${ n.noticeNo }&memNo=0">${ n.noticeTitle }</a>
							      	</td>
							      </c:otherwise>
						      </c:choose>
						       					       
						      <td scope="row" width="20%" style="text-align: center">${ n.memNo }</td>
						      <td scope="row" width="20%" style="text-align: center">${ n.noticeCreateDate }</td>
						      <td scope="row" width="10%" style="text-align: center">${ n.views }</td>
						      <td scope="row" width="10%" style="text-align: center">${ n.likeCount }</td>
						    	</tr>
						    </c:forEach>
						</c:otherwise>	
					  </c:choose>

					  </tbody>
					</table>
					
					<!-- 로그인 했을 경우에만 글 쓰기 버튼 보이게하기 --> 
					<c:if test="${ loginUser ne null }">
						<div id="writeWrap">
						<a id="writeButton" class="btn btn-primary" href="" >글 쓰기</a>
						</div>
					</c:if>
				</article>
			</div>
			
			
			<div id="page">
				<c:if test="${ pi.currentPage ne 1 }">
		        	<button class="btn btn-light" onclick="location.href='list.no?cpage=${ pi.currentPage - 1 }'">&lt;</button>
		        </c:if> 
		       
		        <c:forEach var="i" begin="${ pi.startPage }" end="${ pi.endPage }">
		       		<c:choose>
			       		<c:when test="${ pi.currentPage ne i }">
			          		<button class="btn btn-light" onclick="location.href='list.no?cpage=${ i }'">${ i }</button>
			         	</c:when>
			         	<c:otherwise>
			         		<button disabled class="btn btn-default">${ i }</button>
			         	</c:otherwise>
		         	</c:choose>
		        </c:forEach>
		        
		        <c:if test="${ pi.currentPage ne pi.maxPage }">
		        	<button class="btn btn-light" onclick="location.href='list.no?cpage=${ pi.currentPage + 1 }'">&gt;</button>
		        </c:if>
			</div>
		</section>
		
		<aside id="aside2" class="aside">
		</aside>
		
		
	</main>
	<br clear="both">
	
	<footer id="pageFooter">
        &lt;footer&gt; <br>
        id=pageFooter
	        여기는 푸터 <br>
	        여기는 푸터 <br>
	        여기는 푸터 <br>
	        여기는 푸터 <br>
	        여기는 푸터 <br>
	        여기는 푸터 <br>
	        쓰는만큼 늘어나요<br>
	     <jsp:include page="../common/footer.jsp" />
	</footer>


</body>
</html>