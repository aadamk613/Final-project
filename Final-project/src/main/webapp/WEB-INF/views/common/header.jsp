<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<link rel="stylesheet" href="resources/css/common/template.css">
<link rel="stylesheet" href="resources/css/common/header.css">
<!-- jQuery 라이브러리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- 부트스트랩에서 제공하고 있는 스타일 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- 부트스트랩에서 제공하고 있는 스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- Nanum Gothic Regular 400, Bold 700, ExtraBoard 800 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">

</head>

<body>
		<div id="headerWrap">
			<div id="logoWrap">
				<div id="logo">
					<a href="main">싹 트 다</a>
				</div>
			</div>
			<div id="menubarWrap">
				<ul>
					<li><a href="#">스토어</a></li>
					<li><a href="yrlist.exp">체험학습</a></li>
					<li><a href="#">일손모집</a></li>
					<li><a href="main.co">커뮤니티</a></li>
					<li><a href="main.bl">블로그</a></li>
					<c:choose>
						<c:when test="${sessionScope.loginUser.memStatus eq 'A'}">
							<li><a href="main.admin">관리자메뉴</a></li>	
						</c:when>
						<c:otherwise>
							<li><a href="createTicket.tk">마이페이지</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<div id="loginWrap">
				<ul>
					<c:choose>
						<c:when test="${ empty sessionScope.loginUser }" >
							<li><a id="login" href="loginForm.me">로그인</a></li>
						</c:when>
						<c:otherwise>
							<li><a id="login" href="logout.me">로그아웃</a></li>
						</c:otherwise>
					</c:choose>
					<li><a href="chatMain.ch"><img src="resources/images/comment.png" /></a></li>
					<li><a href="#"><img src="resources/images//alarm.png" /></a></li>
				</ul>
			</div>
		</div>
		
		<c:if test="${ not empty alertMsg }">
			<script>
				alert('alertMsg');
			</script>
			<c:remove var="alertMsg" scope="session"/>
		</c:if>
		
</body>
</html>