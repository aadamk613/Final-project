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
    <h1>사업자등록정보 상태조회</h1>
    <br>
    
    		<label for="businessNumber">사업자 등록 번호:</label>
    		<input type="text" id="businessNumber" placeholder="사업자 등록 번호 입력" required>
    		<br>
    		
    		<button onclick="checkNo();">확인</button>
    		<button type="button" onclick="location.href='main' ">메인화면</button>


    		
    		
    <table border="1" align="center">
      <thead>
        <tr>
          <th>사업자등록번호(필수)</th> 
          <th>납세자상태(명칭)</th> 
          <th>납세자상태(코드)</th> 
          <th>과세유형메세지(명칭)</th> 
          <th>과세유형메세지(코드)</th> 
          <th>법인등록번호</th> 
          <th>폐업일</th> 
          <th>단위과세전환폐업여부</th> 
          <th>최근과세유형전환일자</th>
          <th>세금계산서적용일자</th>
          <th>직전과세유형메세지(명칭)</th>
          <th>직전과세유형메세지(코드)</th>
        </tr>
      </thead>
    <tbody id="businessInfo">
      	<!-- 데이터 보일 문제 -->
    </tbody>
    </table>
   </div>
    <br><br><br>
    
    <div id="result"></div>

  <script>
    function checkNo() {
    	var businessNumber = document.getElementById("businessNumber").value;
        
       
        console.log(businessNumber);
        // 입력한 사업자번호로 조회
        var data = {
        		"b_no": [businessNumber],
        };

        $.ajax({
            url: "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=XSyDrKZA66etAyknXmiWPgDRU%2BSa7u6IkO2Oc%2B3%2Bcwmnwfwdsujh1OvosKadicupI74e88WjfDF4Q0DSh%2B3%2Fxw%3D%3D",
            type: "POST",
            data: JSON.stringify(data), // json 을 string으로 변환하여 전송
            dataType: "JSON",
            contentType: "application/json",
            accept: "application/json",
            success: function(result) {
            	console.log(result);
				/*
            	console.log(result.data[0]['b_stt_cd']); //사업자 01 번 호출
            	
                let valid = result.data[0]['b_stt_cd'];
            	
                
                if (valid=='01'){
                    msg1();
                }else {
                    msg2();
                }

				function msg1(){
				    let msg = document.getElementById('regimessage');
				    msg.innerHTML = "<br>계속사업자 입니다.";
				}
				
				function msg2(){
				    let msg = document.getElementById('regimessage');
				    msg.innerHTML = "<br>현재 게속사업자 상태가 아닙니다.";
				
				}
				*/
                var businesses = result.data;
                let tableContent = "";
				
                for (let i in businesses) {
                    const busi = businesses[i];
                    console.log(busi);

                    tableContent += '<tr>' +
                        '<th>' + busi.b_no + '</th>' +
                        '<th>' + busi.b_stt + '</th>' +
                        '<th>' + busi.b_stt_cd + '</th>' +
                        '<th>' + busi.tax_type + '</th>' +
                        '<th>' + busi.tax_type_cd + '</th>' +
                        '<th>' + busi.end_dt + '</th>' +
                        '<th>' + busi.utcc_yn + '</th>' +
                        '<th>' + busi.invoice_apply_dt	 + '</th>' +
                        '<th>' + busi.rbf_tax_type + '</th>' +
                        '<th>' + busi.rbf_tax_type_cd + '</th>' +
                        '</tr>';
                }
                $('#businessInfo').html(tableContent);
            },
            error: function(result) {
                console.log(result.responseText); //responseText의 에러메세지 확인
            }
            
            });
        };
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