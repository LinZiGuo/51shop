<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header id="mr-header" class="wrap mr-header">
	<div class="container">
		<div class="row">
			<!-- //网站Logo -->
			<!-- 主导航条 -->
			<nav id="mr-mainnav"
				class="col-xs-12 col-md-6 mr-mainnav navbar navbar-default">
				<div class="mr-navbar navbar-collapse collapse">
					<div class="mr-megamenu animate slide" data-duration="400"
						data-responsive="true">
						<ul class="nav navbar-nav level0" id="c_ul">
							<li itemprop="name" data-id="435" data-level="1"><a
								id="index" itemprop="url" class="" href="index.jsp"
								data-target="#">首页 </a></li>

						</ul>
					</div>

				</div>
			</nav>
			<!-- //主导航条 -->
		</div>
	</div>
</header>
<script>
	// 获取页面参数
	function GetString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");//正则表达式
		var r = window.location.search.substr(1).match(reg);//获取鼠标点击区域
		if (r != null)
			return unescape(r[2]);//返回区域编号
		return null;
	}
	var type = GetString('type');
	if (type === '14') {
		var jingying = document.getElementById('jingying');
		jingying.style.backgroundColor = "#9E2626";

	}
	if (type === '15') {
		var wenyi = document.getElementById('wenyi');
		wenyi.style.backgroundColor = "#9E2626";

	}
	if (type === '16') {
		var jiaoyu = document.getElementById('jiaoyu');
		jiaoyu.style.backgroundColor = "#9E2626";

	}
	if (type === '17') {
		var shenghuo = document.getElementById('shenghuo');
		shenghuo.style.backgroundColor = "#9E2626";

	}
	if (type == null) {//默认登录无点击
		var index = document.getElementById('index');
		index.style.backgroundColor = "#9E2626";

	}
</script>
<script type="text/javascript">
    $(function(){
    	//发送Ajax 查询所有分类
    	$.post("${pageContext.request.contextPath}/category",{"method":"findAll"},function(obj){
    		//遍历json列表，获取每一个分类，包装成li标签，插入到ul内部
    		//$.each($(obj),function(){});
    		$(obj).each(function(){
    			$("#c_ul").append("<li ><a href='${pageContext.request.contextPath}/goods?method=findByPage&pageNumber=1&ID="+this.ID+"'>"+this.typename+" </a></li>");
    		})
    	},"json");
    });
</script>