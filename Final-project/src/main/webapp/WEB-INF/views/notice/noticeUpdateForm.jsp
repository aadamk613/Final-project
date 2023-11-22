<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정하기</title>

<link rel="stylesheet" href="resources/css/board/boardEnrollForm.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js">
</script>

</head>
<body>

	<header id="pageHeader">
		<jsp:include page="../common/header.jsp" />
	</header> 
	<main>
		<aside id="pageAsideLeft" class="aside">
		<jsp:include page="../common/boardSideBar.jsp"/>
		</aside>
		
		<section id="section">
			
			<div id="contentTitle">
				게시글 수정
			</div>
			<div id="content">
				<article>
					<form id="updateForm" action="update.no" method="post" enctype="multipart/form-data">
					<input type="hidden" name="noticeNo" value="${n.noticeNo }">
					<input type="hidden" name="memNo" value="${loginUser.memNo }">
					<div id="boardCategoryWrap">
						<input type="text" name="category" value="${ n.category == 1 ? "공지사항" : '필독사항'}" readonly>
					</div>
					<div id="boardHeader">
						<input type="text" placeholder="${n.noticeTitle }" name="noticeTitle" required>		
					</div>
					<div id="fileWrap">
						<input type="file" name="reUpfile">
						현재 업로드된 파일:
						  <c:forEach var="file" items="${f}">
						<c:if test="${ not empty file.originalName }">
						    <a href="/finalProject${file.filePath }${file.updateName}" download="${file.originalName}">${file.originalName}</a>
						    <input type="hidden" value="${ file.originalName }" name="originalName"/>
						    <input type="hidden" value="${ file.updateName }" name="updateName"/>
						    <input type="hidden" value="${ file.fileNo}" name="fileNo"/>
						</c:if>
						  </c:forEach>
					</div>
					<div id="boardContent">
					<hr>
						<textarea id="boardContent" placeholder="${n.noticeContent }" name="noticeContent" required></textarea>
					<hr>
					</div>
					<button type="submit" class="btn btn-primary" id="buttonWrite">글 작성</button>
				</form>
				</article>
			</div>
			
		
		</section>
		
		<aside id="aside2" class="aside">
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