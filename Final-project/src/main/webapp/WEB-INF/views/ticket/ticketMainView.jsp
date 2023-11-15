<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ticket Main</title>
<link rel="stylesheet" href="resources/css/common/template.css">

</head>
<style>

/* 화면틀입니다 안에 부분은 자기가 맡은 파트로 채워주세요 
사용하면 안되는 id값 : pageHeader, pageAsideLeft, pageAsideRight, pageSection, contentTitle, content, 
				pageArea, searchArea, pageFooter
위의 id는 참조한 css에 이름 이미 있어서 화면 모양이 이상해질 수 있습니다
*/

* {
    border: 1px solid skyblue;
	box-sizing: border-box;
}

</style>
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
                Ticket List
			</div>
			
			<div id="content">
				<article id="pageArticle">
					<table id="tb" class="table table-sm table-hover" align="center" style="width: 78%" style="cursor:default">
						<thead class="thead-light">
							<tr>
								<th width="100">번호</th>
								<th width="300">제목</th>
								<th width="50">작성자</th>
								<th width="170">작성일</th>
							</tr>
						</thead>
						<tbody style="cursor:default">
							<c:choose>
								<c:when test="${empty requestScope.list}">
									<tr>
										<td colspan="5">조회된 게시글이 없습니다..</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${requestScope.list}" var="b">
										<tr>
											<td>${b.boardNo}</td>
											<td>${b.title}</td>
											<td>${b.writer}</td>
											<td>${b.createDate}</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</article>
			</div>
			
			<div id="pageArea">
                &lt;div&gt; <br>
                id=page
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