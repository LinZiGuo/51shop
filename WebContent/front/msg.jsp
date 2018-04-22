<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>提示页面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/front/css/mr-01.css" type="text/css">
<script src="${pageContext.request.contextPath}/front/js/jsArr01.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/module.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/jsArr02.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/tab.js" type="text/javascript"></script>
</head>
<body>
    <!-- 主体内容 -->
	<div id="mr-mainbody" class="container mr-mainbody">
		<div class="row">
			<div id="mr-content" class="mr-content col-xs-12">
				<div class="login-wrap" style="margin-bottom: 60px; margin-top: 50px">
					<div style="max-width: 540px; margin: 0 auto;">
						<a href="${pageContext.request.contextPath}/front/index.jsp" title="点击返回首页"><img src="${pageContext.request.contextPath}/front/images/51logo.png"></a>
					</div>
					<div class="login">
						<div class="page-header" style="pause: 0px;">
							<h1 class="login_h1">提示信息</h1>
						</div>
						<h3 align="center">${msg }</h3>
						<h5 id="show" align="center"></h5>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- //主体内容 -->
	<script>
	  var t=3;
	  setInterval("refer()",1000);
	  function refer(){
		  if(t==0)
			  location="http://localhost:8080/shop";
		  document.getElementById("show").innerHTML=""+t+"秒后跳转到首页";
		  t--;
	  }
	</script>
</body>