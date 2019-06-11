<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>  

    <meta charset="UTF-8">  

    <title>用户注册</title>  
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/login.css"/>  

</head>  

<body>  
    	<div style="background: cyan;height: 1000px">
         <center>
        <div style="height: 200px"></div>
     
        <h1>用户注册</h1>  

        <form  action="register"  method="post">  

            <input type="text"  placeholder="用户名" name="uname">*</input>  
            <br>
            <input type="password"  placeholder="密码" name="upassword">*</input>
            <br>
            <input type="text"  placeholder="收货人姓名" name="name">*</input>
            <br>
            <input type="text"  placeholder="收货人电话" name="phone">*</input>
            <br>
            <input type="text"  placeholder="收货人邮箱" name="email">*</input>  
            <br>
            <input type="text"  placeholder="收货人地址" name="home">*</input>

            <button class="but" type="submit">确认注册</button>  

        </form>
        <div id="login"></div>
        <div id="login" style="width: 700px;">
                <div style="float: right;height:30px;width: 100px"><a href="admin_login.jsp">管理员入口</a></div>
                <div style="float: right;height:30px;width: 20px"><a href="#"></a></div>
                <div style="float: right;height:30px;width: 100px"><a href="login.jsp">用户登录</a></div>
        </div>  

    </center>    
        </div>


</body>
</html>