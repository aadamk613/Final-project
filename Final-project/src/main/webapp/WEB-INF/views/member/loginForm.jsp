<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
</head>
<style>

/* 화면틀입니다 안에 부분은 자기가 맡은 파트로 채워주세요 */
* {
    border: 1px solid skyblue;
	box-sizing: border-box;
}

#id_pw_wrap{width: 400px; height: 300px; margin: 40px auto;}

#id_pw_wrap > div{float: left;}

#idWrap, #pwWrap, #submitWrap{width: 300px; height: 80px; margin: 0px auto; }

#logoWrap{width: 300px; height: 60px; margin: 0px auto; 	
	font-size : 30px;
	font-weight: bold;
	line-height : 200%;
    color: #88c080;}

ul{
	list-style: none;
	margin: 5px 0px;
}
li{
	position: relative;
    display: inline-block;
	margin: 0 13px;
}

#page, #search{
	width : 100%;
	height : 100px;
	padding: 20px;
	text-align: center;
}

#pageFooter {
	width: 100%;
	height: auto;
}

.loginFormInput{
	width: 300px;
	height: 45px;
    font-size: 20px;
    font-weight: bolder;
    border-radius: 10px;
    border:2px solid #afdba3;
    color: rgb(83, 57, 32);
	margin: 10px auto;
}

.loginFormInput::placeholder {
	font-size: 20px;
    font-weight: bolder;
	color: #afdba3;
}

.button{
	width: 300px;
	height: 45px;
	cursor: pointer;
}
.forest{
    font-size: 20px;
    font-weight: bolder;
    border-radius: 10px;
    border:2px solid #afdba3;
	background-color: #afdba3;
    color: white;
	margin: 10px auto;
}

a{
    cursor: pointer;
	display: inline-block;
    font-size: 14px;
    line-height: 17px;
    text-decoration: none;
    color: #888;
	}
</style>
<body>

	<header id="pageHeader">
        &lt;header&gt;
        id=pageHeader
        여기는 헤더
	</header> 
	<main>
		<aside id="pageAsideLeft" class="aside">
      
		</aside>
		
		<section id="section">
	
			<div id="content">
                
				<article>
                <div id="id_pw_wrap">
					<form action="login.me" method="post">
						<div id="logoWrap">
							싹트다
						</div>
						<div id="idWrap">
							<input type="text" name="memId" placeholder="아이디" class="loginFormInput">
						</div>
						<div id="pwWrap">
							<input type="password" name="memPwd" placeholder="비밀번호" class="loginFormInput">
						</div>
						<div id="submitWrap">
							<button type="submit" class="button forest">로그인</button>
						</div>
					</form>
					<ul>
						<li><a href="#">아이디 찾기</a></li>
						<li><a href="insert.me">비밀번호 찾기</a></li>
						<li><a href="#">회원가입</a></li>
					</ul>
					
				</div>

				</article>
			</div>
			
		
		</section>
		
		<aside id="pageAsideRight" class="aside">
       
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
	</footer>


</body>
</html>