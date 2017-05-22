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
    
    <title>数据统计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <script type="text/javascript" src="admin/js/jquery-1.7.1.js"></script>
	 <script type="text/javascript" src="admin/js/jquery.XYTipsWindow.min.2.8.js"></script>
	 <script type="text/javascript" src="admin/js/Manage.js" charset="utf-8"></script>
	 <script type="text/javascript" src="admin/lhgcalendar/lhgcore.js"></script>
	 <script type="text/javascript" src="admin/lhgcalendar/lhgcalendar.js"></script>

	 <link rel="stylesheet" type="text/css" href="admin/css/box_style.css">
	 <link rel="stylesheet" type="text/css" href="admin/css/common.css">

  </head>
  <%
    long t = new Date().getTime();
	java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	String enterDate = "" + df.format(t);
  %>
  <body>
    <div style="height: 20px"></div>
    <div class="List_title"><img src="admin/images/g.png" width="17px" height="15px"/> 数据统计</div>
  	<div class="List_column">
  		<button class="List_button2" onclick="charts('analysis/anaAct!hbxw.action')">环保新闻TOP10</button>
  		<button class="List_button2" onclick="charts('analysis/anaAct!hbzcfg.action')">政策法规浏览TOP10</button>
  		<button class="List_button2" onclick="charts('analysis/anaAct!hbzyzs.action')">环保专业知识TOP10</button>
  		<button class="List_button2" onclick="charts('analysis/anaAct!visitTimeAll.action')">网站访问时间段统计</button>
  		<span style="margin-left: 20px;">

		<img src="admin/lhgcalendar/images/date.gif" onclick="J.calendar.get({id:'statDate'});"style="margin-top:8px;vertical-align: middle;"/>
		</span>
		
	</div>
	<div id="charts" style="margin-left: 20px;">
	<iframe id="chartsFrame" width="100%" height="700" name="down_frame" frameborder="0" src="analysis/anaAct!hbxw.action"></iframe>
	</div>
  </body>
</html>
