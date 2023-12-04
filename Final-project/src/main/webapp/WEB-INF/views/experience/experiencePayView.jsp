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
<body>

	
	
	<button type="button" onclick="kakaopay();">결제하기</button>
	
	<script>
		
		// 1-1. 결제하기 버튼 누르면 이동
		function kakaopay(){
			$.ajax({
				url : 'yrreadyForPay.exp',
				success : result => {
					console.log(result);
					console.log(result.nextRedirectPcUrl);
					
					//  url을 보내면 성공url뒤에 쿼리스트링으로 pg_token을 넘겨줌
					location.href=result.nextRedirectPcUrl;
					
					//location.href='yrtest.exp/' + result;
					
					//kakao(result);
				},
				error : () => {
					console.log("결제 통신 오류");
				}
			
				
				
			})
			
		}
		

		function kakao(value){
			
			console.log("여기 들어와야 해");
			console.log(value);
			console.log(value.nextRedirectPcUrl);
			const nextUrl = value.nextRedirectPcUrl;
			//location.href=nextUrl;
			
			$.ajax({
				url : nextUrl,
				
				success : result => {
					console.log(result);
					
					//location.href='yrtest.exp/' + result;
				},
				error : () => {
					console.log("결제 통신 오류");
				}
			
				
				
			})
			
			
		}
	
	</script>





</body>
</html>