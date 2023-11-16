<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>체험학습 상세조회</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<style>
* {
    border: 1px solid skyblue;
	box-sizing: border-box;
}



.container > div {
	
}

.title{
	align : center;
    display : flex;
    align-items: center;
    justify-content: center;
    clear : both;
}

#forWriter {
	float : right;
	
}

.writer {
	margin-left : 300px;
}

.count li {
	display: inline-block;
	list-style: none;
}

h1 {
	padding : 20px;
}

#thumb{
	width : 400px;
	height : 400px;
}

.summary{
	
}


.summary li {
	list-style : none;
	align : center;
	
}


</style>

</head>
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
		아하하하하하 이게 사이브다~~
		
		<section id="pageSection">
			<div class="container">
				<!-- 작성자만 보이는 버튼 -->
				<div id="forWriter">
					<button type="button" class="btn btn-primary">수정하기</button>
					<button type="button" class="btn btn-danger">삭제하기</button>
				</div>
	
				<div class="title">
					<h1>${ exp.expTitle }</h1>
				</div>
				
				<div>
					<div class="count">
						<ul>
							<li>조회수 ${ exp.expCount }</li>
							<li>댓글수 ${ exp.expReplyCount } </li>
							<li>좋아요수 ${ exp.expLikeCount }</li>
							<li>
								<div class="writer">
									작성자 : ${ exp.expWriter } | 
									<c:choose>
									<c:when test="${ not empty exp.expUpdateDate }">
										수정일 : ${ exp.expUpdateDate }
									</c:when>
									<c:otherwise>
										작성일 : ${ exp.expCreateDate }
									</c:otherwise>
									</c:choose>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<div>
					<div class="summary">
						<c:if test="${ not empty files }">
							<c:forEach var="f" items="${ files }">
								<img src="${ f.filePath }/${ f.updateName }" id="thumb" />
							</c:forEach>
						</c:if>

					</div>
					<div class="summary">
						<ul>
							<li>카테고리 : ${ exp.expCategoryName }</li>
							<li>체험학습일 : ${ exp.expWorkDate }, ${ exp.expWorkTime }시간</li>
							<li>모집인원 : ${ exp.expSupportCount } / ${ exp.expPeople }명</li>
							<li>지역 : ${ exp.expAddress }</li>
							<li>모집마감일 : ${ exp.expEndDate }</li>
						</ul>
						<button type="button" class="btn btn-primary">지원하기</button>
					</div>
				</div>
				<div>
					<p>내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용</p>
				</div>
				<div id="">
					<img src="https://static.vecteezy.com/system/resources/thumbnails/004/141/669/small/no-photo-or-blank-image-icon-loading-images-or-missing-image-mark-image-not-available-or-image-coming-soon-sign-simple-nature-silhouette-in-frame-isolated-illustration-vector.jpg" />
					<p>주석</p>
				</div>
			</div>
			
			
			
			
			
			
			
			

		
		</section>
		
		<aside id="pageAsideRight" class="aside">
           &lt;aside&gt; <br>
           id=pageAsideRight<br>
	            여기는 pageAsideRight 공백공간 <br>
	            사이드바 넣을 수도 있음 <br>
	            필요하면 쓰세요 <br>
	            중앙정렬되어있어요 <br>
		</aside>
		
	</main>
	
	<br clear="both">
	
	<footer id="pageFooter">
        &lt;footer&gt; <br>
        id=pageFooter
	        여기는 푸터 <br>
	        여기는 푸터 <br>
	        여기는 푸터 <br>
	        여기는 푸터 <br>
	        여기는 푸터 <br>
	        여기는 푸터 <br>
	        쓰는만큼 늘어나요<br>
	     <jsp:include page="../common/footer.jsp" />
	</footer>


	


</body>
</html>