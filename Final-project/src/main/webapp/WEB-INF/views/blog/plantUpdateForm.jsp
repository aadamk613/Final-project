<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식물 수정</title>
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

#plantImg{width: 20%; height: 200px; float: middle;}

#plantImgInput{width:100%; height: 200px;}

#plantImfo{width: 80%; height: auto;}
#plantName{font-size: 20px; font-weight: bold;}
#plantNick{font-size: 17px; font-weight: bold; color: #448300;}
#plantNick > div{display: inline; padding: 10px;}

#plantCreateDate{font-size: 15px; color: #888;}

#plantComment{width: 100%; height: 160px; padding: 10px;}
article ul{
	list-style: none;
    padding: 0px;
    margin: 0px;
}
article li{
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
.beige{
    font-size: 20px;
    font-weight: bolder;
    border-radius: 10px;
    border:2px solid beige;
	background-color: beige;
    color: rgb(83, 57, 32);
	margin: 10px auto;
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

#plantCare a{text-decoration: none;  color: white;}

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
			   	식물 수정
			</div>
			
			<div id="content">
				<article>
					<form method="post" action="update.bl_pl" enctype="multipart/form-data">  
					<input type="hidden" name="blogNo" value="${ blogNo }"/>
					<input type="hidden" name="plantNo" value="${ plant.plantNo }"/>
					<input type="hidden" name="updateName" value="${ plant.updateName }" />
					<input type="hidden" name="filePath" value="${ plant.filePath }" />
                    <div id="plantInfoWrap">
                        <div id="plantImg"><input type="file" name="upfile" id="plantInput"/>
                        	<c:choose>
                        	<c:when test="${ empty plant.filePath }" >
                        	<img src="resources/images/defaultPlant.png" id="plantImgInput" name="plantThumbnail">
                        	</c:when>
                        	<c:otherwise>
                        	<img src="${ plant.filePath }${ plant.updateName }" id="plantImgInput" name="plantThumbnail">
                        	</c:otherwise>
                        	</c:choose>
                        </div>
                        <div id="plantImfo">
                            <ul>
                                <li id="plantName">식물명 : <input type="text" name="plantName" value="${ plant.plantName }" readOnly/></li>
                                <li id="plantNickName">
	                                <div>애칭 : <input type="text" name="plantNickName" value="${ plant.plantNickName }"/>
	                                </div>키우기 시작한 일자: <div id="plantLogDate"><input type="date" name="plantLogDate" value="${ plant.plantLogDate }"></div>
                                </li>
                                <li id="plantComment" >
                                	<textarea placeholder="식물에 대한 코멘트를 작성해주세요" name="plantComment" >${ plant.plantComment }</textarea>
                                </li>
                            </ul>
                        </div>
                        <div>
                        	<button type="submit" id="plantCare" class="button forest">수정하기</button>
                        	<button id="plantCare" class="button forest"><a href="select.bl_pl?plantNo=${ plant.plantNo }">돌아가기</a></button>
                        </div>
                    </div>
                    
                    <br clear="both">
					</form>
				</article>
			</div>

			<script>
			
				/* 식물 logDate문자열 날짜로 변환시키려 했는데 어려워서 sql에서 잘라서 가져오기
			     console.log(new Date('${ plant.plantLogDate }'));
			     
			     var logdate = new Date('${ plant.plantLogDate }').getYear();
				
				$('input[name=plantLogDate]').val(logdate);
				*/
				
				// 현재 날짜를 알아낸 후 식물 등록 일자에서 현재 날짜 이후는 입력 불가
				var nowDate = Date.now();
				var timeOff = new Date().getTimezoneOffset()*60000;
				var today = new Date(nowDate-timeOff).toISOString().split("T")[0];
				$('input[name=plantLogDate]').attr("max", today);
				
				
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
		 달력 부분
		</aside>
		
	</main>
	
	<br clear="both">
	
	<footer id="pageFooter">
		<jsp:include page="../common/footer.jsp" />
	</footer>


</body>
</html>