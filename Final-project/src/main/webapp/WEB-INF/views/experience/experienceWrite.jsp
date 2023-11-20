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


#summary{
	width : 100%;
}

#summary > div{
	float : left;
	padding : 40px;
	height : 100%;
	width : 50%;
}

#thumb{
	width : 400px;
	height : 400px;
}

input, select{
	display : block;
}

#content{
	width : 100%;
	resize : none;
	display : inline-block;
}

#asdf{
	height : 500px;
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
					
					카테고리 : <select id="category">
						<option value="1">화훼농장</option>
						<option value="2">과일농장</option>
						<option value="3">채소농장</option>
						<option value="4">꽃꽂이</option>
					</select>
					체험학습일 : <input type="date" />
					체험시간 : <input type="number" min=1 max=10 />
					모집인원 : <input type="number" min=1 max=100 />
					모집마감일 : <input type="date" />
				</div>
			</div>
			
			<script>
				console.log(new Date());
				// toISOString() => YYYY-MM-DDTHH:mm:ss.sssZ
				let mindate = new Date().toISOString().split('T')[0];
				$(() => {
					$('input[type=date]').prop('min', mindate);
				});
			</script>
			
			<br clear="both">
			
			<textarea id="content" placeholder="내용을 입력해 주세요." rows="5"></textarea>
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		
		
		
		
		
			
			
			
			
			
			
			
			
			
			
			
			
			

		
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