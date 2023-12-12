<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식물 일지 작성</title>
<link rel="stylesheet" href="resources/css/common/template.css">

</head>
<style>


* {
    border: 1px solid skyblue;
	box-sizing: border-box;
}

#blogTitle{
	font-size: 25px; 
	font-weight: bold; 
	padding: 20px;
	color: #00610C;
}
	
#plantInfoWrap > div{float: left;}
#plantInfoWrap{width: 100%; height: auto;}

#plantImg{width: 20%; height: 180px; float: middle;}

#plantImgInput{width:100%; height: 160px;}

#plantImfo{width: 80%; height: auto;}
#plantNick{font-size: 17px; font-weight: bold; color: #448300;}
#plantNick > div{display: inline; padding: 10px;}

#plantCreateDate{font-size: 15px; color: #888;}

#plantComment{width: 100%; height: 160px; padding: 10px;}
ul{
	list-style: none;
    padding: 0px;
    margin: 0px;
}
li{
	padding: 5px 20px;
    position: relative;
}
.button{
	width: 130px;
	height: 35px;
	cursor: pointer;
    margin: 0px;
}
.forest{
    font-size: 20px;
    font-weight: bolder;
    border-radius: 10px;
    border:2px solid #afdba3;
	background-color: #afdba3;
    color: white;
	margin: 5px 15px 5px 0px;
}

#blogIntroduce a{
    cursor: pointer;
	display: inline-block;
    font-size: 14px;
    line-height: 17px;
    text-decoration: none;
    color: #888;
	}
	
input[type=file]{display: none;}

textarea{
    resize: none;
    width: 99%;
    height: 100px;
    outline-color: #afdba3;
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
                    <li id="blogImg">
                    <img src=""/>사진 공간</li>
                    <li id="memId">닉네임(아이디)</li>
                    <li id="blogIntroduce">${ blog.blogIntroduce }</li>
                    <li id="">
	                    <a href="">글 쓰기</a>
	                    <a href="/final/blog/updateForm?blogNo=${ blog.blogNo }">블로그 관리</a>
	                    <a href="/final/blog/updateForm/category?blogNo=${ blog.blogNo }">카테고리 관리</a>
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
			   	식물 일지 추가
			</div>
			
			<div id="content">
				<article>
					<form method="post" action="/final/blog/insert.pr" enctype="multipart/form-data" enctype="multipart/form-data">  
					<input type="hidden" name="blogNo" value="${ plant.blogNo }"/>
					<input type="hidden" name="topPlantNo" value="${ plant.plantNo }"/>
					<input type="hidden" name="plantReportcategoryNo" value="10"/>
					
                    <div id="plantInfoWrap">
                        <div id="plantImg"><input type="file" name="upfile" id="plantInput"/>
                        	<img src="resources/images/defaultPlant.png" id="plantImgInput" name="plantThumbnail">
                        </div>
                        <div id="plantImfo">
                            <ul>
                                <li id="plantNickName">
	                                <div>${ plant.plantNickName }의 기록입니다
	                                </div>기록 일자: <div id="plantLogDate"><input type="date" name="reportDate"></div>
                                </li>
                                <li id="plantComment" >
                                	<textarea placeholder="식물에 대한 기록을 작성해주세요. 최대  1000자 까지 작성할 수 있습니다. " name="reportComment" required></textarea>
                                </li>
                            </ul>
                        </div>
                        <div>
                        	<button type="submit" id="plantCare" class="button forest">등록하기</button>
                        	<button id="plantCare" class="button forest">돌아가기</button>
                        </div>
                    </div>
                    
                    <br clear="both">
					</form>
				</article>
			</div>
			
			<script>
				// 식물 관리 날짜: 식물 등록일 ~ 오늘 날짜
				var nowDate = Date.now();
				var timeOff = new Date().getTimezoneOffset()*60000;
				var today = new Date(nowDate-timeOff).toISOString().split("T")[0];
				var logDate = '${ plant.plantLogDate}';
				
				$('input[name=reportDate]').attr("min", logDate).attr("max", today).val(today);
			      plantImgInput.addEventListener('click', function(){
			    	  plantInput.click();
			    });
			      
	            // 이미지를 첨부했을 시 미리보기 가능하게
	            $('input[name=upfile]').on('change', function(inputFile){
					let reader = new FileReader();
					reader.readAsDataURL(inputFile.target.files[0]);
					reader.onload = e => {
					$('img[name=plantThumbnail]').attr('src', e.target.result);
					}
				});
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