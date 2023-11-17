<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
<link rel="stylesheet" href="resources/css/common/template.css">
</style>
</head>
<body>

            <div id="mp_navi">
                <div id="empty">
                    <table id="user" align="center">
                        <tr>
                            <img src="" alt="회원사진" id="user_photo" >하하호호
                        </tr>
                        <c:choose>
                    	<c:when test="${ not empty sessionScope.loginUser }">
		                <tr>
		                    <div align="center">${ sessionScope.loginUser.memId }님 환영합니다.</div>
		                </tr>
		            	</c:when>
		            	<c:otherwise>
	                    <tr>
	                        <div id="loginPlz">로그인을 해주세요.</div>
	                    </tr>
						</c:otherwise>
						</c:choose>
                    </table>
                </div>
                <ul id="navigator">
                	<c:choose>
	                	<c:when test="${ not empty sessionScope.loginUser }">
<<<<<<< HEAD
	                	    <c:if test="${loginUser.memStatus eq 'A' }">
	                    	<li><a href="enrollForm.no" class="btn btn-primary">공지게시글작성</a></li>
	                    	</c:if>
=======
	                	  	<c:if test="${ loginUser.memStatus eq 'A' }">
	                    	<li><a href="enrollForm.no" class="btn btn-primary">공지게시글작성</a></li>
	                	  	</c:if>
>>>>>>> 890b38490e1cbaf0950931332e634c0be1f724cb
	                    	<li><a href="#" class="btn btn-primary">일반게시글작성</a></li>
	                    	<li><a href="#" class="btn btn-primary">쪽지함</a></li>
	                    	<li><a href="#" class="btn btn-light loginMemUser">내가 쓴 글 확인</a>
	                    </c:when>
	                    <c:otherwise>
	                    	<li><a href='javascript:void(0);' onclick="alert('로그인 후 이용 가능한 기능입니다.');" class="btn btn-primary">공지게시글작성</a></li>
<<<<<<< HEAD
	                    	<li><a href='javascript:void(0);' onclick="alert('로그인 후 이용 가능한 기능입니다.');" class="btn btn-primary">게시글작성</a></li>
=======
	                    	<li><a href='javascript:void(0);' onclick="alert('로그인 후 이용 가능한 기능입니다.');" class="btn btn-primary">일반게시글작성</a></li>
>>>>>>> 890b38490e1cbaf0950931332e634c0be1f724cb
	                    	<li><a href='javascript:void(0);' onclick="alert('로그인 후 이용 가능한 기능입니다.');" class="btn btn-primary">쪽지함</a></li>
	                    	<li><a href='javascript:void(0);' onclick="alert('로그인 후 이용 가능한 기능입니다.');" class="btn btn-light">내가 쓴 글 확인</a></li>
	                    </c:otherwise>
                    </c:choose>
                        	<li>
                                <a href="list.no" class="naviText">공지사항</a>
                            </li>
                            <li>
                                <a href="#" class="naviText">자유게시판</a>
                            </li>
                </ul>


            </div>
            
</body>
</html>