<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블로그 카테고리 관리 페이지</title>
<link rel="stylesheet" href="resources/css/common/template.css">

</head>
<style>


* {
    border: 1px solid skyblue;
	box-sizing: border-box;
}


#content{pagging: 20px;}

#content div{margin: 10px;}






#categoryInfo ul{
	width: 100%;
	margin: 0px;
	padding: 0px;
	list-style: none; 
	display: block;
}

#categoryInfo li{
	display: inline-block;
	cursor: pointer;
	height: 30px;
	}

</style>
<body>

	<header id="pageHeader">
		<jsp:include page="../common/header.jsp" />
	</header> 
	<main>
		<aside id="pageAsideLeft" class="aside">
		</aside>
		
		<section id="pageSection">
			
			<div id="contentTitle">
	   			블로그 카테고리 관리
			</div>
			
			<div id="content">
					
					<div id="pageSettingWrap">
						페이지 당 글 
						<input type="radio">1개
						<input type="radio">3개
						<input type="radio">5개
						<input type="radio">10개
					</div>
					
					<div id="categoryWrap">
						<div id="categoryInfo">
							카테고리 관리 설정
							<ul>
								<li><a href="insert.bl_ct?blogNo=${ blogNo }"><button>카테고리 추가</button></a></li>
								<li><a><button>구분선 추가</button></a></li>
								<li><a href="delete.bl_ct"><button>삭제</button></a></li>
							</ul>
							
							
						</div>
						<div id="categorySetting">
							<div id="category">
								카테고리<br>
								카테고리1<br>
								------1<br>
								카테고리1<br>
								카테고리1<br>
								------<br>
								카테고리1<br>
							</div>
						</div>
					</div>

			</div>
			
		
		</section>
		
		<aside id="pageAsideRight" class="aside">
		</aside>
		
	</main>
	
	<br clear="both">
	
	<footer id="pageFooter">
		<jsp:include page="../common/footer.jsp" />
	</footer>


</body>
</html>