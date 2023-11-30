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
  	body {
  font-family: Arial, sans-serif;
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



<script>
  function checkBusinessNum() {
    // 입력된 사업자 등록 번호 가져오기
    var businessNum = document.getElementById("businessNumber").value;

    // AJAX를 이용해 서버로 사업자 등록 번호 전송
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
      if (xhr.readyState === XMLHttpRequest.DONE) {
        if (xhr.status === 200) {
          // 서버로부터 온 응답을 받아 결과를 화면에 출력
          document.getElementById("result").innerHTML = xhr.responseText;
        } else {
          // 오류 처리
          document.getElementById("result").innerHTML = "오류 발생: " + xhr.status;
        }
      }
    };

    // 서버로 전송할 URL 설정 (실제 서버 URL에 맞게 수정 필요)
    var url = "business_verification.php"; // 예시 URL

    // POST 방식으로 데이터 전송
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send("businessNumber=" + businessNum);
  }
</script>


</section>
	<aside id="pageAsideRight" class="aside">
       
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
	</footer>

</body>
</html>