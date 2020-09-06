<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有书籍列表</title>
<style type="text/css">
</style>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
});
function putBookInBorrow(userId,bookId,number){
	$.ajax("${pageContext.request.contextPath}/put_in_borrow",// 发送请求的URL字符串。
			{
			dataType : "json", // 预期服务器返回的数据类型。
   			type : "post", //  请求方式 POST或GET
		    //contentType:"application/json", //  发送信息至服务器时的内容编码类型
		   // 发送到服务器的数据。
		   //data:JSON.stringify({id : 1, name : "Spring MVC企业应用实战"}),
		   data:{"userId":userId,"bookId":bookId,"number":number},
		   async:  true , // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
		   // 请求成功后的回调函数。
		   success :function(data){
			  // alert(data.size);
			  $("#borrow_size").html(data.size);	
			  $("#bookQuantity").html(data.quantity);
                          if(data.error!="null")
  		 		  $("#errorInfo").html(data.error);
			 },
		   // 请求出错时调用的函数
		   error:function(){
			   alert("数据发送失败");
		   }
	});
}
function go()
{
      location.href="loginForm";
}
</script>
</head>
	<body>
		<b><font size="5" >${sessionScope.user.name}&nbsp;&nbsp;&nbsp;&nbsp;</font></b>
		<a href="history_orders"><b><font size="5" >借书历史清单</font></b></a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="display_borrow"><b><font size="5" >我的借书栏(<b id="borrow_size">${sessionScope.user.borrow.itemsSize}</b>)</font></b></a>
		<div style="width:600px;border-style:solid none;border-color:bule;border-width:10px;">

			<table border="1" bgcolor="#ADFEDC">
			<c:forEach items="${requestScope.all_books }" var="book">
				<tr>
						<td><img src="image/${book.imagePath}" width="100",height="80"></td>
						<td>${book.category}</td>
						<td>${book.name}</td>
						<td>${book.price}</td>
						<td><a href="javascript:void(0)" onclick="putBookInBorrow(${sessionScope.user.id},${book.id},1)">加入我的借书栏</a></td>
				</tr>
			</c:forEach>
			</table>
		</div>
		<input type="button" value="退出登录" onclick="go()">
		<font color="red" id="errorInfo"></font>
	</body>

</html>