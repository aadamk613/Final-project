<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>

<link rel="stylesheet" href="/final/resources/css/common/header.css">
<link rel="stylesheet" href="/final/resources/css/common/template.css">

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

    <!-- Alertify -->
	<!-- JavaScript -->
	<script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>
	<!-- CSS -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
	<!-- Default theme -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/default.min.css"/>
	<!-- Semantic UI theme -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/semantic.min.css"/>
	<!-- Bootstrap theme -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/bootstrap.min.css"/>
	<link rel="stylesheet" href="/final/resources/css/common/template.css">
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
					<li><a href="/final/yrlist.exp">체험학습</a></li>
					<li><a href="#">일손모집</a></li>
					<li><a href="/final/main.co">커뮤니티</a></li>
					<li><a href="/final/blog/main.bl">블로그</a></li>
					<c:choose>
						<c:when test="${sessionScope.loginUser.memStatus eq 'A'}">
							<li><a href="main.admin">관리자메뉴</a></li>	
						</c:when>
						<c:otherwise>
							<li><a href="myPage.me">마이페이지</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<div id="loginWrap">
				<ul>
					<c:choose>
						<c:when test="${ empty sessionScope.loginUser }" >
							<li><a id="login" href="/final/loginForm.me">로그인</a></li>
						</c:when>
						<c:otherwise>
							<li><a id="login" href="/final/logout.me">로그아웃</a></li>
						</c:otherwise>
					</c:choose>
					<li><a href="chatMain.ch"><img src="/final/resources/images/comment.png" /></a></li>
					<li><a href="#"><img src="/final/resources/images//alarm.png" /></a></li>
				</ul>
			</div>
		</div>
		
		<c:if test="${ not empty sessionScope.alertMsg }">
			<script>
				alertify.alert('알림', '${ sessionScope.alertMsg }', function(){alertify.success('확인')});
			</script>
			<c:remove var="alertMsg" scope="session"/>
		</c:if>
		
		
		
</body>
</html>