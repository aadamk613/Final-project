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

<style>
	.heart {width : 30px; height : 30px;}

</style>

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
					${ n.category == 1 ? "공지게시글" : '필독게시글'}
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
										<a id="writerIdButton">${ n.memNo }</a>
							<div id="writeInfoHidden">
								<ul id="writeInfoHiddenUl">
									<li><a href="#">게시글 보기</a></li>
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
							${ n.noticeCreateDate }&nbsp;&nbsp;&nbsp;&nbsp;조회 ${ n.views }
						</div>
					</div>
					<hr>
					
					<c:choose>
						<c:when test="${ empty f }">
						첨부파일이 없습니다.
						</c:when>
						<c:otherwise>
						<div>
						  첨부파일
						  <c:forEach var="file" items="${f}">
						    <a href="/finalProject${file.filePath }${file.updateName}" download="${file.originalName}">${file.originalName}</a>
						  </c:forEach>
						</div>
						</c:otherwise>
					</c:choose>
					
					<hr clear="both">
					<div id="boardContent">
						${ n.noticeContent }
					</div>
					<div id="boardlikeWrap">
						<c:choose>
						<c:when test="${ loginUser ne null }" >
							<c:choose>
								<c:when test="${ like.noticeLikeStatus eq 'Y' }">
									<a href="#" onclick="deleteLike();" class="like"><img class="heart" src="resources/images/fullHeart.png" alt="하트" ></a>&nbsp;${ n.likeCount } 
								</c:when>
								<c:when test="${ like.noticeLikeStatus eq 'N' }">
									<a href="#" onclick="updateLike();" class="like"><img class="heart" src="resources/images/emptyHeart.png" alt="빈하트"></a>&nbsp;${ n.likeCount } 
								</c:when>
								<c:otherwise>
									<a href="#" onclick="insertLike();" class="like"><img class="heart" src="resources/images/emptyHeart.png" alt="빈하트"></a>&nbsp;${ n.likeCount } 
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
					 		<a href='#' onclick="alert('로그인 후 이용 가능한 기능입니다.');" id="like" class="like"><img class="heart" src="resources/images/emptyHeart.png" alt="빈하트"></a>&nbsp;${ n.likeCount } 
						</c:otherwise>
						</c:choose>
						
					</div>	
					<hr clear="both">
					
				
				</article>
			</div>
			

			<div id="page">
				<div id="writeWrap">
						<c:if test="${ loginUser.memNick eq n.memNo }" >
							<a class="btn btn-light" onclick="postFormSubmit(0);">수정</a>
							<a class="btn btn-light" onclick="postFormSubmit(1);">삭제</a>
						</c:if>
				&nbsp;
				</div>
				<div id="upWrap">
					<a href="#header" class="btn btn-light">^</a>
				</div>
			</div>
			
			<form action="" method="post" id="postForm">
				<input type="hidden" name="bno" value="${ n.noticeNo }">
			</form>
			
			<script>
				function postFormSubmit(num) {
					if(num == 0) {
						$('#postForm').attr('action', 'updateForm.no').submit();				
					}
					else {
						$('#postForm').attr('action', 'delete.no').submit();				
					}
				}
			</script>
			
			<!-- 좋아요 update -->
			<script>
			function updateLike() {
				  $.ajax({
				    url: 'updateNoticeLike.do',
				    data: {
				      noticeNo: ${n.noticeNo},
				      memNo: '${loginUser.memNo}'
				    },
				    success: function(response) {
			              alert('좋아요 등록!');
			              location.href = 'detail.no?bno=' + ${n.noticeNo};}
				  });
				}
			</script>
			<!-- 좋아요 delete -->
			<script>
			function deleteLike() {
				  $.ajax({
				    url: 'deleteNoticeLike.do',
				    data: {
				    	noticeNo : ${n.noticeNo},
				      memNo: '${loginUser.memNo}'
				    },
				    success: function(response) {
			              alert('좋아요 삭제!');
			              location.href = 'detail.no?bno=' + ${n.noticeNo};}
				  });
				}
			</script>
						
			<!-- 좋아요 insert -->
			<script>
			function insertLike() {
				  $.ajax({
				    url: 'insertNoticeLike.do',
				    data: {
				     noticeNo : ${n.noticeNo},
				     memNo: '${loginUser.memNo}'
				    },
				    success: function(response) {
			              alert('좋아요 등록!');
			              location.href = 'detail.no?bno=' + ${n.noticeNo};}
				  });
				}
			</script>
		
		
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