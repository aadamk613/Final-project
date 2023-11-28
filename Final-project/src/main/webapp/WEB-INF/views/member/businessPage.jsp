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
    <button id="checkButton">확인</button>
    <div id="result"></div>
  </div>
  </form>

  <!-- 
  <script src="script.js"></script>


<script>
	document.addEventListener('DOMContentLoaded', function() {
	  const checkButton = document.getElementById('checkButton');
	  const businessNumberInput = document.getElementById('businessNumber');
	  const resultDiv = document.getElementById('result');

	  checkButton.addEventListener('click', function() {
	    const businessNumber = businessNumberInput.value;
	    
	    // API 호출 함수 (실제 API 호출 코드를 여기에 작성)
	    // 예시: checkBusinessRegistration 함수는 API 호출 및 결과를 화면에 표시하는 역할을 가정
	    checkBusinessRegistration(businessNumber);
	  });

	  function checkBusinessRegistration(businessNumber) {
	    // 여기에서 실제 API 호출을 수행하고, 결과를 처리하는 로직을 작성
	    // 예시: 가짜 결과를 받아와 화면에 표시하는 코드
	    const fakeResult = Math.random() < 0.5 ? '진위 확인됨' : '진위 미확인';
	    displayResult(fakeResult);
	  }

	  function displayResult(result) {
	    resultDiv.innerText = `사업자등록정보: ${result}`;
	  }
	});
</script>
-->
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