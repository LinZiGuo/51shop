<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>菜单</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/manage/css/dtree.css" type="text/css">
</head>
<table width="100" border="0" cellspacing="0">
	<tr>
		<td height="12"></td>
	</tr>
</table>
<table width="100%" border="0">
	<tr>
		<td>
			<div class="dtree">
				<a href="javascript:d.openAll();">展开所有</a> | <a href="javascript:d.closeAll();">关闭所有</a>
				
				<script type="text/javascript" src="${pageContext.request.contextPath}/manage/js/dtree.js"></script>
				<script type="text/javascript">
					d = new dTree('d');
					d.add(0,-1,'系统菜单树');
					d.add(1,0,'分类管理','mainFrame');
					d.add(2,1,'分类列表','${pageContext.request.contextPath}/adminCategory?method=findAll&page=1','分类列表','mainframe');
					d.add(7,1,'添加分类','${pageContext.request.contextPath}/adminCategory?method=addUI','添加分类','mainframe');
					d.add(3,0,'商品管理');
					d.add(4,3,'商品管理','${pageContext.request.contextPath}/adminGoods?method=findAll&page=1','商品列表','mainframe');
					d.add(8,3,'添加商品','${pageContext.request.contextPath}/adminGoods?method=addUI','添加商品','mainframe');
					d.add(5,0,'订单管理');
					d.add(6,5,'订单列表','${pageContext.request.contextPath}/adminOrder?method=findAllByState','订单列表','mainframe');
					d.add(9,5,'未付款订单','${pageContext.request.contextPath}/adminOrder?method=findAllByState&state=0','未付款订单','mainframe');
					d.add(10,5,'已付款订单','${pageContext.request.contextPath}/adminOrder?method=findAllByState&state=1','已付款订单','mainframe');
					d.add(11,5,'已发货订单','${pageContext.request.contextPath}/adminOrder?method=findAllByState&state=2','已发货订单','mainframe');
					d.add(12,5,'已完成订单','${pageContext.request.contextPath}/adminOrder?method=findAllByState&state=3','已完成订单','mainframe');
					document.write(d);
				</script>
			</div>
		</td>
	</tr>
</table>
</html>