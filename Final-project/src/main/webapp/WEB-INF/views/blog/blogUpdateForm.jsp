<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블로그 수정 화면</title>
<link rel="stylesheet" href="resources/css/common/template.css">

</head>
<style>


* {
    border: 1px solid skyblue;
	box-sizing: border-box;
}
article{
	
	height: 550px;
	margin : 20px;
	border: 1.5px solid #c6c6c6;
	border-radius: 10px;
}
#contentTitle{
    font-size: 35px ;
    font-weight: bold;
    color: #00610C;
    padding: 20px;
}

#blogInfo{
    font-size: 20px ;
    padding: 20px;
}

#createFormWrap{
    padding: 20px;
}

#createForm{
    border: none;
    font-size: 20px;
    width: 100%;
    border-spacing: 5px;
    
}

#createForm th{
    width: 25%;
    text-align: left;
    padding: 8px;
    background-color: beige;
    color: rgb(83, 57, 32);;
}

#createForm td{
    width: 75%;
    text-align: left;
    padding: 5px;
    border: 1px solid #c6c6c6;
}
textarea{
    resize: none;
    width: 99%;
    height: 100px;
    outline-color: #afdba3;
    border: none;
}

#blogButtonWrap {
   width: 100%;
   height: 100px; 
}

#blogButtonWrap > div{
   margin-top: 30px;
   float: left;
   width: 50%;
   height: 70px;
   text-align: center;
}

input[type=text] {
            width: 99%;
            height: 35px;
            font-size: 23px;
            font-weight: bolder;
            border-radius: 10px;
            border: none;
            color: rgb(83, 57, 32);
            outline-color: #afdba3;
}
.button{
	width: 250px;
	height: 45px;
	cursor: pointer;
}
.forest{
    font-size: 20px;
    font-weight: bolder;
    border-radius: 10px;
    border:2px solid #afdba3;
	background-color: #afdba3;
    color: white;
	margin: 10px auto;
}
.beige{
    font-size: 20px;
    font-weight: bolder;
    border-radius: 10px;
    border:2px solid beige;
	background-color: beige;
    color: rgb(83, 57, 32);
	margin: 10px auto;
}

a{
    cursor: pointer;
	display: inline-block;
    font-size: 14px;
    line-height: 17px;
    text-decoration: none;
    color: #888;
	}
	
#imageInputWrap{
  width: 300px; height: 300px; 
  display: flex;
  align-items: center;
  justify-content: center;

}

input[name=upfile]{display: none; }

img[name=imageThumbnail]{    
    width: 100%;
    height: 100%;
    object-fit: cover;
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
				블로그 정보 수정
			</div>
			
			<div id="content">
               <div id="blogInfo">
                   	블로그 관심사와 관심 식물 카테고리를 설정하시면 관련 글을 추천해드립니다
               </div>
               
               
				<article id="pageArticle">
				
					<div id="createFormWrap">
                        <form action="/final/blog/update.bl" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="blogNo" value="${ blog.blogNo }">
                        <input type="hidden" name="memNo" value="${ blog.memNo }">
                        <input type="hidden" name="updateName" value="${ blog.updateName }">
                        <input type="hidden" name="filePath" value="${ blog.filePath }">
							<table id="createForm">
								<tr>
									<th>블로그 이름</th>
									<td><input type="text" id="blogTitle" name="blogTitle"
										placeholder="${ sessionScope.loginUser.memNick }님의 블로그"
										value="${ blog.blogTitle }">
									</td>
								</tr>
								<tr>
									<th>블로그 소개글</th>
									<td><textarea id="blogIntroduce" name="blogIntroduce"
											placeholder="${ sessionScope.loginUser.memNick }님의 블로그입니다.">${ blog.blogIntroduce }
										</textarea></td>
								</tr>
								<tr>
									<th>블로그 이미지</th>
									<td>
									<div id="imageInputWrap">
									<input type="file" name="upfile" >
									<c:choose>
										<c:when test="${ not empty blog.updateName }">
											<img src="${ blog.filePath }${ blog.updateName }" name="imageThumbnail" onclick="insertImage(this);" >
										</c:when>
										<c:otherwise>
											<img src="/final/resources/images/defaultProfile.png" name="imageThumbnail" onclick="insertImage(this);">
										</c:otherwise>
									</c:choose>
									</div>
									</td>
								</tr>
								<tr>
									<th>블로그 관심사</th>
									<td><select name="blogInterest" value="blogInterest">
											<option value="no">선택안함</option>
											<option value="book">문학&middot;책</option>
											<option value="movie">영화</option>
											<option value="art">미술&middot;디자인</option>
											<option value="show">공연&middot;전시</option>
											<option value="music">음악</option>
											<option value="drama">드라마</option>
											<option value="enter">스타&middot;연예인</option>
											<option value="animation">만화&middot;애니</option>
											<option value="broadcast">방송</option>
											<option value="daily">일상&middot;생각</option>
											<option value="parenting">육아&middot;결혼</option>
											<option value="pet">반려동물</option>
											<option value="fasion">패션&middot;미용</option>
											<option value="interior">인테리어&middot;DIY</option>
											<option value="cook">요리&middot;레시피</option>
											<option value="review">상품리뷰</option>
											<option value="game">게임</option>
											<option value="sport">스포츠</option>
											<option value="picture">사진</option>
											<option value="car">자동차</option>
											<option value="hobby">취미</option>
											<option value="travel">여행</option>
											<option value="restaurant">맛집</option>
											<option value="computer">IT&middot;컴퓨터</option>
											<option value="health">건강</option>
											<option value="economy">경제</option>
											<option value="language">외국어</option>
											<option value="edu">교육</option>
									</select></td>
								</tr>
								<tr>
									<th>관심 식물</th>
									<td><input type="text" name="plantPrefer" value="g"></td>
								</tr>
							</table>
							<div id="blogButtonWrap">
                                <div><button type="submit" class="button forest" id="blogUpdateButton">정보 수정</button></div>
                                <div><a href="/final/blog/select.bl?blogNo=${ blog.blogNo }"><button type="button" class="button beige" id="goBlogHome">돌아가기</button></a></div>
                            </div>
                            </form>
					</div>

				</article>
			</div>

			<script>
				
				// 블로그 타이틀 입력 시 포커스되면 input의 value값 ''
               	$('input[name=blogTitle]').on('focus', () => {
               		$('input[name=blogTitle]').val('');
               		return true;
               	});
               	
               	// input type="file"숨기고 이미지 클릭시 파일 첨부
               	function insertImage(){
               		$('input[name=upfile]').click();
               	};
               	
               	// 이미지를 첨부했을 시 미리보기 가능하게
               	$('input[name=upfile]').on('change', function(inputFile){
					let reader = new FileReader();
					reader.readAsDataURL(inputFile.target.files[0]);
					reader.onload = e => {
						$('img[name=imageThumbnail]').attr('src', e.target.result);
					}
				});
               	
               </script>
			
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