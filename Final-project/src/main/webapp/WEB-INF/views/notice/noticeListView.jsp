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
		<aside id="pageAsideLeft" class="aside">
		<jsp:include page="../common/boardSideBar.jsp"/>
		</aside>

		
		<section id="section">
			
			<div id="contentTitle">
				<h2>공지게시판</h2>
			</div>
			
			
			<div id="content">
				<article>
					<table id="boardTable" class="table table-hover">
					  <thead>
					    <tr>
					      <th scope="col" width="10%" style="text-align: center">번호</th>
					      <th scope="col" width="28%" style="text-align: center">제목</th>
					      <th scope="col" width="10%" style="text-align: center">작성자</th>
					      <th scope="col" width="10%" style="text-align: center">작성일</th>
					      <th scope="col" width="15%" style="text-align: center">조회수</th>
					      <th scope="col" width="15%" style="text-align: center">좋아요</th>
					    </tr>
					  </thead>
					  
					  <tbody>
						  <c:if test="${ empty list  }">
							  <tr>
							  <td colspan="5" style="text-align: center">작성한 게시글이 없습니다</td>
							  </tr>
						  </c:if>

						  
						  
						  	<c:if test="${ pi.currentPage eq 1 }">
						  	<c:forEach var="bn" items="${ requestScope.best }">
							  <tr>
						      <td scope="col" width="10%" style="text-align: center">
						      <span class="best">BEST</span>
						      ${ bn.category == 1 ? "공지" : '필독'}
						      <span class="bno">${ bn.noticeNo }</span>
						      </td>
					          <td scope="row" width="20%" style="text-align: center">${ bn.noticeTitle }</td>	       
						      <td scope="row" width="20%" style="text-align: center">${ bn.memNo }</td>
						      <td scope="row" width="20%" style="text-align: center">${ bn.noticeCreateDate }</td>
						      <td scope="row" width="15%" style="text-align: center">${ bn.views }</td>
						      <td scope="row" width="15%" style="text-align: center">${ bn.likeCount }</td>
						    	</tr>
						    </c:forEach>
						    </c:if>
						    
						  	<c:forEach var="n" items="${ requestScope.list }">
						  	  <tr>
						      <td scope="col" width="10%" style="text-align: center">
						      ${ n.category == 1 ? "공지" : '필독'}
						      <span class="bno">${ n.noticeNo }</span></td>
					        <td scope="row" width="20%" style="text-align: center">${ n.noticeTitle }</td>	       
						      <td scope="row" width="20%" style="text-align: center">${ n.memNo }</td>
						      <td scope="row" width="20%" style="text-align: center">${ n.noticeCreateDate }</td>
						      <td scope="row" width="15%" style="text-align: center">${ n.views }</td>
						      <td scope="row" width="15%" style="text-align: center">${ n.likeCount }</td>
						    	</tr>
						    </c:forEach>

					  </tbody>
					</table>
				</article>
			</div>
			
			<script>
				$(function(){
					$('#boardTable > tbody > tr').click(function(){
						location.href = 'detail.no?bno=' + $(this).find('.bno').text();
					});
				})
			</script>
			
			
			<div id="page">
				<c:if test="${ pi.currentPage ne 1 }">
		        	<button class="btn btn-light" onclick="location.href='list.no?cPage=${ pi.currentPage - 1 }'">&lt;</button>
		        </c:if> 
		       
		        <c:forEach var="i" begin="${ pi.startPage }" end="${ pi.endPage }">
		       		<c:choose>
			       		<c:when test="${ pi.currentPage ne i }">
			          		<button class="btn btn-light" onclick="location.href='list.no?cPage=${ i }'">${ i }</button>
			         	</c:when>
			         	<c:otherwise>
			         		<button disabled class="btn btn-default">${ i }</button>
			         	</c:otherwise>
		         	</c:choose>
		        </c:forEach>
		        
		        <c:if test="${ pi.currentPage ne pi.maxPage }">
		        	<button class="btn btn-light" onclick="location.href='list.no?cPage=${ pi.currentPage + 1 }'">&gt;</button>
		        </c:if>
			</div>
			

			            <form id="searchForm" action="" method="get" align="center">
                <div class="select">
                    <select class="custom-select" name="condition">
                        <option value="writer">작성자</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                    </select>
                </div>
                <div class="text">
                    <input type="text" class="form-control" name="keyword">
                </div>
                <button type="submit" class="searchBtn btn btn-secondary">검색</button>
            </form>
			
			
		</section>
		
		<aside id="pageAsideRight" class="aside">
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