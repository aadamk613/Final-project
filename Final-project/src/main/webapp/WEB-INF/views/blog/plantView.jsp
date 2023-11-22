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

#blogTitle{
	font-size: 25px; 
	font-weight: bold; 
	padding: 20px;
	color: #00610C;
	}

article{padding: 10px;}
#plantInfoWrap > div{float: left;}
#plantInfoWrap{width: 100%; height: auto;}

#plantImg{width: 20%; height: 160px; float: middle;}

#plantInfo{width: 80%; height: 160px;}
#plantName{font-size: 20px; font-weight: bold;}
#plantNick{font-size: 17px; font-weight: bold; color: #448300;}
#plantNick > div{display: inline; padding: 10px;}

#plantCreateDate{font-size: 15px; color: #888;}

#plantComment{width: 100%; height: 160px; padding: 10px;}

#plantInfo ul{
	list-style: none;
    padding: 0px;
    margin: 0px;
}
#plantInfo li{
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
			   	${ sessionScope.loginUser.memNick }의 정원
			</div>
			
			<div id="content">
                
                <c:forEach var="p" items="${ list }">
				<article>
                    <div id="plantInfoWrap">
                        <div id="plantImg">
                        	<c:choose>
                        	<c:when test="${ empty p.filePath }" >
                        	<img width="100%" height="100%" src="resources/images/defaultPlant.png" class="files" />
							</c:when>
							<c:otherwise>
							<img width="100%" height="100%" src="${ p.filePath }${ p.updateName }" class="files" />
							</c:otherwise>
							</c:choose>
						</div>
                        <div id="plantInfo">
                            <ul>
                                <li id="plantName">식물 이름 : ${ p.plantName }</li>
                                <li id="plantNick">
	                                <div>별명 : ${ p.plantNickName }</div>
	                                <div id="plantCreateDate">D+${ p.plantLogDate }</div>
                                </li>
                                <li id="plantButtonWrap">
                                <form action="" method="post" id="postForm">
                                	
                                	<input type="hidden" id="plantNo" name="plantNo" value="${ p.plantNo }">
                                	<input type="hidden" name="plantNickName" value="${ p.plantNickName }">
									<input type="hidden" id="category" name="category" value="">
									
                                	<a id="plantReport" class="button forest" onclick="plantCare(10);">일지 추가</a>
                                    <a id="plantCare" class="button forest" onclick="plantCare(20);">관리하기</a>
                                    
                                </form>
                                <!-- 
                                    <button id="plantReport" class="button forest" onclick="plantCare(${ p.plantNo }, 10, ${ p.plantLogDate });">일지 추가</button>
                                    <button id="plantCare" class="button forest" onclick="plantCare(${ p.plantNo }, 20, ${ p.plantLogDate });">관리하기</button>
                                 -->
                                </li>
                            </ul>
                        </div>
                    </div>
                    <br clear="both">
				</article>
				</c:forEach>
			</div>
			
			<script>
	         	function plantCare(category){
	            		//console.log($(arguments[0]).parent().children().find($('#plantNo')).val());
	            		console.log($(arguments[0]).parent().children());
	            		
	            		if(category == 10){ // 일지 추가 클릭 시
	            			$(arguments[0].children().children(1).attr('value',10));
	            			//$('#postForm').children().find('#category').attr('value',10);
	            			//$('#postForm').children().find('input[name=category]').attr('value',10);
	            			$('#postForm').attr('action', 'insertForm.bl_pr').submit();
	            		
	            		}
	            		else{ // 관리하기 클릭 시
	            			$(arguments[0].children().children(1).attr('value',20));
	            			//$('#postForm').children().find('#category').attr('value',20);
	            			//$('#postForm').children().find('input[name=category]').attr('value',20);
	            			$('#postForm').attr('action', 'insertForm.bl_pr').submit();
	            		}
	            	}
			      
			    /*
			    function plantCare(plantNo, category, plantNickName){
						console.log(category);
						console.log(plantNo);
						console.log(plantNickName);
						
			    	 location.href= 'insertForm.bl.pr/' +  plantNo + '/' + category + '/' + plantNickName;
			    };
			     */
			    
			    
			      
			      
			      
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