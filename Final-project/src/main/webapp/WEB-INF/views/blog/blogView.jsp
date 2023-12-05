<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블로그 메인</title>
<link rel="stylesheet" href="resources/css/common/template.css">

</head>
<style>


* {
    border: 1px solid skyblue;
	box-sizing: border-box;
}

#pageAsideLeft{display: block; padding: 30px;}

#pageAsideLeft div{
   width: 100%;
   height: auto;
}

article{padding: 20px; border:1px solid #888; border-radius: 10px; margin-top: 10px;}

main{height: auto; display: flex !important;}

main > div{float: none;}

#content{padding: 10px;}

#blogInfo{
    width: 100%;
    height: 100px;
    padding: 10px;
}

#plantWrap{
    width: 100%;
    height: 260px;
    font-size: 25px;
    font-weight: bold;
    color: #448300;
}

#plantList{
	padding-left: 10px;
}

#plantBox{width: 100%; height: auto;}

#plantArticle{ height: auto;}

#plantWrap > div > div{
    float: left;
    height: 225px;
    text-align: center;
    color: #448300;
    padding: 10px;
}

#plantNickName{padding: 10px;}

#plantButtonWrap{width: 140px; item-align: center;}

.button{
	width: 45px;
	height: 45px;
	cursor: pointer;
}
.forest{
    font-size: 20px;
    font-weight: bolder;
    border-radius: 30px;
    border:2px solid #afdba3;
	background-color: #afdba3;
    color: white;
	margin: 10px auto;
}

#blogImg{width: 100%; height: 200px;}

#blogImg > div{width: 100%; height: 200px;}

#memId{font-size: 20px; font-weight: bold;}

#blogIntr{font-size: 15px; }

#categoryWrap{
    width: 100%;
    height: 100px;
    list-style: none;
    padding: 10px;
}

li{list-style-type: none;}
ul{padding: 10px;}

#blogTitle{font-size: 25px; font-weight: bold; padding: 10px;}

#blogBoardWrap{width: 100%; height: auto;}

#blogBoardWrap div{float: left;}

#boardCategory, #blogBoardTitle, #createDateWrap{
	padding: 5px;
}

#boardCategory{
	width: 100%;
	height: 40px; 
	font-size: 15px; 
	color: gray;
	padding-left: 7px;
}

#blogBoardTitle{
	width: 100%;
	height: 40px; 
	font-size: 30px;
	font-weight: bold;
	color: #448300;
}

#createDateWrap{
	width: 100%;
	height: 40px; 
	font-size: 15px; 
	color: gray;
	padding-left: 7px;
}

#blogBoardContent{
	width: 100%;
	height: auto; 
	font-size: 15px; 
	padding: 10px;
	border-top: 1px solid #888;
}

#blogImg img{object-fit: cover; width:200px;}

img[name=plantImg]{width: 160px; height: 160px; border-radius: 10px;}

img[name=plantImg]:hover{cursor: pointer; }

#imageInputWrap{
  width: 300px; height: 300px; 
  display: flex;
  align-items: center;
  justify-content: center;

}

img[name=imageThumbnail]{    
    width: 100%;
    height: 100%;
    object-fit: cover;
}


#commentWrap {width: 100%; height: auto; }

#commentWrap > div{float: left;}

#commentOption{width:100%; height:auto; font-size: 18px; font-weight: 600; padding:0 10px;}

#commentOption > a{color : gray; font-size : 12px; padding:10px;}

.commentContentBox{width: 100%; height: auto; display: none; padding:10px;}

#commentContentBox > div{float: left; padding:5px; }

#commentWriteMemId{width: 100%; height: 30px; font-weight: bold; font-size: 14px; padding: 0 10px;}

#commentContent{width: 100%; height: 30px; font-size: 13px; padding: 0 10px;}

#commentCreateDate{width: 100%; height: 30px; font-size: 12px; color: gray; padding: 0 10px;}

#commentCreateDate > a{color: gray;}

#commentInsertBox{width: 98%; height: 100px; border: 0.5px solid darkgray; border-radius: 10px; background-color: white; padding: 5px; height: 95%; margin: 10px; display: none;}

