<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제험학습 결제</title>
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


</head>
<style>

h3{
	text-align : center;
}

.container{
	width : 500px;
	padding-top: 50px;
    margin: 50px 500px;
}

input {
   width: 100%;
   padding: 12px 20px;
   margin: 8px 0px 0px 0px;
   display: inline-block;
   border: 1px solid #ccc;
   box-sizing: border-box;
}

/* Set a style for all buttons */
button {
    background-color: rgb(88, 87, 87);
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

</style>
<body>
	<h3>${ loginUser.memId }님의 즐거운 체험학습 신청란입니다.</h3>
	<h3>결제까지 완료되어야 신청접수되오니 참고하시기 바랍니다.</h3>
	
	<div class="container">
		<input type="text" name="contact" placeholder="공지에 대한 연락을 받으실 연락처를 적으세요." /> <br />
		<input type="number" name="quantity" />
		
		<button type="button" onclick="kakaopay();">결제하기</button>
	</div>
	
	
	
	<script>
		
		// 1-1. 결제하기 버튼 누르면 이동
		
		
		
		
		function kakaopay(){
			$.ajax({
				url : 'yrreadyForPay.exp',
				data : {
					userId : '${loginUser.memId}',
					contact : $('input[name=contact]').val(),
					quantity : $('input[name=quantity]').val()
				},
				success : result => {
					// url을 보내면 성공url뒤에 쿼리스트링으로 pg_token을 넘겨줌
					location.href=result;
				},
				error : () => {
					console.log("결제 통신 오류");
				}
			})
		}
	</script>





</body>
</html>