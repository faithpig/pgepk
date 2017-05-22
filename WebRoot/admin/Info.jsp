<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"/>
    
    <title>INFO管理</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>

	<link rel="stylesheet" type="text/css" href="admin/css/info.css"/>
	 <script type="text/javascript" src="admin/js/jquery-1.7.1.js"></script>
	 <script type="text/javascript" src="admin/js/jquery.XYTipsWindow.min.2.8.js"></script>
	 <script type="text/javascript" src="admin/js/Manage.js"></script>
	 <link rel="stylesheet" type="text/css" href="admin/css/box_style.css"/>

  </head>
  
  <body>
    <table>
    <tr></tr>
    <tr><td width="40px"></td>
    <td width="500px" valign="top">

    	<h2>Hi，<s:property value="a_username"/>！欢迎进入后台管理系统</h2>
    	<h4>您登录的时间：${enterTime}</h4>
    	<h4>您登录的IP为：${ip}&nbsp;&nbsp;<a href="javascript:addWindow('修改密码','admin/changePassword.jsp',295,125)"  class="fast_link">修改密码</a></h4>

    </td>
    
    <td valign="top" width="450px">

    <h2>快捷操作</h2>
    <h4><img src="admin/images/a.png"/>&nbsp;<a href="javascript:addWindow('增加环保新闻','admin/addEPNews.jsp',900,480)" class="fast_link">增加环保新闻　　</a>
    &nbsp;&nbsp;<img src="admin/images/c.png"/>&nbsp;<a href="javascript:addWindow('增加环保政策法规','admin/addEPPolicy.jsp',900,480)"  class="fast_link">增加环保政策法规</a>
    
    </h4>
	<h4><img src="admin/images/b.png"/>&nbsp;<a href="javascript:addWindow('增加环保专业知识','admin/addEPTerm.jsp',900,480)"  class="fast_link">增加环保专业知识</a>
    &nbsp;&nbsp;<img src="admin/images/d.png"/>&nbsp;<a href="javascript:addWindow('增加友情链接','admin/addLink.jsp',700,180)"  class="fast_link">增加友情链接</a>
	</h4>
    </td>
    </tr>
    <tr>
    	<td colspan="3"><hr width="100%" size="10"  color="#035C57"/></td>
    </tr>
    <tr><td></td><td colspan="2" align="left"><h2>【<a href="admin/admAct!log_out.action">退出管理系统</a>】</h2></td></tr>
    </table>
  </body>
</html>
