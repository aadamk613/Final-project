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

</style>
<body>

	<header id="pageHeader">
		<jsp:include page="../common/header.jsp" />
	</header> 
	<main>
		<aside id="pageAsideLeft" class="aside">
            &lt;aside1&gt; <br>
            id=pageAsideLeft <br>
	            여기는 pageAsideLeft 공백공간 <br>
	            사이드바 넣을 수도 있음 <br>
	            필요하면 쓰세요 <br>
	            중앙정렬되어있어요 <br>
		</aside>
		
		<section id="pageSection">
			
			<div id="contentTitle">
                &lt;div&gt;
                id=contentTitle 
				제목(삭제해도 됨)
			</div>
			
			<div id="content">
				<article>
					<table class="table table-borderless table-sm" align="center" style="width: 50%">
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
			                        <span class="input-group-text">제목</span>
			                    </div>
			                    <p class="form-control" name="title" style="cursor : default">${t.ticketTitle}</p>
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
			                    >${t.ticketContent}</p>
			                </div>
			            </td>
			          </tr>
			          <tr>
			              <td colspan="2">
			                <form action="#" method="post">
			                  
			                  <button type="submit" class="btn btn-primary btn-block btn-warning">수정</button>
			                </form>
			                </td>
			                <td colspan="2">
			                  <form action="#" method="post">
			                    <button type="submit" class="btn btn-primary btn-block btn-danger">삭제</button>
			                  </form>
			                </td>
			                <td colspan="2">
			                  <a href="#" class="btn btn-primary btn-block" >목록으로</a>
			                </td>
		                </tr>
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


</body>
</html>