#commentWriter{width: 100%; height: 30px; font-size : 15px; font-weight: 600; padding:0 10px;}

#commentContentInsert{width: 100%; height: 30px; background-color: transparent; resize: none; outline: 0; border: 0; padding:0 10px;}

.visible{display: block !important;}

#clear{clear:both;}

</style>
<body>

	<header id="pageHeader">
		<jsp:include page="../common/header.jsp" />
	</header> 
	<main>
		<aside id="pageAsideLeft" class="aside">
            <div id="blogInfo">
                <ul id="blogInfoUl">
                    <li id="blogImg">
                    <div id="imageInputWrap"><img name="imageThumbnail" src="${ blog.filePath }${ blog.updateName }"/></div>
                    </li>
                    <li id="memId">${ blog.memNick }(${ blog.memId })</li>
                    <li id="blogIntroduce">${ blog.blogIntroduce }</li>
                    <li id="">
	                    <a href="insertForm.bl_bo?blogNo=${ blog.blogNo }">글 쓰기</a>
	                    <a href="updateForm.bl?blogNo=${ blog.blogNo }">블로그 관리</a>
	                    <a href="updateForm.bl_ct?blogNo=${ blog.blogNo }">카테고리 관리</a>
                    </li>
                </ul>
          
            </div>
            <div id="categoryWrap">
                <ul>
                	<c:forEach var="i" items="${ list }">
                    	<li><a href="#" >${ i.categoryMemName }</a></li>
                    </c:forEach>
                   	
                </ul>
            </div>
		</aside>
		
		<section id="pageSection">
			
			<div id="blogTitle">
			    ${ blog.blogTitle }
			</div>
			
			<br clear="both">
			
			<div id="content">
               <div id="plantWrap">
               <div id="plantList" >
                    <a href="selectList.bl_pl?blogNo=${ blog.blogNo }">식물 일지</a>
               </div>
				<div id="plantBox">
					<c:forEach var="p" items="${ plantList }">
						<div id="plantArticle">
						<c:choose>
							<c:when test="${ empty p.filePath }">
							<div><img name="plantImg" src="resources/images/defaultPlant.png"
									value="${ p.plantNo }" /></div>
							</c:when>
							<c:otherwise>
							<div><img name="plantImg" src="${ p.filePath }${ p.updateName }"
										value="${ p.plantNo }" /></div>
							</c:otherwise>
						</c:choose>
							<div id="plantNickName">${ p.plantNickName }</div>
						</div>
					</c:forEach>
						
					<div id="plantButtonWrap">
						<a href="insertForm.bl_pl?blogNo=${ blog.blogNo }">
						<button class="button forest">+</button></a>
					</div>
					<br clear="both">
				</div>
				</div>
				
				<c:forEach var="b" items="${ blogBoardList }" >
				<article>
					<div id="blogBoardWrap" >
						<div id="boardCategory">
						 	${ b.categoryMemName }
						</div>
						<div id="blogBoardTitle">
						 	${ b.blogBoardTitle }
						</div>
						<div id="createDateWrap" >
							${ b.createDate }
						</div>
						<div id="blogBoardContent">
							${ b.blogBoardContent }
						</div>
					</div>
					
					<br clear="both">
					
					<div id="commentWrap" name="${ b.blogBoardNo }">
						<div id="commentOption">
							<button id="toggleButton" onclick="buttonClicked(this)">&or;댓글 18개</button> 좋아요 30
						</div>
						<!-- 
						<div id="commentContentBox" name="toggleDiv" display="block"> 
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
						-->				
						<div id="commentInsertBox" name="toggleDiv">
							<div id="commentWriter">유저ID</div>
							<textarea id="commentContentInsert" placeholder="댓글을 남겨보세요"></textarea>
							<div id="submitWrap"><a id="insertCommentButton" href='#' onclick="insertComment(this);" name="${ b.blogBoardNo }">등록</a></div>
						</div>
					</div>
					
					<br clear="both">
				</article>
				</c:forEach>
			</div>
			
		
		</section>
		
		<aside id="pageAsideRight" class="aside">
		 달력 부분
		</aside>
		
	</main>
	
	
	<script>
		// 댓글 작성
		function insertComment(){
			var blogBoardNo = $(arguments[0]).attr('name');
			var content = $(arguments[0]).parent().parent().children().next();
			var aa = $(arguments[0]).parent().parent().parent().attr('name');
			console.log(aa);
			
			if('${ sessionScope.loginUser.memNo}' != ''){
				
				$.ajax({
					type: "POST",
					url: 'insert.bl_re', 
					data: {blogBoardNo : blogBoardNo, 
						   writer: '${ sessionScope.loginUser.memNo}', 
						   blogReplycontent: content.val()}, 
					success: data => {
						console.log('댓글 입력 통신 성공');
						alert('댓글 작성 성공');
						content.val('');
						
					}, 
					error: () => {
						console.log('댓글 입력 통신 실패');
					}
				});
			}
			else{
				alert('로그인 후 이용 가능한 기능입니다.');
			}
		};

	// 토글 이벤트 실행 상태 
	 let flag = true;
	
	// 댓글 영역 클릭 했을 시 토글 이벤트
	function buttonClicked(button) {
	  //console.log($(arguments[0]).parent().parent().attr('name'));
			
	  var commentWrap = $(button.parentNode.nextElementSibling);
	  if (commentWrap.style.display === 'none') {
	    commentWrap.style.display = 'block';
	  } else {
	    commentWrap.style.display = 'none';
	  }
  			
			const toggleButton = commentWrap.find('#toggleButton');
			const commentInsertBox = commentWrap.find('#commentInsertBox');
			const divElements = toggleButton.siblings().add(commentInsertBox);
			//$(arguments[0]).parent().siblings().toggle('visible');
			
			divElements.toggle('visible');

			  if (flag) {
			  	flag = false; // AJAX 요청을 보내기 전에 플래그 변수를 false로 설정하여 다음에 요청이 발생하지 않도록 합니다.
			  } 
			  else {
			  	return; // 이미 AJAX 요청을 보냈으면 함수를 종료합니다.
			  }
			
			console.log(flag);
	};
	
	
	jQuery(document).ready(function(){});
		
	// 댓글 조회 
	// 인자값: 어디서 실행 되는지 
	function selectReply(){
		
		$.ajax({
			url: 'selectList.bl_re',
			data: {currentPage: 1,
				   blogBoardNo: commentWrap.attr('name')}, 
			success: data => {
				console.log('댓글 불러오기 통신 성공');
				
					const commentOption = commentWrap.find('#commentOption'); // commentWrap 내부에서 commentOption을 찾음
					for(let i in data){
				
						const commentOption = $('#commentOption'); // 여기 밑에서부터 추가되어야 함
						
						var memNick = data[i].memNick;
						var blogReplycontent = data[i].blogReplycontent;
						var createDate = data[i].createDate;
						
						const commentWriteMemId = document.createElement("div");
						$(commentWriteMemId).html('memNick')
											.addClass('#commentWriteMemId');
						var strMemId = "<div id='commentWriteMemId'>" + memNick + "</div>";
						
						const commentContent = document.createElement("div");
						$(commentContent).html('commentContent')
										 .addClass('#commentContent');
						var strContent = "<div id='commentContent'>" + blogReplycontent + "</div>";
						
						
						const commentCreateDate = document.createElement("div");
						$(commentCreateDate).html('createDate')
											.addClass('#commentContent');
						var strDate = "<div id='commentCreateDate'>" + createDate + "</div>";
						
						$(commentOption).append(strMemId).append(strContent).append(strDate);
					
				}

			},
			error: () => {
				console.log('댓글 불러오기 통신 실패');
			}
		});
		
		
		
	}		
		
	// 식물 사진 클릭 시 식물 상세보기로 이동
	 $('img[name=plantImg]').on('click', e => {
		var plantNo = $(e.target).attr('value');
		location.href = "select.bl_pl?plantNo=" + plantNo ; 
	 });
	
	</script>

	<br clear="both">
	
	<footer id="pageFooter">
		<jsp:include page="../common/footer.jsp" />
	</footer>


</body>
</html>