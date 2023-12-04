<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고글 조회</title>
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
                신고글 조회
			</div>
			
			<div id="content">
				<article id="pageArticle">
					<table id="tb" class="table table-sm table-hover" align="center" style="width: 100%" style="cursor:default">
						<thead class="thead-light">
							<tr>
								<th width="40">번호</th>
								<th width="40">신고내용</th>
								<th width="60">신고일시</th>
								<th width="50">신고대상글</th>
								<th width="50">신고대상유저</th>					
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
			url : 'ajaxReportedArticles.admin',
			success : data => {
				let result = '';
				for (let i in data) {
					result += '<tr>'
									+ '<td>' + data[i].reportNo + '</td>'
									+ '<td>' + data[i].reportContent + '</td>'
									+ '<td>' + data[i].reportDate + '</td>'
									+ '<td>' + data[i].refBoardNo + '</td>'
									+ '<td>' + data[i].member.memId + '</td>'
									+ '<tr>';			
				}
				$('#tb > tbody').html(result);
			}
		})
	});
	// ajax 비동기 통신으로 생성된 tr요소를 선택하기 위해서 jquery 콜백함수를 사용.
	$(() => {
		$(document).on('click', ('#tb > tbody > tr'), function() {
			$('#open-modal').click();
			clickMember(this);
		});
	});
	function clickMember(e) {
		$.ajax({
			url : 'ajaxSelectMember.admin',
			data : {
				memNo : $(e).children().eq(0).text()
			},
			async : false,
			success : data => {	
				$('input[name=memNo]').val(data.memNo);
				$('input[name=memStatus]').val(data.memStatus);
				$('input[name=memId]').val(data.memId);
				$('input[name=memNick]').val(data.memNick);
				$('input[name=email]').val(data.email);
				$('input[name=memTemp]').val(data.memTemp);
				$('#modal-tb > tbody').html(result);
			}
		})
	};
	function submitForm() {
		$('#edit-form').submit();
	}

</script>
</body>
</html>