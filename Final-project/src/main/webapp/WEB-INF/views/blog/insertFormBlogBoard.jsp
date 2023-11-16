<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블로그 생성 화면</title>
<link rel="stylesheet" href="resources/css/common/template.css">

<script type="text/javascript" src="naverAditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>










</head>
<style>


* {
    border: 1px solid skyblue;
	box-sizing: border-box;
}
article{
	
	height: 550px;
	margin : 20px;
	border: 1.5px solid #c6c6c6;
	border-radius: 10px;
}
#contentTitle{
    font-size: 35px ;
    font-weight: bold;
    color: #00610C;
    padding: 20px;
}

#blogInfo{
    font-size: 20px ;
    padding: 20px;
}

#createFormWrap{
    padding: 20px;
}

#createForm{
    border: none;
    font-size: 20px;
    width: 100%;
    border-spacing: 5px;
    
}

#createForm th{
    width: 25%;
    text-align: left;
    padding: 8px;
    background-color: beige;
    color: rgb(83, 57, 32);;
}

#createForm td{
    width: 75%;
    text-align: left;
    padding: 5px;
    border: 1px solid #c6c6c6;
}
textarea{
    resize: none;
    width: 99%;
    height: 100px;
    outline-color: #afdba3;
    border: none;
}

#blogButtonWrap {
   width: 100%;
   height: 100px; 
}

#blogButtonWrap > div{
   margin-top: 30px;
   float: left;
   width: 50%;
   height: 70px;
   text-align: center;
}

input[type=text] {
            width: 99%;
            height: 35px;
            font-size: 23px;
            font-weight: bolder;
            border-radius: 10px;
            border: none;
            color: rgb(83, 57, 32);
            outline-color: #afdba3;
}
.button{
	width: 250px;
	height: 45px;
	cursor: pointer;
}
.forest{
    font-size: 20px;
    font-weight: bolder;
    border-radius: 10px;
    border:2px solid #afdba3;
	background-color: #afdba3;
    color: white;
	margin: 10px auto;
}
.beige{
    font-size: 20px;
    font-weight: bolder;
    border-radius: 10px;
    border:2px solid beige;
	background-color: beige;
    color: rgb(83, 57, 32);
	margin: 10px auto;
}
a{
    cursor: pointer;
	display: inline-block;
    font-size: 14px;
    line-height: 17px;
    text-decoration: none;
    color: #888;
	}
	
</style>
<body>

	<header id="pageHeader">
		<jsp:include page="../common/header.jsp" />
	</header> 
	
	
	<main>
		<aside id="pageAsideLeft" class="aside">
		</aside>
		
		<section id="pageSection">
			
			<div id="contentTitle">
				블로그에 오신것을 환영합니다!
			</div>
			
			<div id="content">
               <div id="blogInfo">
                   	블로그 관심사와 관심 식물 카테고리를 설정하시면 관련 글을 추천해드립니다
               </div>
				<article id="pageArticle">
							<!-- SmartEditor2  -->
				<div class="jsx-2303464893 editor">
					<div class="fr-box fr-basic fr-top" role="application">
						<div class="fr-wrapper show-placeholder" dir="auto" style="overflow: scroll;">
							<textarea name="notice_content" id="smartEditor"
								style="width: 100%; height: 412px;"></textarea>
						</div>
					</div>
				</div>
				
				
				
				
				
				<div id="se2_sample" style="margin:10px 0;">
	<input type="button" onclick="save();" value="본문 내용 가져오기">
</div>








				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				</article>
			</div>
			
		
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

<!-- SmartEditor2 -->
<script type="text/javascript" src = "resources/js/naverAditor.js"></script>
