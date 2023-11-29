<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식물 등록</title>
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
			   	식물 등록
			</div>
			
			<div id="content">
				<article>
					<form method="post" action="insert.bl_pl" enctype="multipart/form-data">  
					<input type="hidden" name="blogNo" value="${ blogNo }"/>
                    <div id="plantInfoWrap">
                        <div id="plantImg"><input type="file" name="upfile" id="plantInput"/>
                        	<img src="resources/images/defaultPlant.png" id="plantImgInput" name="plantThumbnail">
                        </div>
                        <div id="plantImfo">
                            <ul>
                                <li id="plantName">식물명 : <input type="text" name="plantName"/></li>
                                <li id="plantNickName">
	                                <div>애칭 : <input type="text" name="plantNickName" />
	                                </div>키우기 시작한 일자: <div id="plantLogDate"><input type="date" name="plantLogDate"></div>
                                </li>
                                <li id="plantComment" >
                                	<textarea placeholder="식물에 대한 코멘트를 작성해주세요" name="plantComment"></textarea>
                                </li>
                            </ul>
                        </div>
                        <div>
                        	<button type="submit" id="plantCare" class="button forest">등록하기</button>
                        	<a href="javascript:window.history.back();"><button id="plantCare" class="button forest">돌아가기</button></a>
                        </div>
                    </div>
                    
                    <br clear="both">
					</form>
				</article>
			</div>

			<script>
			
				// 현재 날짜를 알아낸 후 식물 등록 일자에서 현재 날짜 이후는 입력 불가
				var nowDate = Date.now();
				var timeOff = new Date().getTimezoneOffset()*60000;
				var today = new Date(nowDate-timeOff).toISOString().split("T")[0];
				console.log(today);
				
				console.log($('input[name=plantLogDate]'));
				$('input[name=plantLogDate]').attr("max", today);
				
				
				// input type="file"을 숨긴 후 기본이미지 클릭 시 이벤트 수행되게
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