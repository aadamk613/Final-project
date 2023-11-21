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
#plantInfoWrap > div{float: left;}
#plantInfoWrap{width: 100%; height: auto;}

#plantImg{width: 20%; height: 160px; float: middle;}

#plantImfo{width: 80%; height: auto;}
#plantName{font-size: 20px; font-weight: bold;}
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
    border: none;
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
			
			<div id="blogTitle">
			   	식물 일지 리스트
			</div>
			
			<div id="content">
                
                <c:forEach var="p" items="${ list }">
				<article>
                    <div id="plantInfoWrap">
                        <div id="plantImg">
                        	식물 사진
							<img src="${ files.filePath }/${ files.updateName }" class="files" />
						</div>
                        <div id="plantImfo">
                            <ul>
                                <li id="plantName">식물 이름 : ${ p.plantName }</li>
                                <li id="plantNick">
	                                <div>별명 : ${ p.plantNickName }</div>
	                                <div id="plantCreateDate">D+${ p.plantLogDate }</div>
                                </li>
                                <li id="plantButtonWrap">
                                    <button id="plantReport" class="button forest" onclick="plantCare(${ p.plantNo }, 10, ${ p.plantLogDate });">일지 추가</button>
                                    <button id="plantCare" class="button forest" onclick="plantCare(${ p.plantNo }, 20, ${ p.plantLogDate });">관리하기</button>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <br clear="both">
                    <div id="plantApiWrap">
                                                      어쩌구 저쩌구 <br>
                        Api에서 불러 온 정보
                    </div>
				</article>
				</c:forEach>
			</div>
			
			<script>
			    $(()=>{
			    	
			      plantImgInput.addEventListener('click', function(){
			    	  plantInput.click();
			      });
			    });
			      
			      function plantCare(plantNo, category, plantNickName){
						console.log(category);
						console.log(plantNo);
						console.log(plantNickName);
						
			    	 location.href= 'insertForm.bl.pr.' +  plantNo + '.' + category + '.' + plantNickName;

		
			    };
			     
			      
			      
			      
			</script>
			
			
		
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