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
	
	.introduceMyself{
		font-size: 16px;
		line-height: 1.5;
	}
	input[name=file1]{display: none;}
	
	
    
</style>
</head>
<body>

	<header id="pageHeader">
		<jsp:include page="../common/header.jsp" />
	</header> 
	<main>
		<aside id="pageAsideLeft" class="aside">
			<jsp:include page="myPageAsideLeft.jsp"/>
		</aside>
		
		<section id="pageSection">
			
			<div id="contentTitle">
                내 정보수정
			</div>
			
			<div id="content">
			
			<article id="pageArticle">
				
				<br>  
				
				<form action="updateMyPage" method="post" enctype="multipart/form-data" id="profile">
				<table border="1" align="center">
					<tr>
						<!-- 마이페이지에서 이미지 표시 부분 -->
						<td colspan="5" rowspan="6" width="250" height="140">
						    <input type="file" name="file1" id="file1">
						    <img src="<c:url value='/resources/uploadFiles/myPage'/>" id="upImage" name="upImage" width="300" height="400" onclick="loadImg(this);">
						    <!-- console.log(upImage); -->
						</td>
						<td align="center">ID</td>
						<td><input type="text" class="form-control" id="memId" value="${ sessionScope.loginUser.memId }" name="memId"  readonly></td>
						<td>닉네임</td>
						<td width="10" height="60"><input type="text" id="memNick" value="${ sessionScope.loginUser.memNick }" name="memNick" ></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td width="130" height="60"><input type="text" id="memNick" value="${ sessionScope.loginUser.email }" name="email"></td>
					</tr>
					<tr>
						<td height="60" align="center">블로그주소</td>
						<td colspan="3"><input type="text" class="blogAddress"></td>
					</tr>
					<tr>
						<td height="200" align="center">자기소개</td>
						<td colspan="3"><textarea class="introduceMyself" rows="3" cols="50" ></textarea></td>
					</tr>
					</table>
					<br>
					<div class="btns" align="center">
						<button type="submit" class="button forest">수정하기</button>
						<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm">회원탈퇴</button>
					</div>
					</form>
					<br>
			</article>
			
				<script>
					function loadImg(){
						$('input[name=file1]').click();
					};
					
					$('input[name=file1]').on('change', function(inputFile){
						let reader = new FileReader();
						reader.readAsDataURL(inputFile.target.files[0]);
						reader.onload = e => {
							$('img[name=upImage]').attr('src', e.target.result);
						}
					});
				</script>
			</div>
			
			<!-- 회원탈퇴 버튼 클릭시 보열시 모달창 -->
			<div class="modal fade" id="deleteForm">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
					
						<!-- Modal Header -->
						<div class="modal-header">
							<h4 class="modal-title">회원탈퇴</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						
						<form action="delete.me" method="post">
							<!-- Modal body -->
							<div class="modal-body">
								<div align="center">
									탈퇴 후 복구가 불가능합니다. <br>
									탈퇴하시겠습니까?
								</div>
								<br>
									<label for="memPwd">PassWord : </label>
									<input type="text" placeholder="Enter your Password" id="memPwd" name="memPwd">
							</div>
							
							<!-- Modal footer -->
							<div class="modal-footer" align="center">
								<button type="submit" class="btn btn-danger">탈퇴</button>
							</div>
						</form>
					
					</div>
				</div>
			</div>
			<div id="pageArea">
			</div>
		
		</section>
		
		<aside id="pageAsideRight" class="aside">
			<jsp:include page="myPageAsideRight.jsp"/>
		</aside>
		
		
	</main>
	
	<br clear="both">
	
	<footer id="pageFooter">
		<jsp:include page="../common/footer.jsp" />
	</footer>
	

</body>
</html>