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
.error {
            color: red;
            font-size: 0.7em;
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
				<input type="text" id="memberId" name="memId" class="checkId" maxlength="12" min="4" max="12" placeholder="한글은 쓸 수 없어요" required> 
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
				<input type="email"placeholder="@기입필수!" name="email" pattern="/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i
" required>
			</div>
			<div class="Qualification">
		    개인/기업
		    <br>
		    <select name="memStatus" id="memStatus">
		        <option value="U">개인</option>
		        <option value="B">기업</option>
		    </select>
		</div>
		<br>
		<div class="btns" align="center">
		    <button type="submit" class="disabled btn-forest" onclick="register()">회원가입</button>
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

		        if (filteredValue.length >= 5) {
		            // AJAX 요청을 통해 아이디 중복 체크
		            $.ajax({
		                url: 'idCheck.me',
		                data: { checkId: filteredValue }, // 필터링된 값으로 중복 체크 요청
		                success: function(result) {
		                    console.log(result);
		                    if (result.substr(4) === 'N') {// 사용불가능 
		                        $checkResult.show().css('color', 'red').text('이미 사용 중인 아이디입니다.');
		                        $joinFormSubmit.attr('disabled', true);
		                    } else {
		                        $checkResult.show().css('color', 'green').text('사용 가능한 아이디입니다.');
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
	// $idInput.on('input', function() {...})을 사용하여 아이디 입력란의 내용이 바뀔 때마다 이벤트를 감지함
	// 입력된 값에서 한글을 필터링하여 한글이 입력되면 해당 부분을 제거하고, 필터링된 값으로 다시 아이디 입력란에 설정
	// 그리고 필터링된 값이 5글자 이상일 경우, AJAX를 사용하여 서버에 중복 체크를 요청
	// 서버에서 반환된 결과에 따라 중복 여부를 사용자에게 알려주고
	// 폼 제출 버튼을 활성화 하거나 비활성화 해줌

    
   $(function() {
        $('#join-form').submit(function(e) {
            e.preventDefault(); // 기존의 폼 제출 이벤트 막기

            const formData = $(this).serialize(); // 폼 데이터 직렬화, 직렬화는 입력받은 여러 데이터를 하나의 쿼리 문자열로 만들어주는것

            $.ajax({	// 페이지를 새로고침하지 않고 데이터를 서버로 보낼 수 있는 방법으로 ajax를 사용했다 
                type: 'POST',
                url: 'join.me',
                data: formData,
                success: function(response) {

                    const memStatus = $("#memStatus option:selected").val();	 // 물론 다른페이지로 이동하는 방법은 자바스크립트 영역이라 
																				 // 
                    if (memStatus === 'B') {
                        location.href = 'http://localhost:8001/final/businessPage.me'; 
                        // 기업인 경우, businessPage.jsp로 이동
                    } else {
                        location.href = 'http://localhost:8001/final/';
                        // 개인 회원가입 로직 처리
                    }
                    // 이렇게 한 이유 ? 회원가입 폼에서 회원 상태를 선택하고 회원가입 버튼을 클릭하면 
                    // 해당 회원 상태에 따라 페이지를 이동할 수 있음. 선택된 값이 "B"면 businessPage.jsp로 이동하고
                    // 그렇지 않은 경우에는 일반 유저로 분류되어 메인페이지로 이동한다. 
                },
                error: function() {
                    console.log('서버 요청 실패');
                }
            });
        });
    });
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