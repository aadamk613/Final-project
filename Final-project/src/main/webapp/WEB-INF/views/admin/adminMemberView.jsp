<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원상태변경</title>
<link rel="stylesheet" href="resources/css/common/template.css">

</head>
<style>

/* 화면틀입니다 안에 부분은 자기가 맡은 파트로 채워주세요 
사용하면 안되는 id값 : pageHeader, pageAsideLeft, pageAsideRight, pageSection, contentTitle, content, 
				pageArea, searchArea, pageFooter
위의 id는 참조한 css에 이름 이미 있어서 화면 모양이 이상해질 수 있습니다
*/

* {
    border: 1px solid skyblue;
	box-sizing: border-box;
}

</style>
<body>

	<header id="pageHeader">
		<jsp:include page="../common/header.jsp" />
	</header> 
	<main>
		<aside id="pageAsideLeft" class="aside">
        	<jsp:include page="adminAsideLeft.jsp"/>
		</aside>
		
		<section id="pageSection">
			
			<div id="contentTitle">
                회원상태변경
			</div>
			
			<div id="content">
				<article id="pageArticle">
					<table id="tb" class="table table-sm table-hover" align="center" style="width: 100%" style="cursor:default">
						<thead class="thead-light">
							<tr>
								<th width="20">번호</th>
								<th width="30">상태</th>
								<th width="50">아이디</th>
								<th width="50">닉네임</th>
								<th width="100">이메일</th>					
								<th width="30">온도</th>
								<th width="50">블로그번호</th>
								<th width="170">마지막 로그인</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</article>
			</div>
			
			
		</section>
		
		<aside id="pageAsideRight" class="aside">
			<jsp:include page="adminAsideRight.jsp"/>
		</aside>
		
	</main>
	
	<br clear="both">
	
	<footer id="pageFooter">
		<jsp:include page="../common/footer.jsp" />
	</footer>

<script>
	$(() => {
			$.ajax({
				url : 'getMemberList.me',
				success : data => {
					let result = '';
					for (let i in data) {
						result += '<tr>'
										+ '<td>' + data[i].memNo + '</td>'
										+ '<td>' + data[i].memStatus + '</td>'
										+ '<td>' + data[i].memId + '</td>'
										+ '<td>' + data[i].memNick + '</td>'
										+ '<td>' + data[i].email + '</td>'
										+ '<td>' + data[i].memTemp + '</td>'
										+ '<td>' + data[i].blogNo + '</td>'
										+ '<td>' + data[i].lastLogin + '</td>'
										+ '<tr>';			
					}
					$('#tb > tbody').html(result);
				}
			})
		});
</script>
</body>
</html>