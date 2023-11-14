<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>화면 틀입니다 복사해서 사용해주세요</title>
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


#plantWrap > div{
    width: 100px;
    height: 100px;
    padding: 10px;

    
}

#plantWrap > div > div{
    float: left;
    width: 100px;
    height: 100px;
    padding: 10px;
    
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


</style>
<body>

	<header id="pageHeader">
		<jsp:include page="../common/header.jsp" />
	</header> 
	<main>
		<aside id="pageAsideLeft" class="aside">
            <div id="blogInfo">
                <ul>
                    <li id="blogImg"><img src=""/>사진 공간</li>
                    <li id="memId">닉네임(아이디)</li>
                    <li id="blogIntr">블로그 소개 어쩌구저쩌구 블로그입니당 우하하하!</li>
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
			    블로그 제목
			</div>
			
			<div id="content">
               <div id="plantWrap">
                   <div>
                        <div>사진</div>
                        <div>사진</div>
                        <div>사진</div>
                        <div>사진</div>
                        <div>사진</div>

                   </div>
               </div>
				<article>

                    <table>
                        <tr>
                            
                        </tr>

                    </table>

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