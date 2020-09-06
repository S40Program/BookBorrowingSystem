<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>

<title>欢迎来到图书管理界面</title>
<style type="text/css">
	.black_overlay{ display: none; position: absolute; top: 0%; left: 0%; width: 100%; height: 100%; background-color: blue; z-index:1001; -moz-opacity: 0.6; opacity:.60; filter: alpha(opacity=60); }
	.white_content { display: none; position: absolute; top: 25%; left: 25%; width: 50%; height: 50%; padding: 16px; border: 16px solid green; background-color: white; z-index:1002; overflow: auto; }
  	.add_new_book{background:url('image/addbook.png') no-repeat;border:none;width:64px;height:64px;}
	.bg{width:600px;height:338px;background-image:url("image/bookmanage.png");margin:0 auto;}
	body{color:black;}
	.lg{padding:100px;50px;50px;100px;}
</style>
<script>
   function display_modify_div(){
    //document.getElementById('modify_div').style.display='block';
    //document.getElementById('fade').style.display='block'
    $('#fade').show(1000);
    $('#modify_div').show(1000);
   }
   function display_new_div(){
	    //document.getElementById('new_div').style.display='block';
	    //document.getElementById('fade').style.display='block'
	    $('#new_div').fadeIn(1000);
	    $('#fade').fadeIn(1000);
	   }
   function hide_modify_div(){
    //document.getElementById('modify_div').style.display='none';
    //document.getElementById('fade').style.display='none'
	   $('#fade').slideUp(1000);
	    $('#modify_div').slideUp(1000);
   }
   function hide_new_div(){
	    //document.getElementById('new_div').style.display='none';
	    //document.getElementById('fade').style.display='none'
	   $('#new_div').fadeOut(1000);
	    $('#fade').fadeOut(1000);
	   }
   </script>
<script type="text/javascript">
$(document).ready(function(){
	//$('#modify_div').hide();
});
function requestModifyBook(bookId){
	$.ajax("${pageContext.request.contextPath}/get_book_json",// 发送请求的URL字符串。
			{
			dataType : "json", // 预期服务器返回的数据类型。
   			type : "get", //  请求方式 POST或GET
		    //contentType:"application/json", //  发送信息至服务器时的内容编码类型
		   // 发送到服务器的数据。
		   //data:JSON.stringify({id : 1, name : "Spring MVC企业应用实战"}),
		   data:{"id":bookId},
		   async:  true , // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
		   // 请求成功后的回调函数。
		   success :function(data){
			  // alert(data);
			  $("#id").val(data.id);
			  $("#name").val(data.name);
			  $("#category").val(data.category);
			  $("#quantity").val(data.quantity);
			  $("#price").val(data.price);
			  display_modify_div();
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
<div class="bg">
	<div class="lg">
		<h2 style="text-align:center">图书管理界面</h2>
		<input class="add_new_book" type="button" onclick="display_new_div()" />
		<table border="1" bgcolor="#ADFEDC">
	
		<c:forEach items="${requestScope.all_books }" var="book">
			<tr>
			<td><img src="${pageContext.request.contextPath}/image/${book.imagePath}" width="100" height="80"/></td>
			<td>${book.category}</td>
			<td>${book.name}</td>
			<td>${book.quantity}</td>
			<td>${book.price}</td>
			<td><a href="javascript:void(0)" onclick="requestModifyBook(${book.id})">修改</a></td>
			<td><a href="delete_book?id=${book.id }">删除</a></td>
			</tr>
			
		</c:forEach>
		</table>
		<input type="button" value="退出登录" onclick="go()">
		<div id="modify_div" class="white_content">
			<form action="do_modify_book" method="post">
				<table><caption><font size="4">修改书籍信息</font></caption>
					<tr>
					<td>id</td>
					<td><input type="edit" id="id" name="id" value="" readonly></td>
					<td>名称</td>
					<td><input type="edit" id="name" name="name" value=""></td></tr>
					<tr><td>分类</td>
					<td><input type="edit" id="category" name="category" value=""></td></tr>
					<tr><td>数量</td>
					<td><input type="edit" id="quantity" name="quantity" value=""></td></tr>
					<tr><td>价格</td>
					<td><input type="edit" id="price" name="price" value=""></td>
					</tr>
					<tr>
					<td ><input type="submit" value="确定" ></a></td>
					<td><input type="button" value="放弃" onclick="hide_modify_div()"></td>
					</tr>
				</table>	
			</form>
  		</div>

  		<div id="fade" class="black_overlay">

  		</div>
 		<div id="new_div" class="white_content">
			<form action="do_add_book" enctype="multipart/form-data" method="post">
				<table><caption><font size="4">新增书籍信息</font></caption>
				<tr>
				<td>名称</td>
				<td><input type="edit" id="name" name="name" value=""></td></tr>
				<tr><td>分类</td>
				<td><input type="edit" id="category" name="category" value=""></td></tr>
				<tr><td>数量</td>
				<td><input type="edit" id="quantity" name="quantity" value=""></td></tr>
				<tr><td>价格</td>
				<td><input type="edit" id="price" name="price" value=""></td>
				</tr>
				<tr>
				<td>选择图片:</td>
				<td><input type="file" name="image"></td>
				</tr>
				<tr>
				<td ><input type="submit" value="确定" ></a></td>
				<td><input type="button" value="放弃" onclick="hide_new_div()"></td>
				</tr>
				</table>	
			</form>
 		 </div>
 	</div>
</div>

</body>
</html>