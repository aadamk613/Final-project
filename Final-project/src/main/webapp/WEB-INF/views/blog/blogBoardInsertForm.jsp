<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블로그 게시글 작성 화면</title>
<link rel="stylesheet" href="resources/css/common/template.css">

</head>
<!-- TUI 에디터 JS CDN -->
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>

<style>

* {
    border: 1px solid skyblue;
	box-sizing: border-box;
}

#contentTitle{width : 100%; height : 100px; padding: 20px; font-size : 30px; font-weight: bold; line-height : 200%;}

#content{width : 100%; height : auto;}

article{width: 95%; height: auto; margin: 20px auto; border: 1px solid rgb(230, 230, 230); border-radius: 10px; padding: 20px;}

#boardCategoryWrap{width:100%; height:auto; padding: 5px;}

#boardCategory{width:35%; height:30px;}

#boardHeader {width: 100%; height: auto; font-size: 25px; font-weight : 600; padding: 5px;}

#boardHeader > input{width: 40%; height: 40px;}

#boardContent{width: 100%; height: auto; min-height: 400px; border: none; outline:none; padding:0px 5px; }

#buttonWrite{margin: 0px 0px 0px 5px;}


</style>
<body>
	<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />

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
				게시글 작성
			</div>
			<div id="content">
					<script>
						consolo.log("블로그 글 작성 시 categorySettingNo");
						consolo.log(${ c.categorySettingNo });
						consolo.log("블로그 글 작성 시 categoryMemName");
						consolo.log(${ c.categoryMemName });
					</script>
				<article>
					<form id="insertForm.bl" action="" method="post">
					<input type="hidden" name="writer" value="${ sessionScope.loginUser.memNo }">
					<input type="hidden" name="blogNo" value="${ sessionScope.loginUser.blogNo }">
					
						<div id="boardCategoryWrap">
							게시판&nbsp;&nbsp;
							
					
							<select name="cattegorySettingNo" id="boardCategory">
								<c:forEach var="c" items="${ list }">
									<option value="${ c.categorySettingNo }">${ c.categoryMemName }</option>
								</c:forEach>
							</select> 
							
							카테고리&nbsp;&nbsp;
							<select name="blogInterest" id="boardCategory">
								<option value="no">선택안함</option>
								<option value="book">문학&middot;책</option>
								<option value="movie">영화</option>
								<option value="art">미술&middot;디자인</option>
								<option value="show">공연&middot;전시</option>
								<option value="music">음악</option>
								<option value="drama">드라마</option>
								<option value="enter">스타&middot;연예인</option>
								<option value="animation">만화&middot;애니</option>
								<option value="broadcast">방송</option>
								<option value="daily">일상&middot;생각</option>
								<option value="parenting">육아&middot;결혼</option>
								<option value="pet">반려동물</option>
								<option value="fasion">패션&middot;미용</option>
								<option value="interior">인테리어&middot;DIY</option>
								<option value="cook">요리&middot;레시피</option>
								<option value="review">상품리뷰</option>
								<option value="game">게임</option>
								<option value="sport">스포츠</option>
								<option value="picture">사진</option>
								<option value="car">자동차</option>
								<option value="hobby">취미</option>
								<option value="travel">여행</option>
								<option value="restaurant">맛집</option>
								<option value="computer">IT&middot;컴퓨터</option>
								<option value="health">건강</option>
								<option value="economy">경제</option>
								<option value="language">외국어</option>
								<option value="edu">교육</option>
							</select>
						</div>
						<div id="boardHeader">
						<input type="text" placeholder="제목을 입력해주세요" name="blogBoardTitle" required>		
					</div>
					
					<div id="editor" required>
					</div>
					
					<!-- 에디터의 글 내용을 받아줄 div-->
    				<input type="hidden" id="contents" name="blogBoardContent" />
    				
					<button type="submit" class="btn btn-primary" id="buttonWrite">글 작성</button>
					<a href="javascript:window.history.back();" class="btn btn-default" id="buttonWrite">뒤로가기</a>
					
				</form>
				</article>
			</div>
			
		    
		    <script>
		    	//const Editor = toastui.Editor;
		        const editor = new toastui.Editor({
		            el: document.querySelector('#editor'), // 에디터를 적용할 요소 (컨테이너)
		            height: '600px',                        // 에디터 영역의 높이 값 (OOOpx || auto)
		            initialEditType: 'wysiwyg',            // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
		            initialValue: '내용을 입력해 주세요.',     // 내용의 초기 값으로, 반드시 마크다운 문자열 형태여야 함
		            previewStyle: 'vertical'                // 마크다운 프리뷰 스타일 (tab || vertical)
		        });
				
				$(document).on('click', $('#buttonWrite'),function(){
					
			        var str = editor.getHTML();
			        console.log(str);
					$('input[name=blogBoardContent]').val(str);
					$('#insertForm').attr('action', 'insert.bl_bo');
					
				});
		        
		        
		        /* 클릭 했을 시 내용을 입력해주세요 없애려고 하는데 모르겠따 띵띵~
		        $(function(){
		        	document.querySelector('#contents').insertAdjacentHTML('afterbegin' ,editor.getHtml());
		        	console.log(editor.getHtml());
		        	
		        	$('.ProseMirror').focusin(function(){
		        		console.log($('.ProseMirror').children.eq(0));
		        		$('.ProseMirror').children.eq(0).attr('value', '');
		        	})
		        })
		        */
		        
		    </script>
		
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