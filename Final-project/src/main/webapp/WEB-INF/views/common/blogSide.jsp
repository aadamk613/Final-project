<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
        #mp_navi {
            width : 225px;
            height: 700px;
            border: 2px rgb(230, 230, 230);
            border-style: solid none;
        }
        
        #userProfile{width: 100%; height: 100%;}
        
        #welcomeProfile{width: 100%; height: 100%;}
        
        #user_photo{
            width:100px;
            height:100px;
            margin: 20px;
        }
        
        #loginPlz, #loginOk{text-align: center; width: 100%; height: 50px;}
        
        #isLogin{
            background-color: white;
        }
        
        #MyBlogInfo{
        	width: 100%; height: 100px;
        }
        
        
        .naviText{color: black;}
        
        #loginOk div{float: left;}
        #myBlogButton, #writeButton{width: 50%; height:100%;}
        
</style>

<link rel="stylesheet" href="resources/css/common/template.css">

</head>
<body>
            <div id="mp_navi">
            
                <div id="isLogin">
                	<c:choose>
                	<c:when test="${ not empty sessionScope.loginUser }" >
                    <div id="user" align="center">
                        <div id="userProfile">
                            <img src="resources/images/defaultProfile.png" alt="회원사진" id="user_photo" >
                        </div>
                        
                        <div id="welcomeProfile">
		                    <div align="center">${ sessionScope.loginUser.memId }님 환영합니다.</div>
		                    		<div id="loginOk">
		                    			<div id="myBlogButton"><a href="#">내 블로그</a></div>
		                    			<div id="writeButton"><a href="#">글 쓰 기</a></div>
									</div>
						</div>
					</div>
					</c:when>
					<c:otherwise>
						<div id="user" align="center">
							<a href="insertForm.bl">블로그 생성</a>
						</div>
					</c:otherwise>
					</c:choose>
                </div>
                <div id="MyBlogInfo">
                    	블로그 소식이 없습니다.
                </div>    
                </div>

            </div>
</body>
</html>