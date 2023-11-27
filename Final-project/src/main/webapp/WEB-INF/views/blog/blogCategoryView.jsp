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
	float: left;
}

#category{width: 40%; }

#categorySetting{width: 60%;}

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
								<li><button onclick="insertBlogCategory(10);">카테고리 추가</button></li>
								<li><button onclick="insertBlogCategory(99);">구분선 추가</button></li>
							</ul>
							
							
						</div>
						<div id="categorySettingWrap">
							<div id="category">
								category	
								
							</div>
							
							<div id="categorySetting">
								
								<!-- 
								카테고리 명 : <input type="text" id="rename">
								<button onclick="categoryRename();">이름 변경</button>
								<button onclick="categoryDelete();">삭제하기</button>
								-->
								
							</div>
								<br clear="both">
						</div>
						
						<br clear="both">
						
						<a href="javascript:window.history.back();"><button >뒤로 가기</button></a>
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
					//console.log(data);
					
					let value='';
					for(let i in data){
						
						var categorySettingNo = data[i].categorySettingNo;
						var categoryMemName = data[i].categoryMemName;
						var categoryNo = data[i].categoryNo;
						
						if(categoryNo == 99){
							value += '<ul>'
							       + '<li id="aa"><button onclick="selectCategory(' 
							       + categorySettingNo 
							       + ',\''
							       + categoryMemName
							       + '\')">' 
							       + '----------------'
							       + '</button></li>'
							       + '</ul>'; 
							
						} else {
							
						value  += '<ul>'
						       + '<li id="aa"><button onclick="selectCategory(' 
						       + categorySettingNo 
						       + ',\''
						       + categoryMemName
						       + '\')">' 
						       + data[i].categoryMemName 
						       + '</button></li>'
						       + '</ul>'; 
						}
						
					}
					$('#category').html(value);
					
				}, 
				error: () => {
					console.log('카테고리 불러오기 실패');
				}
			})
			
		}

		function selectCategory(cateNo, cateName){
			
			console.log(cateNo, cateName);
			let categoryValue = '';
			
			categoryValue += '카테고리 명 : <input type="text" id="rename" class="rename" placeholder="' + cateName + '">'
				  // + '<button onclick="categoryRename(' + cateNo +');">이름 변경</button>'
				+ '<button onclick="categoryRename(' + cateNo + ', this)">이름 변경</button>'
				  + '<button onclick="categoryDelete(' + cateNo + ');">삭제하기</button>';

			$('#categorySetting').html(categoryValue);
			console.log("d");
			console.log($('#rename').val());
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
					console.log('카테고리 생성 통신 실패');
				}
			})
			
		}
		
		
		function categoryRename(cateNo, cateName){
			console.log(cateName);
			//console.log($(arguments[1]).parent().find('.rename').val());
			const str = $(arguments[1]).parent().find('.rename').val();
			$.ajax({
				url: 'update.bl_ct',
				data: {categorySettingNo: cateNo,
					   categoryMemName: str,
					   blogNo : ${ blogNo }},
				success: data => {
					selectBlogCategory();
				}, 
				error: () => {
					console.log('카테고리 이름 변경 통신 실패');
				}
			})
			
		}
		
		function categoryDelete(cateNo){
			
			if(confirm("카테고리를 삭제하면 카테고리 안의 게시글도 삭제됩니다. 정말 삭제하시겠습니까??")){
				
				$.ajax({
					url: 'delete.bl_ct',
					data: {categorySettingNo: cateNo,
						   blogNo : ${ blogNo }},
					success : data => {
						selectBlogCategory();
						alert(data);
					}, 
					error: () => {
						alert('카테고리 삭제 통신 실패');
					}
				})
				
				
			}else{
				//alert("제출실패");
			}
		}
		
		
	</script>
	
	<br clear="both">
	
	<footer id="pageFooter">
		<jsp:include page="../common/footer.jsp" />
	</footer>


</body>
</html>