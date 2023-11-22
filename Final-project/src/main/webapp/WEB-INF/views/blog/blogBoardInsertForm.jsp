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
                content
				<article id="pageArticle">
					<div align="center"><img src="이미지URL" width="38" height="53"></div>
					<div align="center"><h1> 내가 뽑은 올해의책 BEST 5 </h1></div>
					<p align="center">
					   주말 나들이도 좋지만 마음을 채우는 독서에 취미를 가져보기로 했던 2015년.<br>
					   목표했던만큼 읽지는 못했지만<br>
					   올해 내가 읽었던 책중에서 베스트 5권을 소개하려한다.<br>
					</p>
					<div align="center"><img src="이미지URL" width="38" height="46"></div>
					<div align="center"><h1>오늘은 태안</h1></div>
					<div align="center"><img src="이미지URL" width="541" height="300"></div>
					<p>얘기꾼 여행서 '오늘은' 시리즈 태안편이다. 두 작가가 일곱 해변 길을 걸으며 겪는 에피소드가 펼쳐진다.
					 태안의 아름다운 풍경과 바다와 함께 살아가는 사람들의 이야기를 담았다. 책을 읽다보면 바람이 머무는
					 태안으로 훌쩍 떠나게 될 것이다. 누구나 한 번쯤은 바다여행을 꿈꾼다. 흐르는 시간에 쉼은 없지만, 가끔
					 쉼이 필요할 때가 아닐까 생각한다. 작은 꾸러미 하나 메고 바람이 머무는 곳으로 떠나보라고 권하고 싶다.
					 수평선을 바라보는 순간 크게 느껴졌던 근심도 한낱 모래알갱이처럼 작게 보이고, 시간은 천천히 흐르고
					 마음은 바다처럼 넓어질 것이다.</p>
					<div align="center">
					   <table>
					       <tr>
					           <td colspan="2"><b>오늘은 태안</b></td>
					       </tr>
					       <tr>
					           <td>저자</td>
					           <td>김미정, 전현서</td>
					       </tr>
					       <tr>
					           <td>출판</td>
					           <td>얘기꾼</td>
					       </tr>
					       <tr>
					           <td>발매</td>
					           <td>2015.07.13.</td>
					       </tr>
					   </table>
					</div>
				</article>
			</div>
			
			<div id="pageArea">
                &lt;div&gt; <br>
                id=page
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