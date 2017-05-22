<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String adminName = (String) request.getSession().getAttribute(
		"a_username");
if (null == adminName || adminName.length() == 0) {
	response.sendRedirect(basePath + "admin/admAct!login.action");
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>留言回复</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <script type="text/javascript" src="admin/js/jquery-1.7.1.js"></script>
	 <script type="text/javascript" src="admin/js/jquery.XYTipsWindow.min.2.8.js"></script>
	 <script type="text/javascript" src="admin/js/Manage.js"></script>
	 <link rel="stylesheet" type="text/css" href="admin/css/box_style.css">
	<link rel="stylesheet" type="text/css" href="admin/css/common.css">

  </head>
  
  <body>
    <form name="subForm" onsubmit="return checkMessage(this)"  action="message/mesAct!replyDo.action" target="_parent" method="post">
    <input type="hidden" name="id" value="${entity.id }">
    <table border="0" class="addTable">
    <tr><th valign="top">留言内容</th><td><textarea class="addInput" style="width: 500px;height: 70px;" name="detail" disabled="disabled">${entity.detail }</textarea></td></tr>
    <tr><th valign="top">留言分类</th><td><input class="addInput" style="width: 100px;height: 20px;" disabled="disabled" value="${entity.column }"/></td></tr>
    <tr><th valign="top">留言回复</th><td><textarea class="addInput" style="width: 500px;height: 100px;" name=reply>${entity.reply }</textarea></td></tr>
    <tr><th></th><td><button type="submit" class="List_button">确认回复</button></td></tr>
    </table>
    </form>
  </body>
</html>
