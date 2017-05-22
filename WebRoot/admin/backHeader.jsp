<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>BackHeader</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="admin/css/backHeader.css">


  </head>
  
  <body>
    <div><img src="admin/images/adminBanner.png" style="width: 100%;min-width: 1000px;" /></div>
    <div id="menu">
		<img src="admin/images/e.gif" style="margin-top:9px;margin-right:5px;margin-left: 10px;"/><span style="font-size:14px;color:#ffffff;font-family: 微软雅黑;font-weight: bolder;">后台管理菜单</span>
</div>
  </body>
</html>
