<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
	                	<c:when test="${empty loginUser.blogNo}" >
	                	<div id="user" align="center">
								<c:choose>
									<c:when test="${ empty sessionScope.loginUser }" >
										<a href="javascript:alert('로그인 후 이용가능합니다.');" >블로그 생성</a>
									</c:when>
									<c:otherwise>
										<a href="insertForm.bl">블로그 생성</a>
									</c:otherwise>
								</c:choose>
							</div>
	                    
						</c:when>
						<c:otherwise>
							<div id="user" align="center">
	                        <div id="userProfile">
	                            <img src="resources/images/defaultProfile.png" alt="회원사진" id="user_photo" >
	                        </div>
	                        
	                        <div id="welcomeProfile">
			                    <div align="center">${ sessionScope.loginUser.memId }님 환영합니다.</div>
			                    		<div id="loginOk">
			                    			<div id="myBlogButton"><a href="select.bl?blogNo=${ loginUser.blogNo }">내 블로그</a></div>
			                    			<div id="writeButton"><a href="#">글 쓰 기</a></div>
										</div>
							</div>
						</div>
						</c:otherwise>
					</c:choose>
                </div>
                <div id="MyBlogInfo">
                    	블로그 소식이 없습니다.
                </div>    
                </div>

            </div>
            
            <script>
            	$(() => {
            		$.ajax({
            			url: 'select.me',
            			data: { memNo: ${ sessionScope.loginUser.memNo }},
            			success:  data => {
            				
            				console.log(data);
            			
            			},
            			error: () => {
            				console.log('블로그 사이드바 통신 실패');
            			}
            		})
            		
            	});
            	
            	
            </script>
            
            
            
            
            
            
            
            
            
            
</body>
</html>