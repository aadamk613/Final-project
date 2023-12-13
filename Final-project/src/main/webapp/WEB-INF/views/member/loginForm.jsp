<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.5.0/kakao.min.js" integrity="sha384-kYPsUbBPlktXsY6/oNHSUDZoTX6+YI51f63jCPEIPFP09ttByAdxd2mEjKuhdqn4" crossorigin="anonymous"></script>
<script src="https://accounts.google.com/gsi/client" async></script>

<script>
	// kakao login APi 
	// SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해야 합니다.
	Kakao.init('c17039c1894d25ecc561f2fb009cbcf6');
	// SDK 초기화 여부를 판단합니다.
	console.log(Kakao.isInitialized());
</script>
</head>
<style>

#pageHeader{height: 200px;}

#id_pw_wrap{width: 400px; height: 300px; margin: 40px auto;}

#id_pw_wrap > div{float: left;}

#idWrap, #pwWrap, #submitWrap{width: 300px; height: 80px; margin: 0px auto;}

#logoWrap{width: 300px; height: 60px; margin: 0px auto; 	
	font-size : 30px;
	font-weight: bold;
	line-height : 200%;
    }
    
#logoWrap a{
	color: #88c080;
	font-size : 30px;
	font-weight: bold;
}

ul{
	list-style: none;
	margin: 5px 0px;
}

li{
	position: relative;
    display: inline-block;
	margin: 0 13px;
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

li a{
	display: inline-block;
    font-size: 14px;
    line-height: 17px;
    color: #888;
	}
	
a{
    cursor: pointer;
 	text-decoration: none;
}

</style>
<body>

	<header id="pageHeader">
	</header> 
	<main>
		<aside id="pageAsideLeft" class="aside">
      
		</aside>
		
		<section id="pageSection">
	
			<div id="content">
                
				<article>
                <div id="id_pw_wrap">
					<form action="login.me" method="post">
						<div id="logoWrap" >
							<a href="main">싹트다</a>
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
						<li><a href="joinForm.me">회원가입</a></li>
					</ul>
					
				</div>
				 <!-- 네이버 로그인 버튼 노출 영역 -->
				 <div align="center">
					<ul >
						<li>
							<div align="center" id="naver_id_login"></div>
						</li>
						<li>
							<div align="center" id=""kakao-login>
								<a href="javascript:loginClick()">
									<img src="resources/images/Kakao login.png" alt="카카오로그인버튼"/>
								</a>
						</div>
						</li>
						<li>
							<div class="g_id_signin"
								data-type="standard"
								data-shape="rectangular"
								data-theme="filled_blue"
								data-text="signin_with"
								data-size="large"
								data-logo_alignment="left">
							</div>
						</li>
					</ul>
				</div>
				<div id="g_id_onload"
						data-client_id="347219723290-po60vc83hnfdh6pac0tfgufblgqmvvbk.apps.googleusercontent.com"
						data-context="signin"
						data-ux_mode="popup"
						data-login_uri="http://localhost:8001/final/googleLogin.me"
						data-auto_prompt="false">
				</div>
				
				<!-- //네이버 로그인 버튼 노출 영역 -->
				<script type="text/javascript">
					var naver_id_login = new naver_id_login("RoTjq9rTwVYvgRm6M73T", "http://localhost:8001/final/naverLogin.me");
					var state = naver_id_login.getUniqState();
					naver_id_login.setButton("green", 3,47);
					naver_id_login.setDomain("http://localhost:8001/final");
					naver_id_login.setState(state);
					naver_id_login.setPopup();
					naver_id_login.init_naver_id_login();
				</script>
				
			
				<script>
					function loginClick() {
						Kakao.Auth.authorize({
							redirectUri: 'http://localhost:8001/final/kakaoLogin.me'
						});
					};
				</script>
				</article>
			</div>
			
		
		</section>
		
		<aside id="pageAsideRight" class="aside">
       
		</aside>
		
	</main>
	
	<br clear="both">
	
	<footer id="pageFooter">
	</footer>


</body>
</html>