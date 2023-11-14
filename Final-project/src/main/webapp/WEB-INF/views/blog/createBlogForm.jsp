<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블로그 생성 화면</title>
<link rel="stylesheet" href="resources/css/common/template.css">

</head>
<style>


* {
    border: 1px solid skyblue;
	box-sizing: border-box;
}
article{
	
	height: 550px;
	margin : 20px;
	border: 1.5px solid #c6c6c6;
	border-radius: 10px;
}
#contentTitle{
    font-size: 35px ;
    font-weight: bold;
    color: #00610C;
    padding: 20px;
}

#blogInfo{
    font-size: 20px ;
    padding: 20px;
}

#createFormWrap{
    padding: 20px;
}

#createForm{
    border: none;
    font-size: 20px;
    width: 100%;
    border-spacing: 5px;
    
}

#createForm th{
    width: 25%;
    text-align: left;
    padding: 8px;
    background-color: beige;
    color: rgb(83, 57, 32);;
}

#createForm td{
    width: 75%;
    text-align: left;
    padding: 5px;
    border: 1px solid #c6c6c6;
}
textarea{
    resize: none;
    width: 99%;
    height: 100px;
    outline-color: #afdba3;
    border: none;
}

#buttonWrap > div{
   margin-top: 30px;
   float: left;
   width: 50%;
   padding: 0px 100px;
}

input[type=text] {
            width: 99%;
            height: 35px;
            font-size: 23px;
            font-weight: bolder;
            border-radius: 10px;
            border: none;
            color: rgb(83, 57, 32);
            outline-color: #afdba3;
}
.button{
	width: 250px;
	height: 45px;
	cursor: pointer;
}
.forest{
    font-size: 20px;
    font-weight: bolder;
    border-radius: 10px;
    border:2px solid #afdba3;
	background-color: #afdba3;
    color: white;
	margin: 10px auto;
}
.beige{
    font-size: 20px;
    font-weight: bolder;
    border-radius: 10px;
    border:2px solid beige;
	background-color: beige;
    color: rgb(83, 57, 32);
	margin: 10px auto;
}
a{
    cursor: pointer;
	display: inline-block;
    font-size: 14px;
    line-height: 17px;
    text-decoration: none;
    color: #888;
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
			
			<div id="contentTitle">
				블로그에 오신것을 환영합니다!
			</div>
			
			<div id="content">
               <div id="blogInfo">
                   블로그 관심사와 관심 식물 카테고리를 설정하시면 관련 글을 추천해드립니다
               </div>
				<article id="pageArticle">

					<div id="createFormWrap">
                        <form>
                            <table id="createForm">
                            <tr>
                                    <th>블로그 이름</th>
                                    <td><input type="text" name="blogTitle" placeholder="${ sessionScope.loginUser.memNick }님의 블로그"></td>
                                </tr>
                                <tr>
                                    <th>블로그 소개글</th>
                                    <td><textarea name="blogIntr" id="" placeholder="${ sessionScope.loginUser.memNick }님의 블로그입니다."></textarea>
                                </tr>
                                <tr>
                                    <th>블로그 이미지</th>
                                    <td><input type="file" name="blogImg" value=""></td>
                                </tr>
                                <tr>
                                    <th>블로그 관심사</th>
                                    <td>
                                        <select name="blogInterest">
                                            <option value="">선택안함</option>
                                            <option value="">문학&middot;책</option>
                                            <option value="">영화</option>
                                            <option value="">미술&middot;디자인</option>
                                            <option value="">공연&middot;전시</option>
                                            <option value="">음악</option>
                                            <option value="">드라마</option>
                                            <option value="">스타&middot;연예인</option>
                                            <option value="">만화&middot;애니</option>
                                            <option value="">방송</option>
                                            <option value="">일상&middot;생각</option>
                                            <option value="">육아&middot;결혼</option>
                                            <option value="">반려동물</option>
                                            <option value="">패션&middot;미용</option>
                                            <option value="">인테리어&middot;DIY</option>
                                            <option value="">요리&middot;레시피</option>
                                            <option value="">상품리뷰</option>
                                            <option value="">게임</option>
                                            <option value="">스포츠</option>
                                            <option value="">사진</option>
                                            <option value="">자동차</option>
                                            <option value="">취미</option>
                                            <option value="">여행</option>
                                            <option value="">맛집</option>
                                            <option value="">IT&middot;컴퓨터</option>
                                            <option value="">건강</option>
                                            <option value="">경제</option>
                                            <option value="">외국어</option>
                                            <option value="">교육</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th>관심 식물</th>
                                    <td></td>
                                </tr>
                            </table>
                            <div id="buttonWrap">
                                <div><button type="submit" class="button forest">블로그 생성</button></div>
                                <div><button type="button" class="button beige">돌아가기</button></div>
                            </div>
                            </form>
					</div>

				</article>
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