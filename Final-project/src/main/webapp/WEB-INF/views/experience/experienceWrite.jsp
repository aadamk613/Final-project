<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>체험학습 게시글 작성</title>


<style>
* {
    border: 1px solid skyblue;
	box-sizing: border-box;
}

#summary{
	width : 100%;
}

#summary > div{
	float : left;
	padding : 40px;
	height : 100%;
	width : 50%;
	box-sizing : border-size;
}

#thumb{
	width : 400px;
	height : 400px;
}

input, select{
	display : block;
	width : 80%;
}

#content{
	width : 100%;
	resize : none;
	display : inline-block;
}

img{
	width : 100%;
	height : 400px;
	object-fit : contain;
}

.deleteFile{
	width : 20%;
	display : inline-block;
}

input[name=anno] {
	width : 100%;
	height : 50px;
}


</style>
</head>
<body>

	<header id="pageHeader">
		<jsp:include page="../common/header.jsp" />
	</header> 
	<main>
		<aside id="pageAsideLeft" class="aside">
             &lt;aside1&gt; <br>
             id=pageAsideLeft <br>
		            여기는 pageAsideLeft 공백공간 <br>
		            사이드바 넣을 수도 있음 <br>
		            필요하면 쓰세요 <br>
		            중앙정렬되어있어요 <br>
		</aside>
		
		<section id="pageSection">
			<form enctype="multipart/form-data" action="yrinsertExp.exp" method="post">
			
			<c:if test="${ not empty exp }" >
				<input type="hidden" name="expNo" value="${ exp.expNo }" />
			</c:if>
			<input type="hidden" name="expWriter" value="${ loginUser.memId }" />
			
			<h1><input type="text" name="expTitle" placeholder="제목을 입력해 주세요." value="${ exp.expTitle }" autofocus /></h1>
			<hr>
			
			<div id="summary">
				<div>
					<input type="file" name="upfiles" id="thumbFile" />
					<img src="${ files[0].filePath }/${ files[0].updateName }" id="thumb" class="thumbFile" required />
					<input type="hidden" name="anno" value="thumb" placeholder="사진첨부 후 작성해 주세요"  />
					<input type="hidden" name="" class="old" value="${ files[0].filePath }${ files[0].updateName }" />
					<!-- 기존의 파일을 지우거나 삭제하지 않기 위해 파일번호를 가져감 -->
					<script>
						console.log('${ files }');
						console.log('${ files[0] }');
						console.log('${ files[0].fileNo }');
					</script>
				</div>
				
				<script>
					// 대표 이미지를 클릭하면 파일 업로드 input
					/*
					$(() => {
						$('#thumbFile').hide();
						$('#thumb').click(() => {
							$('#thumbFile').click();
						});
					});
					*/
					
					$(() => {
						$('input[type=file]').hide();
						//$('img').on('click', function abc(e){
						$(document).on('click', 'img', function clickImg(e) {
							const imgId = e.target.id;
							// console.log("이건 뭔데");
							// console.log($('img[id=' + imgId +']').siblings().eq(0));
							//console.log("왜 안나와");
							//console.log($(this)); // => 화살표함수.............쓰면 window로 잡힘
							// $('img[id=' + imgId +']').siblings().eq(0).click();
							$(this).siblings().eq(0).click();
							// console.log("이얍");
							//console.log($('img').last());
							// $('img').last().siblings().eq(0).click();
							
							// 클릭된 파일은 무조건 사라짐
							if('${ exp.expNo }' != ''){
								$(this).siblings('input.old').attr('name', 'oldFiles');
							}
							
							$(this).attr('src', '');
							$(this).siblings().eq(1).val(' ');
							$(this).siblings().eq(1).attr('readonly', true);
							
							console.log("이게 anno일 텐데");
							console.log($(this).siblings().eq(1));
							
							
							
							console.log("이거 클릭하면 이름속성 바꿔줄거임");
							console.log($(this).nextAll('input.old'));
							
							
							
							
							
							
							
						});
						
						//console.log($('input[type=file]'));
						//$('input[type=file]').on('change', ()=>{
						//	console.log("??");
						//});
						
						// input 파일요소가 바뀌면, 이미지가 보여짐
						//$('input[type=file]').on('change', function asd(inputFile){
						$(document).on('change', 'input[type=file]', function changeFiles(inputFile){
							const changeFile = $(this);
							console.log("그치");
							console.log(changeFile);
							
							// 기존파일이 있다면 기존파일의 oldFileNo를 아예 지워버림(왜 여기서 하냐면, 파일을 누르는 순간 기존 파일은 변경되거나 취소를 누르면 삭제됨)
							console.log("지울 파일 요소...");
							console.log(changeFile.siblings().eq(2).attr('name'));
							//changeFile.siblings().eq(2).attr('name', '');
							console.log(changeFile.siblings().eq(2).attr('name'));
							
							//changeFile.siblings().eq(2)
							
							
							// 파일이 있다면
							if(inputFile.target.files.length == 1){
								
								// 수정된 파일을 oldFiles로 넘겨줄 것임
								changeFile.siblings().eq(2).attr('name', 'oldFiles');
								
								
								//changeFile.siblings().eq(1).attr('name', 'newAnno');
								//console.log(changeFile.siblings().eq(1).attr('name'));
								console.log("왜 안넘어가");
								console.log(changeFile.siblings().eq(1).attr('name'));
								console.log(changeFile.siblings().eq(1).val());
								
								let reader = new FileReader();
								reader.readAsDataURL(inputFile.target.files[0]);
								reader.onload = function(e){
									//const inputFileId = inputFile.target.id;
									console.log(reader);
									// console.log($('input[id=' + inputFileId + ']').siblings());
									//console.log($('input[type=file]').last());
									//$('input[id=' + inputFileId + ']').siblings().eq(0).attr('src', e.target.result);
									
									
									// input요소 바로 다음 img의 이미지가 바뀜
									changeFile.siblings().eq(0).attr('src', e.target.result);
									
									console.log("아니 자식요소였어??????");
									console.log(changeFile.siblings().eq(0));
									console.log(changeFile.children().find('img[class=file-img]'));
									//console.log("사람살려");
									//console.log(changeFile.siblings().eq(0).find($('img[class=file-img]')));
									//console.log(changeFile.siblings().eq(1).find($('input[name=anno]')).attr('disabled', false));
									
									
									// 파일이 바뀌어야 주석을 쓸 수 있음
									//changeFile.siblings().eq(1).attr('disabled', false);
									changeFile.siblings().eq(1).attr('readonly', false);
									
									
									// $('input[type=file]').last().siblings().eq(0).attr('src', e.target.result);
									// console.log($('img[class=inputFileId]'));
									// $('img[class='+ inputFileId + ']').attr('src', e.target.result);
									// console.log($('#thumb'));
									// inputFile.target.siblings().eq(0).attr('src', e.target.result);
									
									// 그 이미지가 첨부되어야 주석 input요소가 열림 => 안해도됨 => 해야됨 했음
									// input.attr('display', 'inline-block');
									
									
									
								}
							}
							// 파일 첨부하기 취소를 누르면
							else{
								
								console.log("취소를 누르면");
								console.log(changeFile);
								console.log(changeFile.siblings().eq(0));
								console.log(changeFile.siblings().eq(1));
							
								changeFile.siblings().eq(0).attr('src', '');
								changeFile.siblings().eq(1).val('');
								changeFile.siblings().eq(1).attr('readonly', true);
								
							}
							
							
							
							
							
							
							
						});
						
						
						
						
						
						
					});
					
					
					/*
					// onchange="loadImg(this, 1)" 쓰고 하는법 (수업시간에 한거)
					function loadImg(inputFile, num){
						console.log("이건 됨");
						console.log(inputFile);
						
						console.log(inputFile.files);
						if(inputFile.files.length == 1) {// 파일이 첨부
							let reader = new FileReader();
							reader.readAsDataURL(inputFile.files[0]);
							console.log(inputFile.files[0]);
							reader.onload = function(e){
								console.log(e);
								$('#thumb').attr('src', e.target.result);
							}
						}
						
					}
					*/
					
					// 파일추가를 누르면 input요소와 img가 생성됨
					
				
				</script>
				
				<div>
					<h5>※필수 입력 사항입니다. </h5>
					
					카테고리 : <select id="category" name="expCategoryNo" required>
								<option class="categoryOption" value="1">화훼농장</option>
								<option class="categoryOption" value="2">과일농장</option>
								<option class="categoryOption" value="3">채소농장</option>
								<option class="categoryOption" value="4">꽃꽂이</option>
							</select>
					체험학습일 : <input type="date" name="expWorkDate" value="${ exp.expWorkDate }" required />
					체험시간 : <input type="number" name="expWorkTime" min=1 max=10 value="${ exp.expWorkTime }" required />
					모집인원 : <input type="number" name="expPeople" min=1 max=100 value="${ exp.expPeople }" required />
					모집마감일 : <input type="date" name="expEndDate" value="${ exp.expEndDate }" required />
					가격 : <input type="number" name="expPrice" min=0 max=100000000 step=500 value="${ exp.expPrice }" required />
					주소 : 
						<input type="text" name="expAddress" id="expAddress" placeholder="주소" value="${ exp.expAddress }" required readonly />
						<input type="button" value="주소 검색" onclick="searchAddress();"  /><br>
						<input type="hidden" name="expArea" id="expArea" value="${ exp.expArea }" />
						
						<script>
							// 선택되어있던 값을 selected해줌
							$(()=> {
								$('#category').val('${ exp.expCategoryNo }').attr('seleted', true);
							});
						</script>
						
						
						
						
				</div>
			</div>
			<!-- 주소찾기 API -->
			<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
			<script>
			    function searchAddress() {
			    	
			        new daum.Postcode({
			            oncomplete: function(data) {
			                var addr = data.address; // 최종 주소 변수
			                // 주소 정보를 해당 필드에 넣는다.
			                document.getElementById("expAddress").value = addr;
			                document.getElementById("expArea").value = data.sido + ' ' + data.sigungu;
			            }
			        }).open();
			    }
			</script>
			
			<script>
				console.log(new Date());
				// toISOString() => YYYY-MM-DDTHH:mm:ss.sssZ
				let mindate = new Date().toISOString().split('T')[0];
				$(() => {
					$('input[type=date]').prop('min', mindate);
				});
				
				const value = '<div>'
							 +'<input type="file" name="upfiles" />'
							 +'<img src="" class="file-img" />'
							 +'<textarea name="anno" placeholder="사진첨부 후 작성해 주세요." rows="5" readonly ></textarea>'
							 +'<input type="hidden" name="" value="" />'
							 +'<button onclick="deleteFile($(this));">삭제하기</button>'
							 +'</div>';	
				
				// 버튼 클릭하면 div 생성
				$(() => {
					$('#addContent').click(() => {
						console.log("클릭했음");
						/* 
						const value = '<div>'
							 +'<input type="file" name="upfiles" />'
							 +'<img src="" class="file-img" />'
							 +'<input type="text" name="anno" placeholder=">" />'
							 +'</div>';	 
						*/
							 
						$('#content-div').append(value);
						$('input[type=file]').hide();
						// $('#content-div').children().last().addEventListener('click', );
					});
				});
				
				
				if('${ exp.expNo }' != ''){
					$('form').attr('action', 'yrupdateExp.exp');
				}
				else{
					$('form').attr('action', 'yrinsertExp.exp');
				}
				
			</script>
			
			<br clear="both">
			
			<textarea id="content" name="expContent" placeholder="내용을 입력해 주세요." rows="5" >${ exp.expContent }</textarea>
			
			<div id="content-div">
				<!-- 파일과 주석값이 원래 있었다면 보여주기 -->
				<c:if test="${ not empty requestScope.files }">
					<c:forEach var="f" items="${ requestScope.files }" begin="1">
						<div>
							<input type="file" name="upfiles" />
							<img src="${ f.filePath }${ f.updateName }" class="file-img" />
							<textarea name="anno" placeholder="사진첨부 후 작성해 주세요." rows="5" readonly  >${ f.fileAnnotation }</textarea>
							<input type="hidden" name="" class="old" value="${ f.filePath }${ f.updateName }" />
							<button class="deleteFile" onclick="deleteFile();">삭제하기</button>
						</div>
					</c:forEach>
				</c:if>
			</div>
			
			<script>
				// 업로드되어 있는 파일 삭제하기
				function deleteFile(e){
					
					console.log("삭제삭제삭제");
					console.log(e);
				
					/* changeFile.siblings().eq(0).attr('src', '');
					changeFile.siblings().eq(1).val('');
					changeFile.siblings().eq(1).attr('disabled', true); */
				}
			
			</script>
			
			
			
			
			<button type="button" id="addContent">추가하기</button>
			
			
			<input type="submit" class="btn btn-warning" value="등록하기">
			
			</form>

		
		</section>
		
		<aside id="pageAsideRight" class="aside">
           &lt;aside&gt; <br>
           id=pageAsideRight<br>
	               여기는 pageAsideRight 공백공간 <br>
	               사이드바 넣을 수도 있음 <br>
	               필요하면 쓰세요 <br>
	               중앙정렬되어있어요 <br>
		</aside>
		
	</main>
	
	<br clear="both">
	
	<footer id="pageFooter">
        &lt;footer&gt; <br>
        id=pageFooter
	        여기는 푸터 <br>
	        여기는 푸터 <br>
	        여기는 푸터 <br>
	        여기는 푸터 <br>
	        여기는 푸터 <br>
	        여기는 푸터 <br>
	        쓰는만큼 늘어나요<br>
	    <jsp:include page="../common/footer.jsp" />
	</footer>


</body>
</html>