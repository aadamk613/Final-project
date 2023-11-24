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
			<jsp:include page="myPageAsideLeft.jsp"/>
		</aside>
		
		<section id="pageSection">
			
			<div id="contentTitle">
        나의 문의
			</div>
			
			<div id="content">
				<article>
          <table id="tb" class="table table-sm table-hover" align="center" style="width: 100%" style="cursor:default">
            <thead class="thead-light">
              <tr>
                <th width="50">답변여부</th>
                <th width="200">제목</th>
                <th width="120">작성일</th>
                <th width="120">답변 작성일</th>
              </tr>
            </thead>
            <tbody style="cursor:default">
              <c:choose>
                <c:when test="${empty list}">
                  <tr>
                    <td colspan="4">작성한 문의 내역이 없습니다.</td>
                  </tr>
                </c:when>
                <c:otherwise>
                  <c:forEach items="${list}" var="b">
                    <tr>
                        <c:choose>
                          <c:when test="${b.status.equals('R')}">
                            <td>답변완료</td>
                          </c:when>
                          <c:otherwise>
                            <td>미답변</td>
                          </c:otherwise>
                        </c:choose>
                      <td id="ticket-num" value="${b.ticketNo}">${b.ticketTitle}</td>
                      <td>${b.createDate}</td>
                      <td>${b.answerModifyDate}</td>
                    </tr>
                  </c:forEach>
                </c:otherwise>
              </c:choose>
            </tbody>
          </table>
          <div>
            <a href="addTicket.me" class="btn btn-primary btn-block btn-primary">새 문의 작성</a>
          </div>
				</article>
			</div>
		</section>
		
		<aside id="pageAsideRight" class="aside">
			<jsp:include page="myPageAsideRight.jsp"/>
		</aside>
		
	</main>
	
	<br clear="both">
	
	<footer id="pageFooter">
		<jsp:include page="../common/footer.jsp" />
	</footer>

  <script>
    $(() => {
      $('#tb > tbody > tr').click(function() {
        location.href = "memberTicketDetailView.me?bno=" + $(this).children().eq(1).attr("value");
      })


    });
  </script>
</body>
</html>