<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>

<link rel="stylesheet" href="resources/css/board/boardDetailView.css">
<script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>
		
		<!-- CSS -->
		<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
		<!-- Default theme -->
		<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/default.min.css"/>
		<!-- Semantic UI theme -->
		<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/semantic.min.css"/>
    <!-- jQuery 라이브러리 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- 부트스트랩에서 제공하고 있는 스타일 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- 부트스트랩에서 제공하고 있는 스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
	#boardReportBtn { float : right; }
	.commentReportBtn {float : right; }
	#commentContentBox > div { float : none;}
	.form-control {height: 200px;}
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
				일반게시글
				</div>
			</div>
			<div id="content">
				<article>
					<div id="boardHeader">
						<div id="title">${ b.boardTitle }</div>
						<!-- 로그인이 되어있고, 자신의 게시글이 아니고, 신고를 안했을경우에만 보임 -->
						<c:choose>
							<c:when test="${ loginUser.memNick ne b.memNo && not empty loginUser && empty br }" >
							<button id="boardReportBtn" class="btn btn-primary">신고하기</button>
							</c:when>
							<c:otherwise>
							<button disabled id="boardReportBtn" class="btn btn-primary">신고하기</button>
							</c:otherwise>
						</c:choose>
					</div>
					<div id="writerInfoWrap">
						<div id="writerThumbnail">
							<img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTAzMTlfMjA1%2FMDAxNjE2MDgwOTM1MDIx.JZKXWzM8gscL4K0VtyQuYki9jetacIhoppgLJ0PlxEcg.iqtKX-tjRe6nSqfieZ6uYV1QS-4S2LewzhkIAVyic4kg.PNG.wnsghks1017%2Fimage.png&type=a340"
							alt="회원사진" id="user_photo2" >
                        
						</div>
						<div id="writeIdWrap">
							<div id="writerId">
										<a id="writerIdButton">${ b.memNo }</a>
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
							${ b.boardCreateDate }&nbsp;&nbsp;&nbsp;&nbsp;조회 ${ b.views }
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
						${ b.boardContent }
					</div>
					<div id="boardlikeWrap">
						<c:choose>
						<c:when test="${ loginUser ne null }" >
							<c:choose>
								<c:when test="${ like.boardLikeStatus eq 'Y' }">
									<a href="#" onclick="deleteLike();" class="like"><img class="heart" src="resources/images/fullHeart.png" alt="하트" ></a>&nbsp;${ b.likeCount } 
								</c:when>
								<c:when test="${ like.boardLikeStatus eq 'N' }">
									<a href="#" onclick="updateLike();" class="like"><img class="heart" src="resources/images/emptyHeart.png" alt="빈하트"></a>&nbsp;${ b.likeCount } 
								</c:when>
								<c:otherwise>
									<a href="#" onclick="insertLike();" class="like"><img class="heart" src="resources/images/emptyHeart.png" alt="빈하트"></a>&nbsp;${ b.likeCount } 
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
					 		<a href='#' onclick="alert('로그인 후 이용 가능한 기능입니다.');" id="like" class="like"><img class="heart" src="resources/images/emptyHeart.png" alt="빈하트"></a>&nbsp;${ b.likeCount } 
						</c:otherwise>
						</c:choose>
						
					</div>	
					<hr clear="both">
										<!-- 댓글 하나도 없을 시 등록 순 최신순 버튼 비활성화 -->
					<div id="commentWrap">
					<c:choose>
					
					
					
					
						<c:when test="${ empty cList }">
						<div id="commentOption">
							댓글 <a>등록순</a> <a>최신순</a>
						</div>
						</c:when>
						<c:otherwise>
						<div id="commentOption">
							댓글 <a href="">등록순</a> <a href="#">최신순</a>
						</div>
						</c:otherwise>
					</c:choose>					
						<hr>
							<div id="commentContentBox">
							
							<c:forEach var="c" items="${ cList }">
								<div class="commentNo" style="display:none">${ c.commentNo }</div>
								<div class="memNo" style="display:none">${ c.memNo }</div>
								<div id="commentWriteMemId">
								${ c.memNo }
								</div>
								<div id="commentContent">
								${ c.commentContent }
								<button <c:if test="${loginUser.memNick eq c.memNo || empty loginUser}">disabled</c:if> id="${ c.commentNo }" class="commentReportBtn btn btn-primary">신고하기</button>
								</div>
								<div id="commentCreateDate">
									${ c.commentCreateDate }
									<c:if test="${ loginUser.memNick eq c.memNo }">
									<a class="boardCommentUpdate btn" onclick="postFormSubmit(2, ${c.commentNo});">수정</a>
									<a class="boardCommentdelete btn" onclick="postFormSubmit(3, ${c.commentNo});">삭제</a>
									</c:if>
								</div>
								<hr>
							</c:forEach>


						<form action="rinsert.do" method="post">
						<div id="commentInsertBox">
							<textarea id="commentContentInsert" placeholder="댓글을 남겨보세요" name="commentContent" required></textarea>
							<input type="hidden" name=boardNo value="${b.boardNo }"></div>
							<input type="hidden" name=memNo value="${loginUser.memNo }"></div>
							<c:choose>
							<c:when test="${ loginUser ne null }">
								<div id="submitWrap"><button type="submit">등록</button></div>
							</c:when>
							<c:otherwise>
								<div id="submitWrap"><a href='#' onclick="alert('로그인 후 이용 가능한 기능입니다.');">등록</a></div>
							</c:otherwise>							
							</c:choose>
						</div>
						</form>
					</div>
				</article>
			</div>
			

		 				<div id="writeWrap">
						<c:if test="${ loginUser.memNick eq b.memNo }" >
							<a class="btn btn-light" onclick="postFormSubmit(0);">수정</a>
							<a class="btn btn-light" onclick="postFormSubmit(1);">삭제</a>
						</c:if>
				&nbsp;
				</div>
				<div id="upWrap">
					<a href="#header" class="btn btn-light">^</a>
				</div>

			
			<form action="" method="post" id="postForm">
				<input type="hidden" name="bno" value="${ b.boardNo }">
			</form>
			

		  
			<!-- 게시글 신고 모달창 -->
			<div class="modal fade modal-dialog  modal-dialog-centered modal-dialog-scrollable" id="boardReport" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="staticBackdropLabel">게시글 신고하기</h5>
			        <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
			      </div>
			      
			      <form action="report.bo" method="post">
			      <div class="modal-body">
			      <textarea class="form-control" style="height: 200px;" placeholder="신고내용을 입력하세요" id="message-text" name="reportContent" required></textarea>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소하기</button>
			        <button type="submit" class="btn btn-danger">신고하기</button>
			        <input type="hidden" name="refBoardNo" value="${ b.boardNo }">
			        <input type="hidden" name="refMemberNo" value="${ b.memNo }">
			        <input type="hidden" name="memNo" value="${ loginUser.memNo }">
			      </div>
			      </form>
			    </div>
			  </div>
			</div>
			
			<!-- 댓글신고하기 모달창 -->
			<!-- Modal -->
			<div class="modal fade modal-dialog modal-dialog-centered modal-dialog-scrollable" id="commentReport" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="staticBackdropLabel">댓글 신고하기</h5>
			        <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
			      </div>
			      
			      <div class="modal-body">
			      <textarea class="form-control" style="height: 200px;" placeholder="신고내용을 입력하세요" id="reportContent" name="reportContent" required></textarea>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소하기</button>
			        <button id="commentReportSubmit" type="submit" class="btn btn-danger">신고하기</button>
			      </div>
			    </div>
			  </div>
			</div>
			
			<!-- 댓글 수정 모달창 -->
			<div class="modal fade modal-dialog  modal-dialog-centered modal-dialog-scrollable" id="boardCommentUpdateModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="staticBackdropLabel">댓글 수정하기</h5>
			        <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
			      </div>
			      
			      <form action="update.co" method="post">
			      <div class="modal-body">
			      <textarea class="form-control" style="height: 200px;" placeholder="수정하실 내용을 입력해주세요" id="message-text" name="commentContent" required></textarea>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소하기</button>
			        <button type="submit" class="btn btn-danger">수정하기</button>
			        <input type="hidden" name="commentNo" id="commentNoInput" value="">
			        <input type="hidden" name="boardNo" value="${ b.boardNo }">
			        <input type="hidden" name="memNo" value="${ loginUser.memNo }">
			      </div>
			      </form>
			    </div>
			  </div>
			</div>
	
				<script>
				function postFormSubmit(num, commentNo) {
					if(num == 0) {
						$('#postForm').attr('action', 'updateForm.bo').submit();				
					}
					else if(num == 1) {
						$('#postForm').attr('action', 'delete.bo').submit();				
					}
					else if(num == 2) {
						$('.boardCommentUpdate').click(function(e){
							$('#boardCommentUpdateModal').modal("show");
						    $('#commentNoInput').val(commentNo);
						})
					} else {					
						$('#commentInput').val(commentNo);
						$('#CommentPostForm').attr('action', 'delete.co').submit();
					}
				}
			</script>
			
			<form id="CommentPostForm" action="delete.co" method="post">
			<input id="commentInput" type="hidden" name="commentNo" value="">
			<input type="hidden" name="boardNo" value="${ b.boardNo }">
			</form>
			
			<script>
				$('#boardReportBtn').click(function(e){
					$('#boardReport').modal("show");
				});
				
				$('.commentReportBtn').click(function(e){
					$('#commentReport').modal("show");
				});
				
				
			</script>
			
			<%--
			<!-- 좋아요 select -->
			<script>
			$(() => {
			    $.ajax({
			        url: 'selectLike.do',
			        data: {
			            boardNo: ${b.boardNo},
			            memNo: '${loginUser.memNo}'
			        },
			        success: function(like) {
			            if (like.boardLikeStatus === "Y") {
			                $(".like").html(`<a href="#" id="like" class="like"><img class="heart" src="resources/images/fullHeart.png" alt="하트"></a>&nbsp;${b.likeCount}`);
			            } else {
			                $(".like").html(`<a href="insertboardLike.do" id="like" class="like"><img class="heart" src="resources/images/emptyHeart.png" alt="빈하트"></a>&nbsp;${b.likeCount}`);
			            }
			        }
			    });
			});
			</script>
			 --%>
			<!-- 좋아요 update -->
			<script>
			function updateLike() {
				  $.ajax({
				    url: 'updateBoardLike.do',
				    data: {
				      boardNo: ${b.boardNo},
				      memNo: '${loginUser.memNo}'
				    },
				    success: function(response) {
			              alert('좋아요 등록!');
			              location.href = 'detail.bo?bno=' + ${b.boardNo};}
				  });
				}
			</script>
			<!-- 좋아요 delete -->
			<script>
			function deleteLike() {
				  $.ajax({
				    url: 'deleteBoardLike.do',
				    data: {
				      boardNo: ${b.boardNo},
				      memNo: '${loginUser.memNo}'
				    },
				    success: function(response) {
			              alert('좋아요 삭제!');
			              location.href = 'detail.bo?bno=' + ${b.boardNo};}
				  });
				}
			</script>
						
			<!-- 좋아요 insert -->
			<script>
			function insertLike() {
				  $.ajax({
				    url: 'insertBoardLike.do',
				    data: {
				      boardNo: ${b.boardNo},
				      memNo: '${loginUser.memNo}'
				    },
				    success: function(response) {
			              alert('좋아요 등록!');
			              location.href = 'detail.bo?bno=' + ${b.boardNo};}
				  });
				}
			</script>
			
			<!-- 댓글신고하기 ajax -->
			<script>
				$(() => {
					$.ajax({
						url : 'disabled.btn',
						data : {
							refBoardNo : ${b.boardNo},
							memNo : '${loginUser.memNo}'
						},
						success : data => {
							const btns = $('.commentReportBtn');
							//console.log(btns);
							const nums = data.map((a, b) => {
								//console.log(a);
								//console.log(b);
								return a.refCommentNo;
							});
								//console.log(nums);
							btns.map((b, i) => {
								//console.log(i);
								nums.map((n, j) => {
									console.log(j);
									if(i.id == n){
										
										$(i).attr('disabled', true);
									}
								})
							})
						}
					})
				})
			</script>
			
			
			<script>
			$(function() {
				  $('.commentReportBtn').each(function(index) {
				    $(this).click(function(e) {
				      var selectedComment = $(this).closest('#commentContentBox');
				      var refCommentNo = selectedComment.find('.commentNo').eq(index).text(); // 피신고댓글번호
				      var refMemberNo = selectedComment.find('.memNo').eq(index).text(); // 피신고자번호
				      
				      $('#commentReportSubmit').click(function(e) {
				        if ($('#reportContent').val().trim() != '') {
				          $.ajax({
				            url: 'report.co',
				            data: {
				              refCommentNo: refCommentNo,
				              refMemberNo: refMemberNo,
				              refBoardNo: ${b.boardNo},
				              reportContent: $('#reportContent').val(),
				              memNo: '${loginUser.memNo}'
				            },
				            success: function() {
				              alert('신고성공!');
				              location.href = 'detail.bo?bno=' + ${b.boardNo};
				            },
				            error: function() {
				            }
				          });
				        } else {
				          alert('내용을 입력해주세요!');
				        }
				      });
				      
				      $('.commentReport').modal("show");
				    });
				  });
				});
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