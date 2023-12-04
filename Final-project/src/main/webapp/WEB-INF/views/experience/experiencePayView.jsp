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

		function kakaopay(){
			$.ajax({
				url : 'yrreadyForPay.exp',
				success : result => {
					console.log(result);
					
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