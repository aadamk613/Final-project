<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>사업자등록정보 진위확인</title>
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
	
  	<div class="container">
    <h1>사업자등록정보 진위확인</h1>
    
    <label for="businessNumber">사업자 등록 번호:</label>
    <input type="text" id="businessNumber" placeholder="사업자 등록 번호 입력">
    <button onclick="check();">확인</button>
    
    <br><br><br>
    
    <table border="1" align="center">
      <thead>
        <tr>
          <th>사업자등록번호(필수)</th> 
          <th>대표자성명(필수)</th> 
          <th>개업일자(필수)</th> 
          <th>상호</th> 
          <th>법인등록번호</th> 
          <th>주업태명</th> 
          <th>주종목명</th> 
          <th>사업장주소</th>
       	  <th>사업장주소</th>
        </tr>
      </thead>
      
      <tbody id="businessInfo">
      <!̆̈— 데이터가 여기에 보여질 내용 —>
    </tbody>
    </table>
    
    <div id="result"></div>
  </div>

  <script>
    function check() {
      var businessNumber = document.getElementById("businessNumber").value;
   		console.log(businessNumber);
      // 입력한 사업자번호로 조회
      var data = {
        "b_no": businessNumber
      };
      
      $.ajax({
        url: "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=XSyDrKZA66etAyknXmiWPgDRU%2BSa7u6IkO2Oc%2B3%2Bcwmnwfwdsujh1OvosKadicupI74e88WjfDF4Q0DSh%2B3%2Fxw%3D%3D&returnType=JSON",  // serviceKey 값을 xxxxxx에 입력
        type: "POST",
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json", // json 을 string으로 변환하여 전송
        //accept: "application/json",
        success: function(responseData) {
          var tableContent = '';

          if (responseData.data[0] && responseData.data[0].length > 0) {
            var business = responseData.data[0];

            tableContent += '<tr>';
            tableContent += '<td>' + data[0].b_no + '</td>';
            tableContent += '<td>' + data[0].p_nm + '</td>';
            tableContent += '<td>' + data[0].start_dt + '</td>';
            tableContent += '<td>' + data[0].b_nm + '</td>';
            tableContent += '<td>' + data[0].corp_no + '</td>';
            tableContent += '<td>' + data[0].b_sector + '</td>';
            tableContent += '<td>' + data[0].b_type + '</td>';
            tableContent += '<td>' + data[0].b_adr + '</td>';
            tableContent += '</tr>';
          } else {
            tableContent += '<tr><td colspan="8">정보가 없습니다</td></tr>';
          }

          $('#businessInfo').html(tableContent);
        },
        error: function(xhr, status, error) {
        	console.log(result.responseText); //responseText의 에러메세지 확인
        }
      });
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