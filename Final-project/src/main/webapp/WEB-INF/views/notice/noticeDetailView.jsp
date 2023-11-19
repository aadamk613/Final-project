<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>

<link rel="stylesheet" href="resources/css/board/boardDetailView.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js">
</script>


</head>
<body>

	<header id="pageHeader">
		<jsp:include page="../common/header.jsp" />
	</header> 
	<main>
		<aside id="pageAsideLeft" class="aside">
		<jsp:include page="../common/boardSideBar.jsp"/>
		</aside>

		
		<section id="section">
			
			<div id="contentTitleWrap">
				<div id="contentTitle">
					${ n.noticeTitle }
				</div>
				<div id="backWrap">
					<a href="list.no?cPage=1&type=${ n.category }" class="btn btn-light">목록으로</a>
				</div>
			</div>
			<div id="content">
				<article>
					<div id="boardHeader">
						<div id="title">${ n.noticeTitle }</div>		
					</div>
					<div id="writerInfoWrap">
						<div id="writerThumbnail">
							<img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTAzMTlfMjA1%2FMDAxNjE2MDgwOTM1MDIx.JZKXWzM8gscL4K0VtyQuYki9jetacIhoppgLJ0PlxEcg.iqtKX-tjRe6nSqfieZ6uYV1QS-4S2LewzhkIAVyic4kg.PNG.wnsghks1017%2Fimage.png&type=a340"
							alt="회원사진" id="user_photo2" >
                        
						</div>
						<div id="writeIdWrap">
							<div id="writerId">
										<a id="writerIdButton">${ b.memNick }</a>
							<div id="writeInfoHidden">
								<ul id="writeInfoHiddenUl">
									<li><a href="#	">게시글 보기</a></li>
									<c:if test="${ loginUser ne null }">
										<li><a href="#" class="hiddenButton">쪽지 보내기</a></li>
									</c:if>
								</ul>
							</div>
							</div>
						</div>
					</div>
					<div id="boardInfor">	
						<div id="boardDate">
							2023.09.05&nbsp;&nbsp;20:53&nbsp;&nbsp;&nbsp;&nbsp;조회 ${ n.views }
						</div>
					</div>
					<hr clear="both">
					<div id="boardContent">
						${ n.noticeContent }
					</div>
					<div id="boardlikeWrap">
						<c:choose>
						<c:when test="${ loginUser ne null }" >
							<c:choose>
								<c:when test="${ b.likeMem eq 1 }">
									<img src="resources/img/fullHeart.png" alt="하트" >
									<a href="#" id="like" class="like">좋아요</a>&nbsp;${ n.likeCount } 
								</c:when>
								<c:otherwise>
									<img src="resources/img/emptyHeart.png" alt="빈하트">
									<a href="#" id="like" class="like">좋아요</a>&nbsp;${ n.likeCount } 
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<img src="resources/img/emptyHeart.png" alt="빈하트">
					 		<a href='#' onclick="alert('로그인 후 이용 가능한 기능입니다.');" id="like" class="like">좋아요</a>&nbsp;${ n.likeCount } 
						</c:otherwise>
						</c:choose>
						
					</div>	
					<hr clear="both">
					
					
				
				</article>
			</div>
			
			
			
			<div id="page">
				<div id="writeWrap">
					<c:if test="${ loginUser ne null }">
						<a class="btn btn-primary" href="#" >글 쓰기</a>
						<c:if test="${ loginUser.nickName eq b.writer }" >
							<a class="btn btn-light" href="#">수정</a>
							<a class="btn btn-light" href="#">삭제</a>
						</c:if>
					</c:if>
				&nbsp;
				</div>
				<div id="upWrap">
					<a href="#header" class="btn btn-light">^</a>
				</div>
			</div>
		
		
		</section>
		
		<aside id="aside2" class="aside">
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