<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>


</head>
<style>
section img{
	width: 100%;
}
</style>
<body>

	<header id="pageHeader">
		<jsp:include page="header.jsp" />
	</header> 
	<main>
		<aside id="pageAsideLeft" class="aside">
		</aside>
		
		
		<section id="pageSection">
			
			<img src="resources/images/mainImg.jpg">
		
		</section>
		
		<aside id="pageAsideRight" class="aside">
		</aside>
		
	</main>
	
	<br clear="both">
	
	<footer id="pageFooter">
	    <jsp:include page="footer.jsp" />
	</footer>


</body>
</html>