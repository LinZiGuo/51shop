<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加分类</title>
</head>
<body>
<form name="Form1" action="${pageContext.request.contextPath}/adminCategory" method="post">
	<input type="hidden" name="method" value="save">
	&nbsp;
	<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
		<tr>
			<td align="center" bgColor="#afd1f3" colspan="4" height="26">
				<strong>添加分类</strong>
			</td>
		</tr>
		<tr>
			<td width="18%" align="center" bgColor="#f5fafa">
				分类名称：
			</td>
			<td bgColor="#ffffff" colspan="3">
				<input type="text" name="typename" id="categoryAction_save_typename">
			</td>			
		</tr>
		<tr>
			<td><input type="submit" value="添加"></td>
		</tr>
	</table>
</form>
</body>
</html>