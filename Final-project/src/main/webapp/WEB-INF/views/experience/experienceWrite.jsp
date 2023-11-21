<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>체험학습 게시글 작성</title>

<style>
* {
    border: 1px solid skyblue;
	box-sizing: border-box;
}

#summary > div{
	float : left;
	margin : 40px;
}

#thumb{
	width : 400px;
	height : 400px;
}

input{
	display : block;
}





</style>
</head>
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
			
			<h1><input type="text" placeholder="제목을 입력해 주세요." /></h1>
			<hr>
			
			<div id="summary">
				<div>
					<img src="" id="thumb" />
				</div>
				<div>
					<h5>※필수 입력 사항입니다. </h5>
					<select value="선택">
						<option value="1">화훼농장</option>
						<option value="2">과일농장</option>
						<option value="3">채소농장</option>
						<option value="4">꽃꽂이</option>
						
					</select>
					<input type="text" />
					<input type="text" />
					<input type="text" />
					<input type="number" />
					<input type="text" />
				</div>
			
			</div>
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			

		
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