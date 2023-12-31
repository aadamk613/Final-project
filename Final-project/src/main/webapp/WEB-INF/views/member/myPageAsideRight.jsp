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

</style>
<body>
	<span>현재 시간: </span> <br/> 
	<span id="clock">zzz</span>
	<script>
		function printTime() {
				var clock = document.getElementById("clock");
				var now = new Date();

				clock.innerHTML = now.getFullYear() + "/" +
				(now.getMonth()+1) + "/" +
				now.getDate() + "  " +
				now.getHours() + ":" +
				now.getMinutes() + ":" +
				now.getSeconds();

				setTimeout("printTime()", 1000);
				}

				window.onload = function() {
				printTime();
				};
		</script>
</body>
</html>