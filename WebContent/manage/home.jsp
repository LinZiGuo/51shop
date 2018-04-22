<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>51商城后台管理系统</title>
</head>
<frameset rows="103,*,43" frameborder=0 border="0" framespacing="0">
	<frame src="${pageContext.request.contextPath}/manage/top.jsp" name="topframe" scrolling="no" noresize="noresize"/>
	<frameset cols="159,*" frameborder=0 border="0" framespacing="0">
		<frame src="${pageContext.request.contextPath}/manage/left.jsp" name="leftframe" scrolling="yes"  noresize="noresize" />
		<frame src="${pageContext.request.contextPath}/manage/index.jsp" name="mainframe"  noresize="noresize" />
	</frameset>
	<frame src="${pageContext.request.contextPath}/manage/bottom.jsp" name="bottomframe" scrolling="no"/>
</frameset>
</html>