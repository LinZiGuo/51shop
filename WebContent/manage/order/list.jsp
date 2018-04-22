<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta ttp-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript">
	function showDetail(oid){
		//发送ajax请求
		$.post("${pageContext.request.contextPath}/adminOrder",{"method":"showDetail","orderID":oid},function(d){
			var s="<table border='1' width='99%'>";
			s+="<tr><th>商品名称</th><th>购买数量</th></tr>";		
			$(d).each(function(){
				s+="<tr><td>"+this.goods.goodsname+"</td><td>"+this.count+"</td></tr>";
				s+="<tr><td colspan='2'>总计："+this.subtotal+"</tr>";
			});
			s+="</table>";
			
			layer.open({
				type:1,//0:信息框； 1：页面； 2：iframe层 3：加载层； 4：tip层
				title:"订单号："+oid,//标题
				area:['500px','300px'],//大小
				shadeClose:true,//点击弹窗外区域 遮罩关闭
				content:s//内容
			});
		},"json");
	}

</script>
<title>订单列表</title>
</head>
<body>
	<br>
	<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
		<tbody>
			<tr>
				<td align="center" bgColor="#afd1f3">
					<strong>订单列表</strong>
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
							<td>订单编号</td>
							<td>订单状态</td>
							<td>收货人</td>
							<td>订单详情</td>
							<td>编辑</td>
							<td>删除</td>
						</tr>
						<c:forEach var="c" items="${list }" varStatus="vs">
							<tr>
								<td>${vs.count }</td>
								<td>${c.orderID }</td>
								<td>
									<c:if test="${c.state==0 }">未付款</c:if>
									<c:if test="${c.state==1 }">
										<a href="${pageContext.request.contextPath}/adminOrder?method=updateState&orderID=${c.orderID}">已付款</a></c:if>
									<c:if test="${c.state==2 }">已发货</c:if>
									<c:if test="${c.state==3 }">已完成</c:if>
								</td>
								<td>${c.receiveName }</td>
								<td>
									<input type="button" value="订单详情" onclick="showDetail('${c.orderID}')">
								</td>
								<td>
									<a href="${pageContext.request.contextPath}/adminOrder">
									编辑</a>
								</td>
								<td>
									<a href="${pageContext.request.contextPath}/adminOrder">
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