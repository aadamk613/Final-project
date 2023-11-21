<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>체험학습 목록 조회</title>
	<link rel="stylesheet" href="resources/css/common/template.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

</head>


<style>


* {
    border: 1px solid skyblue;
	box-sizing: border-box;
}

.table table-bordered th, td{
	text-align : center;
}

.pagination {
	align : center;
    display : flex;
    align-items: center;
    justify-content: center;
}

#insert-btn{
	float : right;
	margin : 5px 0px;
}

a {
	text-decoration : none;
}

</style>

<body>

	
	<header id="pageHeader">
	<jsp:include page="../common/header.jsp" />
	
	
	</header> 
	<main>
		<aside id="pageAsideLeft" class="aside">
            &lt;aside1&gt; <br>
            id=pageAsideLeft <br>
	            여기는 pageAsideLeft 공백공간 <br>
	            사이드바 넣을 수도 있음 <br>
	            필요하면 쓰세요 <br>
	            중앙정렬되어있어요 <br>
		</aside>
		
		<!-- 게시판 -->
		<section id="pageSection">
			
			<div>
				<h1>체험학습</h1>
				
				<c:if test="${ empty loginUser }">
					<button type="button" class="btn btn-primary" id="insert-btn" onclick="location.href='yrinsertExpForm.exp'">작성하기</button>
				</c:if>
			</div>
			
			<div class="table-sm">
			<table class="table table-bordered">
			    <thead>
			      <tr>
 					<th>번호</th>
					<th>카테고리</th>
					<th>제목</th>
					<th>지역</th>
					<th>모집마감일</th>
					<th>작성자</th>
					<th>모집인원</th>
					<th>상태</th>
					<th>조회수</th>
			      </tr>
			    </thead>
			    <tbody>
			    	<c:forEach items="${ experienceList }" var="el">
						<tr>
						  <td>${ el.expNo }</td>
						  <td>${ el.expCategoryName }</td>
						  <td><a href="yrdetail.exp?expNo=${ el.expNo }">${ el.expTitle }</a></td>
						  <td>${ el.expArea }</td>
						  <td>${ el.expEndDate }</td>
						  <td>${ el.expWriter }</td>
						  <td>${ el.expPeople }</td>
						  <td>${ el.expStatus }</td>
						  <td>${ el.expCount }</td>
						</tr>
			      	</c:forEach>
			    </tbody>
			  </table>
			</div>
			
			<script>
			
				function test(a, b){
					// a는 매핑값
					// b는 페이지 뒤에 쿼리스트링 받으면 어떨까?
				}
				// 음 페이징바를 js파일로 빼서 공유해보기 => ajax처리? 어떰???
			</script>
			
			<!-- 페이징바 -->
			<div id="pagingbar">
				<ul class="pagination">
					<c:choose>
						<c:when test="${ pi.currentPage ne 1 }">
					  		<li class="page-item"><a class="page-link" href="yrlist.exp?page=${ pi.currentPage - 1 }">&lt;</a></li>
					  	</c:when>
					  	<c:otherwise>
					  		<li class="page-item"><a class="page-link" href="">&lt;</a></li>
					  	</c:otherwise>
				  	</c:choose>
					<c:forEach begin="${ pi.startPage }" end="${ pi.endPage }" var="p">
			  			<li class="page-item"><a class="page-link" href="yrlist.exp?page=${ p }">${ p }</a></li>
			  		</c:forEach>
				  	<c:choose>
					  	<c:when test="${ pi.currentPage ne pi.maxPage }">
					  		<li class="page-item"><a class="page-link" href="yrlist.exp?page=${ pi.currentPage + 1 }">&gt;</a></li>
					  	</c:when>
					  	<c:otherwise>
					  		<li class="page-item"><a class="page-link" href="">&gt;</a></li>
					  	</c:otherwise>
				  	</c:choose>
				</ul>
			</div>
			
			<script>
				$(function(){
					$('.pagination > li:contains(${ pi.currentPage })').attr('class', 'page-item active');
					
				});
			</script>
			
			<!-- 게시글삭제성공 -->
			<c:if test="${ not empty sessionScope.error }">
				<script>
					console.log('${ sessionscope.error }');
					alert('${ error }');
				</script>
				<c:remove var="error" scope="session" />
			</c:if>
			
			<!-- 게시글삭제실패 -->
			<c:if test="${ not empty sessionScope.success }">
				<script>
					console.log('${ sessionScope.success }');
					alert('${ success }');
				</script>
				<c:remove var="success" scope="session" />
			</c:if>
		
		
		
		
		
		
			
			
			
			
			
			
		
		</section>
		
		<aside id="pageAsideRight" class="aside">
           &lt;aside&gt; <br>
           id=pageAsideRight<br>
	            여기는 pageAsideRight 공백공간 <br>
	            사이드바 넣을 수도 있음 <br>
	            필요하면 쓰세요 <br>
	            중앙정렬되어있어요 <br>
		</aside>
		
	</main>
	
	<br clear="both">
	
	<!-- footer -->
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