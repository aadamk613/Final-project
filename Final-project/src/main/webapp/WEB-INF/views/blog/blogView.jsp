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

#pageAsideLeft{display: block;}

#pageAsideLeft div{
   width: 100%;
   height: auto;
}
#content{padding: 10px;}
#blogInfo{
    width: 100%;
    height: 100px;
    padding: 10px;
}

#plantWrap{
    width: 100%;
    height: 120px;
    padding: 10px;
}

#plantWrap > div {
    float: left;
    width: 100px;
    height: 100px;
    text-align: center;
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
}

#blogBoardTitle{
	width: 100%;
	height: 40px; 
	font-size: 20px;
	font-weight: bold;
}

#createDateWrap{
	width: 100%;
	height: 40px; 
	font-size: 15px; 
	color: gray;
}

#blogBoardContent{
	width: 100%;
	height: auto; 
	font-size: 15px; 
	padding: 10px;
	
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

#blogImg img{object-fit: cover; width:200px;}


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
                    <div><img name="blogImg" src="${ blog.filePath }${ blog.updateName }"/></div>
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
			
			<div id="content">
               <div id="plantWrap">
                        <div>사진</div>
                        <div>사진</div>
                        <div>사진</div>
                        <div>사진</div>
                        <div>사진</div>
                        <div>사진</div>
                        <div>사진</div>
                        <div>
                        	<a href="insertForm.bl_pl?blogNo=${ blog.blogNo }">식물추가 + </a>
                        	<a href="select.bl_pl?blogNo=${ blog.blogNo }">식물 일지</a>
                        </div>
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
					<br clear="both">


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