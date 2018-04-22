<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>登录-51商城</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/front/css/mr-01.css" type="text/css">
</head>

<body>
	<div id="mr-mainbody" class="container mr-mainbody">
		<div class="row">
			<!-- 主体内容 -->
			<div id="mr-content" class="mr-content col-xs-12">
				<div class="login-wrap" style="margin-bottom: 60px; margin-top: 50px">
					<div style="max-width: 540px; margin: 0 auto;">
						<a href="index.jsp" title="点击返回首页"><img src="${pageContext.request.contextPath}/front/images/51logo.png"></a>
					</div>
					<div class="login">
						<div class="page-header" style="pause: 0px;"> <h1 class="login_h1">会员登录</h1> </div>
						<!-- 会员登录表单 -->
						<form action="${pageContext.request.contextPath}/member" method="post" class="form-horizontal">
						    <input type="hidden" name="method" value="login">
							<fieldset>
								<div class="form-group">
									<div class="col-sm-4 control-label">
										<label id="username-lbl" for="username" class="required">账户 ：</label>
									</div>
									<div class="col-sm-8">
										<!-- 账户文本框 -->
										<input type="text" name="username" id="username" size="38"
											class="validate-username required" required="required" autofocus="">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-4 control-label">
										<label id="password-lbl" for="password" class="required">密码 ：</label>
									</div>
									<div class="col-sm-8">
										<!-- 密码文本框 -->
										<input type="password" name="password" id="password" value=""
											class="validate-password required" size="38" maxlength="99"
											required="required" aria-required="true">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-4 control-label">
										<label id="password-lbl" for="password" class="required">验证码 ：</label>
									</div>
									<div class="col-sm-8" style="clear: none;">
										<!-- 验证码文本框 -->
										<input type="text" name="checkCode" id="checkCode" value=""
											class="validate-password required" style="float: left;"
											title="验证码区分大小写" size="18" maxlength="4" required="required"
											aria-required="true"> 
											<!-- 显示验证码 -->
											<img src="${pageContext.request.contextPath}/CheckCode" name="img_checkCode" onClick="return myReload();" width="116"
											     height="43" class="img_checkcode" id="img_checkCode"/>
									</div>
								</div>
								<div class="form-group">
								    <div class="col-sm-offset-4 col-sm-8 control-label">
								        <div class="checkbox">
								            <label>
								                <input type="checkbox" name="savename" id="savename"> 记住用户名
								            </label>
								        </div>
								    </div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-4 col-sm-8">
										<button type="submit" class="btn btn-primary login">登录</button>
									</div>
								</div>
								<div class="form-group"
									style="border-top: 1px solid #D9D9D9; margin: 20px;">
									<label
										style="float: right; color: #858585; margin-right: 40px; margin-top: 10px; font-size: 14px;">没有账户？<a
										href="${pageContext.request.contextPath}/front/register.jsp">立即注册</a></label>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
			<!-- //主体内容 -->
		</div>
	</div>
	<c:if test="${not empty msg }">
	    <script>
	        alert("${msg}");
	    </script>
	</c:if>
	<script src="${pageContext.request.contextPath}/front/js/jquery.1.3.2.js" type="text/javascript"></script>
	<script language="javascript">
		//刷新验证码
		function myReload() {
			document.getElementById("img_checkCode").src = document
					.getElementById("img_checkCode").src + "?nocache=" + new Date().getTime();
		}
		if(document.getElementById("savename").checked){
			document.getElementById("savename").value="ok";
		}
		var val="${cookie.saveName.value}";
		document.getElementById("username").value=decodeURI(val);
	</script>
</body>
</html>