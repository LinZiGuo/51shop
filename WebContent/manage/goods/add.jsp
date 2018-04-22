<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加商品</title>
</head>
<body>
<form name="Form1" action="${pageContext.request.contextPath}/adminGoods" method="post">
	<input type="hidden" name="method" value="save">
	&nbsp;
	<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
		<tr>
			<td align="center" bgColor="#afd1f3" colspan="4" height="26">
				<strong>添加商品</strong>
			</td>
		</tr>
		<tr>
			<td width="18%" align="center" bgColor="#f5fafa">
				商品名称：
			</td>
			<td bgColor="#ffffff" colspan="3">
				<input type="text" name="goodsname">
			</td>
			<td width="18%" align="center" bgColor="#f5fafa">
				是否热门：
			</td>
			<td bgColor="#ffffff" colspan="3">
				<select name="hit">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</td>
		</tr>
		<tr>
			<td width="18%" align="center" bgColor="#f5fafa">
				市场价格：
			</td>
			<td bgColor="#ffffff" colspan="3">
				<input type="text" name="price">
			</td>
			<td width="18%" align="center" bgColor="#f5fafa">
				商城价格：
			</td>
			<td bgColor="#ffffff" colspan="3">
				<input type="text" name="nowprice">
			</td>
		</tr>
		<tr>
			<td width="18%" align="center" bgColor="#f5fafa">
				商品图片：
			</td>
			<td bgColor="#ffffff" colspan="3">
				<input type="file" name="picture">
			</td>
			<td width="18%" align="center" bgColor="#f5fafa">
				所属分类：
			</td>
			<td bgColor="#ffffff" colspan="3">
				<select name="typename">
					<c:forEach items="${list }" var="c">
						<option value="${c.ID }">${c.typename }</option>					
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td width="18%" align="center" bgColor="#f5fafa">
				商品描述：
			</td>
			<td bgColor="#ffffff" colspan="3">
				<textarea name="introduce" rows="5" cols="30"></textarea>
			</td>
		</tr>
		<tr>
			<td><input type="submit" value="添加"></td>
		</tr>
	</table>
</form>
</body>
</html>