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
    
    <title>联系我们管理</title>
    
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
  <div style="height: 20px"></div>
    <div class="List_title"><img src="admin/images/g.png" width="17px" height="15px"/> 联系我们管理</div> 	
    <hr color="#00ff00" size="2" style="margin-left: 10px;">
    <center>
    <div style="background:url(admin/images/lxwm.jpg) no-repeat; width:734px; height:329px;">
    <div style="margin-left: -90px;"><br/><br/><br/><br/><br/><br/>
    <form action="contact/conAct!change.action" target="_parent" method="post" onsubmit="return checkContact(this)">
    <table>
    <tr><td>联系地址：</td><td><input class="inputBox" name="address" value="${entity.address}"></td></tr>
    <tr><td>邮政编码：</td><td><input class="inputBox"  name="postcode" value="${entity.postcode}"></td></tr>
    <tr><td>联系人：</td><td><input class="inputBox"  name="linkman" value="${entity.linkman}"></td></tr>
    <tr><td>联系电话：</td><td><input class="inputBox"  name="phone" value="${entity.phone}"></td></tr>
    <tr><td>Email：</td><td><input class="inputBox"  name="e_mail" value="${entity.e_mail}"></td></tr>
    <tr><td></td><td><button type="submit" class="List_button">应用修改</button></td></tr>
    </table>
    </form>
      </div>
      </div></center>
  </body>
</html>
