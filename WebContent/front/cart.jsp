﻿<%@page import="javax.crypto.Cipher"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>我的购物车-51商城</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/front/css/mr-01.css" type="text/css">

<script src="${pageContext.request.contextPath}/front/js/jsArr01.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/module.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/jsArr02.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/tab.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/jquery.1.3.2.js" type="text/javascript"></script>
</head>

<body>
	<jsp:include page="index-loginCon2.jsp" />
	<!-- 网站头部 -->
	<%@ include file="common-header.jsp"%>
	<!-- //网站头部 -->
	<div id="mr-mainbody" class="container mr-mainbody">
		<div class="row">
			<!-- 页面主体内容 -->
			<c:if test="${empty cart || empty cart.cartItems }">
				<h3>购物车空空如也，亲，请先去逛逛吧~~~~~~~~</h3>
				<div class="buttons">
					<div class="pull-left">
						<a href="index.jsp" class="btn btn-primary btn-default">继续购物</a>
					</div>
				</div>
			</c:if>
			<c:if test="${not empty cart.cartItems }">

				<div id="mr-content" class="mr-content col-xs-12">
					<div id="mrshop" class="mrshop common-home">
						<div class="container_oc">
							<div class="row">
								<div id="content_oc" class="col-sm-12">
									<h1>我的购物车</h1>
									<!-- 显示购物车中的商品 -->
									<div class="table-responsive cart-info">
										<table class="table table-bordered">
											<thead>
												<tr>
													<td class="text-center image">商品图片</td>
													<td class="text-left name">商品名称</td>
													<td class="text-left quantity">数量</td>
													<td class="text-right price">单价</td>
													<td class="text-right total">总计</td>
													<td class="text-left remove">操作</td>
												</tr>
											</thead>
											<tbody>

												<!-- 显示一条商品信息 -->
												<c:forEach items="${cart.cartItems }" var="ci">
													<tr>
														<td class="text-center image" width="20%"><a
															href="${pageContext.request.contextPath}/goods?method=getById&ID=${ci.goods.ID }">
																<img width="80px"
																src="${pageContext.request.contextPath}/images/goods/${ci.goods.picture}">
														</a></td>
														<td class="text-left name"><a
															href="${pageContext.request.contextPath}/goods?method=getById&ID=${ci.goods.ID }">
																${ci.goods.goodsname }</a></td>
														<td class="text-left quantity">${ci.count }件</td>
														<c:if test="${not empty ci.goods.nowprice }">
														<td class="text-right price">${ci.goods.nowprice }元</td>
														</c:if>
														<c:if test="${not empty ci.goods.price } && ${empty ci.goods.nowprice }">
														<td class="text-right price">${ci.goods.price }元</td>
														</c:if>
														<td class="text-right total">${ci.subtotal }元</td>
														<td class="text-left remove">
														<a href="${pageContext.request.contextPath}/cart?method=remove&ID=${ci.goods.ID}">删除</a>
														</td>
													</tr>
												</c:forEach>
												<!-- 显示一条商品信息 -->

												<!-- //遍历购物车中的商品并显示 -->
											</tbody>
										</table>
									</div>
									<!-- //显示购物车中的商品 -->
									<!-- 显示总计金额  -->
									<div class="row cart-total">
										<div class="col-sm-4 col-sm-offset-8">
											<table class="table table-bordered">
												<tbody>
													<tr>
														<span> <strong>总计:￥${cart.total }</strong>
															<p>元</p>
														</span>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
									<!-- //显示总计金额  -->
								</div>
							</div>

							<!-- 填写物流信息 -->
							<div class="row">
								<div id="content_oc" class="col-sm-12">
									<h1>物流信息</h1>
									<!-- 填写物流信息的表单 -->
									<form action="${pageContext.request.contextPath}/order?method=save" method="post" id="myform">
										<div class="table-responsive cart-info">
											<table class="table table-bordered">
												<tbody>
													<tr>
														<td class="text-right" width="20%">收货人姓名：</td>
														<td class="text-left quantity">
															<div class="input-group btn-block"
																style="max-width: 400px;">
																<input type="text" id="recevieName" name="recevieName"
																	size="10" class="form-control">
															</div>
														</td>
													</tr>
													<tr>
														<td class="text-right">收货人手机：</td>
														<td class="text-left quantity">
															<div class="input-group btn-block"
																style="max-width: 400px;">
																<input type="text" id="tel" name="tel" size="10"
																	class="form-control">
															</div>
														</td>
													</tr>
													<tr>
														<td class="text-right">收货人地址：</td>
														<td class="text-left quantity">
															<div class="input-group btn-block"
																style="max-width: 400px;">
																<input type="text" id="address" name="address" size="1"
																	class="form-control">
															</div>
														</td>
													</tr>
													<tr>
														<td class="text-right">备注：</td>
														<td class="text-left quantity">
															<div class="input-group btn-block"
																style="max-width: 400px;">
																<input type="text" name="bz" size="1"
																	class="form-control">
															</div>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
										<input type="hidden" name="price" value="${cart.total }"/>
									</form>
									<!-- //填写物流信息的表单 -->
								</div>
							</div>
							<!-- //填写物流信息 -->
							<br />
							<!-- 显示支付方式 -->
							<div class="row">
								<div id="content_oc" class="col-sm-12">
									<h1>支付方式</h1>
									<div class="table-responsive cart-info">
										<table class="table table-bordered">
											<tbody>
												<tr>
													<td class="text-left"><img src="images/zhifubao.png" /></td>
												</tr>
											</tbody>
										</table>
									</div>
									<br /> <br />
									<div class="buttons">
										<div class="pull-left">
											<a href="index.jsp" class="btn btn-primary btn-default">继续购物</a>
										</div>
										<div class="pull-left">
											<a
												href="${pageContext.request.contextPath}/cart?method=clear"
												class="btn btn-primary btn-default">清空购物车</a>
										</div>
										<div class="pull-right">
											<button type="button" onclick="cart_order()" class="tigger btn btn-primary btn-primary">结账</button>
										</div>
									</div>
								</div>
							</div>
							<!-- //显示支付方式 -->
						</div>
					</div>
				</div>
			</c:if>
			<!-- //页面主体内容 -->
		</div>
	</div>
	<!-- 版权栏 -->
	<%@ include file="common-footer.jsp"%>
	<!-- //版权栏 -->

	<!-- 使用jBox插件实现一个支付对话框 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/front/js/jBox/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/front/js/jBox/jquery.jBox-2.3.min.js"></script>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/front/js/jBox/Skins2/Pink/jbox.css" />
	<script type="text/javascript">
		function zhifu() {
			//验证收货人姓名
			if ($('#recevieName').val() === "") {
				alert('收货人姓名不能为空！');
				return;
			}
			//验证收货人手机
			if ($('#tel').val() === "") {
				alert('收货人手机不能为空！');
				return;
			}
			//验证手机号是否合法
			if (isNaN($('#tel').val())) {
				alert("手机号请输入数字");
				return;
			}
			//验证收货人地址
			if ($('#address').val() === "") {
				alert('收货人地址不能为空！');
				return;
			}
			//设置对话框中要显示的内容
			var html = '<div class="popup_cont">'
					+ '<p>扫一扫支付</p>'
					+ '<strong>￥<font id="show_money_info">0.01元</font></strong>'
					+ '<div style="width: 256px; height: 250px; text-align: center; margin-left: auto; margin-right: auto;" >'
					+ '<image src="images/qr.png" width="256" height="256" /></div>'
					+ '</div><p style="text-align:center">支付二维码仅为测试用（相关知识点在书中有介绍）</p>';
			var content = {
				state1 : {
					content : html,
					buttons : {
						'取消' : 0,
						'支付' : 1
					},
					buttonsFocus : 0,
					submit : function(v, h, f) {
						if (v == 0) {//取消按钮的响应事件
							return true; //关闭窗口
						}
						if (v == 1) {//支付按钮的响应事件
							document.getElementById('myform').submit();//提交表单
							return true;
						}
						return false;
					}
				}
			};
			$.jBox.open(content, '支付', 400, 450);//打开支付窗口
		}
		function cart_order(){
			document.getElementById("myform").submit();
		}
	</script>
	<!-- // 使用jBox插件实现一个支付对话框 -->
</body>
</html>