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

#content div{padding: 10px;}






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
	
#category ul{
	list-style: none; 
	margin: 0px;
	padding: 0px;
	width: 150px;
}

#categorySettingWrap {
	width: 100%; 
	height: auto;
}

#categorySettingWrap div{
	width: 100%; 
	height: auto;
}

#category{width: 100px;}

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
					categoryWrap
						<div id="categoryInfo">
							카테고리 관리 설정
							<ul>
								<li><button onclick="insertBlogCategory(10);">카테고리 추가</button></li>
								<li><button onclick="insertBlogCategory(99);">구분선 추가</button></li>
								<li><a href="delete.bl_ct"><button>삭제</button></a></li>
							</ul>
							
							
						</div>
						<div id="categorySettingWrap">
						categorySettingWrap
							<div id="category">
								category	
								
							</div>
							<div id="categorySetting">
								카테고리 명 : <input type="text">
								<button>이름 변경</button>
								<button>삭제하기</button>
								 
							</div>
						</div>
						
						<button>뒤로 가기</button>
					</div>

			</div>
			
		
		</section>
		
		<aside id="pageAsideRight" class="aside">
		</aside>
		
	</main>
	
	<script>
	
		$(() => {
			selectBlogCategory();
		});
		
		function selectBlogCategory(){
			$.ajax({
				url: 'select.bl_ct',
				data: {blogNo: ${ blogNo }},
				success: data => {
					console.log(data);
					
					let value='';
					for(let i in data){
						//console.log(data[i].categorySettingNo);
						
						var categorySettingNo = data[i].categorySettingNo;
						
						value  += '<ul>'
						       + '<li><button onclick="selectCategory(' + categorySettingNo +')">' + data[i].categoryMemName + '</button></li>'
						       + '</ul>'; 
					}
					$('#category').html(value);
					
				}, 
				error: () => {
					console.log('카테고리 불러오기 실패');
				}
			})
			
		}
		
		function selectCategory(){
			
			
			
			
		}
		
		function insertBlogCategory(cateNo){
			
			$.ajax({
				url: 'insert.bl_ct',
				data: {
					blogNo : ${ blogNo}, 
					categoryNo : cateNo
				},
				success: data => {
					selectBlogCategory();
					console.log(data);
				},
				error: () => {
					console.log('카테고리 생성 실패');
				}
			})
			
		}
		
		function selectCategory(catoNo){
			
			
			$('categorySetting').html(value);
		}
		
	</script>
	
	<br clear="both">
	
	<footer id="pageFooter">
		<jsp:include page="../common/footer.jsp" />
	</footer>


</body>
</html>