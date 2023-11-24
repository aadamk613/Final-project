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
		width:30%;
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
	width:150px;
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
				<input type="text" id="memberId" name="memId" class="checkId" maxlength="12" min="4" max="12" placeholder="아이디를 잘 입력해주세요" required> 
				<div id="checkResult" style="font-size:0.7em; display:none;"></div>
			</div>
			<div class="pwdWrap">
				* 비밀번호 :
				<input type="password" name="memPwd" maxlength="14" min="6" max="14" required > 
			</div>
			<div class="pwdChkWrap">
				* 비밀번호 확인 :
				<input type="password" name="memPwdChk"  maxlength="14" min="6" max="14" required>
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
				<button type="submit" class="disabled btn-forest">회원가입</button>			
				<button type="reset" class="btn btn-danger">다시</button>			
			</div>
		</form>
	</div>
	</article>
	</div>
	
	<script>
	$(function() {
		// 자주쓰는, 중복되는 요소는 변수로 지정해놓는게 나아서 해놓음
	    const $idInput = $('#memberId');
	    const $checkResult = $('#checkResult');
	    const $joinFormSubmit = $('#join-form :submit');

	    $idInput.on('input', function() {
	        const inputValue = $idInput.val();
	        const filteredValue = inputValue.replace(/[ㄱ-ㅎㅏ-ㅣ가-힣]/g, ''); // 한글을 필터링

	        $idInput.val(filteredValue); // 한글이 입력된 경우 필터링된 값으로 대체
			
	     	// 최소 5글자 이상 입력했을 떄만 AJAX 요청을 보내서 중복체크
	        if (filteredValue.length >= 5) {
	            $.ajax({
	                url: 'idCheck.me',
	                data: { checkId: filteredValue }, // 필터링된 값으로 중복 체크 요청
	                success: function(result) {
	                	
	                    console.log(result);
	                    
	                    if(result.substr(4) === 'N') { // 사용불가능
	                        $checkResult.show().css('color', 'red').text('이미 사용 중인 아이디입니다.');
	                        $joinFormSubmit.attr('disabled', true);
	                    } else { // 사용가능
	                        $checkResult.show().css('color', 'green').text('멋진 아이디네요!');
	                        $joinFormSubmit.removeAttr('disabled');
	                    }
	                },
	                error: function() {
	                    console.log('아이디 중복체크용 AJAX 통신 실패');
	                }
	            });
	        } else {
	            $checkResult.hide();
	            $joinFormSubmit.attr('disabled', true);
	        }
	    });
	});
	
	// 위 코드에서는 $idInput.on('input', function() {...})을 사용하여 아이디 입력란의 내용이 바뀔 때마다 이벤트를 감지함
	// 입력된 값에서 한글을 필터링하여 한글이 입력되면 해당 부분을 제거하고, 필터링된 값으로 다시 아이디 입력란에 설정
	// 그리고 필터링된 값이 5글자 이상일 경우, AJAX를 사용하여 서버에 중복 체크를 요청
	// 버에서 반환된 결과에 따라 중복 여부를 사용자에게 알려주고
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