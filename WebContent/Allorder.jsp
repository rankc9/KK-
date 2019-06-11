<%@page import="mybean.data.AdminInfo"%>
<%@page import="mybean.data.Order"%>
<%@page import="java.util.Vector"%>
<%@page import="mybean.data.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/cart.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
String name="";
AdminInfo adminInfo=(AdminInfo)session.getAttribute("AdminInfo");
if(adminInfo==null){
	adminInfo = new AdminInfo();
	name="游客";
}
else{
	name=adminInfo.getAname();
}
%>
<% 
Vector Allorder=new Vector();
if(request.getAttribute("Allorder")==null){
	response.sendRedirect("showAllorder");
}
else{
	Allorder=(Vector)request.getAttribute("Allorder");
}
Order order =new Order();
%>
<body>
	<div id="css">
		<div id="header"></div>
		<div style="width: 1500px;_height:1000px; min-height:1000px ">
			<div id="daohang"><div style="height: 100px"></div>
			<center><div id="photo"><img src="img/a.png"></div>
				<div style="height: 100px">
					<h2>【  <%=name %>  】</h2><br>
				</div>
				<a href="admin.jsp">书店首页</a><br>
			    
			    <a href="ALLorder.jsp">所有订单</a><br>
			    
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
			<div id="order">
				<div id="orderItemName">
					<div style="width: 200px ;text-align:center;line-height:30px;border-right:1px solid #F00;float: left;">订单号</div>
					<div style="width: 200px ;text-align:center;line-height:30px;border-right:1px solid #F00;float: left;">订单时间</div>
					<div style="width: 300px ;text-align:center;line-height:30px;border-right:1px solid #F00;float: left;">订单信息</div>
					<div style="width: 200px ;text-align:center;line-height:30px;border-right:1px solid #F00;float: left;">订单价格</div>
					<div style="width: 300px ;text-align:center;line-height:30px;float: left;">收货信息</div>
				</div>
				
				<%
				int size=Allorder.size();
				for(int i=0;i<size;i++){
					order= (Order)Allorder.elementAt(i);
				%>
				<div class="ordertable">
					<div class="orderNumber">
						<%=order.getOid() %>
					</div>
					<div class="orderDate">
						<%=order.getOdate() %>
					</div>
					<div class="orderMessage">
						<p>
							<%=order.getOmessage() %>
						</p>
					</div>
					<div class="orderPrice">
						<%=order.getOtotal() %>
					</div>
					<div class="orderHome">
							 <p>
							 	<%=order.getOname() %><br>
							 	<%=order.getOphone() %><br>
							 	<%=order.getOaddress() %><br>
							 </p>
					</div>
					
				</div>
				<%} %>
					
		    </div>
		</div>	
		<div id="footer">
				
			</div>
		</div>
	</div>
	
</body>
</html>