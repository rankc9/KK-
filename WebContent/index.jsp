<%@page import="mybean.data.UserInfo"%>
<%@page import="mybean.data.BookInfo"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="css/style.css">
	<meta charset="UTF-8">
	<title>书店主页</title>
</head>
<%
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=UTF-8");
	Vector<BookInfo> Booklist=new Vector();
	if(session.getAttribute("booklist")==null){
		response.sendRedirect("showBook");
	}
	else{
		Booklist=(Vector)session.getAttribute("booklist");
	}
	BookInfo bookInfo;
	String Bname="",Bpic="",Bprice="",Bid="";
%>
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
<body>
	<div id="css">
		<div id="header"></div>
		<div style="width: 1500px;height: 1000px">
			<div id="daohang"><div style="height: 100px"></div>
			<center><div id="photo"><img src="img/a.png"></div>
				<div style="height: 100px">
					<h2>【  <%=name %> 】</h2><br>
					
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
			<div id="login">
				<div style="float: right;height:30px;width: 100px"><a href="admin_login.jsp">管理员入口</a></div>
				<div style="float: right;height:30px;width: 20px"><a href="#"></a></div>
				<div style="float: right;height:30px;width: 100px"><a href="login.jsp">用户登陆</a></div>
				<div style="float: right;height:30px;width: 20px"><a href="#"></a></div>
				<div style="float: right;height:30px;width: 100px"><a href="register.jsp">用户注册</a></div>
			</div>
			<div id="login"></div>
			<%
				for(int i=0;i<Booklist.size();i++){
					bookInfo=Booklist.elementAt(i);
					Bname=bookInfo.getBname();
					Bpic=bookInfo.getBpic();
					Bprice=bookInfo.getBprice();
					Bid=bookInfo.getBid();
					%>
			<form action="addcart" method="post">
			<div id="bookinfo">
				<center>
					<div id="bookimg"><img src="img/<%=Bpic%>" alt=""></div>
					<input type="hidden" value="<%=Bname%>" name='bookname'>
					<input type="hidden" value="<%=Bprice %>" name='bookprice'>
					<input type="hidden" value="<%=Bid %>" name='bookid'>
					<button class="but" type="submit">加入购物车</button>
				</center>	
			</div>
			</form>
					<%}%>
			
		

		</div>
		</div>
		<div id="footer"></div>
	</div>
	
</body>
</html>