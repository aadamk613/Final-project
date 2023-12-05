<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제성공</title>
</head>
<body>

	<h1>결제성공</h1>
	${ payment.userId }
	${ payment.orderId }
	${ payment.contact }
	${ payment.payStatus }
	${ payment.tid }
	${ payment.approvedAt }
	${ payment.quantity }
	

</body>
</html>