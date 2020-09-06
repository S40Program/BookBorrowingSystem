<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>借阅界面</title>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
});
function check_borrow(){
	var len=$("input[name='borrow_book_id']:checked").length;
	if(len>0)
		alert("借阅成功");
	else{
		alert("未选择要借阅的书籍");
		return false;
	}
}
function go()
{
      location.href="book_list";
}
</script>
</head>
<body>
<div style="width:600px;border-style:solid none;border-color:bule;border-width:10px;">
<form action="borrow_check" onsubmit="return check_borrow()" method="post">
<table cellspacing="2" border="1" bgcolor="#ADFEDC">
		<tr>
			<td align="center">样式</td>
        	<td align="center">类型</td>
        	<td align="center">名称</td>        
        	<td align="center">数量</td>
        	<td align="center">选择</td>
        </tr>
	<c:forEach items="${requestScope.borrow.borrowItems}" var="borrowItem">
		<tr>
			<td><img src="image/${borrowItem.book.imagePath}" width="100",height="80"></td>
			<td>${borrowItem.book.category}</td>
			<td>${borrowItem.book.name}</td>
			<td>${borrowItem.quantity}</td>
			<td><input type="checkbox" name="borrow_book_id" value="${borrowItem.book.id}"></td>
		</tr>
	</c:forEach>
</table>
<input type="submit" value="借阅">
<input type="button" value="取消" onclick="go()">
</form>
</div>
<font color="red" id="errorInfo"></font>
</body>
</html>