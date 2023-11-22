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
								<th width="40">번호</th>
								<th width="40">상태</th>
								<th width="60">아이디</th>
								<th width="60">닉네임</th>
								<th width="100">이메일</th>					
								<th width="30">온도</th>
								<th width="50">블로그</th>
								<th width="130">마지막 로그인</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<!-- Button to Open the Modal -->
					<input id="open-modal" type="hidden" class="btn btn-primary" data-toggle="modal" data-target="#myModal"/>
					<!-- The Modal -->
					<div class="modal fade" id="myModal">
						<div class="modal-dialog modal-lg modal-dialog-centered">
							<div class="modal-content">

								<!-- Modal Header -->
								<div class="modal-header">
									<h4 class="modal-title">회원상태변경</h4>
									<button type="button" class="close" data-dismiss="modal">&times;</button>
								</div>

								<!-- Modal body -->
								<div class="modal-body">
									<table id="modal-tb" class="table table-sm table-hover" align="center" style="width: 100%" style="cursor:default">
										<thead class="thead-light">
											<tr>
												<th width="40">번호</th>
												<th width="40">상태</th>
												<th width="60">아이디</th>
												<th width="60">닉네임</th>
												<th width="140">이메일</th>					
												<th width="50">온도</th>
											</tr>
										</thead>
										<form id="edit-form" action="editMember.admin" method="post">
											<tbody>
												<tr>
													<td>
														<input type="text" name="memNo" style="width:40px" value="" readonly />
													</td>
													<td>
														<input type="text" name="memStatus" style="width:40px" value=""/>
													</td>
													<td>
														<input type="text" name="memId" style="width:60px" value=""/>
													</td>
													<td>
														<input type="text" name="memNick" style="width:60px" value=""/>
													</td>
													<td><input type="text" name="email" style="width:140px" value=""/>
													</td>
													<td><input type="text" name="memTemp" style="width:50px" value=""/>
													</td>
												<tr>
											</tbody>
										</form>
										</table>
									</div>
									<!-- Modal footer -->
									<div class="modal-footer">
										<button type="button" onclick="submitForm();" class="btn btn-warning" data-dismiss="modal">수정</button>
										<button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
								</div>
							</div>
						</div>
					</div>
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
									+ '<td>' + '<a href="select.bl?blogNo=' + data[i].blogNo + '">' + data[i].blogNo + '</a>' + '</td>'
									+ '<td>' + data[i].lastLogin + '</td>'
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