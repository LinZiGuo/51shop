<%@page import="com.web.servlet.GoodsServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>51商城</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front/css/mr-01.css"
	type="text/css">
<script src="${pageContext.request.contextPath}/front/js/jsArr01.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/module.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/jsArr02.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/tab.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/front/js/jquery.1.3.2.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$.ajax({
		url : '${pageContext.request.contextPath}/goods',
		success : function() {
		}
	});
</script>
</head>

<body>
	<jsp:include page="index-loginCon2.jsp" />
	<!-- 网站头部 -->
	<%@ include file="common-header.jsp"%>
	<!-- //网站头部 -->
	<div id="mr-mainbody" class="container mr-mainbody">
		<div class="row">
			<!-- 页面主体内容 -->
			<div id="mr-content"
				class="mr-content col-xs-12 col-sm-12 col-md-9 col-md-push-3">
				<div id="mrshop" class="mrshop common-home">
					<div class="container_oc">
						<div class="row">
							<div id="content_oc" class="col-sm-12 view-product">

								<!-- 根据商品ID获取并显示商品信息 -->
								<!-- 显示商品详细信息 -->
								<div class="row">
									<div class="col-xs-12 col-md-4 col-sm-4">
										<ul class="thumbnails" style="list-style: none">
											<li><a class="thumbnail" href="#"> <img
													src="${pageContext.request.contextPath}/images/goods/${goods.picture }"></a></li>
										</ul>
									</div>
									<div class="col-xs-12 col-md-8 col-sm-8">
										<div style="margin-left: 30px; margin-top: 20px">
											<h1 class="product-title">${goods.goodsname }</h1>
											<ul class="list-unstyled price">
												<li><h2>${goods.nowprice }元</h2></li>
											</ul>
											<ul class="list-unstyled price">
												<li>原价: ${goods.price }元</li>
											</ul>
											<div class="rating">
												<p>商城活动：全场满99包邮</p>
											</div>
											<div id="product">
												<hr>
												<div class="form-group">
													<form id="cartForm" method="get"
														action="${pageContext.request.contextPath}/cart">
														<!--提交的方法 -->
														<input type="hidden" name="method" value="add2Cart">

														<!-- 商品的ID -->
														<input type="hidden" name="ID" value="${goods.ID }">
														<label class="control-label" for="shuliang"> 数量 </label> <input
															type="number" name="count" value="1" size="2"
															id="shuliang" class="form-control"> <br>
													</form>
													<div class="btn-group">
														<button type="button" onclick="addCart()"
															class="btn btn-primary btn-primary">
															<i class="fa fa-shopping-cart"></i> 加入购物车
														</button>
														<button type="button" id="button-wishlist"
															data-toggle="tooltip" class="btn" title="收藏"
															data-original-title="收藏">
															<i class="fa fa-heart"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-sm-12 description_oc clearfix">
										<ul class="nav nav-tabs htabs">
											<li class="active" style="width: 150px"><a
												href="#tab-description" data-toggle="tab"
												aria-expanded="true">商品描述</a></li>
										</ul>
										<div class="tab-content"
											style="border: 1px solid #eee; overflow: hidden;">
											<div class="tab-pane active" id="tab-description">
												${goods.introduce }</div>
										</div>
									</div>
								</div>
								<!-- //显示商品详细信息 -->
								<!-- 显示相关商品 -->
								<div class="mr-module related-products">
									<h3 class="module-title ">相关商品</h3>
									<!-- 显示底部相关商品 -->
									<%-- 									<jsp:include page="/goods?method=getRelatedGoods&typename=${goods.typename }"> --%>
									<%-- 										<jsp:param name="typename" value="${goods.typename }" /> --%>
									<%-- 									</jsp:include> --%>
									<!-- // 显示底部相关商品 -->
								</div>
								<!-- //显示相关商品 -->
								<!-- //根据商品ID获取并显示商品信息 -->
							</div>
						</div>
					</div>

				</div>
			</div>
			<!-- //页面主体内容 -->
			<!-- 显示左侧热门商品 -->
			<!-- // 显示左侧热门商品 -->

		</div>
	</div>
	<!-- 版权栏 -->
	<%@ include file="common-footer.jsp"%>
	<!-- //版权栏 -->

</body>
<script type="text/javascript">
	function addCart() {
		//让指定的表单提交
		document.getElementById("cartForm").submit();
	}
</script>
</html>
