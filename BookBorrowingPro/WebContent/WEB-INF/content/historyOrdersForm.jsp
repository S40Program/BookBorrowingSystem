<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
});
function displayBookBorrowInfo(bookorderId){
	var v= $('#bookorder_'+bookorderId);
	var a=$('#href_'+bookorderId);
	v.slideToggle('slow',function(){
		if(v.is(':hidden')){
			a.html(">");
		}else{
			a.html("<");
		}
	});
}
function check_return(){
	var len=$("input[name='return_book_id']:checked").length;
	if(len>0)
		alert("归还成功");
	else{
		alert("未借阅任何书籍");
		return false;
	}
}
function go()
{
      location.href="book_list";
}
</script>
<style type="text/css"> 
div.bookorderInfo
{
margin:0px;
padding:5px;
text-align:center;
background:#e5eecc;
border:solid 1px #c3c3c3;
}
div.bookorderInfo
{
display:none;
}
a{ text-decoration:none} 
</style>
</head>
<body>
<h3><b>借书历史详单</b></h3>
<div style="width:600px;border-style:solid none;border-color:bule;border-width:10px;">
<form action="return_check" onsubmit="return check_return()" method="post">
<table border="1" bgcolor="#ADFEDC">	
	<c:forEach items="${sessionScope.user.orderHistory.bookorders }" var="bookorder" varStatus="bookorderStatus">
		<tr>
			<td>${bookorderStatus.count } </td>
			<td>${bookorder.formatDate}</td>		
			<td><a id="href_${bookorderStatus.count }" href="javascript:void(0)" onclick="displayBookBorrowInfo(${bookorderStatus.count})">></a></td>
			<td align="center">选择</td>
				<td><div class="bookorderInfo" id="bookorder_${bookorderStatus.count}">
				<table border="1">	
				<c:forEach items="${bookorder.borrowItems }" var="borrowitem" varStatus="borrowitemStatus">
					<tr>
					<td>${borrowitemStatus.count } </td>
					<td>${borrowitem.book.name}</td>
					<td>${borrowitem.quantity }</td>
					<td><input type="checkbox" name="return_book_id" value="${borrowItem.book.id}"></td>	
					</tr>
				</c:forEach>
				</table></div></td>
		</tr>
	</c:forEach>
</table>

<input type="submit" value="归还">
<input type="button" value="返回" onclick="go()">
</form>
</div>
<font color="red" id="errorInfo"></font>
</body>
</html>