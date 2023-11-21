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
}

#thumb{
	width : 400px;
	height : 400px;
}

input, select{
	display : block;
}

#content{
	width : 100%;
	resize : none;
	display : inline-block;
}

#asdf{
	height : 500px;
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
			
			<h1><input type="text" placeholder="제목을 입력해 주세요." /></h1>
			<hr>
			
			<div id="summary">
				<div>
					<img src="" id="thumb" />
				</div>
				<div>
					<h5>※필수 입력 사항입니다. </h5>
					
					카테고리 : <select id="category">
						<option value="1">화훼농장</option>
						<option value="2">과일농장</option>
						<option value="3">채소농장</option>
						<option value="4">꽃꽂이</option>
					</select>
					체험학습일 : <input type="date" />
					체험시간 : <input type="number" min=1 max=10 />
					모집인원 : <input type="number" min=1 max=100 />
					모집마감일 : <input type="date" />
					주소 : <input type="text" id="sample5_address" placeholder="주소">
						<input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색"><br>
						<div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>
						<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

					
				</div>
			</div>
			<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=발급받은 API KEY를 사용하세요&libraries=services"></script>
			<script>
			    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
			        mapOption = {
			            center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
			            level: 5 // 지도의 확대 레벨
			        };
			
			    //지도를 미리 생성
			    var map = new daum.maps.Map(mapContainer, mapOption);
			    //주소-좌표 변환 객체를 생성
			    var geocoder = new daum.maps.services.Geocoder();
			    //마커를 미리 생성
			    var marker = new daum.maps.Marker({
			        position: new daum.maps.LatLng(37.537187, 127.005476),
			        map: map
			    });
			
			
			    function sample5_execDaumPostcode() {
			        new daum.Postcode({
			            oncomplete: function(data) {
			                var addr = data.address; // 최종 주소 변수
			
			                // 주소 정보를 해당 필드에 넣는다.
			                document.getElementById("sample5_address").value = addr;
			                // 주소로 상세 정보를 검색
			                geocoder.addressSearch(data.address, function(results, status) {
			                    // 정상적으로 검색이 완료됐으면
			                    if (status === daum.maps.services.Status.OK) {
			
			                        var result = results[0]; //첫번째 결과의 값을 활용
			
			                        // 해당 주소에 대한 좌표를 받아서
			                        var coords = new daum.maps.LatLng(result.y, result.x);
			                        // 지도를 보여준다.
			                        mapContainer.style.display = "block";
			                        map.relayout();
			                        // 지도 중심을 변경한다.
			                        map.setCenter(coords);
			                        // 마커를 결과값으로 받은 위치로 옮긴다.
			                        marker.setPosition(coords)
			                    }
			                });
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
			
			<textarea id="content" placeholder="내용을 입력해 주세요." rows="5"></textarea>
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		
		
		
		
		
			
			
			
			
			
			
			
			
			
			
			
			
			

		
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