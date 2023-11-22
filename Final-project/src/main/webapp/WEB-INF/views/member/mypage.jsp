<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
	<link rel="stylesheet" href="resources/css/common/template.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
<style>

	
    .myContent{
    	width:800px;
    	height:100px;
    }

	.introduceMyself{
		width:100%;
		height:200px;
	}

	.blogAddress{
		width:100%;
		height:auto;
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
			
			<div id="infoTitle">
                마이페이지(여긴 마이페이지입니다!)
			</div>
			
			<div id="content">
			
			<article id="pageArticle">
				
				<br>  
				
				<form action="loadImg.me" method="post" id="profile">
				<table border="1" align="center">
					<tr>
						<td colspan="5" rowspan="6" width="250" height="140">
							<img src="https://cdn-icons-png.flaticon.com/512/259/259987.png" id="myPhoto" width="200" height="120" readonly>
							<input type="file" name="file1" id="file1" onchange="loadImg(this, 1)">
						</td>
						<td align="center">ID</td>
						<td><input type="text" class="form-control" id="memId" value="${ sessionScope.loginUser.memId }" name="memId"  readonly></td>
						<td>닉네임</td>
						<td width="10" height="60"><input type="text" id="memNick" value="${ sessionScope.loginUser.memNick }" name="memNick"  readonly></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td width="130" height="60"><input type="text" id="memNick" value="${ sessionScope.loginUser.email }" name="email" readonly></td>
					</tr>
					<tr>
						<td height="60" align="center">블로그주소</td>
						<td colspan="3"><input type="text" class="blogAddress"></td>
					</tr>
					<tr>
						<td height="200" align="center">자기소개</td>
						<td colspan="3"><input type="text" class="introduceMyself"></td>
					</tr>
					</table>
					<br>
					<div class="btns" align="center">
						<button type="submit" class="button forest">수정하기</button>
						<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm">회원탈퇴</button>
					</div>
					</form>
					<br>
				<script>
					function loadImg(inputFile, num){
						if(inputFile.files.length == 1) { // 파일 첨부
                        // 선택된 파일을 읽어서 영역에 맞게 미리보기
                        // 파일을 읽어들일 FileReader객체 생성
                        let reader = new FileReader();
                        // console.log(inputFile.files[0]);
                        // FileReader객체로부터 파일을 읽어들이는 메소드를 호출
                        reader.readAsDataURL(inputFile.files[0]);
                        // 해당 파일을 읽어들이는 순간 파일만의 고유한 겁나긴 url이 부여됨
                        // 해당 url을 src속성의 값으로 부여할 것 (attr)
                        // 파일 읽기가 완료되면 실행할 익명함수를 정의
                        reader.onload = function(e){
							// e의 target => e.target => 이벤트 발생한 친구
                            // console.log(e.target);
                            // e.target.result에 파일의 url이 담긴다.
                            // 각 영역에 맞춰서 이미지 미리보기

							switch(num){
								case 1 : $('#myPhoto').attr('src', e.target.result); break;
							}
						}
					} else {
                        const str = '';
                        switch(num){
                            case 1 : $('#myPhoto').attr('src', str); break;
						}
						}
					};

				</script>
				</article>
			</div>
			
			<div id="pageArea">
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