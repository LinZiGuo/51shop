<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>51商城</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/front/css/mr-01.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/front/css/page.css" type="text/css">
<script src="${pageContext.request.contextPath}/front/js/jsArr01.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/module.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/jsArr02.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/tab.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/jquery.1.3.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/jsQuery.js" type="text/javascript"></script>
</head>
<body>
<jsp:include page="index-loginCon2.jsp" />
	<!-- 网站头部 -->
	<%@ include file="common-header.jsp"%>
	<!-- //网站头部 -->

	<div class="ja-tab-content ja-tab-content col-6 active"
		style="opacity: 1; width: 100%; visibility: visible;">
		<div class="ja-tab-subcontent">
			<div class="mijoshop">
				<div class="container_oc">
					<div class="row">
						<!-- 循环显示商品 ：添加12条商品信息-->
						<c:forEach items="${pageBean.data }" var="pageBean">
							<div class="product-grid col-lg-2 col-md-3 col-sm-6 col-xs-12">
								<div class="product-thumb transition">
									<div class="actions">
										<div class="image">
											<a
												href="${pageContext.request.contextPath}/goods?method=getById&ID=${pageBean.ID }">
												<img
												src="${pageContext.request.contextPath}/images/goods/${pageBean.picture }"
												alt="${pageBean.goodsname }" class="img-responsive">
											</a>
										</div>
										<div class="button-group">
											<div class="cart">
												<button class="btn btn-primary btn-primary" type="button"
													data-toggle="tooltip"
													onclick='javascript:window.location.href="cart_add.jsp?goodsID=56&num=1"; '
													style="display: none; width: 33.3333%;"
													data-original-title="加入到购物车">
													<i class="fa fa-shopping-cart"></i>
												</button>
											</div>
										</div>
									</div>
									<div class="caption">
										<div class="name" style="height: 40px">
											<a
												href="${pageContext.request.contextPath}/goods?method=getById&ID=${pageBean.ID }">
												<span style="color: #0885B1">商品名：</span>
												${fn:substring(pageBean.goodsname,0,10) }...
											</a>
										</div>
										<div class="name" style="margin-top: 10px">
											<p class="price">价格：${pageBean.price }元</p>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
						<!-- //循环显示最新上架商品：添加12条商品信息 -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="width: 380px; margin: 0 auto; margin-top: 50px;">
		<ul class="nav navbar-nav level0"
			style="text-align: center; margin-top: 10px;">
			<!-- 判断是否是第一页 -->
<%-- 			<c:if test="${pageBean.pageNumber == 1 }"> --%>
<!-- 			<li><a href="javascript:void(0)" aria-label="Previous"><span -->
<!-- 					aria-hidden="true">&laquo;</span></a></li> -->
<%-- 			</c:if> --%>
			
			<!-- 不是第一页 -->
<%-- 			<c:if test="${pageBean.pageNumber ！= 1 }"> --%>
<%-- 			<li><a href="${pageContext.request.contextPath}/goods?method=findByPage&pageNumber=${pageBean.pageNumber-1}&ID=${param.ID}" aria-label="Previous"><span --%>
<!-- 					aria-hidden="true">&laquo;</span></a></li> -->
<%-- 			</c:if> --%>
			
			<c:forEach begin="1" end="${pageBean.totalPage }" var="n">
			    <c:if test="${pageBean.pageNumber == n }">
			        <li><a href="javascript:void(0)">${n }</a></li>
			    </c:if>
			    <c:if test="${pageBean.pageNumber != n }">
			        <li><a href="${pageContext.request.contextPath}/goods?method=findByPage&pageNumber=${n}&ID=${param.ID}">${n }</a></li>
			    </c:if>
			</c:forEach>
			
			<!-- 判断是否是最后一页 -->
<%-- 			<c:if test="${pageBean.pageNumber == pageBean.totalPage }"> --%>
<!-- 			<li><a href="javascript:void(0)" aria-label="Next"><span -->
<!-- 					aria-hidden="true">&laquo;</span></a></li> -->
<%-- 			</c:if> --%>
			
<%-- 			<c:if test="${pageBean.pageNumber ！= pageBean.totalPage }"> --%>
<%-- 			<li><a href="${pageContext.request.contextPath}/goods?method=findByPage&pageNumber=${pageBean.pageNumber+1}&ID=${param.ID}" aria-label="Next"><span --%>
<!-- 					aria-hidden="true">&laquo;</span></a></li> -->
<%-- 			</c:if> --%>
		</ul>
	</div>
	<!-- 版权栏 -->
	<%@ include file="common-footer.jsp"%>
	<!-- //版权栏 -->
</body>
</html>