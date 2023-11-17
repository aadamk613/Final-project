<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>화면 틀입니다 복사해서 사용해주세요</title>
<link rel="stylesheet" href="resources/css/common/template.css">
<script src="https://cdn.jsdelivr.net/npm/@yaireo/tagify"></script>
<script src="https://cdn.jsdelivr.net/npm/@yaireo/tagify/dist/tagify.polyfills.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/@yaireo/tagify/dist/tagify.css" rel="stylesheet" type="text/css" />
</head>
<style>

/* 화면틀입니다 안에 부분은 자기가 맡은 파트로 채워주세요 
사용하면 안되는 id값 : pageHeader, pageAsideLeft, pageAsideRight, pageSection, contentTitle, content, 
				pageArea, searchArea, pageFooter
위의 id는 참조한 css에 이름 이미 있어서 화면 모양이 이상해질 수 있습니다
*/


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
					<textarea name='tags2' placeholder='hashtags'>
					</textarea>
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
							
						</tbody>
						<tr>
							<td colspan="3">
								<input type="hidden" id="hdtag" name="tagNo" value="">
								<a href="addHashtag.admin" class="btn btn-primary btn-block btn-primary">추가</a>
							</td>

							<td colspan="2">
								<a class="btn btn-primary btn-block btn-danger" onclick="postformSubmit(1)">선택삭제</a>
							</td>
						</tr>
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
		$(() => {
			$.ajax({
				url : 'ajaxGetHashtag.admin',
				success : data => {
					var input = document.querySelector('textarea[name=tags2]'),
					tagify = new Tagify(input, {
						enforceWhitelist : true,
						delimiters       : null,
						whitelist        : data,
						callbacks        : {
							add    : console.log,  // callback when adding a tag
							remove : console.log   // callback when removing a tag
						}
					})
				},
				error : e => {
					console.log("해시태그 목록 조회 실패!");
				}
			});
		});
		$(() => {
			$.ajax({
				url : 'ajaxHashtagList.admin',
				success : data => {
					let result = '';
					for (let i in data) {
						result += '<tr>'
										+ '<td>' + data[i].tagNo + '</td>'
										+ '<td>' + data[i].tagName + '</td>'
										+ '<td>' + data[i].tagDate + '</td>'
										+ '<td>' + data[i].tagUsage + '</td>'
										+ '<td>' + data[i].tagNo + '</td>'
										+ '<td>' + data[i].tagNo + '</td>'
										+ '<td>' + data[i].tagNo + '</td>'
										
					}
				}
			})
		});
		function deleteChecked() {
			var checkedElements = document.postform.rowcheck;
			var flag = false;
			for (i = 0; i < checkedElements.length; i++) {
				if (checkedElements[i].checked) {
					flag = true;
				}
			}
			if (flag === false) {
				alert("하나 이상의 해시태그를 선택하여야 합니다.");
			}
			document.postform.submit();
		}
	</script>
</body>
</html>