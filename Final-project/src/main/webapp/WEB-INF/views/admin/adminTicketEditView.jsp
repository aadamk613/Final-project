<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 상세보기</title>
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
textarea {
    resize: none;
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
                고객문의 수정
			</div>
			
			<div id="content">
				<article>
					<table class="table table-borderless table-sm" align="center" style="width: 100%">
						<thead>
							<tr>
								<th width="100"></th>
								<th width="100"></th>
								<th width="100"></th>
								<th width="100"></th>
								<th width="100"></th>
								<th width="100"></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="6">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text">문의제목</span>
										</div>
										<p class="form-control" name="title" style="cursor : default">${ticket.ticketTitle}</p>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="6">
									<div class="form-group">
										<label for="comment">문의 내용: </label>
										<p
										class="form-control"
										id="article"
										name="content"
										style="cursor : default"
										>${ticket.ticketContent}</p>
									</div>
								</td>
							</tr>
							<form action="" method="post" id="postform">
							<tr>
								<td colspan="6">
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text">답변제목</span>
										</div>
										<input name="answerTitle" type="text" class="form-control" value="${ ticket.answerTitle }">
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="6">
									<div class="form-group">
										<label for="comment">답변 수정:</label>
										<textarea name="answerContent" class="form-control" rows="10" id="comment">${ ticket.answerContent }</textarea>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<input type="hidden" name="ticketNo" value="${ticket.ticketNo}">
									<input type="hidden" name="ticketWriter" value="${sessionScope.loginUser.memId}">
									<a class="btn btn-primary btn-block btn-primary" onclick="postformSubmit(0)">답변수정</a>
								</td>
								<td colspan="2">
									<a class="btn btn-primary btn-block btn-danger" onclick="postformSubmit(1)">답변삭제</a>
								</td>
							</tr>
						</form>
						</tbody>
					</table>
				</article>
			</div>
		</section>
		
		<aside id="pageAsideRight" class="aside">
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
			$('#postform').attr('action', 'editResolvedTicket.admin').submit();
		} else {
			//삭제버튼클릭시
			$('#postform').attr('action', 'deleteResolvedTicketStatus.admin').submit();
		}
	};
</script>

</body>
</html>