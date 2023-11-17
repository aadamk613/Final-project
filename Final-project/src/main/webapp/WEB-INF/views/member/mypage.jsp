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
			
			<div id="contentTitle">
                마이페이지(여긴 마이페이지입니다!)
			</div>
			
			<div id="content">
			
			<article id="pageArticle">
				
				<div id="wrap">
        			<div id="myImgWrap1">
            			<div id="header_1">
                			<img src="https://geojecci.korcham.net/images/no-image01.gif" alt="myImage" width="200" height="150">
            			</div>
            		<div id="myImgWrap2">
            	</div>
            <div id="myImgWrap3">
                <a href=""><img src="https://blog.kakaocdn.net/dn/uqJpZ/btqyenBIIXx/mh1Cc5F023UGpfQTFBdqV0/img.jpg" width="35" height="35" img-align="right" alt=""></a>
            </div>
        </div>
				
					
						
					</table>
					
							
					<br><br><br>
						
					<form action="update.me" method="post" enctype="multipart/form-data">
						<div class="form-group">
							<label for="userId">*ID : </label>
							<input type="text" class="form-control" id="memId" value="${ sessionScope.loginUser.memId }" name="memId" readonly><br>
							
							<label for="email">닉네임</label>
							<input type="text" class="form-control" id="memNick" value="${ sessionScope.loginUser.email }" name="memNick">
							
							<label for="email">이메일</label>
							<input type="text" class="form-control" id="memNick" value="${ sessionScope.loginUser.email }" name="memNick">
						</div>
						
						<br>
						
						<div class="btns" align="center">
							<button type="submit" class="button forest">수정하기</button>
							<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm">회원탈퇴</button>
						</div>
					</form>

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