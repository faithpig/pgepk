<%@ page language="java"
	import="java.util.*,com.opensymphony.xwork2.ActionContext"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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

<title>留言板管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="admin/js/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="admin/js/jquery.XYTipsWindow.min.2.8.js"></script>
<script type="text/javascript" src="admin/js/Manage.js"></script>
<link rel="stylesheet" type="text/css" href="admin/css/box_style.css">
<link rel="stylesheet" type="text/css" href="admin/css/common.css">


</head>

<body>
	<div style="height: 20px"></div>
	<div class="List_title">
		<img src="admin/images/g.png" width="17px" height="15px" /> 留言板管理
	</div>
	<div class="List_column">
		<button class="List_button"
			onclick="manage('message/mesAct!Message_index.action','reply','')">已回复</button>
		<button class="List_button"
			onclick="manage('message/mesAct!Message_index.action','notReply','')">未回复</button>
		<button class="List_button"
			onclick="manage('message/mesAct!Message_index.action','verify','')">已审核</button>
		<button class="List_button"
			onclick="manage('message/mesAct!Message_index.action','notVerify','')">未审核</button>
		<span class="List_search" style="margin-left:399px;">
			<form action="message/mesAct!searchMessage.action" method="post"
				style="margin-bottom:0px;margin-top: 0px;padding:0 0; ">
				<span style="font-size:14px;">选择浏览栏目：</span><select name="type"
					class="List_search_choice">
					<option value="基础知识">基础知识</option>
					<option value="政策解答">政策解答</option>
					<option value="技术支持">技术支持</option>
					<option value="主题活动">主题活动</option>
					<option value="" selected="selected">全部</option>
				</select> <img src="admin/images/jiansuo.gif"
					style="height:20px;margin-bottom: -6px;cursor: hand;"
					onclick="submit()" />
			</form>
		</span>
	</div>
	<div class="List_info">
		<table class="List_table">
			<tr>
				<th width="10px"><input type="checkbox"
					onclick="checkbox('checkbox')" name="_checkbox" /></th>
				<th width="80px">ID</th>
				<th width="400px">留言信息</th>
				<th width="135px">提交时间</th>
				<th width="100px">分类</th>
				<th width="100px">回复者</th>
				<th width="100px">审核者</th>
				<th width="180px">操作</th>
			</tr>
			<s:iterator value="entity" id="entity">
				<tr>
					<td align="center"><input type="checkbox" value="${entity.id}"
						name="checkbox" /></td>
					<td align="center">${entity.id}</td>
					<td align="left"><div class="longTextWrapMsg" title="${entity.detail}">${entity.detail}</div></td>
					<td align="center">${entity.addTime}</td>
					<td align="center">${entity.column}</td>
					<td align="center">${entity.gname==null?'/':entity.gname}</td>
					<td align="center">${entity.sname==null?'/':entity.sname}</td>
					<td align="center">[<a
						href="javascript:changeWindow('回复留言','message/mesAct!reply.action?id=${entity.id }',650,270)"
						class="opLink">回复</a>][<a
						href="javascript:verify('message/mesAct!setVerify.action?id=${entity.id }','message/mesAct!Message_index.action')"
						class="opLink">审核</a>][<a
						href="javascript:manage('message/mesAct!Message','delete','${entity.id}')"
						class="opLink">删除</a>]
					</td>
				</tr>
			</s:iterator>
		</table>
		<table class="List_table_page">
			<tr>
				<td>共&nbsp;<s:property value="pager.maxPages" />&nbsp;页&nbsp;每页&nbsp;<s:property
						value="pager.getPageSize()" />&nbsp;条&nbsp;页次：<s:property
						value="pager.currentPage" />/<s:property value="pager.maxPages" /></td>
				<td width="30px"></td>
				<td><a
					href="<s:url value="message/mesAct!Message_page.action"><s:param name="method" value="%{'first'}"/><s:param name="pager.currentPage" value="pager.currentPage"/></s:url>">
						<span style="background:#DCD9D4;">首页</span>
				</a></td>
				<td><a
					href="<s:url value="message/mesAct!Message_page.action"><s:param name="method" value="%{'back'}"/><s:param name="pager.currentPage" value="pager.currentPage"/></s:url>">
						<span style="background:#DCD9D4;">上一页</span>
				</a></td>
				<td><span style="background:#EBEBEA;">&nbsp;<s:property
							value="pager.currentPage" />&nbsp;
				</span></td>
				<td><a
					href="<s:url value="message/mesAct!Message_page.action"><s:param name="method" value="%{'next'}"/><s:param name="pager.currentPage" value="pager.currentPage"/></s:url>">
						<span style="background:#DCD9D4;">下一页</span>
				</a></td>
				<td align="left">

					<form action="message/mesAct!Message_page.action" method="post"id="gotoform" 
						style="margin-bottom: 0">
						<s:hidden name="method" value="%{'jump'}" />
						<input type="text" name="pager.currentPage"
							style="width:20px;height:17px;font-size: 12px;padding:0 0 0 0;"
							value="" id="gotopage" /> <img src="admin/images/go.gif"
							onclick="goPage(<s:property value="pager.maxPages"/>)"
							style="height:15px;margin-top: 0px;margin-bottom: -3px;cursor: hand;" />
					</form>
				</td>
				<td width="0px"></td>
				<td><a
					href="<s:url value="message/mesAct!Message_page.action"><s:param name="method" value="%{'last'}"/><s:param name="pager.currentPage" value="pager.currentPage"/></s:url>">
						<span style="background:#DCD9D4;">尾页</span>
				</a></td>
			</tr>
		</table>
	</div>
	<div class="List_column">
		<div style="margin-top: 12px;font-size: 14px;">
			选择：<a href="javascript:checkbox('all')">全部</a> - <a
				href="javascript:checkbox('inverse')">反选</a> - <a
				href="javascript:manage('message/mesAct!Message','deleteChecked','')">所选删除</a>
		</div>
	</div>
</body>
</html>
