<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="chStr" scope="page" class="com.tools.ChStr" />
<div id="toolbar" style="background-color: #F0F0F0; width: 100%;">
	<div class="container">
		<div class="row">
			<div class="toolbar-ct-1">
				<p>
					<i class="fa fa-phone"></i> <span style="margin-right: 15px;">电话：400-675-1066</span>
					<c:if test="${empty member }">
					<a href="${pageContext.request.contextPath}/member?method=loginUI">登录</a>&nbsp; ｜ &nbsp;
					<a href="${pageContext.request.contextPath}/member?method=registUI">注册</a>
					</c:if>
					<c:if test="${not empty member }">
					${member.username }:您好！
					<a href="modifyMember.jsp">修改</a>&nbsp;&nbsp;
					|&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/member?method=logout">退出</a>
					</c:if>
				</p>
			</div>
			<div class="toolbar-ct-2">
				<a href="${pageContext.request.contextPath}/order?method=findMyOrdersByPage&pageNumber=1">我的订单</a>&nbsp;&nbsp; | &nbsp;&nbsp; 我的收藏
			</div>
		</div>
	</div>
	<div style="background-color: white; width: 100%">
		<div class="container">
			<div class="row">
				<div class="toolbar-ct col-xs-12 col-md-6  hidden-sm hidden-xs">
					<div class="toolbar-ct-1">
						<img src="${pageContext.request.contextPath}/front/images/51logo.png">
					</div>
					<div>
						<!-- 搜索条 -->
						<div class="search_box">
							<div class="top-nav-search">
								<form method="post" action="search_result.jsp">
									<input type="text" name="searchword" size="38"
										style="border: 0px;" class="top-nav-search-input"
										placeholder="请输入内容" /> <input type="image"
										src="${pageContext.request.contextPath}/front/images/search.png" class="search_box_img"
										onFocus="this.blur()" />
								</form>
							</div>
						</div>
						<!-- //搜索条 -->



					</div>
				</div>

				<div class="toolbar-ct toolbar-ct-right col-xs-12 col-md-3">


					<div class="toolbar-ct-2 "
						style="margin-top: 35px; border: 1px solid #EAEAEA; padding: 5px;">
						<a href="${pageContext.request.contextPath}/front/cart.jsp" style="color: #E33737; font-size: 20px;"><i
							class="fa fa-cart1"></i> 我的购物车</a>


					</div>
				</div>
			</div>
		</div>
	</div>
</div>