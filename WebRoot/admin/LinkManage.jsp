<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
    
    <title>友情链接管理</title>
    
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
    <div class="List_title"><img src="admin/images/g.png" width="17px" height="15px"/>友情链接管理</div>
	<div class="List_column">
  		<button class="List_button" onclick="addWindow('增加友情链接','admin/addLink.jsp',700,150)">增加</button>
  	</div>
	<div class="List_info">
		<table class="List_table">
			<tr><th width="10px"><input type="checkbox"onclick="checkbox('checkbox')"name="_checkbox" /></th><th width="80px">ID</th><th width="200px">链接标题</th><th width="60px">链接网址</th><th width="100px">图片</th><th width="100px">添加者</th><th width="180px">操作</th></tr>
			<s:iterator value="entity" id="entity">
			<tr>
					<td align="center"><input type="checkbox" value="${entity.id}" name="checkbox"/></td>
					<td align="center" >${entity.id}</td>
					<td align="left"><div class="longTextWrap">${entity.title}</div></td>
					<td align="left">${entity.link}</td>
					<td align="center"><img src="file/linkPicture/${entity.name}" width="180px" height="41"></td>
					<td align="center">${entity.gname}</td>
					<td align="center">[<a href="javascript:changeWindow('修改友情链接','link/linAct!update?id='${entity.id }',700,180)" class="opLink" >修改</a>][<a href="javascript:manage('link/linAct!Link','delete','${entity.id}')" class="opLink">删除</a>]</td>
			</tr>
			</s:iterator>
		</table>
		</div>
		<div class="List_column" ><div style="margin-top: 12px;font-size: 14px;">选择：<a href="javascript:checkbox('all')">全部</a> - <a href="javascript:checkbox('inverse')">反选</a> - <a href="javascript:manage('link/linAct!Link','deleteChecked','')">所选删除</a></div></div>
  </body>
</html>
