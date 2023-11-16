<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성하기</title>

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
				게시글 작성
			</div>
			<div id="content">
				<article>
					<form action="insert.no" method="post">
					<input type="hidden" name="memNo" value="${loginUser.memNo }">
					<div id="boardCategoryWrap">
						<select name="category" id="boardCategory">
							<option value="1">공지사항</option>
							<option value="2">필독사항</option>
                        </select>
					</div>
					<div id="boardHeader">
						<input type="text" placeholder="제목을 입력해주세요" name="noticeTitle" required>		
					</div>
					<div id="fileWrap">
						<input type="file" name="file">
					</div>
					<div id="boardContent">
					<hr>
						<textarea id="boardContent" placeholder="내용을 입력해주세요" name="noticeContent" required></textarea>
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
</html>>