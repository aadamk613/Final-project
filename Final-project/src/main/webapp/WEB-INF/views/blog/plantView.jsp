<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식물 상세보기</title>
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

#plantReport{width: 100%; height: auto; padding: 20px;}

#plantName{font-size: 20px; font-weight: bold; }

#plantName div{float: left; width:50%; height: 30px;}

#deleteWrap{margin: 0px;}

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
    width: 100%;
    height: 100%;
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
.visible{display: block !important;}
#plantCareModal{background-color: white; z-index: 2; position:absolute; width:500px; height: 500px; display:none;}
#plantCareWrap{ margin: 50px auto; padding: 20px; border-radius: 10px; border: 1px solid #888;}
#plantCareWrap div{width: 400px; height: 50px;}
#plantCareWrap div button{margin: 10px auto;}
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
			   	${ plant.plantName }
			</div>

			<div id="content">
				
				<!-- 관리 모달 창 -->
				<div id="plantCareModal">
				<div id="plantCareWrap">
					<form action="insert.bl_pr" method="post" enctype="multipart/form-data">
					<input type="hidden" name="topPlantNo" value="${ plant.plantNo }"/>
						<div>${ plant.plantNickName }관리하기</div>
						<div>
							<input type="date" name="reportDate" required>
						</div>
						<div>
							<select name="plantReportcategoryNo">
								<option value="20">물주기</option>
								<option value="30">물갈이하기</option>
								<option value="40">가지치기</option>
								<option value="50">영양관리</option>
								<option value="60">분갈이하기</option>
							</select>
						</div>
						<div>
							<button type="submit">기록하기</button>
						</div>
						</form>
						<div>
							<button id="backButton">돌아가기</button>
						</div>
				</div>
				</div>

				<article>
					<div id="plantInfoWrap">
						<div id="plantImg">
							<c:choose>
								<c:when test="${ empty plant.filePath }">
									<img width="100%" height="100%"
										src="resources/images/defaultPlant.png" class="files" />
								</c:when>
								<c:otherwise>
									<img width="100%" height="100%"
										src="${ plant.filePath }${ plant.updateName }" class="files" />
								</c:otherwise>
							</c:choose>
						</div>
						<div id="plantInfo">
							<ul>
								<li id="plantName">
									<div>식물 이름 : ${ plant.plantName }</div>
									<div id="deleteWrap">
										<button class="button beige" onclick="plantManage('update');">수정하기</button>
										<button class="button forest delete"
											onclick="plantManage('del');">-</button>
									</div> <clear="both">
								</li>
								<li id="plantNick">
									<div>별명 : ${ plant.plantNickName }</div>
									<div id="plantCreateDate">키우기 시작한 날짜: ${ plant.plantLogDate }</div>
								</li>
								<li id="plantButtonWrap">
									<form action="insertForm.bl_pr" method="post" id="postForm">
										<input type="hidden" id="plantNo" name="plantNo"
											value="${ plant.plantNo }"> <input type="hidden"
											name="plantNickName" value="${ plant.plantNickName }">

										<button type="submit" class="button forest" id="plantReportButton">일지 추가</button>
										<button id="plantCareButton" class="button forest">관리하기</button>
									</form>
								</li>

							</ul>
						</div>



												
						<c:forEach var="r" items="${ plant.plantReport }" >
						
							<div id="plantReport">
							<div>
								${ r.reportDate }
							</div>
							<div>
								${ r.categoryName }
							</div>
							<div>
								${ r.reportComment }
							</div>
							<c:if test="${ not empty r.filePath }" >
							<div>
								<img src="${ r.filePath }${ r.updateName }" class="files" />						
							</div>
							</c:if>
						</div>
						</c:forEach>


					</div>
					<br clear="both">
				</article>
			</div>
			<div><a href="javascript:window.history.back();"><button type="button" class="button beige" id="goBlogHome">돌아가기</button></a></div>

			<script>
			
			// 식물 관리 모달 창 토글
			$('#plantCareButton').on('click', (function(){
				$('#plantCareModal').toggle('visible');
				consloe.log($('#plantCareModal'));
				
			}));
			
			// 식물관리 모달 창 닫기
			$('#backButton').on('click', function hideModal(){
				$('#plantCareModal').hide();
			});
			
			
			
			
			
				// 식물 관리하기
	         	function plantCareModal(category){
	            		
	            		if(category == 10){ // 일지 추가 클릭 시
	            			console.log($(arguments[0]).parent().children().find('input[plantNo]').val());
	            			$(arguments[0]).parent().children().find('input[plantNo]').attr('value', ${ p.plantNo});
	            			$('#postForm').attr('action', 'insertForm.bl_pr').submit();
	            		
	            		}
	            		/*
	            		else{ // 관리하기 클릭 시
	            					
	            			//입력할 값이 많지 않아서 모달 창 뜨는걸로 변경 예정
	            			//console.log($(arguments[0]).parent().children().find('input[plantNo]').val());
	            			$(arguments[0]).parent().children().find('input[plantNo]').attr('value', ${ p.plantNo});
	            			
	            			//$('#postForm').attr('action', 'insertForm.bl_pr').submit();
	            		}
	            		*/
	            	}
	         	
	         		// 식물 수정하기(str == update), 삭제하기(str == del)
		         	function plantManage(str){
	         			
						if(str == "update"){
							location.href = "updateForm.bl_pl?plantNo=" + ${ plant.plantNo } + "&blogNo=" + ${ plant.blogNo };	
						}	
						else{
			      			// console.log(${ plant.plantNo });
		         			
		         			if(confirm("해당 식물의 모든 정보와 일지를 삭제합니다. 식물을 삭제하시겠습니까?")){

		         				location.href = "delete.bl_pl?plantNo=" + ${ plant.plantNo } + "&blogNo=" + ${ plant.blogNo };

		         			}
						}
	   
		         	}
	         	
			</script>


			<div id="page">
				<c:if test="${ pi.currentPage ne 1 }">
		        	<button class="btn btn-light" onclick="location.href='select.bl_pl?blogNo=${ blogNo }&currentPage=${ pi.currentPage - 1 }'">&lt;</button>
		        </c:if> 
		       
		        <c:forEach var="i" begin="${ pi.startPage }" end="${ pi.endPage }">
		       		<c:choose>
			       		<c:when test="${ pi.currentPage ne i }">
			          		<button class="btn btn-forest" onclick="location.href='select.bl_pl?blogNo=${ blogNo }&currentPage=${ i }'">${ i }</button>
			         	</c:when>
			         	<c:otherwise>
			         		<button disabled class="btn btn-default">${ i }</button>
			         	</c:otherwise>
		         	</c:choose>
		        </c:forEach>
		        
		        <c:if test="${ pi.currentPage ne pi.maxPage }">
		        	<button class="btn btn-light" onclick="location.href='select.bl_pl?blogNo=${ blogNo }&currentPage=${ pi.currentPage + 1 }'">&gt;</button>
		        </c:if>
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