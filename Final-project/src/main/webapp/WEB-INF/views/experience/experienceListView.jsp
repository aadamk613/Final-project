<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>체험학습 목록 조회</title>
<link rel="stylesheet" href="resources/css/common/template.css">
</head>


<style>


* {
    border: 1px solid skyblue;
	box-sizing: border-box;
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
		
		<section id="pageSection">
		
			<h1>체험학습</h1>
			
			<thead>
				<table>
					<tr>번호</tr>
					<tr>카테고리</tr>
					<tr>제목</tr>
					<tr>지역</tr>
					<tr>모집마감일</tr>
					<tr>작성자</tr>
					<tr>
				
				
				</table>
			
			
			</thead>
		
		
		
		
		
		
		
		
		
			
			
			
			
			
			
		
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