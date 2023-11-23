<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>체험학습 상세조회</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
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

.files{
	width : 80%;
	height : 400px;
	object-fit : contain;
}

.summary{
	
}

.summary li {
	list-style : none;
	align : center;
	
}

<!-- 댓글 --!>
#commentWrap {width: 100%; height: auto;}
#commentWrap > div{float: left;}
#commentOption{width:100%; height:30px; font-size: 18px; font-weight: 600; padding:0 10px;}
#commentOption > a{color : gray; font-size : 12px; padding:0 10px;}
#commentContentBox{width: 100%; height: auto;}
#commentContentBox > div{float: left;}
#commentWriteMemId{width: 100%; height: 25px; font-weight: bold; font-size: 13px; padding: 0 10px;}
#commentContent{width: 100%; height: 25px; font-size: 11px; padding: 0 10px;}
#commentCreateDate{width: 100%; height: 20px; font-size: 10px; color: gray; padding: 0 10px; margin-bottom: 10px;}
#commentCreateDate > a{color: gray;}
#commentInsertBox{width: 100%; height: 100px; border: 0.5px solid darkgray; border-radius: 10px; background-color: white; padding: 5px; height: 95%; margin-top: 10px;}
#commentWriter{width: 100%; height: 30px; font-size : 15px; font-weight: 600; padding:0 10px;}
#commentContentInsert{width: 100%; height: 30px; background-color: transparent; resize: none; outline: 0; border: 0; padding:0 10px;}
#submitWrap{float: right; margin: -36px 10px -36px 0px;}
#submitWrap > a{color : gray; font-size : 12px; padding:0 10px; z-index: 9; position: relative;}

#commentProfile {float:right;}
#reply {
	padding : 20px;
}


</style>

