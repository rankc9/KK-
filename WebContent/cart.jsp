<%@page import="mybean.data.Cartlist"%>
<%@page import="java.util.Vector"%>
<%@page import="mybean.data.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/cart.css">
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

Vector Allcart=new Vector();
if(request.getAttribute("Allcart")==null){
	response.sendRedirect("showCart");
}
else{
	Allcart=(Vector)request.getAttribute("Allcart");
}
Cartlist cartlist=new Cartlist();
String Cbookname,Cprice,Cnumber,Cname,Cbookid;
%>
<body>
	<div id="css">
		<div id="header"></div>
		<div style="width: 1500px;_height:1000px; min-height:1000px ">
			<div id="daohang"><div style="height: 100px"></div>
			<center><div id="photo"><img src="img/a.png"></div>
				<div style="height: 100px">
					<h2>【    <%=name %>  】</h2><br>
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
			<div id="cart">
				<div id="cartItem">
					<div style="width: 200px ;text-align:center;line-height:30px;border-right:1px solid #F00;float: left;">书籍</div>
					<div style="width: 400px ;text-align:center;line-height:30px;border-right:1px solid #F00;float: left;">书籍名称</div>
					<div style="width: 200px ;text-align:center;line-height:30px;border-right:1px solid #F00;float: left;">数量</div>
					<div style="width: 200px ;text-align:center;line-height:30px;border-right:1px solid #F00;float: left;">价格</div>
					<div style="width: 240px ;text-align:center;line-height:30px;float: left;">操作</div>
				</div>
				<%
				int size=Allcart.size();
				for(int i=0;i<size;i++){
					cartlist= (Cartlist)Allcart.elementAt(i);
				%>
					<div class="dd">
					<div class="bookimg">
						<img src="img/a.png" alt="">
					</div>
					<div class="bookname">
						<%=cartlist.getCbookname() %>
					</div>
					<div class="booknum">
						<%=cartlist.getCnumber() %>
					</div>
					<div class="bookprice">
						<%=cartlist.getCprice() %>
					</div>
					<form action="deleteCart">
						<div class="bookset">
						    <input type="hidden" value="<%=cartlist.getId() %>" name='cartID'>
							<button id="but" type="submit" >移除购物车</button>
					    </div>
					</form>	
				</div>
				<%}
				%>			
		    </div>
		    <div id="jiesuan">
		    	<form action="addOrder">
					<button id="but2" type="submit" style="float: right;">去结算</button>
				</form>
		    </div>
		</div>	
		<div id="footer">
				
			</div>
		</div>
	</div>
	
</body>
</html>