<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입양식</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<Style>
	.content{
		width:25%;
		margin:auto;
	}
	.innerOuter{
		marigin:auto;
	}
	
	/* 화면틀입니다 안에 부분은 자기가 맡은 파트로 채워주세요 
	사용하면 안되는 id값 : pageHeader, pageAsideLeft, pageAsideRight, pageSection, contentTitle, content, 
				pageArea, searchArea, pageFooter
	위의 id는 참조한 css에 이름 이미 있어서 화면 모양이 이상해질 수 있습니다
	*/

* {
    border: 1px solid skyblue;
	box-sizing: border-box;
}

.joinFormWrap{
	font-size:30px;
	font-weight:bold;
	line-height:200%;
	color: #88c080;
}

</Style>
</head>
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

	<div class="content">
		<br><br>
		<div class="joinFormWrap" method="post">
			회원가입
		</div>
		<br>
		<form action="join.me" method="post">
			<div class="idWrap">
				* 아이디 : 
				<input type="text" name="memId" onkeydown="inputIdCheck();" class="checkId" maxlength="12" min="4" max="12" autofocus> <!-- 사용자가 키보드의 키를 누를떄 함수 inputIdCheck()가 발생됨 -->
				<button type="button" onclick="fn_dbCheckId()" name="dbCheckId" class="checkId">중복체크</button>
			</div>
			<div class="pwdWrap">
				* 비밀번호 :
				<input type="password" name="memPwd" maxlength="14" min="6" max="14" onkeyup="enterFn()" > 
				</div>
			<div class="pwdChkWrap">
				* 비밀번호 확인 :
				<input type="password" name="memPwdChk"  maxlength="14" min="6" max="14" onkeyup="enterFn()">
				</div>
			<div class="nkWrap">
				닉네임
				<input type="text" name="nickName" placeholder="한글/영문/숫자가능">
				</div>
			<div class="emailWrap" name="email">
				이메일
				<input type="text"placeholder="@ 기입필수!">
				</div>
			<div class="Qualification">
				개인/기업
				<br>
				<select name="userCheck">
				<option>개인</option>
				<option>기업</option>
				</select>
			</div>
			
			<br><br>
			
			<div class="btns" align="center">
				<button type="submit" class="btn btn-primary">회원가입</button>			
				<button type="reset" class="btn btn-danger">다시</button>			
			</div>
		</form>
			
			
			
			
			
			
			
			
			
			<script>
			$(function inputIdCheck(){
				$(document).on("keypress keyup keydown", "input[onlyNumber]", function(e){
					if(/^[ㄱ-ㅎ}ㅏ-ㅣ|가-힣]/g.test(this.value)){ //한글막기
						e.preventDefault();
						this.value = "";
					}
				)};
			)
			</script>
			
				
			<script>
				function enterFn(){
					if(window.event.keyCode == 13){
						passwordCheckFn();
					};
				};
				
				function checkPasswordMatch() {
				    var password = document.getElementById("password");
				    var confirmPassword = document.getElementById("passwordCheck");
				    var message = document.getElementById("passwordMatchMessage");

				    if (password.value === confirmPassword.value) {
				        message.
				       
				innerHTML = "비밀번호가 일치합니다.";
				        
				       
				// You can add more styling or validation logic here, like enabling a submit button.
				    } else {
				        message.
				       
				innerHTML = "비밀번호가 일치하지 않습니다.";
				        // You might want to disable the submit button or give a visual indication that the passwords don't match.
				    }
			</script>
		
		</div>
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