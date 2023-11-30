<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>화면 틀입니다 복사해서 사용해주세요</title>
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

.list-group {
	width: 100%;
}
.admin-menu {
	width: 100%;
}
#pageAsideLeft {
	display : block;
}

</style>
<body>
	<div>
		<div class="admin-menu">
			<ul class="list-group">
				<a href="memberView.admin" class="list-group-item list-group-item-action">회원상태변경</a>
				<a href="#" class="list-group-item list-group-item-action">댓글 신고조회</a>
				<a href="reportedArticleView.admin" class="list-group-item list-group-item-action">게시글 신고조회</a>
				<a href="hashtag.admin" class="list-group-item list-group-item-action">해시태그 관리</a>
				<a href="ticket.admin" class="list-group-item list-group-item-action">Ticket
				<span class="badge badge-primary badge-pill">${numTicket}</span></a>
				<a href="resolvedTicket.admin" class="list-group-item list-group-item-action">Ticket 처리목록</a>
			</ul>
		</div>
		<div>
			<h6>last login: ${loginUser.lastLogin}</h6>
		</div>
	</div>
</body>
</html>