<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta ttp-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function addCategory(){
		window.location.href = "${pageContext.request.contextPath}/";
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<br>
	<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
		<tbody>
			<tr>
				<td align="center" bgColor="#afd1f3">
					<strong>分类列表</strong>
				</td>
			</tr>
			<tr>
				<td align="right">
					<button type="button" id="add" name="add" value="添加">添加</button>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafa">
					<table id=DataGrid1>
						<tr>
							<td>序号</td>
							<td>分类名称</td>
							<td>编辑</td>
							<td>删除</td>
						</tr>
						<c:forEach var="c" items="${list }" varStatus="vs">
							<tr>
								<td>${vs.count }</td>
								<td>${c.typename }</td>
								<td>
									<a href="${pageContext.request.contextPath}/adminCategory">
									编辑</a>
								</td>
								<td>
									<a href="${pageContext.request.contextPath}/adminCategory">
									删除</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>