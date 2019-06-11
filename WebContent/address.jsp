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
Address address=new Address();
if(request.getAttribute("Address")==null){
	response.sendRedirect("getAddress");
}
else{
	address=(Address)request.getAttribute("Address");
}
%>
<body>
	<div id="css">
		<div id="header"></div>
		<div style="width: 1500px;_height:1000px; min-height:1000px ">
			<div id="daohang"><div style="height: 100px"></div>
			<center><div id="photo"><img src=""></div>
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
			<form action="changeAddress">
				<center>
					<h1>修改地址</h1>
					<h1 style="height: 42px"> </h1>
				<div class="table">
					<p>收货人姓名：<%=address.getName() %></p>
					<p>收货人电话：<%=address.getPhone() %></p>
					<p>收货人邮箱：<%=address.getEmail() %></p>
					<p>收货人住址：<%=address.getHome() %></p>
				</div>
				<div class="table">
					
			姓名:<input type="text"  placeholder="收货人姓名" name="name">*</input>
            <br>
            电话:<input type="text"  placeholder="收货人电话" name="phone">*</input>
            <br>
            邮箱:<input type="text"  placeholder="收货人邮箱" name="email">*</input>  
            <br>
            地址:<input type="text"  placeholder="收货人地址" name="home">*</input>
           
				</div>
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