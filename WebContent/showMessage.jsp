<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="userInfo" type="mybean.data.UserInfo" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录信息</title>
</head>
<body><center>
<h1><jsp:getProperty property="backNews" name="userInfo"/></h1>
<h3>三秒之后自动跳转，如未跳转请<a href="index.jsp">点击此处</a></h3>
</center>

</body>
</html>