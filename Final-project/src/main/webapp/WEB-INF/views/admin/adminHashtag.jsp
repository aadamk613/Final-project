<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>화면 틀입니다 복사해서 사용해주세요</title>
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
input[type=checkbox] {

zoom: 1.8;

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
				해시태그 관리
			</div>
			
			<div id="content">       
				<article id="pageArticle">
					<form id="postform" method="post" action="">
					<table id="tb" class="table table-sm table-hover" align="center" style="width: 100%" style="cursor:default">
						<thead class="thead-light">
							<tr>
								<th width="20"></th>
								<th width="50">번호</th>
								<th width="250">해시태그명</th>
								<th width="170">작성일</th>
								<th width="50">사용횟수</th>
							</tr>
						</thead>
						<tbody style="cursor:default">
							<c:choose>
								<c:when test="${empty list}">
									<tr>
										<td colspan="5">조회된 게시글이 없습니다..</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${list}" var="b" varStatus="s">
										<tr>
											<td style="text-align: center;"><input type="checkbox" name="chk"></td>
											<td>${b.tagNo}</td>
											<td>${b.tagName}</td>
											<td>${b.tagDate}</td>
											<td>${b.tagUsage}</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
							<tr>
								<td colspan="2">
									<input type="hidden" id="hdtag" name="tagNo" value="">

									<a href="addHashtag.admin" class="btn btn-primary btn-block btn-primary">추가</a>
								</td>
								<td colspan="2">
									<a class="btn btn-primary btn-block btn-danger" onclick="postformSubmit(0)">수정</a>
								</td>
								<td colspan="1">
									<a class="btn btn-primary btn-block btn-danger" onclick="postformSubmit(1)">삭제</a>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
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
		function postformSubmit(num) {
			if (num == 0) {
				// 수정 버튼 클릭시 
				$('')
				$('#postform').attr('action', 'updateHashtag.admin').submit();
			} else {
				//삭제버튼클릭시
				$('#postform').attr('action', 'deleteHashtag.admin').submit();
			}
		};
		$(() => {
			$('input:checkbox[name=chk]').each(function (index) {
				if ($(this).is(":checked") == true) {
					consloe.log($(this));
					console.log($(this).name);
					console.log($(this).val());
			}
		})
	})
	</script>
</body>
</html>