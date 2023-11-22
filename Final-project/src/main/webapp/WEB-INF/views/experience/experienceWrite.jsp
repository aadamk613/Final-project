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

#asdf{
	height : 500px;
}

img{
	width : 800px;
	height : 400px;
	object-fit : contain;
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
			
			<input type="hidden" name="expWriter" value="${ loginUser.memId }" />
			
			<h1><input type="text" name="expTitle" placeholder="제목을 입력해 주세요."  /></h1>
			<hr>
			
			<div id="summary">
				<div>
					<input type="file" name="upfiles" id="thumbFile" />
					<img src="" id="thumb" class="thumbFile" />
					<input type="text" name="anno" placeholder=">" />
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
						$('img').on('click', e =>{
							// console.log("하이드");
							// console.log(e.target.id);
							const imgId = e.target.id;
							// console.log("이건 뭔데");
							// console.log($('img[id=' + imgId +']').siblings().eq(0));
							$('img[id=' + imgId +']').siblings().eq(0).click();
						});
						
						console.log($('input[type=file]'));
						$('input[type=file]').on('change', ()=>{
							console.log("??");
						});
						
						// input 파일요소가 바뀌면, 이미지가 보여짐
						$('input[type=file]').on('change', (inputFile) =>{
							console.log("바껴라");
							console.log(inputFile.target);
							console.log(inputFile.target.files);
							// 파일이 있다면
							if(inputFile.target.files.length == 1){
								let reader = new FileReader();
								reader.readAsDataURL(inputFile.target.files[0]);
								reader.onload = function(e){
									
									const inputFileId = inputFile.target.id;
									
									console.log($('input[id=' + inputFileId + ']').siblings());
									// input요소 바로 다음 img의 이미지가 바뀜
									$('input[id=' + inputFileId + ']').siblings().eq(0).attr('src', e.target.result);
									// console.log($('img[class=inputFileId]'));
									// $('img[class='+ inputFileId + ']').attr('src', e.target.result);
									// console.log($('#thumb'));
									// inputFile.target.siblings().eq(0).attr('src', e.target.result);
									
									// 그 이미지가 첨부되어야 주석 input요소가 열림
									// input.attr('display', 'inline-block');
									
									// 그리고 그 다음 div가 생성됨
									/*
									const value = '<div>'
												 +'<input type="file" name="upfiles" />'
												 +'<img src="" class="file-img" />'
												 +'<input type="text" name="anno" placeholder=">" />'
												 +'</div>';	
									$('#content-div').html(value);
									*/
								}
								
							}
							else{
								
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
					
					카테고리 : <select id="category" name="expCategoryNo">
								<option value="1">화훼농장</option>
								<option value="2">과일농장</option>
								<option value="3">채소농장</option>
								<option value="4">꽃꽂이</option>
							</select>
					체험학습일 : <input type="date" name="expWorkDate" />
					체험시간 : <input type="number" name="expWorkTime" min=1 max=10 />
					모집인원 : <input type="number" name="expPeople" min=1 max=100 />
					모집마감일 : <input type="date" name="expEndDate" />
					주소 : 
						<input type="text" name="expAddress" id="expAddress" placeholder="주소" readonly />
						<input type="button" value="주소 검색" onclick="searchAddress();"  /><br>
						<input type="hidden" name="expArea" id="expArea" />
						<input type="submit" value="등록하기">
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
			</script>
			
			<br clear="both">
			
			<textarea id="content" name="expContent" placeholder="내용을 입력해 주세요." rows="5"></textarea>
			
			<div id="content-div">
				<div>
					<input type="file" name="upfiles" id="inputfileId1" />
					<img src="" class="file-img" id="file-img1" />
					<input type="text" name="anno" placeholder=">" />
				</div>
				<div>
					<input type="file" name="upfiles" id="inputfileId2" />
					<img src="" class="file-img" id="file-img2" />
					<input type="text" name="anno" placeholder=">" />
				</div>
			
			</div>
			
			
			
			
			
			
			
			
		
		
		
		
		
			
			
			
			
			
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