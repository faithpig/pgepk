<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">

<title>欢迎进入电网环保知识科普网站后台管理系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
	 <script type="text/javascript" src="admin/js/jquery-1.7.1.js"></script>
	 <script type="text/javascript" src="admin/js/jquery.XYTipsWindow.min.2.8.js"></script>
	 <script type="text/javascript" src="admin/js/Manage.js"></script>
<link rel="stylesheet" type="text/css" href="admin/css/Login.css">


</head>
<body>
	<div id="top">
		<img id="admin_img" src="admin/images/banner.png" />
	</div>
	<div id="middle">
		<span style="font-weight:2;font-family: 微软雅黑;color: #ffffff">电网环保知识科普网站后台管理系统</span>
	</div>
	<div id="bottom">
		<form action="admin/admAct!login_check.action" method="post">
			<center>
				<table id="table">
					<tr height="35px">
						<td>用户名：</td>
						<td><input type="text" name="a_username"
							style="width:150px;height:17px;border: 1px solid;border-color: #888888;" /></td>
					</tr>
					<tr height="35px">
						<td>密　码：</td>
						<td><input type="password" name="a_password"
							style="width:150px;height:17px;border: 1px solid;border-color: #888888;" /></td>
					</tr>
					<tr height="35px">
						<td>验证码：</td>
						<td><input type="text" name="a_checkcode"
							style="width:80px;height:17px;border: 1px solid;border-color: #888888;" />
							<span><a href="javascript:changeImg()"><img id="checkcode" src="admin/code.jsp" style="border: 0;vertical-align:middle;margin-bottom: 3px;" width="60px" height="20px" alt="请刷新"></a></span></td>
					</tr>
					<tr>
						<td colspan="2"
							style="font-size:12px;color:#ff0000;text-align: center;">${user_check}</td>
					</tr>
					<tr>
						<td colspan="2" align="left">
						<input type="submit" value="登录"
							style="width: 90px;height:30px;;color:#FFFFFF;background-color: #212121;border-color: #212121;margin-left: 90px;" /></td>
					</tr>
				</table>
			</center>
		</form>
	</div>
</body>
</html>
