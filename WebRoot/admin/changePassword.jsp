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
    
    <title>修改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="admin/css/common.css"/>
  </head>
  
  <body><form action="admin/admAct!changePassword.action" method="post" target="_parent">
   <table>
   <tr><th>输入原密码：</th><td><input type="password" style="border: 1px solid #00AA00;width: 150px;" name="a_password"></td></tr>
   <tr><th>输入新密码：</th><td><input type="password" style="border: 1px solid #00AA00;width: 150px;" name="newPassword"></td></tr>
   <tr><th>确认新密码：</th><td><input type="password" style="border: 1px solid #00AA00;width: 150px;" name="newPassword2"></td></tr>
   <tr><th></th><td><button type="submit" class="List_button">确认修改</button></td></tr>
   </table></form>
  </body>
</html>
