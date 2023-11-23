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
		<form action="join.me" method="post" id="join-form">
			<div class="idWrap">
				* 아이디 : 
				<input type="text" id="memberId" name="memId" class="checkId" maxlength="12" min="4" max="12" placeholder="아이디를 잘 입력해주세요" onkeyup="nokorean()" required> 
				<div id="checkResult" style="font-size:0.7em; display:none;"></div>
			</div>
			<div class="pwdWrap">
				* 비밀번호 :
				<input type="password" name="memPwd" maxlength="14" min="6" max="14" onkeyup="enterFn()" required > 
			</div>
			<div class="pwdChkWrap">
				* 비밀번호 확인 :
				<input type="password" name="memPwdChk"  maxlength="14" min="6" max="14" onkeyup="enterFn()" required>
			</div>
			<div class="nkWrap">
				닉네임
				<input type="text" name="memNick" placeholder="한글/영문/숫자가능" required>
			</div>
			<div class="emailWrap">
				이메일
				<input type="text"placeholder="@ 기입필수!" name="email" required>
			</div>
			<div class="Qualification">
				개인/기업
				<br>
				<select name="memStatus">
				<option value="U">개인</option>
				<option value="B">기업</option>
				</select>
			</div>
			<br>
			<div class="btns" align="center">
				<button type="submit" class="disabled btn-forest" disabled>회원가입</button>			
				<button type="reset" class="btn btn-danger">다시</button>			
			</div>
		</form>
	</div>
	</article>
	</div>
	
	<script>
	
		function noKorean(){
			
			var memId = document.getElementById('memId');
			
			var regExp = /^[a-zA-Z0-9]+$/;
			
				console.log('memId');
				
			if(!regExp.test(memId.value)){
				alert('해당 아이디는 사용하실 수 없습니다.');
				memId.select();
				memId.value = '';
				return false;
			}
			
			// 자주쓰는, 중복되는 요소는 변수로 지정해놓는게 나아서 해놓음
			const $idInput = $('#memberId');
			const $checkResult = $('#checkResult');
			const $joinFormSubmit = $('#join-form :submit');
			
			$idInput.keyup(function(){
			//console.log($idInput);
			
				// 최소 5글자 이상 입력했을 떄만 AJAX 요청을 보내서 중복체크
				if($idInput.val().length >= 5){
					//console.log($idInput.val());
					
					$.ajax({
						url : 'idCheck.me',
						data :  {checkId : $idInput.val()},
						success : function(result){
							
							console.log(result);
							if(result.substr(4) === 'N'){ // 사용불가능
								
								$checkResult.show().css('color', 'red').text('어? 잠시만요! 중복된 아이디가 있네요~?');
								$joyFormSubmit.attr('disabled', true);
							}
							else {  // 사용가능
								$checkResult.show().css('color', 'green').text('와우~ 아주아주 멋진 아이디인걸요?');
								$joinFormSubmit.removeAttr('disabled');
							}
						},
						error : function(){
							console.log('아이디 중복체크용 AJAX 통신실패');
						}
					});
				}
				else{
					$checkResult.hide();
					$joinFormSubmit.attr('disabled', true);
				}
				
		});
		})
	</script>
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