<style>
.material-symbols-outlined {
  font-variation-settings:
  'FILL' 0,
  'wght' 400,
  'GRAD' 0,
  'opsz' 24
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
				<c:if test="${ sessionScope.loginUser ne requestScope.exp.expWriter }">
					<div id="forWriter">
						<a class="btn btn-primary" onclick="expSubmit(0);">수정하기</a>
						<a class="btn btn-danger" onclick="expSubmit(1);">삭제하기</a>
					</div>
				</c:if>
				<!-- 악성유저 방지차원 post방식으로 보내주기 -->
				<form action="" method="post" id="postForm">
					<input type="hidden" name="expNo" value="${ exp.expNo }" />
				</form>
				
				<script>
					function expSubmit(type){
						if(type == 0){
							$('#postForm').attr('action', 'yrupdateExp.exp').submit();
						}
						else{
							if(confirm('정말로 삭제하시겠습니까?')){
								$('#postForm').attr('action', 'yrdeleteExp.exp').submit();
							}
						}
					};
				</script>
	
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
							<img src="${ files[0].filePath }/${ files[0].updateName }" id="thumb" />
						</c:if>
					</div>
					
					<div>
						<a id="like">🤍</a>
					</div>
					
					<script>
						//let heartCheck = 0;
						$('#like').click(function(){
							//heartCheck++;
							
							console.log($('#like').text() == '🤍' );
							
							($('#like').text() == '🤍') ? $(this).text(' 🤍 ') : $(this).text( '🤍' );
							// 좋아요이면 true, 아니면 false
							//const like = $('#heart').text();
							const likeVal = $('#like').text() == '🤍' ? 1 : 0;
							console.log(likeVal)
							
							//const heartVal = (heartCheck % 2);
							
							$.ajax({
								url : 'yrexpLike',
								data : {
									expNo : ${ exp.expNo },
									like : likeVal
								},
								success : result => {
									// (result == 'true') ? $('#heart').text('❤') : $('#heart').text('🤍'); 
								},
								error : () => {
									console.log("체험학습 게시글 좋아요 통신오류")
								}
								
							});
							
						});
							
						
					</script>
					
					
					
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
					<p>${ exp.expContent }</p>
				</div>
				<div id="">
					<c:if test="${ not empty requestScope.files }">
						<c:forEach var="f" items="${ requestScope.files }">
							<img src="${ f.filePath }${ f.updateName }" class="files" />
							<p>${ f.fileAnnotation }</p>
						</c:forEach>
					</c:if>
				</div>
				<!-- 해시태그  -->
				<div>
				</div>
				
				
				<div>
					
					<div id="commentInsertBox">
						<div>
							<div>
								<img src="resources/images/person.png" />
							</div>
							<div id="commentWriter">유저ID</div>
						</div>
						<div>
							<textarea id="commentContentInsert" placeholder="댓글을 남겨보세요"></textarea>
							<input type="checkbox" id="secret" />
							<label for="secret">비밀댓글로 설정하기</label>
						</div>
						<c:choose>
							<c:when test="${ loginUser eq null }">
								<div id="submitWrap"><a href='#' onclick="insertReply();">등록</a></div>
							</c:when>
							<c:otherwise>
								<div id="submitWrap"><a href='#' onclick="alert('로그인 후 이용 가능한 기능입니다.');">등록</a></div>
							</c:otherwise>
						</c:choose>
					</div>
					<!-- 댓글 AJAX처리 -->
					<div>
						댓글
						<table id="reply">
							<thead>
								<tr>
									<th>사진</th>
									<th>아이디</th>
									<th>내용</th>
									<th>작성일</th>
									<th>좋아요</th>
									<th>비밀글</th>
								</tr>
							</thead>
							<tbody>
							
							</tbody>
						</table>
					</div>
				</div>
				
			</div>


		<script>
		
			// 댓글 작성기능
			function insertReply(){
				console.log("엥");
				console.log(${ exp.expNo });
				console.log('user01');
				console.log($('#commentContentInsert').val());
				console.log($('input[type=checkbox]:checked').length);
				
				const data = {
						expNo : '${ exp.expNo }',
						replyWriter : 'user01',  // 로그인 유저로 바꿔야 함 ${ loginUser.memId }
						replyContent : $('#commentContentInsert').val(),
						replySecret : $('input[type=checkbox]:checked').length
				};
				
				$.ajax({
					url : "yrinsertExpReply.exp",
					type : 'post',
					dataType: 'json',
		            contentType: 'application/json; charset=utf-8',
					data :JSON.stringify(data), 
					success : result => {
						console.log(result);
						if(result == 'success'){
							$('#commentContentInsert').val('');
							$('input[type=checkbox]:checked').prop('checked', false);
							selectReply();
						}
					},
					error : () => {
						console.log("체험학습 댓글 작성 통신 오류");
					}
				});
			}
			
			// 댓글 조회기능
			$(() => {
				selectReply();
			});
			
			function selectReply(){
				$.ajax({
					url : "yrselectExpReplyList.exp",
					data : {expNo : ${ exp.expNo }},
					
					success : result => {
						let value = '';
						let $resultValue = result;
						
						// 비밀글은 댓글작성자와 게시글작성자만 보여지게 하기
						for(let i in result){
							console.log("뭐로 나올까");
							console.log(result[i].replyModifyDate);
							
							if((result[i].replySecret == 'N') || 
								((result[i].replySecret == 'Y') && ('user02' == result[i].replyWriter || ${ 'user02' eq exp.expWriter } ))) {
								
									console.log("일치여부 확인");
									console.log(result);
									// console.log(${ result[i].expNo });
									value += '<tr>'
										   + '<td>' + '사진' + '</td>'
										   + '<td>' + result[i].replyWriter + '</td>'
										   + '<td>' + result[i].replyContent + '</td>'
										   // 수정했다면 수정일 보여주기
										   <c:choose>
											   <c:when test="${ not empty result[i].replyModifyDate }" >
											   + '<td>' + result[i].replyModifyDate + '수정됨 </td>'
											   </c:when>
											   <c:otherwise>
											   + '<td>' + result[i].replyCreateDate + '</td>'
											   </c:otherwise>
										   </c:choose>
										   + '<td>' + '♥' + '</th>'
										   if(result[i].replySecret == 'Y'){
											   value += '<td>' + '<input type="checkbox" disabled checked />' + '</th>';
										   } 
										   + '</tr>'
							} 
							else {
								value += '<tr><td colspan=6>' + '비밀댓글입니다' + '</td></tr>';
							}
							
						}
						$('#reply > tbody').html(value);
					},
					error : () => {
						console.log("체험학습 게시글 댓글 조회 통신 오류");
					}
					
				});
			}
		</script>
		

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