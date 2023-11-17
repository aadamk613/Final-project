<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<a%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					<form id="postform" method="post" action="deleteHashtags.admin">
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
						<tfoot>
							<tr>
								<td colspan="2">
									<a class="btn btn-primary btn-block btn-danger" onclick="deleteChecked()">선택삭제</a>
								</td>
							</form>
							<form id="addform" action="addHashtag.admin" method="post">
								<td colspan="3">
									<div class="input-group mb-3">
										<input type="text" class="form-control" placeholder="추가할 해시태그를 입력해주세요" name="tagName">
										<div class="input-group-append">
											<a class="btn btn-success" onkeypress="press(f)">해시태그 추가</a>
										</form>
										</div>
									</div>
								</td>
							</tr>
						</tfoot>
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
		function press(f){
				if(f.keyCode == 13){ //javascript에서는 13이 enter키를 의미함
					$('#addHashtag').val($('#add-btn').val());
						addHashtag(); 
				}
		}
		</script>
	<script>
		function addHashtag() {
			$('#addform').submit();
		};
		$(() => {
			$.ajax({
				url : 'ajaxGetHashtag.admin',
				success : data => {
					var input = document.querySelector('textarea[name=tags2]'),
					tagify = new Tagify(input, {
						enforceWhitelist : true,
						delimiters       : null,
						whitelist        : data,
						dropdown : {
            classname     : "color-blue",
            enabled       : 0,              // show the dropdown immediately on focus
            maxItems      : 10,
            position      : "text",         // place the dropdown near the typed text
            closeOnSelect : false,          // keep the dropdown open after selecting a suggestion
            highlightFirst: true
						},
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
										+ '<td>' + '<input type="checkbox" name="chk"' + 'value="'+ data[i].tagNo + '">' + '</td>'
										+ '<td>' + data[i].tagNo + '</td>'
										+ '<td>' + data[i].tagName + '</td>'
										+ '<td>' + data[i].tagDate + '</td>'
										+ '<td>' + data[i].tagUsage + '</td>'
										+ '<tr>';			
					}
					$('#tb > tbody').html(result);
				}
			})
		});
		function deleteChecked() {
			var checkedElements = $('input[type="checkbox"]:checked');
			var flag = false;
			for (i = 0; i < checkedElements.length; i++) {
				if (checkedElements[i].checked) {
					flag = true;
				}
			}
			if (flag === false) {
				alert("하나 이상의 해시태그를 선택하여야 합니다.");
			}
			$('#postform').submit();
		}
	</script>
</body>
</html>