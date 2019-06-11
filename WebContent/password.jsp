<%@page import="mybean.data.Address"%>
<%@page import="java.util.Vector"%>
<%@page import="mybean.data.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/address.css">
	<meta charset="UTF-8">
	<title>我的购物车</title>
</head>
<%
String name="";
UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
if(userInfo==null){
	userInfo = new UserInfo();
	name="游客";
}
else{
	name=userInfo.getUname();
}
%>
<%
String passwordMessage="";
passwordMessage=(String)request.getAttribute("passwordMessage");
if(passwordMessage==null){
	passwordMessage="修改密码";
}
%>
<body>
	<div id="css">
		<div id="header"></div>
		<div style="width: 1500px;_height:1000px; min-height:1000px ">
			<div id="daohang"><div style="height: 100px"></div>
			<center><div id="photo"><img src="img/a.png"></div>
				<div style="height: 100px">
					<h2>【 <%=name %>  】</h2><br>
				</div>
				<a href="index.jsp">书店首页</a><br>
			    <a href="cart.jsp">我的购物车</a><br>
			    <a href="order.jsp">我的订单</a><br>
			    <a href="address.jsp">收货地址</a><br>
			    <a href="password.jsp">修改密码</a><br>
			    <a href="exit.jsp">退出登录</a><br>
			</center>
		</div>
		<div id="context">
			<!-- <div style="height: 50px;background: cyan"></div> -->
			<div id="login">

				<div style="float: right;height:30px;width: 100px"><a href="admin_login.jsp">管理员入口</a></div>
				<div style="float: right;height:30px;width: 20px"><a href="#"></a></div>
				<div style="float: right;height:30px;width: 100px"><a href="login.jsp">用户登陆</a></div>
				<div style="float: right;height:30px;width: 20px"><a href="#"></a></div>
				<div style="float: right;height:30px;width: 100px"><a href="register.jsp">用户注册</a></div>
			</div>
			<div id="login"></div>
			<div id="address">
			<form action="changePassword">
				<center>
				<h1><%=passwordMessage %></h1>
				输入修改密码:<input type="password"  placeholder="修改密码" name="password">*</input><br>
				 再次输入密码:<input type="password"  placeholder="确认修改密码" name="passwordAgain">*</input><br>
				 <button class="but" type="submit">确认修改</button> 
				</center>
				</form>
			</div>

		</div>	
		<div id="footer">
				
			</div>
		</div>
	</div>
	
</body>
</html>