<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户注册界面</title>
<style type="text/css">
.bg{
	width:960px;
	height:540px;
	background-image:url("image/login.jpg");
	margin:0 auto;
	
}
body{
	color:black;
}
.lg{
	padding:50px;20px;0px;0px;
	
}
</style>
</head>
<body>
<div class="bg">
	<div class="lg">
	<h2>图书借阅系统注册界面</h2>
	<form action="register" method="post">	
	<!-- 提示信息 -->
		<font color="red">${requestScope.error }</font> 
		<table>
			<tr>
				<td><label>用户：</label></td>
		  		<td><input type="text" name="loginname"  placeholder="用户名" ></td>
		  	</tr>
		  	<tr>
				<td><label>密码：</label></td>
				<td><input type="password" name="password"  placeholder="用户密码" ></td>
			</tr>
			<tr>
				<td><input type="radio" id="user" name="user" value="student">学生</td>
         		<td><input type="radio" id="user" name="user" value="admin">管理员</td>
         	</tr>
         	<tr>
        		<td><input type="submit" value="注册"/></td>
        	</tr>
		</table>
	</form>
	</div>
</div>

</body>
</html>