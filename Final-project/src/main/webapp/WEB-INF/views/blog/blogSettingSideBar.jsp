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