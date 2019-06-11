<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="register" type="mybean.data.Register" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>showRegisterMessage</title>
</head>
<body>
<h1><jsp:getProperty property="backNews" name="register"/></h1>
<h3>三秒之后自动跳转，如未跳转请<a href="login.jsp">点击此处</a></h3>

</body>
</html>