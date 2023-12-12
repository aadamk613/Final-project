<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식물 전체 리스트</title>
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

#plantName div{display: inline; padding: 10px;}

#deleteWrap{float: right; padding: 0 0 0 200px !important; margin: 0px;}

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

.delete{
	width: 35px;
 	border-radius: 30px;
    border:2px solid #FF9090;
	background-color: #FF9090;
    color: white;

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

img[name=plantImg]:hover{cursor: pointer; }

textarea{
    resize: none;
    width: 99%;
    height: 100px;
    outline-color: #afdba3;
    border: none;
}

#page{
	width : 100%;
	height : 100px;
	padding: 20px;
	text-align: center;
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
	                        	<img width="100%" height="100%" src="resources/images/defaultPlant.png" class="files" name="plantImg" value="${ p.plantNo }"/>
								</c:when>
								<c:otherwise>
								<img width="100%" height="100%" src="${ p.filePath }${ p.updateName }" class="files" name="plantImg" value="${ p.plantNo }"/>
								</c:otherwise>
							</c:choose>
						</div>
                        <div id="plantInfo">
                            <ul>
                                <li id="plantName">
                                	<div>식물 이름 : ${ p.plantName }</div>
                                	<div id="deleteWrap"><button class="button forest delete" onclick="deletePlant(this);">-</button></div>
                                </li>
                                <li id="plantNick">
	                                <div>별명 : ${ p.plantNickName }</div>
	                                <div id="plantCreateDate">D+${ p.plantLogDate }</div>
                                </li>
                                <li id="plantButtonWrap">
                                <form action="" method="post" id="postForm">
                                	<input type="hidden" id="plantNo" name="plantNo" value="${ p.plantNo }">
                                	<input type="hidden" name="plantNickName" value="${ p.plantNickName }">
									<input type="hidden" id="category" name="category" value="">
									
                                	<a id="plantReport" class="button forest" onclick="plantCare(this);">일지 추가</a>
                                    <a id="plantCare" class="button forest" onclick="plantCare(this);">관리하기</a>
                                </form>
                                </li>
                            </ul>
                        </div>
                        
                    </div>
                    <br clear="both">
				</article>
				</c:forEach>
			</div>
			<div><a href="javascript:window.history.back();"><button type="button" class="button beige" id="goBlogHome">돌아가기</button></a></div>
			
			<script>
				// 식물 관리하기
	         	function plantCare(category){
	            		//console.log($(arguments[0]).parent().children().find($('#plantNo')).val());
	            		console.log($(arguments[0]).parent().children().find('input[plantNo]').val());
	            		
	            		if(category == 10){ // 일지 추가 클릭 시
	            			console.log($(arguments[0]).parent().children().find('input[plantNo]').val());
	            			$(arguments[0]).parent().children().find('input[plantNo]').attr('value', ${ p.plantNo})
	            			$('#postForm').attr('action', 'blog/insertForm.pr').submit();
	            		
	            		}
	            		else{ // 관리하기 클릭 시
	            			console.log($(arguments[0]).parent().children().find('input[plantNo]').val());
	            			$(arguments[0]).parent().children().find('input[plantNo]').attr('value', ${ p.plantNo})
	            			
	            			$('#postForm').attr('action', 'blog/insertForm.pr').submit();
	            		}
	            	}
	         	
	         		// 식물 삭제하기
		         	function deletePlant(plantNo){
		         		
	         			if(confirm("해당 식물의 모든 정보와 일지를 삭제합니다. 식물을 삭제하시겠습니까?")){

	    	         		$.ajax({
								url: 'blog/delete.pl',
								data: {plantNo: plantNo,
									   currentPage: ${ pi.currentPage }},
								success: data => {
									console.log(data);
									
								},
								error: () => {
									console.log('식물 삭제 통신 실패');
								}
			         		});
	         			}
		         	}
	         		
	         		
		        	
		       	 $('img[name=plantImg]').on('click', function(e){
		       		console.log($(e.target).attr('value'));
		       		var plantNo = $(e.target).attr('value');
		       		location.href = "blog/select.pl?plantNo=" + plantNo ; 
		       		 
		       	 });
		       	 
	         	
			</script>
						
			
			<div id="page">
				<c:if test="${ pi.currentPage ne 1 }">
		        	<button class="btn btn-light" onclick="location.href='blog/selectList.pl?blogNo=${ blogNo }&currentPage=${ pi.currentPage - 1 }'">&lt;</button>
		        </c:if> 
		       
		        <c:forEach var="i" begin="${ pi.startPage }" end="${ pi.endPage }">
		       		<c:choose>
			       		<c:when test="${ pi.currentPage ne i }">
			          		<button class="btn btn-forest" onclick="location.href='blog/selectList.pl?blogNo=${ blogNo }&currentPage=${ i }'">${ i }</button>
			         	</c:when>
			         	<c:otherwise>
			         		<button disabled class="btn btn-default">${ i }</button>
			         	</c:otherwise>
		         	</c:choose>
		        </c:forEach>
		        
		        <c:if test="${ pi.currentPage ne pi.maxPage }">
		        	<button class="btn btn-light" onclick="location.href='blog/selectList.pl?blogNo=${ blogNo }&currentPage=${ pi.currentPage + 1 }'">&gt;</button>
		        </c:if>
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