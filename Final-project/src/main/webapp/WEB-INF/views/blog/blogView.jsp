<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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

#pageAsideLeft{display: block;}

#pageAsideLeft div{
   width: 100%;
   height: auto;
}

#blogInfo{
    width: 100%;
    height: 100px;
    padding: 10px;
}

#plantWrap{
    width: 100%;
    height: 100px;
}



#plantWrap > div {
    float: left;
    width: 100px;
    height: 100px;
    padding: 10px;
    margin: 10px;
    text-align: center;
    
}

#blogImg{width: 100%; height: 150px;}

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
	width: 100%;
	height: 30px; 
	margin: 5px;
}

#boardCategory{
	width: 100%;
	height: 30px; 
	font-size: 15px; 
	color: gray;
	
}

#blogBoardTitle{
	width: 100%;
	height: 30px; 
	font-size: 20px;
	font-weight: bold;
}

#createDateWrap{
	width: 100%;
	height: 30px; 
	font-size: 15px; 
	color: gray;
}

#blogBoardContent{
	width: 100%;
	height: auto; 
	font-size: 15px; 
	margin: 5px;
	
}

#likeCommentWrap{
	width: 100%; 
	height: auto;
	
}

#likeCommentUl{
	width: 100%;
	height: 30px;
	list-style-type: none;
}


</style>
<body>

	<header id="pageHeader">
		<jsp:include page="../common/header.jsp" />
	</header> 
	<main>
		<aside id="pageAsideLeft" class="aside">
            <div id="blogInfo">
                <ul id="blogInfoUl">
                    <li id="blogImg"><img src=""/>사진 공간</li>
                    <li id="memId">닉네임(아이디)</li>
                    <li id="blogIntroduce">${ blog.blogIntroduce }</li>
                    <li id=""><a href="">글 쓰기</a><a href="updateForm.bl?blogNo=${ blog.blogNo }">블로그 관리</a></li>
                </ul>
          
            </div>
            <div id="categoryWrap">
                <ul>
                    <li>식물일지</li>
                    <li>---------</li>
                    <li>일반 게시판1</li>
                    <li>일반 게시판2</li>
                    <li>---------</li>
                    <li>일반 게시판3</li>
                    <li>일반 게시판4</li>
                    <li>---------</li>
                </ul>
            </div>
		</aside>
		
		<section id="pageSection">
			
			<div id="blogTitle">
			    ${ blog.blogTitle }
			</div>
			
			<div id="content">
               <div id="plantWrap">
                        <div>사진</div>
                        <div>사진</div>
                        <div>사진</div>
                        <div>사진</div>
                        <div>사진</div>
                        <div>사진</div>
                        <div>사진추가 + </div>
               </div>
				<article>
					<div id="blogBoardWrap">
						<div id="boardCategory">
						 게시글 카테고리
						</div>
						<div id="blogBoardTitle">
						 	블로그 일반 게시글 제목 부분
						</div>
						<div id="createDateWrap" >
							2023/10/31 [15:32]
						</div>
						<div id="blogBoardContent">
							블로그 글 내용입니다.~~~~ <br>
							우하아하<br>
							블로그 글 내용입니다.~~~~ <br>
							우하아하<br>
							블로그 글 내용입니다.~~~~ <br>
							우하아하<br>
							블로그 글 내용입니다.~~~~ <br>
							우하아하<br>
						</div>
					</div>



				</article>
			</div>
			
		
		</section>
		
		<aside id="pageAsideRight" class="aside">
		 달력 부분
		</aside>
		
	</main>
	
	<br clear="both">
	
	<footer id="pageFooter">
		<jsp:include page="../common/footer.jsp" />
	</footer>


</body>
</html>