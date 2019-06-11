<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>  

    <meta charset="UTF-8">  

    <title>用户登陆</title>  
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/login.css"/>  

</head>  
<%
String loginmessage="";
loginmessage=(String)request.getAttribute("loginmessage");
if(loginmessage==null){
	loginmessage="用户登录";
}
%>
<body>  
	<div style="background: cyan;height: 1000px">
    	<center>
    	<div style="height: 200px"></div>
     
        <h1><%=loginmessage %></h1>  
        <form  action="hdlogin" method="post">  

            <input type="text"  placeholder="用户名" name="uname"></input>  
            <br>
            <input type="password"  placeholder="密码" name="upassword"></input>  

            <button class="but" type="submit">登录</button>
          

        </form> 
        <div id="login"></div>
        <div id="login" style="width: 700px;">
                <div style="float: right;height:30px;width: 100px"><a href="admin_login.jsp">管理员入口</a></div>
                <div style="float: right;height:30px;width: 20px"><a href="#"></a></div>
                <div style="float: right;height:30px;width: 100px"><a href="register.jsp">用户注册</a></div>
        </div> 

    </center> 
    </div>

</body>  
</html>