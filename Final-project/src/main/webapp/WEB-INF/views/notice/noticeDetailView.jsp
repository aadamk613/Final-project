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
					${ b.boardName }
				</div>
				<div id="backWrap">
					<a href="list.no?cPage=1&type=${ b.boardType }" class="btn btn-light">목록으로</a>
				</div>
			</div>
			<div id="content">
				<article>
					<div id="boardHeader">
						<div id="title">${ b.boardTitle }</div>		
					</div>
					<div id="writerInfoWrap">
						<div id="writerThumbnail">
							<img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTAzMTlfMjA1%2FMDAxNjE2MDgwOTM1MDIx.JZKXWzM8gscL4K0VtyQuYki9jetacIhoppgLJ0PlxEcg.iqtKX-tjRe6nSqfieZ6uYV1QS-4S2LewzhkIAVyic4kg.PNG.wnsghks1017%2Fimage.png&type=a340"
							alt="회원사진" id="user_photo2" >
                        
						</div>
						<div id="writeIdWrap">
							<div id="writerId">
								<c:choose>
									<c:when test="${ '익명게시판' eq b.boardName }" >
										익명
									</c:when>
									<c:otherwise>
										<a id="writerIdButton">${ b.writer }</a>
									</c:otherwise>
								</c:choose>
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
							2023.09.05&nbsp;&nbsp;20:53&nbsp;&nbsp;&nbsp;&nbsp;조회 ${ b.views }
						</div>
						<div id="commentCount">
							댓글&nbsp;&nbsp;${ b.comments }
						</div>
					</div>
					<hr clear="both">
					<div id="boardContent">
						${ b.boardContent }
					</div>
					<div id="boardlikeWrap">
						<c:choose>
						<c:when test="${ loginUser ne null }" >
							<c:choose>
								<c:when test="${ b.likeMem eq 1 }">
									<img src="resources/img/fullHeart.png" alt="하트" >
									<a href="#" id="like" class="like">좋아요</a>&nbsp;${ b.likes } 
									&nbsp;&nbsp;<img src="resources/img/comment.png" alt="댓글">&nbsp;댓글&nbsp;${ b.comments }
								</c:when>
								<c:otherwise>
									<img src="resources/img/emptyHeart.png" alt="빈하트">
									<a href="#" id="like" class="like">좋아요</a>&nbsp;${ b.likes } 
									&nbsp;&nbsp;<img src="resources/img/comment.png" alt="댓글">&nbsp;댓글&nbsp;${ b.comments }
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<img src="resources/img/emptyHeart.png" alt="빈하트">
					 		<a href='#' onclick="alert('로그인 후 이용 가능한 기능입니다.');" id="like" class="like">좋아요</a>&nbsp;${ b.likes } 
					 		&nbsp;&nbsp;<img src="resources/img/comment.png" alt="댓글">&nbsp;댓글&nbsp;${ b.comments }
						</c:otherwise>
						</c:choose>
						
					</div>	
					<hr clear="both">
					
					<!-- 댓글 하나도 없을 시 등록 순 최신순 버튼 비활성화 -->
					
					<div id="commentWrap">
						<div id="commentOption">
							댓글 <a>등록순</a> <a>최신순</a>
						</div>
						<div id="commentContentBox">
							<div id="commentWriteMemId">
								댓글 단 유저 id
							</div>
							<div id="commentContent">
								댓글 내용입니다
							</div>
							<div id="commentCreateDate">
								2023.10.17 13:14&nbsp;&nbsp;
								<a href="#">답글 쓰기</a>
							</div>
						</div>	
											
						<div id="commentInsertBox">
							<div id="commentWriter">유저ID</div>
							<textarea id="commentContentInsert" placeholder="댓글을 남겨보세요"></textarea>
							<c:choose>
							<c:when test="${ loginUser ne null }">
								<div id="submitWrap"><a href='#javascript:void(0);' onclick="insertComment()">등록</a></div>
							</c:when>
							<c:otherwise>
								<div id="submitWrap"><a href='#' onclick="alert('로그인 후 이용 가능한 기능입니다.');">등록</a></div>
							</c:otherwise>							
							</c:choose>
						</div>
					</div>
				
				</article>
			</div>
			
			<script>
			
				function selectCommentList(){
					
					$.ajax({
						url  : 'coList.co',
						data : {boardNo: ${ b.boardNo }},
						success : function(result){
							console.log(result);
							
							let resultStr = '';
							for(let i in result){
								
								resultStr += '<div id="commentWriteMemId">'
										  + result[i].writer
								          + '</div>'
								          + '<div id="commentContent">'
										  + result[i].commentContent
									      + '</div>'
									      + '<div id="commentCreateDate">'
										  + result[i].createDate
										  + '&nbsp;&nbsp;'
										  + '<div id="commentCreateDate">'
										  + '</div>'
										  + '</div>';
								
							}
							$('#commentContentBox').html(resultStr);
						},
						error : function(){
							console.log('댓글 읽어오기 실패')	
						}
						
					});
					
				}
				
				$(function(){
					selectCommentList();
					
				});
				
				function insertComment(){
					
					$.ajax({
						url : 'coInsert.co',
						type: 'post',
						data : {
							boardNo : ${ b.boardNo },
							content : $('#commentContentInsert').val()
						}, 
						success : function(result){
							//console.log(result);
							if(result > 0){
								$('#commentContentInsert').val('');
								selectCommentList();
							}
						},
						error : function(){alert('댓글 작성에 실패하였습니다.');}
					
					})
						
				}
			
			</script>
			
			
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