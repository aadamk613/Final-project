<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>사업자등록정보 진위확인</title>
  <!-- CSS 파일 링크 -->
  <link rel="stylesheet" href="styles.css">
  <style>
  
	  * {
	    border: 1px solid skyblue;
		box-sizing: border-box;
		cursor: url(https://cur.cursors-4u.net/holidays/hol-5/hol441.ani), url(https://cur.cursors-4u.net/holidays/hol-5/hol441.gif), auto !important;
	}

		.container {
		  width: 80%;
		  margin: 0 auto;
		  text-align: center;
		  padding: 50px 0;
		}
		
		input[type="text"] {
		  padding: 8px;
		  margin: 10px;
		}
		
		button {
		  padding: 10px 20px;
		  background-color: #3498db;
		  color: #fff;
		  border: none;
		  cursor: pointer;
		}
		
		button:hover {
		  background-color: #2980b9;
		}
		
		#result {
		  margin-top: 20px;
		  font-weight: bold;
		}
		  	
  </style>
</head>
<body>

	<header id="pageHeader">
        &lt;header&gt;
        id=pageHeader
        여기는 헤더
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
			
			<div id="contentTitle">
                &lt;div&gt;
                id=contentTitle 
				제목(삭제해도 됨)
			</div>
		</section>
	<aside id="pageAsideLeft" class="aside">
	</aside>
		
	<section id="section">
	
    <form action="businessPage.me" method="post" id="business-regist">
  <div class="container">
    <h1>사업자등록정보 진위확인</h1>
    
    <label for="businessNumber">사업자 등록 번호:</label>
    
    <input type="text" id="businessNumber" placeholder="사업자 등록 번호 입력">
    <button id="checkButton" onclick="checkBusinessNum();">확인</button>
    <div id="result"></div>
  </div>
  </form>

<br><br><br><br><br><br><br><br>

<script>
     function checkBusinessNum() {
    	 var data = {
    			    "b_no": ["xxxxxxx"] // 사업자번호 "xxxxxxx" 로 조회 시,
    			   }; 
    			   
    			$.ajax({
    			  url: "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=XSyDrKZA66etAyknXmiWPgDRU%2BSa7u6IkO2Oc%2B3%2Bcwmnwfwdsujh1OvosKadicupI74e88WjfDF4Q0DSh%2B3%2Fxw%3D%3D",  // serviceKey 값을 xxxxxx에 입력
    			  type: "POST",
    			  data: JSON.stringify(data), // json 을 string으로 변환하여 전송
    			  dataType: "JSON",
    			  contentType: "application/json",
    			  accept: "application/json",
    			  success: function(result) {
    			      console.log(result);
    			  },
    			  error: function(result) {
    			      console.log(result.responseText); //responseText의 에러메세지 확인
    			  }
    			});

         // 서버로 전송할 URL 설정 (실제 서버 URL에 맞게 수정 필요)
         var url = "http://api.odcloud.kr/api/nts-businessman/v1/validate";
         url += "?servicekey=XSyDrKZA66etAyknXmiWPgDRU%2BSa7u6IkO2Oc%2B3%2Bcwmnwfwdsujh1OvosKadicupI74e88WjfDF4Q0DSh%2B3%2Fxw%3D%3D"; // 서비스 키 입력
         url += "&resultType=json";
         url += "&b_no=" + businessNum;

         // GET 방식으로 데이터 전송
         xhr.open("GET", url, true);
         xhr.send();
     }
</script>


</section>
	<aside id="pageAsideRight" class="aside">
       
	</aside>
	
	</main>
	<br>
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
	</footer>

</body>
</html>