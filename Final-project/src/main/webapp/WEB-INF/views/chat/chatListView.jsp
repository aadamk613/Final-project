<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<!-- jQuery 라이브러리 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
     <!-- 부트스트랩에서 제공하고 있는 스타일 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- 부트스트랩에서 제공하고 있는 스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- <link rel="stylesheet" href="resources/css/common/template.css"> -->
</head>
<body>
	<h1>그룹채팅</h1>
	<button onclick="connect();">접속</button>
	<button onclick="disconnect();">접속종료</button>
	<hr>
		<input type="text" id="text-input"/>
		<button onclick="send();">전송</button>
	<script>
		var socket;
		var nickname;
		function connect() {
			nickname = prompt('닉네임을 입력해주세요');
			const uri = 'ws://localhost:8001/final/gp';
			socket = new WebSocket(uri);
			socket.onopen = () => { //소켓이 열리면 호출되는 핸들러
				console.log("열림");
			}
			socket.onclose = () => { // 닫히면 호출됨
				console.log("닫힘");
			}
			socket.onerror = e => { // 에러생기면 호출
				console.log("에러생김");
				
			}
			socket.onmessage = e => {
				console.log(e);
				const div = e.data;
				$('.message-wrap').append(div + '</br>');
			}
	
		}
		function disconnect() {
			socket.close();
		}
		function send() {
			const text = $('#text-input').val();
			socket.send(nickname + ':' + text);
			$('#text-input').val('');
		}
	</script>
	<!-- 수신된 메시지가 출력될 영역 -->
	<div class="message-wrap" display="block"></div>
</body>
</html>